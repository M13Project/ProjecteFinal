package com.example.aleix.projectefinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.aleix.projectefinal.Entity.Usuari;

import java.io.Serializable;


public class Main_View extends Activity implements View.OnClickListener{

    String User;
    Usuari usuari;
    ImageButton btnimg1;
    ImageButton btnimg2;
    ImageButton btnimg3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__view);
        btnimg1 = (ImageButton) findViewById(R.id.btnimg1);
        btnimg2 = (ImageButton) findViewById(R.id.btnimg2);
        btnimg3 = (ImageButton) findViewById(R.id.btnimg3);
        btnimg1.setOnClickListener(this);
        btnimg2.setOnClickListener(this);
        btnimg3.setOnClickListener(this);
        Bundle bundle = getIntent().getExtras();

        usuari = (Usuari) bundle.get("User");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main__view, menu);
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
        Intent opcio = null;
        switch (v.getId()){
            case R.id.btnimg1:
                opcio = new Intent(this, View_AddClient.class );
                break;
            case R.id.btnimg2:
                opcio = new Intent(this,View_ClientSearch.class );
                break;
            case R.id.btnimg3:
                opcio = new Intent(this, View_ClientGeoloc.class);
                break;
        }
        opcio.putExtra("Usuari", (Serializable) usuari);
        if (opcio!= null){
            startActivity(opcio);
        }
    }


}
