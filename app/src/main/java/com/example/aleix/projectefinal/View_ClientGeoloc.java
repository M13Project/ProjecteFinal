package com.example.aleix.projectefinal;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class View_ClientGeoloc extends Activity implements OnMapReadyCallback, GoogleMap.OnMapLoadedCallback{

    private GoogleMap clientMap = null;
    //MapFragment fragmentMapa;
    private static final LatLng INS_BOSC_DE_LA_COMA = new LatLng(42.1727,2.47631);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__client_geoloc);

        clientMap = ((MapFragment)this.getFragmentManager().findFragmentById(R.id.fragClientMap)).getMap();

        MapConfiguration();

/*        GoogleMapOptions options = new GoogleMapOptions();
        options.mapType(GoogleMap.MAP_TYPE_SATELLITE).compassEnabled(false).rotateGesturesEnabled(false).tiltGesturesEnabled(false);
        fragmentMapa = MapFragment.newInstance(options);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragClientMap, fragmentMapa);
        fragmentTransaction.commit();*/

        //MapFragment mf = (MapFragment) getFragmentManager().findFragmentById(R.id.fragClientMap);
        //mf.getMapAsync(this); // calls onMapReady when loaded
    }

    private void MapConfiguration() {

        if(clientMap == null) {
            clientMap = ((MapFragment)this.getFragmentManager().findFragmentById(R.id.fragClientMap)).getMap();
        }
        if(clientMap != null) {
            clientMap.getUiSettings().setCompassEnabled(false);
            clientMap.getUiSettings().setRotateGesturesEnabled(false);
            clientMap.getUiSettings().setTiltGesturesEnabled(false);
            clientMap.setMyLocationEnabled(true);
            clientMap.addMarker(new MarkerOptions().position(INS_BOSC_DE_LA_COMA).title("Bosc de la coma").snippet("Estudis: ESO, Batxillerat, Cicles Formatius i CAS\"));").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {// map is loaded but not laid out yet
        //clientMap.setOnMapLoadedCallback(this); // calls onMapLoaded when layout done
    }

    @Override
    public void onMapLoaded() {

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

}
