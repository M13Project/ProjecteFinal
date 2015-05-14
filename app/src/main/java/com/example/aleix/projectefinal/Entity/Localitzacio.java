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
    private int Id;
    @DatabaseField(columnName = CODIPOSTAL)
    private String CodiPostal;
    @DatabaseField(columnName = DIRECCIO)
    private String Direccio;
    @DatabaseField(columnName = LATITUD)
    private String Latitud;
    @DatabaseField(columnName = LONGITUD)
    private String Longitud;
    @DatabaseField(columnName = CLIENT)
    private String Client;

    public Localitzacio() {
    }

    public Localitzacio(String codiPostal, String direccio, String latitud, String longitud, String client) {
        CodiPostal = codiPostal;
        Direccio = direccio;
        Latitud = latitud;
        Longitud = longitud;
        Client = client;
    }
}
