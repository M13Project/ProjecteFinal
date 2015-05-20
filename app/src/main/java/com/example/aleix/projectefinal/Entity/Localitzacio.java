package com.example.aleix.projectefinal.Entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Aleix on 05/05/2015.
 */
@DatabaseTable(tableName = "Localitzacio")
public class Localitzacio {

    @DatabaseField(generatedId = true)
    private int _id;
    @DatabaseField(columnName = "CodiPostal")
    private String CodiPostal;
    @DatabaseField(columnName = "Direccio")
    private String Direccio;
    @DatabaseField(columnName = "Poblacio")
    private String Poblacio;
    @DatabaseField(columnName = "Latitud")
    private double Latitud;
    @DatabaseField(columnName = "Longitud")
    private double Longitud;
    @DatabaseField(foreign = true, columnName = "ClientId")
    private Client ClientId;

    public Localitzacio() {
    }

    public Localitzacio(String codiPostal, String direccio, String poblacio, Client client) {
        CodiPostal = codiPostal;
        Direccio = direccio;
        Poblacio = poblacio;
        ClientId = client;
    }

    public Localitzacio(String codiPostal, String direccio, String poblacio, double latitud, double longitud, Client client) {
        CodiPostal = codiPostal;
        Direccio = direccio;
        Poblacio = poblacio;
        Latitud = latitud;
        Longitud = longitud;
        ClientId = client;
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

    public String getPoblacio() {
        return Poblacio;
    }

    public void setPoblacio(String poblacio) {
        Poblacio = poblacio;
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

    public Client getClientId() {
        return ClientId;
    }

    public void setClientId(Client client) {
        ClientId = client;
    }

    @Override
    public String toString() {
        return "Id: " + this._id + ", CodiPostal: " + this.CodiPostal + ", Direccio: " + this.Direccio + ", Latitud:" + this.Latitud + ", Longitud: " + this.Longitud + ", ClientId: " + this.ClientId.getId();
    }
}
