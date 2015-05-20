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

import com.example.aleix.projectefinal.Controller.LocalPersistanceManager;
import com.example.aleix.projectefinal.Controller.LoginController;
import com.example.aleix.projectefinal.Entity.Categoria;
import com.example.aleix.projectefinal.Entity.Client;
import com.example.aleix.projectefinal.Entity.Comanda;
import com.example.aleix.projectefinal.Entity.Comanda_Producte;
import com.example.aleix.projectefinal.Entity.Producte;
import com.example.aleix.projectefinal.Entity.Usuari;
import com.example.aleix.projectefinal.proves.ProvaActivity;

import java.security.MessageDigest;
import java.util.Iterator;
import java.util.List;


public class MainActivity extends Activity implements View.OnClickListener {
    Button btn, btnp;
    EditText txtuser;
    EditText txtpassword;
    String User, Password, encPass;
    Boolean samepassword = false;
    LoginController loginController;
    List<Usuari> usuarisList ;//= new ArrayList<Usuari>() {{ add(new Usuari("p", "p")); add(new Usuari("a", "a")); }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btnLogin);
        txtuser = (EditText) findViewById(R.id.txtUser);
        txtpassword = (EditText) findViewById(R.id.txtPassword);
        btn.setOnClickListener(this);
        btnp = (Button) findViewById(R.id.button);
        btnp.setOnClickListener(this);
        /**/
        /*Base de dades*/
        LocalPersistanceManager lpm = new LocalPersistanceManager(this, "m13_project", 2);
        Categoria categoria = new Categoria(1, "categoriaExemple", 10);
        Client client = new Client("X435345", "Michal", "Krysiak", 26, "/image.png", "2015-05-13T00:00:00");
        Comanda comanda = new Comanda(true, "2015-05-13T00:00:00", client);
        //Localitzacio localitzacio = new Localitzacio("45657", "exempleDireccio", 423.23, 2343.23, client);
        Producte producte = new Producte(23, "exempleProducte", 50, 10, "image.png", true, categoria);
       // Usuari u = new Usuari("41626113-T", "Aleix", "Ventura", "AVentura", "E0-35-35-D4-E5-A7-1B-CE-82-48-12-3D-3B-74-53-5A-6D-14-48-28-8D-30-ED-BD-43-1B-43-EC-DF-AD-8D-3B");
      //  lpm.insert(Usuari.class, u);
        // client.addComanda(comanda);
        //categoria.addProducte(producte);
        Comanda_Producte cp = new Comanda_Producte(45, comanda, producte, 10);
        //lpm.insert(Client.class, client);
       // lpm.insert(Comanda.class, comanda);
       // lpm.insert(Localitzacio.class, localitzacio);
//        lpm.insert(Categoria.class, categoria);
//        lpm.insert(Producte.class, producte);
      //  lpm.insert(Comanda_Producte.class, cp);
        Client cl = lpm.getEntity(Client.class, 1);
//        LogAndToastMaker.makeInfoLog(cl.toString());
        usuarisList = lpm.getAllEntities(Usuari.class);
        /**/

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
        Usuari ulog = new Usuari();
        if (v.getId() == R.id.btnLogin){
            User = txtuser.getText().toString();
            Password = txtpassword.getText().toString();
            //
            Iterator<Usuari> iu= usuarisList.iterator();
            while(iu.hasNext()){

                Usuari u =iu.next();
                if (u.getUsuari1().equals(User)){
                    ulog = u;
                }
            }

            Intent main = new Intent(this, Main_View.class);
            //comprovació del login
            try{
                //falla
                //encPass = loginController.makeHashVersionOfPassword(Password);
                //samepassword = encPass.equalsIgnoreCase(loginController.makeHashVersionOfPassword(ulog.getContrasenya()));
                encPass = passwordKeyGeneration(Password);
                samepassword = encPass.equalsIgnoreCase(ulog.getContrasenya());
                Toast.makeText(this, "Usuari: " + User + " Pass: " + encPass, Toast.LENGTH_LONG).show();

            }
            catch (Exception e){
                Log.e("Error en el login", "Error en el login");
            }
            if (samepassword || encPass.equalsIgnoreCase(ulog.getContrasenya())){
                main.putExtra("User", ulog);
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
        if (v.getId() == R.id.button){
            Intent i = new Intent(this, ProvaActivity.class);
            startActivity(i);
        }
    }
    public static String passwordKeyGeneration(String text) {
        int keySize=256;
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
    public void sharedPreferencesManager() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("user_authentication", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("key", "value");
        editor.commit();
    }

}
