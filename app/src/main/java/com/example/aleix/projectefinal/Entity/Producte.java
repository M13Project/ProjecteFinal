package com.example.aleix.projectefinal.Entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Aleix on 12/05/2015. ProjecteFinal
 */
@DatabaseTable(tableName = "Producte")
public class Producte {
    @DatabaseField(id = true)
    private int _id;
    @DatabaseField(columnName = "Nom", canBeNull = false)
    private String Nom;
    @DatabaseField(columnName = "Preu")
    private double Preu;
    @DatabaseField(columnName = "Descompte")
    private double Descompte;
    @DatabaseField(columnName = "Imatge")
    private String Imatge;
    @DatabaseField(columnName = "Habilitat", canBeNull = false)
    private boolean Habilitat;
    @DatabaseField(foreign = true, columnName = "CategoriaId")
    private Categoria CategoriaId;

    public Producte() {

    }

    public Producte(int _id, String nom, double preu, double descompte, String imatge, boolean habilitat) {
        this._id = _id;
        Nom = nom;
        Preu = preu;
        Descompte = descompte;
        Imatge = imatge;
        Habilitat = habilitat;
    }

    public Producte(int _id, String nom, double preu, double descompte, String imatge, boolean habilitat, Categoria categoria) {
        this._id = _id;
        Nom = nom;
        Preu = preu;
        Descompte = descompte;
        Imatge = imatge;
        Habilitat = habilitat;
        this.CategoriaId = categoria;
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

    public double getPreu() {
        return Preu;
    }

    public void setPreu(double preu) {
        Preu = preu;
    }

    public double getDescompte() {
        return Descompte;
    }

    public void setDescompte(double descompte) {
        Descompte = descompte;
    }

    public String getImatge() {
        return Imatge;
    }

    public void setImatge(String imatge) {
        Imatge = imatge;
    }

    public boolean getHabilitat() {
        return Habilitat;
    }

    public void setHabilitat(boolean habilitat) {
        Habilitat = habilitat;
    }

    public Categoria getCategoriaId() {
        return CategoriaId;
    }

    public void setCategoriaId(Categoria categoria) {
        this.CategoriaId = categoria;
    }

    @Override
    public String toString() {
        return "Id: " + this._id + ", Nom: " + this.Nom + ", Preu: " + this.Preu + ", Descompte: " + this.Descompte + ", Imatge: " + this.Imatge + ", Habilitat: " + this.Habilitat + ", CategoriaId: " + this.CategoriaId.getId();
    }
}