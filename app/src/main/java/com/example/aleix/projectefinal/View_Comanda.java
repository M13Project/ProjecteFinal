package com.example.aleix.projectefinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aleix.projectefinal.Controller.LocalPersistanceManager;
import com.example.aleix.projectefinal.Entity.Client;
import com.example.aleix.projectefinal.Entity.Comanda;
import com.example.aleix.projectefinal.Entity.Comanda_Producte;
import com.example.aleix.projectefinal.Entity.Producte;
import com.example.aleix.projectefinal.Proves.CustomListViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class View_Comanda extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    Button addProducte, finishComanda;
    ListView llista;
    TextView numProductes, total;

    LocalPersistanceManager lpm = null;
    List<Comanda_Producte> cp = null;

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

        int idClient = this.getIntent().getExtras().getInt("Client id");
        Toast.makeText(this, "Id Client: " + idClient, Toast.LENGTH_SHORT).show();
        /*
        Client client = new Client(999, "47852961S", "Josep", "Marti", 53, "ruta/imatge.png", "1/7/2015", 111);
        Comanda coma = new Comanda(true, "11/6/2015", client);
        Producte prod = new Producte(999, "nom producte", 100.00, 5, "ruta/imatge.png", true);
        productesComanda= new ArrayList<Comanda_Producte>();
        productesComanda.add(new Comanda_Producte(999, coma, prod, 10));
        productesComanda.add(new Comanda_Producte(999, coma, prod, 3));
        */

        /*
        productesList = selectProductes();
        comandesList = selectComandes();
        productesComanda= new ArrayList<Comanda_Producte>();
        productesComanda.add(new Comanda_Producte(999, comandesList.get(0), productesList.get(0), 10));
        */


        lpm = new LocalPersistanceManager(this, "m13_project", 1);
        cp = lpm.getAllEntities(Comanda_Producte.class);

 /*
        List<Object> list = new ArrayList<>();

        list.add(cp.get(0).getProducteId().getImatge());
        list.add(cp.getProducteId().getNom());
        list.add(cp.getProducteId().getPreu());
        list.add(cp.getQuantitat());

        List <List> productesComanda = new ArrayList<>();
        productesComanda.add(list);
*/
        CustomListViewAdapter adapter = new CustomListViewAdapter(this, cp);
        llista.setAdapter(adapter);
        llista.setOnItemClickListener(this);

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Al hacer click sobre uno de los items del ListView mostramos los
        // datos en los TextView.
        Toast.makeText(this, "posicio: " + position, Toast.LENGTH_LONG).show();
    }

    public List selectProductes() {
        LocalPersistanceManager lpm = new LocalPersistanceManager(this, "m13_project", 1);
        List productesList = lpm.getAllEntities(Producte.class);
        return productesList;
    }

    public List selectComandes() {
        LocalPersistanceManager lpm = new LocalPersistanceManager(this, "m13_project", 1);
        List comandesList = lpm.getAllEntities(Comanda.class);
        return comandesList;
    }
}
