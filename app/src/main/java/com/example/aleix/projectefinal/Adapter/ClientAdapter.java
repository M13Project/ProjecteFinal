package com.example.aleix.projectefinal.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.aleix.projectefinal.Entity.Client;
import com.example.aleix.projectefinal.R;

import java.util.List;

/**
 * Created by Aleix on 18/05/2015. ProjecteFinal
 */
public class ClientAdapter extends BaseAdapter {
    private Activity context;
    //private Cursor dades;
    private List<Client> dades;

    public ClientAdapter(Activity context, List<Client> dades) {
        super();
        this.context = context;
        this.dades = dades;
    }


    @Override
    public int getCount() {
        return dades.size();
        //return dades.getCount();
    }

    @Override
    public Client getItem(int position) {
        Client c= new Client();
        if(dades.get(position)!=null) {
            c.setId(dades.get(position).getId());
            c.setDni(dades.get(position).getDni());
            c.setNom(dades.get(position).getNom());
            c.setCognom(dades.get(position).getCognom());
            c.setEdat(dades.get(position).getEdat());
            c.setImageClient(dades.get(position).getImageClient());
            c.setDataProperaVisita(dades.get(position).getDataProperaVisita());
            //c.setComercialId(dades.get(position).getComercialId());
            //c.setLlistaDeComandes(dades.get(position).getLlistaDeComandes());


        }/*if(dades.moveToPosition(position)) {
            c.setId(dades.getInt(0));
            c.setDni(dades.getString(1));
            c.setNom(dades.getString(2));
            c.setCognom(dades.getString(3));
            c.setEdat(dades.getInt(4));
            c.setImageClient(dades.getString(5));
            c.setDataProperaVisita(dades.getString(6));
            c.setComercialId(dades.getInt(7));
            //c.setLlistaDeComandes(dades.getBlob(8));


        }*/
        return c;
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View element = convertView;
        Client c =  getItem(position);
        if(element == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            element = inflater.inflate(R.layout.layout_clients2, null);

        }
        TextView lblnom = (TextView)element.findViewById(R.id.txtNomClientListView);
        lblnom.setText(c.getNom());

        TextView lblCognom = (TextView)element.findViewById(R.id.txtCognomClientListView);
        lblCognom.setText(c.getCognom());
        return element;
    }
}
