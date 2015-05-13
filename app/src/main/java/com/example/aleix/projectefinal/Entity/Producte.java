package com.example.aleix.projectefinal.Entity;

/**
 * Created by Michal on 13/05/2015.
 */
public class Producte {

    private int Id;
    private String Nom;
    private double Preu;
    private double Descompte;
    private String Imatge;
    private boolean Habilitat;
    private int CategoriaId;

    public Producte() {

    }

    public Producte(int id, String nom, double preu, double descompte, String imatge, boolean habilitat, int categoriaId) {
        Id = id;
        Nom = nom;
        Preu = preu;
        Descompte = descompte;
        Imatge = imatge;
        Habilitat = habilitat;
        CategoriaId = categoriaId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public int getCategoriaId() {
        return CategoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        CategoriaId = categoriaId;
    }
}
