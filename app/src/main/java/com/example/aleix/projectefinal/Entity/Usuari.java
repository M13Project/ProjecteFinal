package com.example.aleix.projectefinal.Entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Aleix on 14/05/2015. ProjecteFinal
 */
@DatabaseTable(tableName = "Usuari")
public class Usuari implements Serializable {
    @DatabaseField(id = true)
    private int Id;
    @DatabaseField(columnName = "Dni", canBeNull = false)
    private String Dni;
    @DatabaseField(columnName = "Nom", canBeNull = false)
    private String Nom;
    @DatabaseField(columnName = "Cognom")
    private String Cognom;
    @DatabaseField(columnName = "Usuari", canBeNull = false)
    private String Usuari1;
    @DatabaseField(columnName = "Contrasenya", canBeNull = false)
    private String Contrasenya;
    @DatabaseField(columnName = "Imatge")
    private String Imatge;

    public Usuari() {

    }

    public Usuari(int id, String dni, String nom, String cognom, String usuari1, String contrasenya, String imatge) {
        Id = id;
        Dni = dni;
        Nom = nom;
        Cognom = cognom;
        Usuari1 = usuari1;
        Contrasenya = contrasenya;
        Imatge = imatge;
    }
    public Usuari(String usuari1, String contrasenya){
        Usuari1 = usuari1;
        Contrasenya = contrasenya;
    }

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

    public String getUsuari1() {
        return Usuari1;
    }

    public void setUsuari1(String usuari1) {
        Usuari1 = usuari1;
    }

    public String getContrasenya() {
        return Contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        Contrasenya = contrasenya;
    }

    public String getImatge() {
        return Imatge;
    }

    public void setImatge(String imatge) {
        Imatge = imatge;
    }
}

