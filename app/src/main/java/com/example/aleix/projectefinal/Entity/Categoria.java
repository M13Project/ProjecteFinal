package com.example.aleix.projectefinal.Entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Aleix on 12/05/2015. ProjecteFinal
 */
@DatabaseTable
public class Categoria {
    public static final String ID = "_id";
    public static final String NOM = "Nom";
    public static final String DESCOMPTE = "Descompte";

    @DatabaseField(generatedId = true, columnName = ID)
    private int Id;

    @DatabaseField(columnName = NOM)
    private String Nom;

    @DatabaseField(columnName = DESCOMPTE)
    private float Descompte;

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

    public float getDescompte() {
        return Descompte;
    }

    public void setDescompte(float descompte) {
        Descompte = descompte;
    }
}
