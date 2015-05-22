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

        SynchronizeController sc = new SynchronizeController(this);
        sc.synchronizeData();

        addDataToTheLocalDatabase();

        sc.synchronizeData();
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


        


    }
}
