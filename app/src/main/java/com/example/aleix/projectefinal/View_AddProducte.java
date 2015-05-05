package com.example.aleix.projectefinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class View_AddProducte extends Activity implements View.OnClickListener {
    Spinner llistaProductes;
    EditText num;
    Button affegirproductealacomanda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__add_producte);
        llistaProductes = (Spinner) findViewById(R.id.spinnerElegirProducte);
        num = (EditText) findViewById(R.id.txtQuantitatProducte);
        affegirproductealacomanda = (Button) findViewById(R.id.btnAfegirComandaAddProducte);
        affegirproductealacomanda.setOnClickListener(this);
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
}
