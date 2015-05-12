package com.example.aleix.projectefinal.Entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.types.DateTimeType;
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
    private int Id;

    @DatabaseField(columnName = LLIURADA)
    private Boolean Lliurada;

    @DatabaseField(columnName = DATA)
    private DateTimeType Data;

    @DatabaseField(foreign = true, columnName = CLIENTID)
    private int ClientId;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Boolean getLliurada() {
        return Lliurada;
    }

    public void setLliurada(Boolean lliurada) {
        this.Lliurada = lliurada;
    }

    public DateTimeType getData() {
        return Data;
    }

    public void setData(DateTimeType data) {
        Data = data;
    }

    public int getClientId() {
        return ClientId;
    }

    public void setClientId(int clientId) {
        this.ClientId = clientId;
    }

    public Comanda(Boolean lliurada, DateTimeType data, int clientId) {
        Lliurada = lliurada;
        Data = data;
        ClientId = clientId;
    }

    public Comanda() {
    }
}
