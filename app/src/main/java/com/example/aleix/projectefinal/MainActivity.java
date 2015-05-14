package com.example.aleix.projectefinal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;


public class MainActivity extends Activity implements View.OnClickListener {
    Button btn;
    EditText txtuser;
    EditText txtpassword;
    String User, Password, encPass;
    Boolean samepassword;
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

            Intent main = new Intent(this, Main_View.class);
            //comprovació del login
            try{
                encPass = passwordKeyGeneration(Password, 256);
                samepassword = encPass.equalsIgnoreCase(encPass);
                Toast.makeText(this, "Usuari: " + User + " Pass: " + encPass, Toast.LENGTH_LONG).show();

            }
            catch (Exception e){
                Log.e("Error en el login", "Error en el login");
            }
            if (samepassword){
                main.putExtra("User", User);
                startActivity(main);
            }
            else{
                //Mostra un dialeg amb un error. Substitueix la clase Error del package Dialog
                //DialogFragment dialog = new Error();
               // dialog.show(getSupportFragmentManager(), "dialog");
                //dialog.show(dialog., "dialog");
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Error");
                dialog.setMessage("Error en el login");
                dialog.setCancelable(false);

                dialog.setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }



        }
    }
    public void sharedPreferencesManager() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("user_authentication", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("key", "value");
        editor.commit();
    }
    public static String passwordKeyGeneration(String text, int keySize) {
        String result = "";
        if ((keySize == 128) || (keySize == 192) || (keySize == 256)) {
            try {
                byte[] data = text.getBytes("UTF-8");
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hash = md.digest(data);
                for (int i = 0; i < hash.length; i++) {
                    String hex = Integer.toHexString(hash[i]);
                    if (hex.length() == 1) {
                        hex = "0" + hex;
                    }
                    hex = hex.substring(hex.length() - 2);
                    result += hex;
                    if (i != hash.length - 1) {
                        result += "-";
                    }
                }
            } catch (Exception ex) {
                System.err.println("Error generant la clau:" + ex);
            }
        }
        return result.toUpperCase();
    }
}
