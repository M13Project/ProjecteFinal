package com.example.aleix.projectefinal.Controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Aleix on 11/05/2015. ProjecteFinal
 */
public class BD extends SQLiteOpenHelper {
    public static String DB_PATH = "/data/data/com.example.aleix/databases/";
    public static String DB_NAME = "DB_Local";
    //private final Context myContext;
    public static int v_db = 0;
    String sqlCreate ="CREATE TABLE Clients (_id INTEGER PRIMARY KEY, Nom TEXT, Cognom TEXT, Edat INTEGER, DataProperaVisita Datetime); " +
            "CREATE TABLE Comanda (_id INTEGER PRIMARY KEY, LLiurada boolean, Data DateTime, ClientId INTEGER, FOREIGN KEY(ClientId) REFERENCES Clients(Id)); " +
            "CREATE TABLE Localitzacio (_id INTEGER PRIMARY KEY, CodiPostal TEXT, Direccio TEXT, Latitud DOUBLE, Longitud DOUBLE, ClientId INTEGER, FOREIGN KEY(ClientId) REFERENCES Clients(Id)); " +
            "CREATE TABLE Categoria (_id INTEGER PRIMARY KEY, Nom TEXT, Descompte DOUBLE); " +
            "CREATE TABLE Producte (_id INTEGER PRIMARY KEY, Nom TEXT, Descompte DOUBLE, Imatge TEXT, Habilitat TEXT, CategoriaId INTEGER, FOREIGN KEY(CategoriaId) REFERENCES Categoria(Id)); " +
            "CREATE TABLE Comanda_Producte(ComandaId INTEGER PRIMARY KEY, ProducteId INTEGER, Quantitat INTEGER , FOREIGN KEY(ProducteId) REFERENCES Producte(Id)); " +
            "";
    String sqlUpdate ="";


    public BD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        if (db.isReadOnly()){
            db = getWritableDatabase();
        }
        db.execSQL(sqlCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion>oldVersion){
            db.execSQL(sqlUpdate);
        }
    }


}
