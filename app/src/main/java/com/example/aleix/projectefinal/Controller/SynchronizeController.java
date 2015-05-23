package com.example.aleix.projectefinal.Controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.example.aleix.projectefinal.Entity.Categoria;
import com.example.aleix.projectefinal.Entity.Client;
import com.example.aleix.projectefinal.Entity.ClientLog;
import com.example.aleix.projectefinal.Entity.Comanda;
import com.example.aleix.projectefinal.Entity.Comanda_Producte;
import com.example.aleix.projectefinal.Entity.Localitzacio;
import com.example.aleix.projectefinal.Entity.Producte;
import com.example.aleix.projectefinal.Entity.ProducteLog;
import com.example.aleix.projectefinal.R;

import org.joda.time.DateTime;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Michal on 18/05/2015.
 */
public class SynchronizeController {

    private Activity activity;
    private LocalPersistanceManager lpm;

    public SynchronizeController(Activity activity) {
        this.activity = activity;
        lpm = new LocalPersistanceManager(activity, GlobalParameterController.DATABASE_NAME, GlobalParameterController.DATABASE_VERSION);
    }

    public void synchronizeData() {
        downloadEntities();
        uploadEntities();
        LogAndToastMaker.makeToast(this.activity, "Synchronizing completed successfully!");
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
            List logEntries = lpm.getAllEntities(logTableClass);

            if (logEntries != null && !logEntries.isEmpty()) {
                for (Object oneLogEntry : logEntries) {
                    pm = new PersistanceManager(this.activity);
                    try {
                        operationType = (String) method.invoke(oneLogEntry);
                        objectId = (int) method2.invoke(oneLogEntry);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    T auxiliarObject = null;
                    if (operationType.equalsIgnoreCase("I")) {
                        auxiliarObject = lpm.getEntity(classToInsert, objectId);
                        String operationResponse = pm.sendAnObjectToServer(classToInsert, auxiliarObject);
                        if (operationResponse.equalsIgnoreCase(GlobalParameterController.OPERATION_OK)) {
                            lpm.deleteLogEntry(logTableClass, oneLogEntry);
                        }
                    }
                }
            }
        } catch (Exception e) {
            LogAndToastMaker.makeErrorLog(e.getMessage());
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
            List logEntries = lpm.getAllEntities(logTableClass);
            if (logEntries != null && !logEntries.isEmpty()) {
                for (Object oneLogEntry : logEntries) {
                    pm = new PersistanceManager(this.activity);
                    try {
                        operationType = (String) method.invoke(oneLogEntry);
                        objectId = (int) method2.invoke(oneLogEntry);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    T auxiliarObject = null;
                    if (operationType.equalsIgnoreCase("U")) {
                        auxiliarObject = lpm.getEntity(classToUpdate, objectId);
                        String operationResponse = pm.updateAnObjectFromServer(classToUpdate, auxiliarObject);
                        if (operationResponse.equalsIgnoreCase(GlobalParameterController.OPERATION_OK)) {
                            lpm.deleteLogEntry(logTableClass, oneLogEntry);
                        }
                    }
                }
            }
        } catch (Exception e) {
            LogAndToastMaker.makeErrorLog(e.getMessage());
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
            List logEntries = lpm.getAllEntities(logTableClass);
            if (logEntries != null && !logEntries.isEmpty()) {
                for (Object oneLogEntry : logEntries) {
                    pm = new PersistanceManager(this.activity);
                    try {
                        operationType = (String) method.invoke(oneLogEntry);
                        objectId = (int) method2.invoke(oneLogEntry);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    T auxiliarObject = null;
                    if (operationType.equalsIgnoreCase("D")) {
                        String operationResult = pm.deleteAnObjectFromServer(classToDelete, objectId);
                        if (operationResult.equalsIgnoreCase(GlobalParameterController.OPERATION_OK)) {
                            lpm.deleteLogEntry(logTableClass, oneLogEntry);
                        }
                    }
                }
            }
        } catch (Exception e) {
            LogAndToastMaker.makeErrorLog(e.getMessage());
        }
    }

    private void downloadEntities() {
        String dateLastDownload = retrieveLastDownloadDate();

        if (dateLastDownload.equalsIgnoreCase(GlobalParameterController.OPERATION_FAIL)) {
            dateLastDownload = "2015-05-22T13:33:43.250Z";
        }

        try {
            DateTime dateLastDownloadInDateTimeFormat = new DateTime(dateLastDownload);

            String resultOfInsertCategoria = insertEntityFromServer(Categoria.class, dateLastDownloadInDateTimeFormat);
            String resultOfInsertProducte = insertEntityFromServer(Producte.class, dateLastDownloadInDateTimeFormat);

            String resultOfUpdateCategoria = updateEntityFromServer(Categoria.class, dateLastDownloadInDateTimeFormat);
            String resultOfUpdateProducte = updateEntityFromServer(Producte.class, dateLastDownloadInDateTimeFormat);

            String resultOfDeleteProducte = deleteEntityFromServer(Producte.class, dateLastDownloadInDateTimeFormat);
            String resultOfDeleteCategoria = deleteEntityFromServer(Categoria.class, dateLastDownloadInDateTimeFormat);

            saveLastDownloadDate();
        } catch (Exception e) {
            LogAndToastMaker.makeErrorLog(e.getMessage());
        }
    }

    private <T> String insertEntityFromServer(Class<T> classToInsert, DateTime dateLastDownloadInDateTimeFormat) {
        String resultOfInsertOperation = GlobalParameterController.OPERATION_OK;
        PersistanceManager pm = new PersistanceManager(this.activity);
        List listOfLogEntries = null;
        DateTime dateOfLogEntry = null;
        String operationType = null;
        int objectId = 0;
        Method method = null;
        Method method2 = null;
        Method method3 = null;
        T auxiliarObject = null;

        try {
            Class classToInsertLog = Class.forName(classToInsert.getName() + "Log");
            listOfLogEntries = pm.getListOfObjectsFromServer(classToInsertLog);
            if (!listOfLogEntries.isEmpty()) {
                method = classToInsertLog.getMethod("getOp");
                method2 = classToInsertLog.getMethod("getId");
                method3 = classToInsertLog.getMethod("getLastUpdate");
                for (Object logEntry : listOfLogEntries) {
                    pm = new PersistanceManager(this.activity);
                    operationType = (String) method.invoke(logEntry);
                    objectId = (int) method2.invoke(logEntry);
                    dateOfLogEntry = new DateTime((String) method3.invoke(logEntry));
                    if (operationType.equalsIgnoreCase("I") && dateOfLogEntry.isAfter(dateLastDownloadInDateTimeFormat)) {
                        auxiliarObject = pm.getObjectFromServer(classToInsert, objectId);
                        if (auxiliarObject != null) {
                            String operationResult = lpm.insert(classToInsert, auxiliarObject);
                            if (operationResult.equalsIgnoreCase(GlobalParameterController.OPERATION_FAIL)) {
                                resultOfInsertOperation = GlobalParameterController.OPERATION_FAIL;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            LogAndToastMaker.makeErrorLog(e.getMessage());
        }
        return resultOfInsertOperation;
    }

    private <T> String updateEntityFromServer(Class<T> classToUpdate, DateTime dateLastDownloadInDateTimeFormat) {
        String resultOfUpdateOperation = GlobalParameterController.OPERATION_OK;
        PersistanceManager pm = new PersistanceManager(this.activity);
        List listOfLogEntries = null;
        DateTime dateOfLogEntry = null;
        String operationType = null;
        int objectId = 0;
        Method method = null;
        Method method2 = null;
        Method method3 = null;
        T auxiliarObject = null;

        try {
            Class classToUpdateLog = Class.forName(classToUpdate.getName() + "Log");
            listOfLogEntries = pm.getListOfObjectsFromServer(classToUpdateLog);
            if (!listOfLogEntries.isEmpty()) {
                method = classToUpdateLog.getMethod("getOp");
                method2 = classToUpdateLog.getMethod("getId");
                method3 = classToUpdateLog.getMethod("getLastUpdate");
                for (Object logEntry : listOfLogEntries) {
                    pm = new PersistanceManager(this.activity);
                    operationType = (String) method.invoke(logEntry);
                    objectId = (int) method2.invoke(logEntry);
                    dateOfLogEntry = new DateTime((String) method3.invoke(logEntry));
                    if (operationType.equalsIgnoreCase("U") && dateOfLogEntry.isAfter(dateLastDownloadInDateTimeFormat)) {
                        auxiliarObject = pm.getObjectFromServer(classToUpdate, objectId);
                        if (auxiliarObject != null) {
                            String operationResult = lpm.update(classToUpdate, auxiliarObject);
                            if (operationResult.equalsIgnoreCase(GlobalParameterController.OPERATION_FAIL)) {
                                resultOfUpdateOperation = GlobalParameterController.OPERATION_FAIL;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            LogAndToastMaker.makeErrorLog(e.getMessage());
        }
        return resultOfUpdateOperation;
    }


    private <T> String deleteEntityFromServer(Class<T> classToDelete, DateTime dateLastDownloadInDateTimeFormat) {
        String resultOfUpdateOperation = GlobalParameterController.OPERATION_OK;
        PersistanceManager pm = new PersistanceManager(this.activity);
        List listOfLogEntries = null;
        DateTime dateOfLogEntry = null;
        String operationType = null;
        int objectId = 0;
        Method method = null;
        Method method2 = null;
        Method method3 = null;
        T auxiliarObject = null;

        try {
            Class classToDeleteLog = Class.forName(classToDelete.getName() + "Log");
            listOfLogEntries = pm.getListOfObjectsFromServer(classToDeleteLog);
            if (!listOfLogEntries.isEmpty()) {
                method = classToDeleteLog.getMethod("getOp");
                method2 = classToDeleteLog.getMethod("getId");
                method3 = classToDeleteLog.getMethod("getLastUpdate");
                for (Object logEntry : listOfLogEntries) {
                    pm = new PersistanceManager(this.activity);
                    operationType = (String) method.invoke(logEntry);
                    objectId = (int) method2.invoke(logEntry);
                    dateOfLogEntry = new DateTime((String) method3.invoke(logEntry));
                    if (operationType.equalsIgnoreCase("D") && dateOfLogEntry.isAfter(dateLastDownloadInDateTimeFormat)) {
                        String operationResult = lpm.delete(classToDelete, objectId);
                        if (operationResult.equalsIgnoreCase(GlobalParameterController.OPERATION_FAIL)) {
                            resultOfUpdateOperation = GlobalParameterController.OPERATION_FAIL;
                        }
                    }
                }
            }
        } catch (Exception e) {
            LogAndToastMaker.makeErrorLog(e.getMessage());
        }
        return resultOfUpdateOperation;
    }


    private void saveLastDownloadDate() {
        PersistanceManager pm = new PersistanceManager(this.activity);
        String dataServiceDate = pm.getServerDateTime();
        if (!dataServiceDate.equalsIgnoreCase(GlobalParameterController.OPERATION_FAIL)) {
            SharedPreferences sharedPreferences = this.activity.getSharedPreferences(GlobalParameterController.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("lastDownloadDate", dataServiceDate);
            editor.commit();
        }
    }

    private String retrieveLastDownloadDate() {
        SharedPreferences sharedPreferences = this.activity.getSharedPreferences(GlobalParameterController.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        String retrievedDate = sharedPreferences.getString("lastDownloadDate", GlobalParameterController.OPERATION_FAIL);
        return retrievedDate;
    }
}
