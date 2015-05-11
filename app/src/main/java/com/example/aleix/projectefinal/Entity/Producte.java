package com.example.aleix.projectefinal.Entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by d_roman on 11/05/2015.
 */

@DatabaseTable
public class Producte {

    public static final String ID = "_id";
    public static final String NOM = "Nom";
    public static final String PREU = "Preu";
    public static final String DESCOMPTE = "Descompte";
    public static final String IMATGE = "Imatge";
    public static final String HABILITAT = "Habilitat";
    public static final String CATEGORIAID = "CategoriaId";

    @DatabaseField(generatedId = true, columnName = ID)
    private int Id;

    @DatabaseField(columnName = NOM)
    private String Nom;

    @DatabaseField(columnName = PREU)
    private float Preu;

    @DatabaseField(columnName = DESCOMPTE)
    private float Descompte;

    @DatabaseField(columnName = IMATGE)
    private String Imatge;

    @DatabaseField(columnName = HABILITAT)
    private boolean Habilitat;

    @DatabaseField(columnName = CATEGORIAID)
    private int CategoriaId;

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

    public float getPreu() {
        return Preu;
    }

    public void setPreu(float preu) {
        Preu = preu;
    }

    public float getDescompte() {
        return Descompte;
    }

    public void setDescompte(float descompte) {
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
