package com.example.aleix.projectefinal.Entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.types.DateTimeType;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Aleix on 05/05/2015.
 */
@DatabaseTable
public class Comanda {
    public static final String ID = "_id";
    public static final String LLIURADA = "LLiurada";
    public static final String DATA = "Data";
    public static final String CLIENT = "ClientId";
    @DatabaseField(generatedId = true, columnName = ID)
    private int Id;
    @DatabaseField(columnName = LLIURADA)
    private String Lliurada;
    @DatabaseField(columnName = DATA)
    private DateTimeType Data;
    @DatabaseField(columnName = CLIENT)
    private String ClientId;

    public Comanda(String lliurada, DateTimeType data, String clientId) {
        Lliurada = lliurada;
        Data = data;
        ClientId = clientId;
    }

    public Comanda(int id) {

        Id = id;
    }
    public Comanda() {


    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getLliurada() {
        return Lliurada;
    }

    public void setLliurada(String lliurada) {
        Lliurada = lliurada;
    }

    public DateTimeType getData() {
        return Data;
    }

    public void setData(DateTimeType data) {
        Data = data;
    }

    public String getClientId() {
        return ClientId;
    }

    public void setClientId(String clientId) {
        ClientId = clientId;
    }
}
