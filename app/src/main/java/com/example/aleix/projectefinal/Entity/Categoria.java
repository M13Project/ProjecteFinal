package com.example.aleix.projectefinal.Entity;

/**
 * Created by Michal on 13/05/2015.
 */
public class Categoria {

    private int Id;
    private String Nom;
    private double Descompte;

    public Categoria() {

    }

    public Categoria(int id, String nom, double descompte) {
        Id = id;
        Nom = nom;
        Descompte = descompte;
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

    public double getDescompte() {
        return Descompte;
    }

    public void setDescompte(double descompte) {
        Descompte = descompte;
    }
}
