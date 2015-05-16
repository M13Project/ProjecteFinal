package com.example.aleix.projectefinal.Proves;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.aleix.projectefinal.Controller.MyJasonEntityConverter;
import com.example.aleix.projectefinal.Controller.PersistanceManager;
import com.example.aleix.projectefinal.Entity.Client;
import com.example.aleix.projectefinal.Entity.Comanda;
import com.example.aleix.projectefinal.Entity.LogAndToastMaker;
import com.example.aleix.projectefinal.R;

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
        String resourceURL = "http://10.0.3.2:52220/M13ProjectWcfDataService.svc/" + etQuery.getText().toString();
        String requestMethod = "GET";
        PersistanceManager async = new PersistanceManager(this);
        String serverResponse = async.getServerResponse(resourceURL, requestMethod);

        //async.execute(serverURL, "GET");
//        List<Client> client = MyJasonEntityConverter.getObjectsFromFormattedJson(Client.class, MyJasonEntityConverter.formatJsonInput(serverResponse));
//        LogAndToastMaker.makeInfoLog(client.get(0).toString());

//        List<Categoria> categoria = MyJasonEntityConverter.getObjectsFromFormattedJson(Categoria.class, MyJasonEntityConverter.formatJsonInput(serverResponseString));
//        LogAndToastMaker.makeInfoLog(categoria.get(0).toString());

        List<Comanda> comanda = MyJasonEntityConverter.getObjectsFromFormattedJson(Comanda.class, MyJasonEntityConverter.formatJsonInput(serverResponse), this);
        LogAndToastMaker.makeInfoLog(comanda.get(0).toString());

//        LogAndToastMaker.makeInfoLog(async.example(serverURL, "GET"));
    }
}
