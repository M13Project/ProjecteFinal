package com.example.aleix.projectefinal.Entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Aleix on 06/05/2015.
 */
@DatabaseTable(tableName = "Comanda_Producte")
public class Comanda_Producte {

//    public static final String COMANDAID = "ComandaId";
//    public static final String PRODUCTEID = "ProducteId";
//    public static final String QUANTITAT = "Quantitat";

    @DatabaseField(generatedId = true)
    private int _id;

    @DatabaseField(foreign = true, columnName = "ComandaId")
    private Comanda ComandaId;

    @DatabaseField(foreign = true, columnName = "ProducteId")
    private Producte ProducteId;

    @DatabaseField(columnName = "Quantitat")
    private int Quantitat;

    public Comanda_Producte() {

    }

    public Comanda_Producte(int _id, Comanda comanda, Producte producte, int quantitat) {
        this._id = _id;
        ComandaId = comanda;
        this.ProducteId = producte;
        Quantitat = quantitat;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public Comanda getComandaId() {
        return ComandaId;
    }

    public void setComandaId(Comanda comanda) {
        ComandaId = comanda;
    }

    public Producte getProducteId() {
        return ProducteId;
    }

    public void setProducteId(Producte producte) {
        this.ProducteId = producte;
    }

    public int getQuantitat() {
        return Quantitat;
    }

    public void setQuantitat(int quantitat) {
        Quantitat = quantitat;
    }

    @Override
    public String toString() {
        return "Id: " + this._id + ", ComandaId: " + this.ComandaId.getId() + ", ProducteId: " + this.ProducteId.getId() + ", Quantitat: " + this.Quantitat;
    }
}
