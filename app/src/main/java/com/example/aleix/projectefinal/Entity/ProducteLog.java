package com.example.aleix.projectefinal.Entity;

/**
 * Created by Michal on 21/05/2015.
 */

public class ProducteLog {
    private int Id;
    private String Op;
    private String LastUpdate;

    public ProducteLog () {

    }

    public ProducteLog(int id, String op, String lastUpdate) {
        Id = id;
        Op = op;
        LastUpdate = lastUpdate;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getOp() {
        return Op;
    }

    public void setOp(String op) {
        Op = op;
    }

    public String getLastUpdate() {
        return LastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        LastUpdate = lastUpdate;
    }
}
