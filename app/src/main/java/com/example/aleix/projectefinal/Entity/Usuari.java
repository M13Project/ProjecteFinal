package com.example.aleix.projectefinal.Entity;

/**
 * Created by Michal on 13/05/2015.
 */
public class Usuari {

    private int Id;
    private String Dni;
    private String Nom;
    private String Cognom;
    private String Usuari1;
    private String Contrasenya;
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
