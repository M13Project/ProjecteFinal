package com.example.aleix.projectefinal.Entity;

import java.util.Date;

/**
 * Created by Aleix on 05/05/2015.
 */
public class Client {

    private int Id;
    private String Dni;
    private String Nom;
    private String Cognom;
    private int Edat;
    private String ImageClient;
    private Date DataProperaVisita;
    private int ComercialId;

    public Client() {

    }

    public Client(int id, String dni, String nom, String cognom, int edat, String imageClient, Date dataProperaVisita, int comercialId) {
        Id = id;
        Dni = dni;
        Nom = nom;
        Cognom = cognom;
        Edat = edat;
        ImageClient = imageClient;
        DataProperaVisita = dataProperaVisita;
        ComercialId = comercialId;
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

    public int getEdat() {
        return Edat;
    }

    public void setEdat(int edat) {
        Edat = edat;
    }

    public String getImageClient() {
        return ImageClient;
    }

    public void setImageClient(String imageClient) {
        ImageClient = imageClient;
    }

    public Date getDataProperaVisita() {
        return DataProperaVisita;
    }

    public void setDataProperaVisita(Date dataProperaVisita) {
        DataProperaVisita = dataProperaVisita;
    }

    public int getComercialId() {
        return ComercialId;
    }

    public void setComercialId(int comercialId) {
        ComercialId = comercialId;
    }
}
