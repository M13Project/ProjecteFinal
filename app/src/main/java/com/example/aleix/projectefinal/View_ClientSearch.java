package com.example.aleix.projectefinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aleix.projectefinal.Adapter.ClientAdapter;
import com.example.aleix.projectefinal.Controller.LocalPersistanceManager;
import com.example.aleix.projectefinal.Entity.Client;
import com.example.aleix.projectefinal.Entity.Usuari;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class View_ClientSearch extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    EditText txtSearchClient;
    TextView txtSenseClients;
    ListView listView;
    Button btnSearch;
    Usuari u;
    //Cursor clients;
    List clients;
    private ClientAdapter adapter;
    LocalPersistanceManager lpm ;
    //SimpleCursorAdapter mAdapter;
    //ArrayAdapter<Client> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__client_search);
        txtSearchClient = (EditText) findViewById(R.id.txtSearchClient);
        listView = (ListView) findViewById(R.id.listViewResultClients);
       listView.setOnItemClickListener( this);
        txtSenseClients = (TextView) findViewById(R.id.txtSenseClientSearch);
        Bundle bundle = getIntent().getExtras();
        u = (Usuari) bundle.get("User");
        registerForContextMenu(listView);
        lpm = new LocalPersistanceManager(this, "m13_project", 2);
       // mAdapter = new SimpleCursorAdapter(this, LlistaClient , android.R.layout.simple_list_item_1, 0);
       // arrayAdapter = new ArrayAdapter<Client>(this, android.R.layout.simple_list_item_multiple_choice, LlistaClient);

        //listView.setAdapter(arrayAdapter);
        refreshData(false);
        btnSearch = (Button) findViewById(R.id.btnClientSearch);
        btnSearch.setOnClickListener(this);

    }
    void refreshData(Boolean busqueda) {
       clients =  lpm.getAllEntities(Client.class);
        if (busqueda){
            List<Client> c =new ArrayList<Client>();
            c.addAll(clients);


            Iterator<Client> i = c.iterator();
            clients.clear();
            while (i.hasNext()){
                Client client = i.next();
                if (client.getNom().equalsIgnoreCase(txtSearchClient.getText().toString()) || client.getCognom().equalsIgnoreCase(txtSearchClient.getText().toString())){
                    clients.add(client);
                }
            }
        }
        adapter = new ClientAdapter(this, clients);
        listView.setAdapter(adapter);

        if(clients.size() == 0) {
            txtSenseClients.setVisibility(txtSenseClients.VISIBLE);
            listView.setVisibility(listView.INVISIBLE);
        }
        else {
            txtSenseClients.setVisibility(txtSenseClients.INVISIBLE);
            listView.setVisibility(listView.VISIBLE);
        }
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
            if (txtSearchClient.getText().toString().equalsIgnoreCase("") || txtSearchClient.getText().toString().equalsIgnoreCase(" ")){
                refreshData(false);
            }else{
                refreshData(true);
            }

            break;
    }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),
                "Click a la posició " + position,
                Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, View_Clients.class);
        i.putExtra("User", u);
        i.putExtra("Client", (Client) clients.get(position));
        startActivity(i);
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contextual_client_search, menu);
    }
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.mnuVeureDades:
                // mostrar les dades de l'element escollit
                Toast.makeText(this, adapter.getItem(info.position).getNom(), Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, View_Clients.class);
                i.putExtra("User", u);
                i.putExtra("Client", (Client) adapter.getItem(info.position));
                startActivity(i);
                return true;
            case R.id.mnuModifDades:
                Intent in = new Intent(this, View_AddClient.class);
                in.putExtra("Client", (Client) adapter.getItem(info.position));
                in.putExtra("User", u);
                startActivity(in);
                // mostrar les dades de l'element escollit
                //Toast.makeText(this, adapter.getItem(info.position).getNom(), Toast.LENGTH_LONG).show();
                return true;
            case R.id.mnuEsborrar:
                // esborrar l'element escollit
                //titularsConv.remove(adapter.getItem(info.position));
                 String a = lpm.delete(Client.class, adapter.getItem(info.position).getId());

                 ;
                // actualitzar la llista
                refreshData(false);
                // mostrar missatge
                Toast.makeText(this, "S'ha esborrat client! ", Toast.LENGTH_LONG).show();
                return true;
            default: break;
        }
        return false;
    }
}
