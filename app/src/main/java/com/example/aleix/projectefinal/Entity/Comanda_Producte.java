package com.example.aleix.projectefinal.Entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Aleix on 06/05/2015.
 */
@DatabaseTable(tableName = "Comanda_Producte")
public class Comanda_Producte {

    public static final String COMANDAID = "ComandaId";
    public static final String PRODUCTEID = "ProducteId";
    public static final String QUANTITAT = "Quantitat";

    public Comanda_Producte() {

    }

    public Comanda_Producte(int _id, int comandaId, int producteId, int quantitat) {
        this._id = _id;
        ComandaId = comandaId;
        this.producteId = producteId;
        Quantitat = quantitat;
    }

    @DatabaseField(generatedId = true)
    private int _id;

    @DatabaseField(foreign = true, columnName = COMANDAID)
    private int ComandaId;

    @DatabaseField(foreign = true, columnName = PRODUCTEID)
    private int producteId;

    @DatabaseField(columnName = QUANTITAT)
    private int Quantitat;

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public int getComandaId() {
        return ComandaId;
    }

    public void setComandaId(int comandaId) {
        ComandaId = comandaId;
    }

    public int getProducteId() {
        return producteId;
    }

    public void setProducteId(int producteId) {
        this.producteId = producteId;
    }

    public int getQuantitat() {
        return Quantitat;
    }

    public void setQuantitat(int quantitat) {
        Quantitat = quantitat;
    }
}
