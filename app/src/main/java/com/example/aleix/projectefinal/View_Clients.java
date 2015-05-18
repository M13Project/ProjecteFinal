package com.example.aleix.projectefinal;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.aleix.projectefinal.Controller.LocalPersistanceManager;
import com.example.aleix.projectefinal.Entity.Client;
import com.example.aleix.projectefinal.Entity.Localitzacio;


public class View_Clients extends Activity implements View.OnClickListener {
    ImageButton imgbtnTrucarTel, imgbtnTrucalMobil, imgbtnUltimaComanda;
    TextView txtNom, txtCognom, txtCarrer, txtTel, txtMobil, txtUltimaComanda, txtEdat;
    Client client;
    Localitzacio loc;
    LocalPersistanceManager lpm ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__clients);
        imgbtnTrucalMobil = (ImageButton) findViewById(R.id.imgbtnTrucarMobil);
        imgbtnTrucarTel = (ImageButton) findViewById(R.id.imgbtntrucarTel);
        imgbtnUltimaComanda = (ImageButton) findViewById(R.id.imgbtnUltimaCompra);
        imgbtnTrucalMobil.setOnClickListener(this);
        imgbtnTrucarTel.setOnClickListener(this);
        imgbtnUltimaComanda.setOnClickListener(this);
        txtNom = (TextView) findViewById(R.id.txtNomClient);
        txtCognom = (TextView) findViewById(R.id.txtCognomClient);
        txtCarrer = (TextView) findViewById(R.id.txtCarrerClient);
        txtTel = (TextView) findViewById(R.id.txtTelClient);
        txtMobil = (TextView) findViewById(R.id.txtMobilClient);
        txtUltimaComanda = (TextView) findViewById(R.id.txtUltimaComandaClient);
        txtEdat = (TextView) findViewById(R.id.txtEdatClient);
        Bundle bundle = getIntent().getExtras();
        //***//
        client = (Client) bundle.get("Client");
        txtNom.setText(client.getNom());
        txtCognom.setText(client.getCognom());
        txtTel.setText("");
        txtCarrer.setText("");



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view__clients, menu);
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
        if (id == R.id.novaComanda){
            Intent a = new Intent(this, View_Comanda.class);
            startActivity(a);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent a = null;
        switch (v.getId()){
            case R.id.imgbtnTrucarMobil:
                a =  new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + txtMobil.getText().toString()));
                break;
            case R.id.imgbtntrucarTel:
                a = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + txtTel.getText().toString()));
                break;
            case R.id.imgbtnUltimaCompra:
                a = new Intent(this, View_Comanda.class);
                break;
        }
        if (a != null){
            startActivity(a);

        }
    }
}
