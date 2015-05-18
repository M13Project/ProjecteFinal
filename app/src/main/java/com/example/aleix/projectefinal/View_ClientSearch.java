package com.example.aleix.projectefinal;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.aleix.projectefinal.Entity.Client;

import java.util.ArrayList;


public class View_ClientSearch extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    EditText txtSearchClient;
    ListView listView;
    Button btnSearch;
    //SimpleCursorAdapter mAdapter;
    ArrayAdapter arrayAdapter;
    ArrayList<Client> LlistaClient = new ArrayList<Client>(){{new Client(40, "X435345", "Michal", "Krysiak", 26, "/image.png", "2015-05-13T00:00:00", 1); new Client(40, "Y435345", "Pepe", "AS", 26, "/image.png", "2015-05-13T00:00:00", 1);}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__client_search);
        txtSearchClient = (EditText) findViewById(R.id.txtSearchClient);
        listView = (ListView) findViewById(R.id.listViewResultClients);
       listView.setOnItemClickListener( this);
       // mAdapter = new SimpleCursorAdapter(this, LlistaClient , android.R.layout.simple_list_item_1, 0);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, LlistaClient);
        listView.setAdapter(arrayAdapter);
        btnSearch = (Button) findViewById(R.id.btnClientSearch);
        btnSearch.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view__client_search, menu);
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
    switch (v.getId()){
        case R.id.btnClientSearch:

            break;
    }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
