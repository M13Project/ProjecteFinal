package com.example.aleix.projectefinal.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aleix.projectefinal.Entity.Producte;
import com.example.aleix.projectefinal.R;

import java.util.List;

/**
 * Created by d_roman on 19/05/2015.
 */
public class CustomSpinnerAdapter extends ArrayAdapter {

        private Context context;
        private List<Producte> dades;

        public CustomSpinnerAdapter(Context context, List<Producte> dades) {
            super(context, R.layout.details_spinner_producte, dades);
            this.context=context;
            this.dades = dades;
        }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        Holder holder;

        if (item == null) {
            item = LayoutInflater.from(context).inflate(R.layout.details_spinner_producte, null);
            holder = new Holder();
            holder.ivProducte = (ImageView) item.findViewById(R.id.ivProducte);
            holder.tvNomProducte  = (TextView) item.findViewById(R.id.tvNomProducte);
            holder.tvPreuProducte  = (TextView) item.findViewById(R.id.tvPreuProducte);
            holder.tvDescProducte  = (TextView) item.findViewById(R.id.tvDescProducte);
            holder.cbHabilitat = (CheckBox) item.findViewById(R.id.cbHabilitat);
            item.setTag(holder);
        }
        holder = (Holder) item.getTag();

        holder.ivProducte.setImageResource(R.mipmap.ic_launcher);
        holder.tvNomProducte.setText(dades.get(position).getNom());
        holder.tvPreuProducte.setText(Double.toString(dades.get(position).getPreu()));
        holder.tvDescProducte.setText(Double.toString(dades.get(position).getDescompte()));
        holder.cbHabilitat.setChecked(dades.get(position).getHabilitat());

        return item;
    }
}

