package com.example.aleix.projectefinal.Controller;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Michal.hostienda on 14/05/2015.
 */
public class LocalPersistanceManager {

    private Activity activity;
    private ConnectionSource connectionSource;
    private SQLiteDatabase databaseManualAccess;

    public LocalPersistanceManager(Activity activity, String databaseName, int currentVersion) {
        this.activity = activity;
        DatabaseOrmLite database = new DatabaseOrmLite(this.activity, databaseName, null, currentVersion);
        this.connectionSource = new AndroidConnectionSource(database);
        databaseManualAccess = database.getReadableDatabase();
    }

    private void createTable(Class classRepresentingTable) {
        try {
            TableUtils.createTable(connectionSource, classRepresentingTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private <T> Dao<T, Integer> tableManager(Class<T> classRepresentingTable) {
        Dao<T, Integer> dao = null;
        try {
            dao = DaoManager.createDao(connectionSource, classRepresentingTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dao;
    }

    public <T> String insert(Class<T> classRepresentingObjectToInsert, T objectToBeInserted) {
        String resultString = null;
        if (!checkIfTableExists(classRepresentingObjectToInsert)) {
            createTable(classRepresentingObjectToInsert);
            addLogTable(classRepresentingObjectToInsert);
        }
        Dao<T, Integer> dao = tableManager(classRepresentingObjectToInsert);
        try {
            int resultOfInsertion = dao.create(objectToBeInserted);
            if (resultOfInsertion == 1) {
                resultString = GlobalParameterController.OPERATION_OK;
            } else {
                resultString = GlobalParameterController.OPERATION_FAIL;
            }
        } catch (SQLException e) {
            LogAndToastMaker.makeErrorLog(e.getMessage());
        }
        LogAndToastMaker.makeInfoLog(resultString);
        return resultString;
    }

    public <T> String update(Class<T> classRepresentingObjectToUpdate, T objectToBeUpdated) {
        String resultString = null;
        if (!checkIfTableExists(classRepresentingObjectToUpdate)) {
            createTable(classRepresentingObjectToUpdate);
            addLogTable(classRepresentingObjectToUpdate);
        }
        Dao<T, Integer> dao = tableManager(classRepresentingObjectToUpdate);
        try {
            int resultOfInsertion = dao.update(objectToBeUpdated);
            if (resultOfInsertion == 1) {
                resultString = GlobalParameterController.OPERATION_OK;
            } else {
                resultString = GlobalParameterController.OPERATION_FAIL;
            }
        } catch (SQLException e) {
            LogAndToastMaker.makeErrorLog(e.getMessage());
        }
        LogAndToastMaker.makeInfoLog(resultString);
        return resultString;
    }

    public <T> T getEntity(Class<T> classRepresentingObjectTORetrieve, int idOfObjectToRetrieve) {
        Dao<T, Integer> dao = null;
        T objectRetrieved = null;
        if(checkIfTableExists(classRepresentingObjectTORetrieve)) {
            dao = tableManager(classRepresentingObjectTORetrieve);
            try {
                objectRetrieved = dao.queryForId(idOfObjectToRetrieve);
            } catch (SQLException e) {
                LogAndToastMaker.makeErrorLog(e.getMessage());
            }
        } else {
            LogAndToastMaker.makeInfoLog("No entries in the table!");
        }
        return objectRetrieved;
    }

    public <T> List<T> getAllEntities(Class<T> classRepresentingObjectsTORetrieve) {
        Dao<T, Integer> dao = null;
        List<T> objectsRetrieved = null;
        if(checkIfTableExists(classRepresentingObjectsTORetrieve)) {
            dao = tableManager(classRepresentingObjectsTORetrieve);
            try {
                objectsRetrieved = dao.queryForAll();
            } catch (SQLException e) {
                LogAndToastMaker.makeErrorLog(e.getMessage());
            }
        } else {
            LogAndToastMaker.makeInfoLog("No entries in the table!");
        }
        return objectsRetrieved;
    }

    public <T> String delete(Class<T> classRepresentingObjectToDelete, int idOfObjectToDelete) {
        String resultString = null;
        Dao<T, Integer> dao = null;
        if(checkIfTableExists(classRepresentingObjectToDelete)) {
            dao = tableManager(classRepresentingObjectToDelete);
            try {
                int resultOfDelete = dao.deleteById(idOfObjectToDelete);
                if (resultOfDelete == 1) {
                    resultString = GlobalParameterController.OPERATION_OK;
                } else {
                    resultString = GlobalParameterController.OPERATION_FAIL;
                }
            } catch (SQLException e) {
                LogAndToastMaker.makeErrorLog(e.getMessage());
            }
        } else {
            LogAndToastMaker.makeInfoLog("No such entry in the table!");
        }
        return resultString;
    }

    public <T> String deleteLogEntry(Class<T> classRepresentingObjectToDelete, T objectToDelete) {
        String resultString = null;
        Dao<T, Integer> dao = null;
        int idOfObjectToDelete = 0;
        String operationType = null;
        try {
            Method method = classRepresentingObjectToDelete.getMethod("getId");
            Method method2 = classRepresentingObjectToDelete.getMethod("getOp");
            idOfObjectToDelete = (int) method.invoke(objectToDelete);
            operationType = (String) method2.invoke(objectToDelete);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String rawQuery = "DELETE FROM " + classRepresentingObjectToDelete.getSimpleName() + " WHERE " + "_id = " + idOfObjectToDelete + " AND Op = " + "'" + operationType + "'";
        if(checkIfTableExists(classRepresentingObjectToDelete)) {
            dao = tableManager(classRepresentingObjectToDelete);
            try {
                int resultOfDelete = dao.executeRawNoArgs(rawQuery);
                if (resultOfDelete == 1) {
                    resultString = "Resource deleted correctly!";
                } else {
                    resultString = "Failed to delete the resource!";
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            LogAndToastMaker.makeInfoLog("No such entry in the table!");
        }
        return resultString;
    }

    private boolean checkIfTableExists(Class classRepresentingTable) {
        boolean tableExists = false;
        if (this.databaseManualAccess != null) {
            Cursor retrievedCursor = this.databaseManualAccess.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + classRepresentingTable.getSimpleName() + "'", null);
            if (retrievedCursor != null) {
                if (retrievedCursor.getCount() > 0) {
                    tableExists = true;
                } else {
                    tableExists = false;
                }
                retrievedCursor.close();
            }
        }
        return tableExists;
    }

    private void addLogTable(Class classRepresentingTable) {
        try {
            Class classOfLogTable = Class.forName("com.example.aleix.projectefinal.Entity." + classRepresentingTable.getSimpleName() + "Log");
            createTable(classOfLogTable);
            addTriggers(classRepresentingTable, classOfLogTable);
            LogAndToastMaker.makeInfoLog("Table " + classOfLogTable.getSimpleName() + " was created!");
        } catch (Exception e) {
            LogAndToastMaker.makeErrorLog("The table you're currently creating has no log table");
        }
    }

    private void addTriggers(Class classRepresentingTable, Class classRepresentingLogTable) {
        String triggerName = classRepresentingTable.getSimpleName().toLowerCase();
        String nameTableOnWhichApplyTrigger = classRepresentingTable.getSimpleName();
        String nameOfLogTable = classRepresentingLogTable.getSimpleName();

        String afterInsertTrigger = "CREATE TRIGGER tr_ai_" + triggerName + " \n" +
                "AFTER INSERT ON " + nameTableOnWhichApplyTrigger + " \n" +
                "BEGIN\n" +
                "INSERT INTO " + nameOfLogTable + " (_id, Op, OperationDate) VALUES(NEW._id, 'I', DateTime('now')); \n" +
                "END;";

        String afterUpdateTrigger = "CREATE TRIGGER tr_au_" + triggerName + " \n" +
                "AFTER UPDATE ON " + nameTableOnWhichApplyTrigger + "  \n" +
                "BEGIN\n" +
                "DELETE FROM " + nameOfLogTable + " WHERE Op='U' AND " + nameOfLogTable + "._id = NEW._id;  \n" +
                "INSERT INTO " + nameOfLogTable + " (_id, Op, OperationDate) VALUES(NEW._id, 'U', DateTime('now'));\n" +
                "END;";

        String afterDeleteTrigger = "CREATE TRIGGER tr_ad_" + triggerName + " \n" +
                "AFTER DELETE ON " + nameTableOnWhichApplyTrigger + " \n" +
                "BEGIN \n" +
                "DELETE FROM " + nameOfLogTable + " WHERE " + nameOfLogTable + "._id=OLD._id;  \n" +
                "INSERT INTO " + nameOfLogTable + " (_id, Op, OperationDate) VALUES(OLD._id, 'D', DateTime('now'));  \n" +
                "END;";
        if(this.databaseManualAccess != null) {
            this.databaseManualAccess.execSQL(afterInsertTrigger);
            this.databaseManualAccess.execSQL(afterUpdateTrigger);
            this.databaseManualAccess.execSQL(afterDeleteTrigger);
        }
    }

    public void closeConnection() {
        try {
            this.connectionSource.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
