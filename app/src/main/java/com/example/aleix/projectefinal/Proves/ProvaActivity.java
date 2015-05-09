package com.example.aleix.projectefinal.Proves;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.aleix.projectefinal.Controller.PersistenceManager;
import com.example.aleix.projectefinal.R;

public class ProvaActivity extends ActionBarActivity implements View.OnClickListener {

    private Button button;
    private Button buttonXmlServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prova);
        button = (Button) findViewById(R.id.buttonJson);
        button.setOnClickListener(this);
        buttonXmlServer = (Button) findViewById(R.id.buttonXml);
        buttonXmlServer.setOnClickListener(this);
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
            String serverURL = "http://10.0.3.2:52220/M13ProjectWcfDataService.svc/Usuari";
            PersistenceManager async = new PersistenceManager(this);
            async.execute(serverURL);
    }
}
