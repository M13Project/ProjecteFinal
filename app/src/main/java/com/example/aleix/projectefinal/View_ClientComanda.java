package com.example.aleix.projectefinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.aleix.projectefinal.Controller.LocalPersistanceManager;
import com.example.aleix.projectefinal.Entity.Client;
import com.example.aleix.projectefinal.Entity.Comanda;
import com.example.aleix.projectefinal.Proves.CustomComandaAdapter;

import java.util.List;


public class View_ClientComanda extends Activity implements AdapterView.OnItemClickListener{
    Intent intent = null;

    ListView lvComandes;
    TextView tvLliurada, tvData, tvNomComanda;

    LocalPersistanceManager lpm = null;
    List<Comanda> comandaList = null;
    Client client = null;
    int idClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__client_comanda);

        tvNomComanda = (TextView) findViewById(R.id.tvNomClient);
        lvComandes = (ListView) findViewById(R.id.lvComandes);
        tvLliurada = (TextView) findViewById(R.id.tvDelivered);
        tvData = (TextView) findViewById(R.id.tvDate);

        lpm = new LocalPersistanceManager(this, "m13_project", 1);
        comandaList = lpm.getAllEntities(Comanda.class);

        tvNomComanda.setText(comandaList.get(0).getClientId().getNom());
        idClient = comandaList.get(0).getClientId().getId();

        CustomComandaAdapter adapter = new CustomComandaAdapter(this, comandaList);
        lvComandes.setAdapter(adapter);
        lvComandes.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view__client_comanda, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        intent = new Intent(this,View_Comanda.class );
        intent.putExtra("Client id", idClient);
        this.startActivity(intent);
    }

}
