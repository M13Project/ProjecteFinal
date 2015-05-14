package com.example.aleix.projectefinal.Entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Aleix on 12/05/2015. ProjecteFinal
 */
@DatabaseTable(tableName = "Categoria")
public class Categoria {

    @DatabaseField(id = true)
    private int _id;
    @DatabaseField(columnName = "Nom", canBeNull = false)
    private String Nom;
    @DatabaseField(columnName = "Descompte")
    private double Descompte;

    public Categoria() {

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

    public int get_id() {
        return _id;
    }

    private void set_id(int _id) {
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
}
