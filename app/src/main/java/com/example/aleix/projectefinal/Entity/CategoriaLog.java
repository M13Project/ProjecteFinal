package com.example.aleix.projectefinal.Entity;

/**
 * Created by Michal on 21/05/2015.
 */
public class CategoriaLog {
    private int Id;
    private String Op;
    private String LastUpdate;

    public CategoriaLog () {

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
