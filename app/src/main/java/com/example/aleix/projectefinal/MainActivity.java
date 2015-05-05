package com.example.aleix.projectefinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener {
    Button btn;
    EditText txtuser;
    EditText txtpassword;
    String User, Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btnLogin);
        txtuser = (EditText) findViewById(R.id.txtUser);
        txtpassword = (EditText) findViewById(R.id.txtPassword);
        btn.setOnClickListener(this);

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
        if (v.getId() == R.id.btnLogin){
            User = txtuser.getText().toString();
            Password = txtpassword.getText().toString();
            Toast.makeText(this, "Usuari: " + User + " Pass: " + Password, Toast.LENGTH_LONG).show();
            Intent main = new Intent(this, Main_View.class);

            startActivity(main);


        }
    }
}
