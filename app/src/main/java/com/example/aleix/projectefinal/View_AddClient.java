package com.example.aleix.projectefinal;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aleix.projectefinal.Controller.LocalPersistanceManager;
import com.example.aleix.projectefinal.Entity.Client;
import com.example.aleix.projectefinal.Entity.Localitzacio;
import com.example.aleix.projectefinal.Entity.Usuari;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class View_AddClient extends Activity implements View.OnClickListener {
    Button add;
    EditText nom,cognom, tel, mobil, email, carrer, Poblacio,cp, altres, DNI, edat;
    Client client ;
    Localitzacio localitzacio ;
    LocalPersistanceManager lpm;
    Usuari usuari;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__add_client);
        add = (Button) findViewById(R.id.btnAddClient);
        add.setOnClickListener(this);
        DNI = (EditText) findViewById(R.id.txtDNIAddClient);
        edat = (EditText) findViewById(R.id.txtEdatAddClient);
        nom = (EditText) findViewById(R.id.txtNomAddClient);
        cognom = (EditText) findViewById(R.id.txtCognomAddClient);
        tel = (EditText) findViewById(R.id.txtTelAddClient);
        mobil = (EditText) findViewById(R.id.txtMobilAddClient);
        email = (EditText) findViewById(R.id.txtEmailAddClient);
        carrer = (EditText) findViewById(R.id.txtCarrerAddClient);
        Poblacio = (EditText) findViewById(R.id.txtPoblacioAddClient);
        cp = (EditText) findViewById(R.id.txtCPAddClient);
        altres = (EditText) findViewById(R.id.txtAltresAddClient);
        lpm = new LocalPersistanceManager(this, "m13_project", 1);
        usuari = (Usuari) getIntent().getExtras().get("Usuari");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view__add_client, menu);
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
    public void onClick(View v) {
            if (!DNI.getText().toString().equalsIgnoreCase("") &&!nom.getText().toString().equalsIgnoreCase("") &&
                    !cognom.getText().toString().equalsIgnoreCase("") && !tel.getText().toString().equalsIgnoreCase("") &&
                    !mobil.getText().toString().equalsIgnoreCase("") && !email.getText().toString().equalsIgnoreCase("") &&
                    !carrer.getText().toString().equalsIgnoreCase("") && !Poblacio.getText().toString().equalsIgnoreCase("") &&
                    !cp.getText().toString().equalsIgnoreCase("") && !altres.getText().toString().equalsIgnoreCase("")){
                client = new Client( DNI.getText().toString(), nom.getText().toString(), cognom.getText().toString() , Integer.parseInt(edat.getText().toString()), "/image.png",  usuari.getId());
                Geocoder geo = new Geocoder(this, Locale.getDefault());
                List<Address> adreça = null;
                try {
                    adreça = geo.getFromLocationName(carrer.getText().toString()+", "+ cp.getText().toString()+", "+ Poblacio.getText().toString(),1);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                localitzacio = new Localitzacio(cp.getText().toString(), carrer.getText().toString() , adreça.get(0).getLatitude(), adreça.get(0).getLongitude(), client);

                lpm.insert(Client.class, client);
                lpm.insert(Localitzacio.class, localitzacio);
                Toast.makeText(this, "Client afegit", Toast.LENGTH_SHORT).show();
                Intent a = new Intent(this, Main_View.class);
                startActivity(a);
            }
        else{
                Toast.makeText(this, "Hi han camps buits!", Toast.LENGTH_SHORT).show();
            }
    }
}
