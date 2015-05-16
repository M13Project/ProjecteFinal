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
    private int CodiPostal;
    @DatabaseField(columnName = DIRECCIO)
    private String Direccio;
    @DatabaseField(columnName = LATITUD)
    private double Latitud;
    @DatabaseField(columnName = LONGITUD)
    private double Longitud;
    @DatabaseField(foreign = true, columnName = CLIENT)
    private Client Client;

    public Localitzacio() {
    }

    public Localitzacio(int codiPostal, String direccio, double latitud, double longitud, Client client) {
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

    public int getCodiPostal() {
        return CodiPostal;
    }

    public void setCodiPostal(int codiPostal) {
        CodiPostal = codiPostal;
    }

    public String getDireccio() {
        return Direccio;
    }

    public void setDireccio(String direccio) {
        Direccio = direccio;
    }

    public double getLatitud() {
        return Latitud;
    }

    public void setLatitud(double latitud) {
        Latitud = latitud;
    }

    public double getLongitud() {
        return Longitud;
    }

    public void setLongitud(double longitud) {
        Longitud = longitud;
    }

    public com.example.aleix.projectefinal.Entity.Client getClient() {
        return Client;
    }

    public void setClient(com.example.aleix.projectefinal.Entity.Client client) {
        Client = client;
    }

    @Override
    public String toString() {
        return "Id: " + this._id + ", CodiPostal: " + this.CodiPostal + ", Direccio: " + this.Direccio + ", Latitud:" + this.Latitud + ", Longitud: " + this.Longitud + ", ClientId: " + this.Client.getId();
    }
}
