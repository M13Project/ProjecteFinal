package com.example.aleix.projectefinal.Entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Aleix on 05/05/2015.
 */
@DatabaseTable(tableName = "Comanda")
public class Comanda {
    public static final String ID = "_id";
    public static final String LLIURADA = "Lliurada";
    public static final String DATA = "Data";
    public static final String CLIENTID = "ClientId";

    @DatabaseField(generatedId = true, columnName = ID)
    private int _id;

    @DatabaseField(columnName = LLIURADA)
    private Boolean Lliurada;

    @DatabaseField(columnName = DATA)
    private String Data;

    @DatabaseField(foreign = true, columnName = CLIENTID)
    private Client ClientId;

    public Comanda(Boolean lliurada, String data, Client client) {
        Lliurada = lliurada;
        Data = data;
        this.ClientId = client;
    }

    public Comanda() {
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }

    public Boolean getLliurada() {
        return Lliurada;
    }

    public void setLliurada(Boolean lliurada) {
        this.Lliurada = lliurada;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public Client getClientId() {
        return ClientId;
    }

    public void setClientId(Client client) {
        this.ClientId = client;
    }

    @Override
    public String toString() {
        return "Id: " + this._id + ", Lliurada: " + this.Lliurada + ", Data: " + this.Data + ", ClientId: " + this.ClientId.getId();
    }
}