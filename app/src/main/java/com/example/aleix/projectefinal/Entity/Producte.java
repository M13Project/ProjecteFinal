package com.example.aleix.projectefinal.Entity;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Michal.hostienda on 14/05/2015.
 */
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
    @DatabaseField(canBeNull = false, foreign = true)
    private Categoria categoria;

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
        this.categoria = categoria;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
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

    public boolean isHabilitat() {
        return Habilitat;
    }

    public void setHabilitat(boolean habilitat) {
        Habilitat = habilitat;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
