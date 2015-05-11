package com.example.aleix.projectefinal.Entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Aleix on 05/05/2015.
 */

@DatabaseTable
public class Localitzacio {

    public static final String ID = "_id";
    public static final String CODIPOSTAL = "CodiPostal";
    public static final String DIRECCIO = "Direccio";
    public static final String LATITUD = "Latitud";
    public static final String LONGITUD = "Longitud";

    @DatabaseField(generatedId = true, foreign = true, columnName = ID)
    private int Id;

    @DatabaseField(columnName = CODIPOSTAL)
    private int CodiPostal;

    @DatabaseField(columnName = DIRECCIO)
    private int Direccio;

    @DatabaseField(columnName = LATITUD)
    private int Latitud;

    @DatabaseField(columnName = LONGITUD)
    private int Longitud;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getCodiPostal() {
        return CodiPostal;
    }

    public void setCodiPostal(int codiPostal) {
        CodiPostal = codiPostal;
    }

    public int getDireccio() {
        return Direccio;
    }

    public void setDireccio(int direccio) {
        Direccio = direccio;
    }

    public int getLatitud() {
        return Latitud;
    }

    public void setLatitud(int latitud) {
        Latitud = latitud;
    }

    public int getLongitud() {
        return Longitud;
    }

    public void setLongitud(int longitud) {
        Longitud = longitud;
    }

}
