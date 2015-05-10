package com.example.aleix.projectefinal.Controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.aleix.projectefinal.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by Aleix on 05/05/2015.
 */
public class PersistenceManager extends AsyncTask {

    private static final char PARAMETER_DELIMITER = '&';
    private static final char PARAMETER_EQUALS_CHAR = '=';
    private String content;
    private String error;
    private ProgressDialog dialog;
    private TextView uiUpdate;
    private TextView jsonParsed;

    public PersistenceManager(Activity activity) {
        this.content = null;
        this.error = null;
        this.dialog = new ProgressDialog(activity);
        this.uiUpdate = (TextView) activity.findViewById(R.id.textView);
        this.jsonParsed = (TextView) activity.findViewById(R.id.textViewJsonFormatted);
    }

    @Override
    protected void onPreExecute() {
        this.dialog.setMessage("Please wait...");
        this.dialog.show();
    }

    @Override
    protected void onPostExecute(Object o) {
        this.dialog.dismiss();
        if (this.error != null) {
            uiUpdate.setText("Output : " + this.error);
        } else {
            this.uiUpdate.setText(this.content);
            StringBuilder outputData = new StringBuilder();
            JSONObject jsonResponse;
            try {
//                jsonResponse = new JSONObject(this.content);
//                JSONArray jsonMainNode = jsonResponse.optJSONArray("d");
                /*Prova*/
                jsonResponse = new JSONObject(this.content);
                JSONObject prova = jsonResponse.getJSONObject("d");
                JSONArray jsonMainNode = prova.optJSONArray("EntitySets");
                for (int i = 0; i < jsonMainNode.length(); i++) {
                    outputData.append(jsonMainNode.getString(i) + " ");
                }
                this.jsonParsed.setText(outputData.toString());
                /**/
//                int lengthJsonArr = jsonMainNode.length();
//
//                for (int i = 0; i < lengthJsonArr; i++) {
//                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
//                    for (int j = 0; j < jsonChildNode.names().length(); j++) {
//                        outputData.append("Nom atribut: " + jsonChildNode.names().getString(j) + ", valor: " + jsonChildNode.optString(jsonChildNode.names().getString(j)) + "\n");
//                    }
//                    outputData.append("-----------------------------\n");
//                }
//                this.jsonParsed.setText(outputData);
            } catch (Exception e) {
                Log.e("Error", "Error in onPostExecte: " + e.getMessage());
            }
        }
    }

    @Override
    protected void onCancelled(Object o) {
        super.onCancelled(o);
    }

    @Override
    protected Object doInBackground(Object[] params) {
        BufferedReader reader = null;
        HttpURLConnection conn = null;
        URL url = null;

        try {
            url = new URL((String) params[0]);
            conn = (HttpURLConnection) url.openConnection();
            //conn.setDoOutput(true);
            conn.setRequestProperty("Accept", "application/json;odata=verbose");

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line + "");
            }
            this.content = sb.toString();
        } catch (Exception e) {
            this.error = e.getMessage();
            Log.e("Error", "Error in doInBackground: " + e.getMessage());
            Log.e("INPUT_ERROR_STREAM", captureInputErrorStream(conn.getErrorStream()));
        } finally {
            try {
                reader.close();
                conn.disconnect();
            } catch (Exception ex) {
                Log.e("Error", "Error while closing buffer: " + ex.getMessage());
            }
        }

        /*Prova POST*/
        provaPost2(params);
        /**/

        return null;
    }

    private void provaPost2(Object[] params) {
        HttpClient client = new DefaultHttpClient();
        HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000); //Timeout Limit
        HttpResponse response;
        JSONObject json = new JSONObject();

        try {
            HttpPost post = new HttpPost(new URI((String) params[0]));
            json.put("Dni", "lololol");
            json.put("Nom", "lololol");
            json.put("Cognom", "lololol");
            json.put("Usuari1", "lololol");
            json.put("Contrasenya", "lololol");
            json.put("Imatge", "lololol");
            StringEntity se = new StringEntity( json.toString());
            se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            post.setEntity(se);
            response = client.execute(post);

                    /*Checking response */
            if(response!=null){
                InputStream in = response.getEntity().getContent(); //Get the data in the entity
                Log.e("SERVER_RESPONSE", captureInputErrorStream(in));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private String captureInputErrorStream(InputStream is) {
        BufferedInputStream bis = new BufferedInputStream(is);
        InputStreamReader isr = new InputStreamReader(bis);
        BufferedReader br = new BufferedReader(isr);
        String line = "";
        StringBuilder sb = new StringBuilder();
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            Log.e("ERROR!", e.getMessage());
        }
        return sb.toString();
    }

    public String createQueryStringForParameters(Map<String, String> parameters) {
        StringBuilder parametersAsQueryString = new StringBuilder();
        if (parameters != null) {
            boolean firstParameter = true;

            for (String parameterName : parameters.keySet()) {
                if (!firstParameter) {
                    parametersAsQueryString.append(PARAMETER_DELIMITER);
                }
                parametersAsQueryString.append(parameterName).append(PARAMETER_EQUALS_CHAR).append(URLEncoder.encode(parameters.get(parameterName)));
                firstParameter = false;
            }
        }
        return parametersAsQueryString.toString();
    }

    public Map<String, String> formatJsonInput(String rawJsonInput) {
        Map<String, String> mappedAttributes = null;


        StringBuilder outputData = new StringBuilder();
        JSONObject jsonResponse;
        try {
            jsonResponse = new JSONObject(rawJsonInput);
            JSONArray jsonMainNode = jsonResponse.optJSONArray("Android");

            int lengthJsonArr = jsonMainNode.length();

            for (int i = 0; i < lengthJsonArr; i++) {
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                for (int j = 0; j < jsonChildNode.names().length(); j++) {
                    outputData.append("Nom atribut: " + jsonChildNode.names().getString(j) + ", valor: " + jsonChildNode.optString(jsonChildNode.names().getString(j)) + "\n");
                }
                outputData.append("-----------------------------\n");
            }
            this.jsonParsed.setText(outputData);
        } catch (Exception e) {
            Log.e("Error", "Error in onPostExecte: " + e.getMessage());
        }


        return mappedAttributes;
    }
}
