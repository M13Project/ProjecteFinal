package com.example.aleix.projectefinal.Entity;

import android.text.method.DateTimeKeyListener;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.types.DateTimeType;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Client {

    //Variables constants que contenen el nom de les columnes de la BD
    public static final String ID = "_id";
    public static final String DNI = "Dni";
    public static final String NOM = "Nom";
    public static final String COGNOM = "COGNOM";
    public static final String EDAT = "Edat";
    public static final String IMAGE_CLIENT = "imageClient";
    public static final String DATA_PROPERA_VISITA = "DataProperaVisita";
    //public static final String COMERCIALID = "ComercialId";

    /*
    generatedId indica que la id es genera automaticament
    generateIdSequence para que se autogenere mediante secuencia de base de datos
    id si volem indicar nosaltres la id
     */
    @DatabaseField(generatedId = true, columnName = ID)
    private int Id;

    @DatabaseField(columnName = DNI)
    private String Dni;

    @DatabaseField(columnName = NOM)
    private String Nom;

    @DatabaseField(columnName = COGNOM)
    private String Cognom;

    @DatabaseField(columnName = EDAT)
    private int Edat;

    @DatabaseField(columnName = IMAGE_CLIENT)
    private String imageClient;

    @DatabaseField(columnName = DATA_PROPERA_VISITA)
    private DateTimeType DataProperaVisita;
/*
    @DatabaseField(foreign = true, columnName = COMERCIALID)
    private int ComercialId;
*/

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String dni) {
        Dni = dni;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getCognom() {
        return Cognom;
    }

    public void setCognom(String cognom) {
        Cognom = cognom;
    }

    public int getEdat() {
        return Edat;
    }

    public void setEdat(int edat) {
        Edat = edat;
    }

    public String getImageClient() {
        return imageClient;
    }

    public void setImageClient(String imageClient) {
        this.imageClient = imageClient;
    }

    public DateTimeType getDataProperaVisita() {
        return DataProperaVisita;
    }

    public void setDataProperaVisita(DateTimeType dataProperaVisita) {
        DataProperaVisita = dataProperaVisita;
    }
}
