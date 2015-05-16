package com.example.aleix.projectefinal.Controller;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.example.aleix.projectefinal.Entity.Categoria;
import com.example.aleix.projectefinal.Entity.Client;
import com.example.aleix.projectefinal.Entity.Comanda;
import com.example.aleix.projectefinal.Entity.LogAndToastMaker;
import com.example.aleix.projectefinal.Entity.Producte;
import com.example.aleix.projectefinal.Entity.Usuari;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Michal.hostienda on 13/05/2015.
 */
public class PersistanceManager extends AsyncTask {

    private ProgressDialog dialog;

    public PersistanceManager(Activity activity) {
        this.dialog = new ProgressDialog(activity);
    }

    @Override
    protected void onPreExecute() {
        this.dialog.setMessage("Please wait...");
        this.dialog.show();
    }

    @Override
    protected Object doInBackground(Object[] params) {
        Object objectToReturn = null;
        switch((String) params[1]) {
            case "GET":
                objectToReturn = doGetRequest((String) params[0]);
                break;
            case "POST":
                objectToReturn = doPostRequest((String) params[0]);
                break;
            case "PUT":
                objectToReturn = doPutRequest((String) params[0]);
                break;
            case "DELETE":
                objectToReturn = doDeleteRequest((String) params[0]);
                break;
            default:
                Log.e("ERROR in doInBackground","ERROR!");
        }
        return objectToReturn;
    }

    @Override
    protected void onPostExecute(Object o) {
        this.dialog.dismiss();
    }


    private Object doGetRequest(String stringUrl) {
        BufferedReader reader = null;
        HttpURLConnection conn = null;
        URL url = null;
        Object serverResponseString = null;
        try {
            url = new URL(stringUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "application/json");

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line + "");
            }
            serverResponseString = sb.toString();
        } catch (Exception e) {
            Log.e("Error", "Error in doInBackground: " + e.getMessage());
        } finally {
            try {
                reader.close();
                conn.disconnect();
            } catch (Exception ex) {
                Log.e("Error", "Error while closing buffer: " + ex.getMessage());
            }
        }
        return serverResponseString;
    }

    private Object doPostRequest(String stringUrl) {
        //PENSAR COM PASSAR EL COS!!!!!!!!!!!!!!!!!!!!!!!!!
        HttpClient client = new DefaultHttpClient();
        HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000);
        HttpResponse response;
        JSONObject json = new JSONObject();
        try {
            HttpPost post = new HttpPost(new URI(stringUrl));
            json.put("Dni", "kkkkk");
            json.put("Nom", "kkkk");
            json.put("Cognom", "kkkk");
            json.put("Usuari1", "kkkkk");
            json.put("Contrasenya", "kkkkk");
            json.put("Imatge", "kkkk");
            StringEntity se = new StringEntity( json.toString());
            se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            post.setEntity(se);
            response = client.execute(post);
            if(response!=null){
                InputStream in = response.getEntity().getContent();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Object doPutRequest(String stringUrl) {
        return null;
    }

    private Object doDeleteRequest(String stringUrl) {
        return null;
    }

    public String getServerResponse(String resourceUrl, String requestMethod) {
        AsyncTask at = this.execute(resourceUrl, requestMethod);
        String serverResponse = null;
        try {
            serverResponse = (String) at.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return serverResponse;
    }
}
