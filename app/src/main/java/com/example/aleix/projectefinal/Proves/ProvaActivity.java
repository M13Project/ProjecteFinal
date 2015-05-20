package com.example.aleix.projectefinal.Proves;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.aleix.projectefinal.Controller.LocalPersistanceManager;
import com.example.aleix.projectefinal.Controller.PersistanceManager;
import com.example.aleix.projectefinal.Controller.SynchronizeController;
import com.example.aleix.projectefinal.Entity.Categoria;
import com.example.aleix.projectefinal.Entity.Client;
import com.example.aleix.projectefinal.Entity.Comanda;
import com.example.aleix.projectefinal.Entity.Localitzacio;
import com.example.aleix.projectefinal.R;

public class ProvaActivity extends ActionBarActivity implements View.OnClickListener {

    private Button button;
    private EditText etQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prova);
        button = (Button) findViewById(R.id.buttonJson);
        button.setOnClickListener(this);
        etQuery = (EditText) findViewById(R.id.editTextQuery);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        PersistanceManager async = new PersistanceManager(this);
        /*Prova GET*/
//        try {
//            Class classRetrieved = Class.forName("com.example.aleix.projectefinal.Entity." + etQuery.getText().toString());
//            List objectsRetrieved = async.getListOfObjectsFromServer(classRetrieved);
//            LogAndToastMaker.makeInfoLog(objectsRetrieved.get(0).toString());
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        /**/
        /*Prova POST*/
//        Categoria categoria = new Categoria(1, "ExampleCategoryLalalalalal", 12);
//        Producte producte = new Producte(20, "poipoipipooip", 40, 4, "image.png", true);
//        producte.setCategoriaId(categoria);
//        String response = async.sendAnObjectToServer(Producte.class, producte);
//        LogAndToastMaker.makeInfoLog(response);
        /**/

        /*Prova PUT*/
//        Categoria categoria = new Categoria(19, "UpdateRealitzatCorrectament2", 111);
//        async.updateAnObjectFromServer(Categoria.class, categoria);
//        Client client = new Client(1, "X435345", "Dawid", "drgdfgds", 26, "/image.png", "2015-05-13T00:00:00", 1);
//        async.updateAnObjectFromServer(Client.class, client);
        /**/

        /*Prova DELETE*/
//        async.deleteAnObjectFromServer(Categoria.class, 19);
        /**/

        /**/
//        Categoria categoria = new Categoria(1, "categoriaExemple", 10);
//        Client client = new Client(40, "X435345", "Michal", "Krysiak", 26, "/image.png", "2015-05-13T00:00:00", 1);
//        Comanda comanda = new Comanda(true, "2015-05-13T00:00:00", client);
//        Localitzacio localitzacio = new Localitzacio(45657, "exempleDireccio", 423.23, 2343.23, client);
//        Producte producte = new Producte(23, "exempleProducte", 50, 10, "image.png", true, categoria);
//        Comanda_Producte cp = new Comanda_Producte(45, comanda, producte, 10);
//        String asd = MyJasonEntityConverter.getJsonObjectFromEntity(Comanda_Producte.class, cp);
//        LogAndToastMaker.makeInfoLog(asd);
        /**/

//        LocalPersistanceManager lpm = new LocalPersistanceManager(this, "m13_project", 1);
//        lpm.delete(Localitzacio.class, 1);
        SynchronizeController sc = new SynchronizeController(this);
        sc.uploadEntities();

//        async.deleteAnObjectFromServer(Localitzacio.class, 1);

    }
}
