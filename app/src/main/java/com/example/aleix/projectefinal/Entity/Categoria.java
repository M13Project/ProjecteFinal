package com.example.aleix.projectefinal.Entity;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Michal.hostienda on 14/05/2015.
 */
@DatabaseTable(tableName = "Categoria")
public class Categoria {

    @DatabaseField(id = true)
    private int _id;
    @DatabaseField(columnName = "Nom", canBeNull = false)
    private String Nom;
    @DatabaseField(columnName = "Descompte")
    private double Descompte;
    @ForeignCollectionField(eager = false)
    private ForeignCollection<Producte> llistaDeProductes;

    public Categoria() {

    }

    public void addProducte(Producte producte) {
        this.llistaDeProductes.add(producte);
    }

    public Categoria(String nom, double descompte) {
        Nom = nom;
        Descompte = descompte;
    }

    public Categoria(int _id, String nom, double descompte) {
        this._id = _id;
        Nom = nom;
        Descompte = descompte;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public double getDescompte() {
        return Descompte;
    }

    public void setDescompte(double descompte) {
        Descompte = descompte;
    }

    @Override
    public String toString() {
        return "Id: " + this._id + ", Nom: " + this.Nom + ", Descompte: " + this.Descompte;
    }
}
