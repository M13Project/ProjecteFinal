package com.example.aleix.projectefinal.Entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Michal on 17/05/2015.
 */
@DatabaseTable(tableName = "Comanda_ProducteLog")
public class Comanda_ProducteLog {

    @DatabaseField(uniqueCombo = true)
    private int _id;
    @DatabaseField(uniqueCombo = true)
    private String Op;
    @DatabaseField(columnName = "OperationDate")
    private String OperationDate;

    public Comanda_ProducteLog() {}

    public Comanda_ProducteLog(int _id, String op, String operationDate) {
        this._id = _id;
        Op = op;
        OperationDate = operationDate;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getOp() {
        return Op;
    }

    public void setOp(String op) {
        Op = op;
    }

    public String getOperationDate() {
        return OperationDate;
    }

    public void setOperationDate(String operationDate) {
        OperationDate = operationDate;
    }
}
