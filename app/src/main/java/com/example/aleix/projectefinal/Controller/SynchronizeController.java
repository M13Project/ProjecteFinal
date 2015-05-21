package com.example.aleix.projectefinal.Controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.example.aleix.projectefinal.Entity.Client;
import com.example.aleix.projectefinal.Entity.ClientLog;
import com.example.aleix.projectefinal.Entity.Comanda;
import com.example.aleix.projectefinal.Entity.Comanda_Producte;
import com.example.aleix.projectefinal.Entity.Localitzacio;
import com.example.aleix.projectefinal.R;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Michal on 18/05/2015.
 */
public class SynchronizeController extends AsyncTask {

    private Activity activity;
    private LocalPersistanceManager lpm;
    //private ProgressDialog dialog;

    public SynchronizeController(Activity activity) {
        this.activity = activity;
        lpm = new LocalPersistanceManager(activity, GlobalParameterController.DATABASE_NAME, GlobalParameterController.DATABASE_VERSION);
        //this.dialog = new ProgressDialog(activity);
    }

    private void uploadEntities() {
        insertEntity(Client.class);
        insertEntity(Comanda.class);
        insertEntity(Comanda_Producte.class);
        insertEntity(Localitzacio.class);

        updateEntity(Client.class);
        updateEntity(Comanda.class);
        updateEntity(Comanda_Producte.class);
        updateEntity(Localitzacio.class);

        deleteEntity(Localitzacio.class);
        deleteEntity(Comanda_Producte.class);
        deleteEntity(Comanda.class);
        deleteEntity(Client.class);
    }

    private <T> void insertEntity(Class<T> classToInsert) {
        PersistanceManager pm = null;
        Class logTableClass = null;
        String operationType = null;
        int objectId = 0;
        Method method = null;
        Method method2 = null;
        try {
            logTableClass = Class.forName(classToInsert.getName() + "Log");
            method = logTableClass.getMethod("getOp");
            method2 = logTableClass.getMethod("getId");
        } catch (Exception e) {
            e.printStackTrace();
        }
        List logEntries = lpm.getAllEntities(logTableClass);
        for (Object oneLogEntry : logEntries) {
            pm = new PersistanceManager(this.activity);
            try {
                operationType = (String) method.invoke(oneLogEntry);
                objectId = (int) method2.invoke(oneLogEntry);
            } catch (Exception e) {
                e.printStackTrace();
            }
            T auxiliarObject = null;
            if(operationType.equalsIgnoreCase("I")) {
                auxiliarObject = lpm.getEntity(classToInsert, objectId);
                String operationResponse = pm.sendAnObjectToServer(classToInsert, auxiliarObject);
                if(operationResponse.equalsIgnoreCase(GlobalParameterController.OPERATION_OK)) {
                    lpm.deleteLogEntry(logTableClass, oneLogEntry);
                }
            }
        }
    }

    private <T> void updateEntity(Class<T> classToUpdate) {
        PersistanceManager pm = null;
        Class logTableClass = null;
        String operationType = null;
        int objectId = 0;
        Method method = null;
        Method method2 = null;
        try {
            logTableClass = Class.forName(classToUpdate.getName() + "Log");
            method = logTableClass.getMethod("getOp");
            method2 = logTableClass.getMethod("getId");
        } catch (Exception e) {
            e.printStackTrace();
        }
        List logEntries = lpm.getAllEntities(logTableClass);
        for (Object oneLogEntry : logEntries) {
            pm = new PersistanceManager(this.activity);
            try {
                operationType = (String) method.invoke(oneLogEntry);
                objectId = (int) method2.invoke(oneLogEntry);
            } catch (Exception e) {
                e.printStackTrace();
            }
            T auxiliarObject = null;
            if(operationType.equalsIgnoreCase("U")) {
                auxiliarObject = lpm.getEntity(classToUpdate, objectId);
                String operationResponse = pm.updateAnObjectFromServer(classToUpdate, auxiliarObject);
                if(operationResponse.equalsIgnoreCase(GlobalParameterController.OPERATION_OK)) {
                    lpm.deleteLogEntry(logTableClass, oneLogEntry);
                }
            }
        }
    }

    private <T> void deleteEntity(Class<T> classToDelete) {
        PersistanceManager pm = null;
        Class logTableClass = null;
        String operationType = null;
        int objectId = 0;
        Method method = null;
        Method method2 = null;
        try {
            logTableClass = Class.forName(classToDelete.getName() + "Log");
            method = logTableClass.getMethod("getOp");
            method2 = logTableClass.getMethod("getId");
        } catch (Exception e) {
            e.printStackTrace();
        }
        List logEntries = lpm.getAllEntities(logTableClass);
        for (Object oneLogEntry : logEntries) {
            pm = new PersistanceManager(this.activity);
            try {
                operationType = (String) method.invoke(oneLogEntry);
                objectId = (int) method2.invoke(oneLogEntry);
            } catch (Exception e) {
                e.printStackTrace();
            }
            T auxiliarObject = null;
            if(operationType.equalsIgnoreCase("D")) {
                    String operationResult = pm.deleteAnObjectFromServer(classToDelete, objectId);
                if(operationResult.equalsIgnoreCase(GlobalParameterController.OPERATION_OK)) {
                    lpm.deleteLogEntry(logTableClass, oneLogEntry);
                }
            }
        }
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        uploadEntities();
        return null;
    }

    @Override
    protected void onPreExecute() {
//        this.dialog.setMessage("Please wait...");
//        this.dialog.show();
    }

    @Override
    protected void onPostExecute(Object o) {
        //this.dialog.dismiss();
        LogAndToastMaker.makeToast(this.activity, this.activity.getResources().getString(R.string.upload_completed));
    }

    //    private <T> void uploadEntity(Class<T> classToUpload) {
//        PersistanceManager pm = null;
//        Class logTableClass = null;
//        String operationType = null;
//        int objectId = 0;
//        Method method = null;
//        Method method2 = null;
//        try {
//            logTableClass = Class.forName(classToUpload.getName() + "Log");
//            method = logTableClass.getMethod("getOp");
//            method2 = logTableClass.getMethod("getId");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        List logEntries = lpm.getAllEntities(logTableClass);
//        for (Object oneLogEntry : logEntries) {
//            pm = new PersistanceManager(activity);
//            try {
//                operationType = (String) method.invoke(oneLogEntry);
//                objectId = (int) method2.invoke(oneLogEntry);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            T auxiliarObject = null;
//            switch (operationType) {
//                case "I":
//                    auxiliarObject = lpm.getEntity(classToUpload, objectId);
//                    pm.sendAnObjectToServer(classToUpload, auxiliarObject);
//                    lpm.deleteLogEntry(logTableClass, oneLogEntry);
//                    break;
//                case "U":
//                    auxiliarObject = lpm.getEntity(classToUpload, objectId);
//                    pm.updateAnObjectFromServer(classToUpload, auxiliarObject);
//                    lpm.deleteLogEntry(logTableClass, oneLogEntry);
//                    break;
//                case "D":
//                    pm.deleteAnObjectFromServer(classToUpload, objectId);
//                    lpm.deleteLogEntry(logTableClass, oneLogEntry);
//                    break;
//            }
//        }
//    }
}
