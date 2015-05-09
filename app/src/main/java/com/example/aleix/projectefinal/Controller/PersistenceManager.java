package com.example.aleix.projectefinal.Controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import com.example.aleix.projectefinal.R;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
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
        this.jsonParsed = (TextView) activity.findViewById(R.id.textView2);
    }

    @Override
    protected void onPreExecute() {
        this.dialog.setMessage("Please wait...");
        this.dialog.show();
    }

    @Override
    protected void onPostExecute(Object o) {
        this.dialog.dismiss();
        if(this.error != null) {
            uiUpdate.setText("Output : " + this.error);
        } else {
            this.uiUpdate.setText(this.content);
            StringBuilder outputData = new StringBuilder();
            JSONObject jsonResponse;
            try {
                jsonResponse = new JSONObject(this.content);
                JSONArray jsonMainNode = jsonResponse.optJSONArray("Android");

                int lengthJsonArr = jsonMainNode.length();

                for(int i = 0; i <lengthJsonArr; i++) {
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                    for(int j = 0; j < jsonChildNode.names().length(); j++) {
                        outputData.append("Nom atribut: " + jsonChildNode.names().getString(j) + ", valor: " + jsonChildNode.optString(jsonChildNode.names().getString(j)) + "\n");
                    }
                    outputData.append("-----------------------------\n");
                }
                this.jsonParsed.setText(outputData);
            } catch(Exception e) {
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

        try {
            URL url = new URL((String)params[0]);
            conn = (HttpURLConnection) url.openConnection();
            //conn.setDoOutput(true);
            conn.setRequestProperty("Accept", "application/json;odata=verbose");

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            while((line = reader.readLine()) != null) {
                sb.append(line + "");
            }
            this.content = sb.toString();
        } catch(Exception e) {
            this.error = e.getMessage();
            Log.e("Error", "Error in doInBackground: " + e.getMessage());
            Log.e("INPUT_ERROR_STREAM", captureInputErrorStream(conn.getErrorStream()));
        } finally{
            try {
                reader.close();
                conn.disconnect();
            } catch(Exception ex) {
                Log.e("Error", "Error while closing buffer: " + ex.getMessage());
            }
        }
        return null;
    }

    private String captureInputErrorStream(InputStream is) {
        BufferedInputStream bis = new BufferedInputStream(is);
        InputStreamReader isr = new InputStreamReader(bis);
        BufferedReader br = new BufferedReader(isr);
        String line = "";
        StringBuilder sb = new StringBuilder();
        try {
            while((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch(Exception e) {
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
}
