package com.example.aleix.projectefinal.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.aleix.projectefinal.Entity.Comanda;
import com.example.aleix.projectefinal.R;

import java.util.List;

/**
 * Created by d_roman on 19/05/2015.
 */
public class CustomComandaAdapter extends ArrayAdapter {
    private Context context;
    private List<Comanda> dades;

    public CustomComandaAdapter(Context context, List<Comanda> dades) {
        super(context, R.layout.details_list_comanda, dades);
        this.context = context;
        this.dades = dades;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        Holder holder;

        if (item == null) {
            item = LayoutInflater.from(context).inflate(R.layout.details_list_comanda, null);
            holder = new Holder();
            holder.cbDelived  = (CheckBox) item.findViewById(R.id.cbDelivered);
            holder.tvDate  = (TextView) item.findViewById(R.id.tvDate);
            item.setTag(holder);
        }
        holder = (Holder) item.getTag();

        holder.cbDelived.setChecked(!dades.get(position).getLliurada());
        holder.tvDate.setText(dades.get(position).getData());

        return item;
    }
}
