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
import java.util.Iterator;
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
        Bundle bundle = getIntent().getExtras();
        lpm = new LocalPersistanceManager(this, "m13_project", 2);

        usuari = (Usuari) getIntent().getExtras().get("Usuari");
        if (bundle != null){
            Client c = (Client) bundle.get("Client");

            if (c !=null){
            nom.setText(c.getNom());
            cognom.setText(c.getCognom());
            edat.setText(Integer.toString(c.getEdat()));
                tel.setText("");
                mobil.setText("");
                email.setText("");
                altres.setText("");
            DNI.setText(c.getDni());
            List<Localitzacio> loc = lpm.getAllEntities(Localitzacio.class);
                Iterator<Localitzacio> i = loc.iterator();
                while (i.hasNext()){
                    Localitzacio l1 = i.next();
                    if (l1.getClientId().equals(c)){
                        localitzacio = l1;
                    }

                }
                carrer.setText(localitzacio.getDireccio());
                Poblacio.setText("");
                cp.setText(localitzacio.getCodiPostal());



            add.setText("Guardar Canvis");}
        }



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
        if (v.getId()==R.id.btnAddClient){
            if (!DNI.getText().toString().equalsIgnoreCase("") &&!nom.getText().toString().equalsIgnoreCase("") &&
                    !cognom.getText().toString().equalsIgnoreCase("") && !tel.getText().toString().equalsIgnoreCase("") &&
                    !mobil.getText().toString().equalsIgnoreCase("") && !email.getText().toString().equalsIgnoreCase("") &&
                    !carrer.getText().toString().equalsIgnoreCase("") && !Poblacio.getText().toString().equalsIgnoreCase("") &&
                    !cp.getText().toString().equalsIgnoreCase("") && !altres.getText().toString().equalsIgnoreCase("")){
                client = new Client( DNI.getText().toString(), nom.getText().toString(), cognom.getText().toString() , Integer.parseInt(edat.getText().toString()), "/image.png", "FALTA POSAR LA DATA!!");
                Geocoder geo = new Geocoder(this, Locale.getDefault());
                List<Address> adreça = null;
                try {
                    adreça = geo.getFromLocationName(carrer.getText().toString()+", "+ cp.getText().toString()+", "+ Poblacio.getText().toString(),2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(adreça.size()>1) {
                    localitzacio = new Localitzacio(cp.getText().toString(), carrer.getText().toString(), Poblacio.getText().toString(), adreça.get(0).getLatitude(), adreça.get(0).getLongitude(), client);
                }
                else{
                    localitzacio = new Localitzacio(cp.getText().toString(), carrer.getText().toString(), Poblacio.getText().toString(), client);
                }
                if (add.getText().toString().equalsIgnoreCase("ADD")){
                lpm.insert(Client.class, client);
                lpm.insert(Localitzacio.class, localitzacio);
                Toast.makeText(this, "Client afegit", Toast.LENGTH_SHORT).show();}
                if (add.getText().toString().equalsIgnoreCase("Guardar Canvis")){
                    
                    Toast.makeText(this, "Client Actualitzat", Toast.LENGTH_SHORT).show();

                }
                Intent a = new Intent(this, Main_View.class);
                a.putExtra("User", usuari);
                startActivity(a);
            }
        else{
                Toast.makeText(this, "Hi han camps buits!", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
