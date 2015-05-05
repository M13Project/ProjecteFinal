package com.example.aleix.projectefinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class View_AddClient extends Activity implements View.OnClickListener {
    Button add;
    EditText nom,cognom, tel, mobil, email, carrer, Poblacio,cp, altres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__add_client);
        add = (Button) findViewById(R.id.btnAddClient);
        add.setOnClickListener(this);
        nom = (EditText) findViewById(R.id.txtNomAddClient);
        cognom = (EditText) findViewById(R.id.txtCognomAddClient);
        tel = (EditText) findViewById(R.id.txtTelAddClient);
        mobil = (EditText) findViewById(R.id.txtMobilAddClient);
        email = (EditText) findViewById(R.id.txtEmailAddClient);
        carrer = (EditText) findViewById(R.id.txtCarrerAddClient);
        Poblacio = (EditText) findViewById(R.id.txtPoblacioAddClient);
        cp = (EditText) findViewById(R.id.txtCPAddClient);
        altres = (EditText) findViewById(R.id.txtAltresAddClient);

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
            if (nom.getText().toString()!=null && cognom.getText().toString()!=null && tel.getText().toString()!=null && mobil.getText().toString()!=null && email.getText().toString()!=null && carrer.getText().toString()!=null && Poblacio.getText().toString()!=null && cp.getText().toString()!=null && altres.getText().toString()!=null){
                Toast.makeText(this, "Client afegit", Toast.LENGTH_SHORT).show();
                Intent a = new Intent(this, Main_View.class);
                startActivity(a);
                startActivity(a);
            }
        else{
                Toast.makeText(this, "Hi han camps buits!", Toast.LENGTH_SHORT).show();
            }
    }
}
