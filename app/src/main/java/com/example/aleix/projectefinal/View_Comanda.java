package com.example.aleix.projectefinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


public class View_Comanda extends Activity implements View.OnClickListener {
    Button addProducte, finishComanda;
    ListView llista;
    TextView numProductes, total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__comanda);
        addProducte = (Button) findViewById(R.id.btnAfegirProducteComanda);
        finishComanda = (Button) findViewById(R.id.btnFinalitzarComanda);
        addProducte.setOnClickListener(this);
        finishComanda.setOnClickListener(this);
        llista = (ListView) findViewById(R.id.listViewComanda);
        numProductes = (TextView) findViewById(R.id.txtNumProductesComanda);
        total = (TextView) findViewById(R.id.txtTotalComanda);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view__comanda, menu);
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
        Intent a  = null ;
         switch (v.getId()){
             case R.id.btnAfegirProducteComanda:
                 a = new Intent(this, View_AddProducte.class);
                 break;
             case R.id.btnFinalitzarComanda:
                 a = new Intent(this, View_Clients.class);
                 break;
         }
        if (a!=null){
            startActivity(a);
        }
    }
}
