package com.example.aleix.projectefinal.Controller;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.aleix.projectefinal.Entity.LogAndToastMaker;
import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableInfo;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

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
        }
        Dao<T, Integer> dao = tableManager(classRepresentingObjectToInsert);
        try {
            int resultOfInsertion = dao.create(objectToBeInserted);
            if (resultOfInsertion == 1) {
                resultString = "Resource inserted correctly!";
            } else {
                resultString = "Failed to insert the resource!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LogAndToastMaker.makeInfoLog(resultString);
        LogAndToastMaker.makeToast(this.activity, resultString);
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
                e.printStackTrace();
            }
        } else {
            LogAndToastMaker.makeInfoLog("No entries in the table!");
            LogAndToastMaker.makeToast(this.activity, "No entries in the table!");
        }
        return objectRetrieved;
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

    public void closeConnection() {
        try {
            this.connectionSource.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
