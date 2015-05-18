package com.example.aleix.projectefinal.Controller;

import android.app.Activity;

import com.example.aleix.projectefinal.Entity.Client;
import com.example.aleix.projectefinal.Entity.ClientLog;
import com.example.aleix.projectefinal.Entity.Comanda;
import com.example.aleix.projectefinal.Entity.Comanda_Producte;
import com.example.aleix.projectefinal.Entity.Localitzacio;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Michal on 18/05/2015.
 */
public class SynchronizeController {

    private Activity activity;
    private LocalPersistanceManager lpm;
    private PersistanceManager pm;

    public SynchronizeController(Activity activity) {
        this.activity = activity;
        lpm = new LocalPersistanceManager(activity, "m13_project" , 1);
        pm = new PersistanceManager(activity);
    }

    public void uploadEntities() {
        uploadEntity(Client.class);
        uploadEntity(Comanda_Producte.class);
        uploadEntity(Comanda.class);
        uploadEntity(Localitzacio.class);
    }

    private <T> void uploadEntity(Class<T> classToUpload) {
        List logEntries = lpm.getAllEntities(classToUpload);
        String operationType = null;
        int objectId = 0;
        Class logTableClass = null;

        for(Object oneLogEntry : logEntries) {
            try {
                logTableClass = Class.forName(classToUpload.getSimpleName() + "Log");
                Method method = classToUpload.getMethod("getOp");
                Method method2 = classToUpload.getMethod("getId");
                operationType = (String) method.invoke(oneLogEntry);
                objectId = (int) method2.invoke(oneLogEntry);
            } catch (Exception e) {
                e.printStackTrace();
            }
            T auxiliarObject = null;
            switch(operationType) {
                case "I":
                    auxiliarObject = lpm.getEntity(classToUpload, objectId);
                    pm.sendAnObjectToServer(classToUpload, auxiliarObject);
                    lpm.deleteLogEntry(logTableClass, oneLogEntry);
                    break;
                case "U":
                    auxiliarObject = lpm.getEntity(classToUpload, objectId);
                    pm.updateAnObjectFromServer(classToUpload, auxiliarObject);
                    lpm.deleteLogEntry(logTableClass, oneLogEntry);
                    break;
                case "D":
                    pm.deleteAnObjectFromServer(classToUpload, objectId);
                    lpm.deleteLogEntry(logTableClass, oneLogEntry);
                    break;
            }
        }
    }
}
