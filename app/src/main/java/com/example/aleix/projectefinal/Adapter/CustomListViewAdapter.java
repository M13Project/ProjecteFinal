package com.example.aleix.projectefinal.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aleix.projectefinal.Entity.Comanda_Producte;
import com.example.aleix.projectefinal.R;

import java.util.List;

/**
 * Created by d_roman on 18/05/2015.
 */
public class CustomListViewAdapter extends ArrayAdapter {
    private Context context;
    private List<Comanda_Producte> dades;

    public CustomListViewAdapter(Context context, List<Comanda_Producte> dades) {
        super(context, R.layout.details_list_producte, dades);
        this.context = context;
        this.dades = dades;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        Holder holder;

        if (item == null) {
            item = LayoutInflater.from(context).inflate(R.layout.details_list_producte,null);
            holder = new Holder();
            holder.ivImatge = (ImageView) item.findViewById(R.id.ivImatge);
            holder.tvNom = (TextView) item.findViewById(R.id.tvNom);
            holder.tvPreu = (TextView) item.findViewById(R.id.tvPreu);
            holder.tvQnt = (TextView) item.findViewById(R.id.tvQnt);
            item.setTag(holder);
        }
        holder = (Holder) item.getTag();

        holder.ivImatge.setImageResource(R.mipmap.ic_launcher);
        holder.tvNom.setText(dades.get(position).getProducteId().getNom());
        holder.tvPreu.setText(Double.toString(dades.get(position).getProducteId().getPreu()));
        holder.tvQnt.setText(Integer.toString(dades.get(position).getQuantitat()));

        return item;
    }
}
