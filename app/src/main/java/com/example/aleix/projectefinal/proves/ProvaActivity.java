package com.example.aleix.projectefinal.proves;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.aleix.projectefinal.Controller.GlobalParameterController;
import com.example.aleix.projectefinal.Controller.LocalPersistanceManager;
import com.example.aleix.projectefinal.Controller.LogAndToastMaker;
import com.example.aleix.projectefinal.Controller.PersistanceManager;
import com.example.aleix.projectefinal.Controller.SynchronizeController;
import com.example.aleix.projectefinal.Entity.Categoria;
import com.example.aleix.projectefinal.Entity.Client;
import com.example.aleix.projectefinal.Entity.Comanda;
import com.example.aleix.projectefinal.Entity.Comanda_Producte;
import com.example.aleix.projectefinal.Entity.Localitzacio;
import com.example.aleix.projectefinal.Entity.Producte;
import com.example.aleix.projectefinal.R;

import org.joda.time.DateTime;

import java.util.List;

public class ProvaActivity extends ActionBarActivity implements View.OnClickListener {

    private Button button, button2;
    private EditText etQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prova);
        button = (Button) findViewById(R.id.buttonJson);
        button.setOnClickListener(this);

        button2 = (Button) findViewById(R.id.button2_prova);
        button2.setOnClickListener(this);

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
        SynchronizeController sc = new SynchronizeController(this);
        LocalPersistanceManager lpm = new LocalPersistanceManager(this, GlobalParameterController.DATABASE_NAME, GlobalParameterController.DATABASE_VERSION);
        if(v.getId() == R.id.button2_prova) {

            Client client1 = lpm.getEntity(Client.class, 1);
            client1.setNom("provaUpdate");
            client1.setCognom("provaCognomUpdate");
            lpm.update(Client.class, client1);

            Client client2 = lpm.getEntity(Client.class, 2);
            client2.setNom("provaUpdate");
            client2.setCognom("provaCognomUpdate");
            client2.setDni("prova");
            lpm.update(Client.class, client2);

            Comanda comanda = lpm.getEntity(Comanda.class, 1);
            comanda.setLliurada(true);
            lpm.update(Comanda.class, comanda);

            Localitzacio loca = lpm.getEntity(Localitzacio.class, 1);
            loca.setPoblacio("PobleProva");
            lpm.update(Localitzacio.class, loca);

            Comanda_Producte cp = lpm.getEntity(Comanda_Producte.class, 1);
            cp.setQuantitat(100000);
            lpm.update(Comanda_Producte.class, cp);

            lpm.delete(Localitzacio.class, 10);
            lpm.delete(Localitzacio.class, 9);
            lpm.delete(Localitzacio.class, 7);
            lpm.delete(Comanda_Producte.class, 16);

            sc.synchronizeData();
        } else {

            sc.synchronizeData();

            addDataToTheLocalDatabase();

            sc.synchronizeData();
        }

