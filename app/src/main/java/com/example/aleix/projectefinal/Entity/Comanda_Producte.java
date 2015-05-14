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

    @DatabaseField(generatedId = true)
    private int _id;

    @DatabaseField(foreign = true, columnName = COMANDAID)
    private Comanda Comanda;

    @DatabaseField(foreign = true, columnName = PRODUCTEID)
    private Producte Producte;

    @DatabaseField(columnName = QUANTITAT)
    private int Quantitat;

    public Comanda_Producte() {

    }

    public Comanda_Producte(int _id, Comanda comanda, Producte producte, int quantitat) {
        this._id = _id;
        Comanda = comanda;
        this.Producte = producte;
        Quantitat = quantitat;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public Comanda getComandaId() {
        return Comanda;
    }

    public void setComandaId(Comanda comanda) {
        Comanda = comanda;
    }

    public Producte getProducteId() {
        return Producte;
    }

    public void setProducteId(Producte producte) {
        this.Producte = producte;
    }

    public int getQuantitat() {
        return Quantitat;
    }

    public void setQuantitat(int quantitat) {
        Quantitat = quantitat;
    }
}
