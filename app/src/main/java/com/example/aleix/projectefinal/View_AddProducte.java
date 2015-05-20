package com.example.aleix.projectefinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aleix.projectefinal.Controller.LocalPersistanceManager;
import com.example.aleix.projectefinal.Entity.Producte;
import com.example.aleix.projectefinal.Adapter.CustomSpinnerAdapter;

import java.util.List;


public class View_AddProducte extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    Spinner llistaProductes;
    EditText num;
    Button affegirproductealacomanda;
    List<Producte> productes = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__add_producte);
        llistaProductes = (Spinner) findViewById(R.id.spinnerElegirProducte);
        num = (EditText) findViewById(R.id.txtQuantitatProducte);
        affegirproductealacomanda = (Button) findViewById(R.id.btnAfegirComandaAddProducte);
        affegirproductealacomanda.setOnClickListener(this);

        productes = selectProductes();
        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(this, productes);
        llistaProductes.setAdapter(adapter);
        llistaProductes.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view__add_producte, menu);
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
        if (v.getId() == R.id.btnAfegirComandaAddProducte){
            Intent a = new Intent(this, View_Comanda.class);
            a.putExtra("Quantitat", num.getText().toString());
            a.putExtra("Producte", num.getText().toString());
            startActivity(a);
        }

    }

    public List selectProductes() {
        LocalPersistanceManager lpm = new LocalPersistanceManager(this, "m13_project", 1);
        List productesList = lpm.getAllEntities(Producte.class);
        return productesList;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "posicio: " + position, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