//        PersistanceManager async = new PersistanceManager(this);
//        Client client11 = new Client();
//        client11.setNom("ExempleNulls");
//        client11.setCognom("ExempleNulls");
//        client11.setDni("ExempleNulls");
//        client11.setId(52);
//        async.sendAnObjectToServer(Client.class, client11);

    }

    public void addDataToTheLocalDatabase() {
        LocalPersistanceManager lpm = new LocalPersistanceManager(this, GlobalParameterController.DATABASE_NAME, GlobalParameterController.DATABASE_VERSION);
                /*Base de dades*/

        Client client1 = new Client("78945612A", "José", "Martínez", 28, "/image.png", "2015-05-13T00:00:00");
        Client client2 = new Client("74185296B", "Marc", "Pérez", 21, "/image.png", "2015-05-13T00:00:00");
        Client client3 = new Client("32165445R", "Aleix", "Ventura", 21, "/image.png", "2015-05-13T00:00:00");
        Client client4 = new Client("85245612J", "David", "Román", 29, "/image.png", "2015-05-13T00:00:00");
        Client client5 = new Client("74186941Y", "Michal", "Krysiak", 26, "/image.png", "2015-05-13T00:00:00");
        Client client6 = new Client("25865763E", "Gabriel", "Monge", 27, "/image.png", "2015-05-13T00:00:00");
        Client client7 = new Client("35845695U", "Míriam", "Ponts", 23, "/image.png", "2015-05-13T00:00:00");
        Client client8 = new Client("43798516O", "Mónica", "Hernández", 27, "/image.png", "2015-05-13T00:00:00");
        Client client9 = new Client("37694123P", "Elena", "Martins", 33, "/image.png", "2015-05-13T00:00:00");
        Client client10 = new Client("84369878R", "Cristina", "Cavaller", 25, "/image.png", "2015-05-13T00:00:00");
        Client client11 = new Client();
        client11.setNom("ExempleNulls");
        client11.setCognom("ExempleNulls");
        client11.setDni("qwerew");

        Localitzacio localitzacio1 = new Localitzacio("08160", "Carrer de la diputació 5", "Montmelo", 423.23, 243.23, client1);
        Localitzacio localitzacio2 = new Localitzacio("85425", "Plaça de Catalunya 78", "Barcelona", 856.265, 494.23, client2);
        Localitzacio localitzacio3 = new Localitzacio("96874", "Carrer Girona 343", "Montornés del Vallés", 423.23, 2343.23, client3);
        Localitzacio localitzacio4 = new Localitzacio("36524", "Avinguda Diagonal 23", "Barcelona", 983.23, 65.23, client4);
        Localitzacio localitzacio5 = new Localitzacio("75369", "Passeig de Gràcia 74", "Barcelona", 723.23, 14.23, client5);
        Localitzacio localitzacio6 = new Localitzacio("95874", "Carrer Central 21", "Granollers", 33.23, 73.23, client6);
        Localitzacio localitzacio7 = new Localitzacio("12745", "Carrer Vic 5", "Granollers", 93.23, 4.23, client7);
        Localitzacio localitzacio8 = new Localitzacio("58736", "Carrer Ramón Llull 150", "Sabadell", 53.23, 53.23, client8);
        Localitzacio localitzacio9 = new Localitzacio("12898", "Avinguda Ordinadors interconnectats 40", "Lliçà d'Amunt", 6.23, 12.23, client9);
        Localitzacio localitzacio10 = new Localitzacio("36452", "Carrer Valencia 12", "Llinars 140", 63.23, 29.23, client10);

        Comanda comanda1 = new Comanda(false, DateTime.now().toString(), client1);
        Comanda comanda2 = new Comanda(false, DateTime.now().toString(), client2);
        Comanda comanda3 = new Comanda(true, DateTime.now().toString(), client3);
        Comanda comanda4 = new Comanda(false, DateTime.now().toString(), client4);
        Comanda comanda5 = new Comanda(false, DateTime.now().toString(), client5);
        Comanda comanda6 = new Comanda(true, DateTime.now().toString(), client6);
        Comanda comanda7 = new Comanda(false, DateTime.now().toString(), client7);
        Comanda comanda8 = new Comanda(false, DateTime.now().toString(), client8);
        Comanda comanda9 = new Comanda(false, DateTime.now().toString(), client9);
        Comanda comanda10 = new Comanda(false, DateTime.now().toString(), client10);

        Producte producte1 = lpm.getAllEntities(Producte.class).get(0);
        Producte producte2 = lpm.getAllEntities(Producte.class).get(1);
        Producte producte3 = lpm.getAllEntities(Producte.class).get(2);
        Producte producte4 = lpm.getAllEntities(Producte.class).get(3);
        Producte producte5 = lpm.getAllEntities(Producte.class).get(4);
        Producte producte6 = lpm.getAllEntities(Producte.class).get(5);
        Producte producte7 = lpm.getAllEntities(Producte.class).get(6);

        Comanda_Producte cp1 = new Comanda_Producte(comanda1, producte1, 10);
        Comanda_Producte cp2 = new Comanda_Producte(comanda2, producte2, 4);
        Comanda_Producte cp3 = new Comanda_Producte(comanda3, producte3, 2);
        Comanda_Producte cp4 = new Comanda_Producte(comanda4, producte4, 3);
        Comanda_Producte cp5 = new Comanda_Producte(comanda5, producte5, 9);
        Comanda_Producte cp6 = new Comanda_Producte(comanda6, producte6, 4);
        Comanda_Producte cp7 = new Comanda_Producte(comanda7, producte7, 5);
        Comanda_Producte cp8 = new Comanda_Producte(comanda7, producte5, 7);
        Comanda_Producte cp9 = new Comanda_Producte(comanda7, producte5, 3);
        Comanda_Producte cp10 = new Comanda_Producte(comanda2, producte5, 15);
        Comanda_Producte cp11 = new Comanda_Producte(comanda8, producte1, 2);
        Comanda_Producte cp12 = new Comanda_Producte(comanda8, producte1, 3);
        Comanda_Producte cp13 = new Comanda_Producte(comanda8, producte1, 6);
        Comanda_Producte cp14 = new Comanda_Producte(comanda9, producte5, 16);
        Comanda_Producte cp15 = new Comanda_Producte(comanda9, producte6, 86);
        Comanda_Producte cp16 = new Comanda_Producte(comanda10, producte6, 32);

        lpm.insert(Client.class, client1);
        lpm.insert(Client.class, client2);
        lpm.insert(Client.class, client3);
        lpm.insert(Client.class, client4);
        lpm.insert(Client.class, client5);
        lpm.insert(Client.class, client6);
        lpm.insert(Client.class, client7);
        lpm.insert(Client.class, client8);
        lpm.insert(Client.class, client9);
        lpm.insert(Client.class, client10);
        lpm.insert(Client.class, client11);

        lpm.insert(Localitzacio.class, localitzacio1);
        lpm.insert(Localitzacio.class, localitzacio2);
        lpm.insert(Localitzacio.class, localitzacio3);
        lpm.insert(Localitzacio.class, localitzacio4);
        lpm.insert(Localitzacio.class, localitzacio5);
        lpm.insert(Localitzacio.class, localitzacio6);
        lpm.insert(Localitzacio.class, localitzacio7);
        lpm.insert(Localitzacio.class, localitzacio8);
        lpm.insert(Localitzacio.class, localitzacio9);
        lpm.insert(Localitzacio.class, localitzacio10);

        lpm.insert(Comanda.class, comanda1);
        lpm.insert(Comanda.class, comanda2);
        lpm.insert(Comanda.class, comanda3);
        lpm.insert(Comanda.class, comanda4);
        lpm.insert(Comanda.class, comanda5);
        lpm.insert(Comanda.class, comanda6);
        lpm.insert(Comanda.class, comanda7);
        lpm.insert(Comanda.class, comanda8);
        lpm.insert(Comanda.class, comanda9);
        lpm.insert(Comanda.class, comanda10);

        lpm.insert(Comanda_Producte.class, cp1);
        lpm.insert(Comanda_Producte.class, cp2);
        lpm.insert(Comanda_Producte.class, cp3);
        lpm.insert(Comanda_Producte.class, cp4);
        lpm.insert(Comanda_Producte.class, cp5);
        lpm.insert(Comanda_Producte.class, cp6);
        lpm.insert(Comanda_Producte.class, cp7);
        lpm.insert(Comanda_Producte.class, cp8);
        lpm.insert(Comanda_Producte.class, cp9);
        lpm.insert(Comanda_Producte.class, cp10);
        lpm.insert(Comanda_Producte.class, cp11);
        lpm.insert(Comanda_Producte.class, cp12);
        lpm.insert(Comanda_Producte.class, cp13);
        lpm.insert(Comanda_Producte.class, cp14);
        lpm.insert(Comanda_Producte.class, cp15);
        lpm.insert(Comanda_Producte.class, cp16);
    }
}
