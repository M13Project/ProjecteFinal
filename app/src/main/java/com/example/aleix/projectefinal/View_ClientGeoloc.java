package com.example.aleix.projectefinal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aleix.projectefinal.Adapter.ClientAdapter;
import com.example.aleix.projectefinal.Controller.GlobalParameterController;
import com.example.aleix.projectefinal.Controller.LocalPersistanceManager;
import com.example.aleix.projectefinal.Entity.Client;
import com.example.aleix.projectefinal.Entity.Localitzacio;
import com.example.aleix.projectefinal.Entity.Usuari;
import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class View_ClientGeoloc extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    ListView listView;
    List clients;
    List loc;
    Usuari u;
    private ClientAdapter adapter;
    LocalPersistanceManager lpm ;
    LocationManager handle;
    private String provider;
    Client[] cli = new Client[5];
    Localitzacio[] lo = new Localitzacio[5];
    double latitude;
    double longitude;
    private GoogleMap clientMap = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__client_geoloc);
        listView = (ListView) findViewById(R.id.listViewResultLocClients);
        listView.setOnItemClickListener( this);
        //Mapa...
//        clientMap = ((MapFragment)this.getFragmentManager().findFragmentById(R.id.fgmClientMap)).getMap();
//        clientMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//            public boolean onMarkerClick(Marker marker) {
//                /*Toast.makeText(
//                        View_ClientGeoloc.this,
//                        "Marcador pulsado:\n" +
//                                marker.getTitle(),
//                        Toast.LENGTH_SHORT).show();*/
//
//                return false;
//            }
//        });
        Bundle bundle = getIntent().getExtras();
        u = (Usuari) bundle.get("User");
        registerForContextMenu(listView);
        lpm = new LocalPersistanceManager(this, GlobalParameterController.DATABASE_NAME, GlobalParameterController.DATABASE_VERSION);
        loc();
        refreshData();
    }
    public void loc(){
        handle = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        Criteria c = new Criteria();
        c.setAccuracy(Criteria.ACCURACY_FINE);
        //obtiene el mejor proveedor en función del criterio asignado
        //(la mejor precisión posible)
        provider = handle.getBestProvider(c, true);


        //Se activan las notificaciones de localización con los parámetros: proveedor, tiempo mínimo de actualización, distancia mínima, Locationlistener
        //handle.requestLocationUpdates(provider, 10000, 1, this);
        //Obtenemos la última posición conocida dada por el proveedor
        Location loc = handle.getLastKnownLocation(provider);
        latitude = loc.getLatitude();
        longitude = loc.getLongitude();



    }
    void refreshData() {
        loc = lpm.getAllEntities(Localitzacio.class);

        clients =  lpm.getAllEntities(Client.class);

//        getDistance(latitude, longitude, );
//        if (busqueda){
            List<Localitzacio> l =new ArrayList<Localitzacio>();
            l.addAll(loc);


            Iterator<Localitzacio> i = l.iterator();
            int[] dis = new int[5];
//            clients.clear();
            while (i.hasNext()){

                Localitzacio localitzacio = i.next();
                for (int o=0; o<dis.length ;o++ ){

                    if (dis[o] < getDistance(latitude, longitude, localitzacio.getLatitud(), localitzacio.getLongitud())){
                        dis[o] = getDistance(latitude, longitude, localitzacio.getLatitud(), localitzacio.getLongitud());
                        lo[o] = localitzacio;
                        cli[o] = (Client) clients.get(localitzacio.getClientId().getId());
                    }
                }

               // if (client.getNom().equalsIgnoreCase(txtSearchClient.getText().toString()) || client.getCognom().equalsIgnoreCase(txtSearchClient.getText().toString())){
              //      clients.add(client);
                }
        clients.clear();
        for (int o=0; o<dis.length ;o++ ){
            clients.add(cli[o]);
            //Punts Mapa
//            clientMap.addMarker(new MarkerOptions()
//                    .position(new LatLng(lo[o].getLatitud(), lo[o].getLongitud()))
//                    .title(cli[o].getNom() + " " + cli[o].getCognom()));
        }
//            }
//        }
        adapter = new ClientAdapter(this, clients);
        listView.setAdapter(adapter);

        if(clients.size() == 0) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Error");
            dialog.setMessage("No hi han Clients");
            dialog.setCancelable(false);

            dialog.setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            dialog.show();
            listView.setVisibility(listView.INVISIBLE);
        }
        else {
            listView.setVisibility(listView.VISIBLE);
        }
    }

    public static int getDistance(double lat_a,double lng_a, double lat_b, double lon_b){
        int Radius = 6371000; //Radio de la tierra
        double lat1 = lat_a / 1E6;
        double lat2 = lat_b / 1E6;
        double lon1 = lng_a / 1E6;
        double lon2 = lon_b / 1E6;
        double dLat = Math.toRadians(lat2-lat1);
        double dLon = Math.toRadians(lon2-lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon /2) * Math.sin(dLon/2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return (int) (Radius * c);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view__client_geoloc, menu);
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
            //case R.id.btnClientSearch:
              /*  if (txtSearchClient.getText().toString().equalsIgnoreCase("") || txtSearchClient.getText().toString().equalsIgnoreCase(" ")){
                    refreshData(false);
                }else{
                    refreshData(true);
                }*/

               // break;
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
        inflater.inflate(R.menu.contextual_client_geoloc, menu);
    }
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.mnuVeureDadesGeoLoc:
                // mostrar les dades de l'element escollit
                Toast.makeText(this, adapter.getItem(info.position).getNom(), Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, View_Clients.class);
                i.putExtra("User", u);
                i.putExtra("Client", (Client) adapter.getItem(info.position));
                startActivity(i);
                return true;
            case R.id.mnuModifDadesGeoLoc:
                Intent in = new Intent(this, View_AddClient.class);
                in.putExtra("Client", (Client) adapter.getItem(info.position));
                in.putExtra("User", u);
                startActivity(in);
                // mostrar les dades de l'element escollit
                //Toast.makeText(this, adapter.getItem(info.position).getNom(), Toast.LENGTH_LONG).show();
                return true;
            case R.id.mnuEsborrarGeoLoc:
                // esborrar l'element escollit
                //titularsConv.remove(adapter.getItem(info.position));
                lpm.delete(Client.class, adapter.getItem(info.position).getId());

                // actualitzar la llista
                refreshData();
                // mostrar missatge
                Toast.makeText(this, "S'ha esborrat client!", Toast.LENGTH_LONG).show();
                return true;
            default: break;
        }
        return false;
    }
}
