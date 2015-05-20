package com.example.aleix.projectefinal.Entity;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Aleix on 05/05/2015.
 */
@DatabaseTable(tableName = "Client")
public class Client implements Serializable{

    @DatabaseField(generatedId = true)
    private int _id;
    @DatabaseField(columnName = "Dni", canBeNull = false)
    private String Dni;
    @DatabaseField(columnName = "Nom", canBeNull = false)
    private String Nom;
    @DatabaseField(columnName = "Cognom", canBeNull = false)
    private String Cognom;
    @DatabaseField(columnName = "Edat")
    private int Edat;
    @DatabaseField(columnName = "ImageClient")
    private String ImageClient;
    @DatabaseField(columnName = "DataProperaVisita")
    private String DataProperaVisita;
    @ForeignCollectionField(eager = false)
    private ForeignCollection<Comanda> llistaDeComandes;


    public Client() {

    }

    public Client(String dni, String nom, String cognom, int edat, String imageClient, String dataProperaVisita) {
        Dni = dni;
        Nom = nom;
        Cognom = cognom;
        Edat = edat;
        ImageClient = imageClient;
        DataProperaVisita = dataProperaVisita;
    }

    public Client(int id, String dni, String nom, String cognom, int edat, String imageClient, String dataProperaVisita) {
        _id = id;
        Dni = dni;
        Nom = nom;
        Cognom = cognom;
        Edat = edat;
        ImageClient = imageClient;
        DataProperaVisita = dataProperaVisita;
    }

    public void addComanda(Comanda comanda) {
        this.llistaDeComandes.add(comanda);
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
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

    public String getDataProperaVisita() {
        return DataProperaVisita;
    }

    public void setDataProperaVisita(String dataProperaVisita) {
        DataProperaVisita = dataProperaVisita;
    }

    public ForeignCollection<Comanda> getLlistaDeComandesx() {
        return llistaDeComandes;
    }

    public void setLlistaDeComandesx(ForeignCollection<Comanda> llistaDeComandes) {
        this.llistaDeComandes = llistaDeComandes;
    }

    @Override
    public String toString() {
        return "Id: " + this._id + ", Dni: " + this.Dni + ", Nom: " + this.Nom + ", Cognom: " + this.Cognom + ", Edat: " + this.Edat + ", ImageClient: " + this.ImageClient + ", DataProperaVisita: " + this.DataProperaVisita;
    }
}
