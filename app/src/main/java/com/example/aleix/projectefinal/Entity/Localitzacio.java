package com.example.aleix.projectefinal.Entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Aleix on 05/05/2015.
 */
@DatabaseTable(tableName = "Localitzacio")
public class Localitzacio {
    public static final String ID = "_id";
    public static final String CODIPOSTAL = "CodiPostal";
    public static final String DIRECCIO = "Direccio";
    public static final String LATITUD = "Latitud";
    public static final String LONGITUD = "Longitud";
    public static final String CLIENT = "ClientId";
    @DatabaseField(generatedId = true, columnName = ID)
    private int _id;
    @DatabaseField(columnName = CODIPOSTAL)
    private String CodiPostal;
    @DatabaseField(columnName = DIRECCIO)
    private String Direccio;
    @DatabaseField(columnName = LATITUD)
    private String Latitud;
    @DatabaseField(columnName = LONGITUD)
    private String Longitud;
    @DatabaseField(columnName = CLIENT)
    private Client Client;

    public Localitzacio() {
    }

    public Localitzacio(String codiPostal, String direccio, String latitud, String longitud, Client client) {
        CodiPostal = codiPostal;
        Direccio = direccio;
        Latitud = latitud;
        Longitud = longitud;
        Client = client;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }

    public String getCodiPostal() {
        return CodiPostal;
    }

    public void setCodiPostal(String codiPostal) {
        CodiPostal = codiPostal;
    }

    public String getDireccio() {
        return Direccio;
    }

    public void setDireccio(String direccio) {
        Direccio = direccio;
    }

    public String getLatitud() {
        return Latitud;
    }

    public void setLatitud(String latitud) {
        Latitud = latitud;
    }

    public String getLongitud() {
        return Longitud;
    }

    public void setLongitud(String longitud) {
        Longitud = longitud;
    }

    public com.example.aleix.projectefinal.Entity.Client getClient() {
        return Client;
    }

    public void setClient(com.example.aleix.projectefinal.Entity.Client client) {
        Client = client;
    }
}
