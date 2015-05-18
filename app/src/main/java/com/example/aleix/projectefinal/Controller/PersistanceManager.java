package com.example.aleix.projectefinal.Controller;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
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
    private Activity activity;

    public PersistanceManager(Activity activity) {
        this.dialog = new ProgressDialog(activity);
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        this.dialog.setMessage("Please wait...");
        this.dialog.show();
    }

    @Override
    protected Object doInBackground(Object[] params) {
        Object objectToReturn = null;
        switch ((String) params[1]) {
            case "GET":
                objectToReturn = doGetRequest((String) params[0]);
                break;
            case "POST":
                objectToReturn = doPostRequest((String) params[0], (String) params[2]);
                break;
            case "PUT":
                objectToReturn = doPutRequest((String) params[0], (String) params[2]);
                break;
            case "DELETE":
                objectToReturn = doDeleteRequest((String) params[0]);
                break;
            default:
                Log.e("ERROR in doInBackground", "ERROR!");
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

    private Object doPostRequest(String stringUrl, String postMessage) {
        HttpClient client = new DefaultHttpClient();
        HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000);
        HttpResponse response;
        String stringResponse = null;
        try {
            HttpPost post = new HttpPost(new URI(stringUrl));
            StringEntity se = new StringEntity(postMessage);
            se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            post.setEntity(se);
            response = client.execute(post);
            if (response != null) {
                InputStream in = response.getEntity().getContent();
                stringResponse = getStringFromInputStream(in);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringResponse;
    }

    private Object doPutRequest(String stringUrl, String postMessage) {
        HttpClient client = new DefaultHttpClient();
        HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000);
        HttpResponse response;
        String stringResponse = null;
        try {
            HttpPut put = new HttpPut(new URI(stringUrl));
            StringEntity se = new StringEntity(postMessage);
            se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            put.setEntity(se);
            response = client.execute(put);
            if (response != null) {
                InputStream in = response.getEntity().getContent();
                stringResponse = getStringFromInputStream(in);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringResponse;
    }

    private Object doDeleteRequest(String stringUrl) {
        HttpClient client = new DefaultHttpClient();
        HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000);
        HttpResponse response;
        String stringResponse = null;
        try {
            HttpDelete delete = new HttpDelete(new URI(stringUrl));
            //StringEntity se = new StringEntity(postMessage);
            //se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            //put.setEntity(se);
            response = client.execute(delete);
            if (response != null) {
                InputStream in = response.getEntity().getContent();
                stringResponse = getStringFromInputStream(in);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringResponse;
    }

    private String getStringFromInputStream(InputStream is) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedInputStream bis = new BufferedInputStream(is);
            InputStreamReader isr = new InputStreamReader(bis);
            BufferedReader br = new BufferedReader(isr);
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            LogAndToastMaker.makeErrorLog(e.getMessage());
        }
        return sb.toString();
    }

    public String getServerResponse(String resourceUrl, String requestMethod, String postMessage) {
        String fullResourceURL = "http://10.0.3.2:52220/M13ProjectWcfDataService.svc/" + resourceUrl;
        /**/
//        AsyncTask at = null;
//        synchronized (this) {
//            while (this.getStatus() != Status.FINISHED) {
//                try {
//                    this.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            at = this.execute(fullResourceURL, requestMethod, postMessage);
//
//            this.notify();
//
//        }
        /**/
        AsyncTask at = this.execute(fullResourceURL, requestMethod, postMessage);
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

    public <T> List<T> getListOfObjectsFromServer(Class<T> objectClass) {
        String serverResponse = getServerResponse(objectClass.getSimpleName(), "GET", null);
        List<T> listOfObjects = MyJasonEntityConverter.getObjectsFromFormattedJson(objectClass, MyJasonEntityConverter.formatJsonInput(serverResponse), this.activity);
        return listOfObjects;
    }

    public <T> String sendAnObjectToServer(Class<T> objectClass, T objectToTransform) {
        String transformedObject = MyJasonEntityConverter.getJsonObjectFromEntity(objectClass, objectToTransform);
        String serverResponse = getServerResponse(objectClass.getSimpleName(), "POST", transformedObject);
        LogAndToastMaker.makeToast(this.activity, "The entry added correctly!");
        return serverResponse;
    }

    public <T> void updateAnObjectFromServer(Class<T> objectClass, T objectToTransform) {
        String transformedObject = MyJasonEntityConverter.getJsonObjectFromEntity(objectClass, objectToTransform);
        String resourceToUpdate = objectClass.getSimpleName() + "(" + getIdOfAnObjectRetrievedFromServer(objectClass, objectToTransform) + ")";
        getServerResponse(resourceToUpdate, "PUT", transformedObject);
        LogAndToastMaker.makeToast(this.activity, "The entry updated correctly!");
    }

    public <T> void deleteAnObjectFromServer(Class<T> objectClass, int idOfObjectToDelete) {
        String resourceToUpdate = objectClass.getSimpleName() + "(" + idOfObjectToDelete + ")";
        getServerResponse(resourceToUpdate, "DELETE", null);
        LogAndToastMaker.makeToast(this.activity, "The entry deleted correctly!");
    }

    private <T> int getIdOfAnObjectRetrievedFromServer(Class<T> objectClass, T objectToTransform) {
        int objectId = 0;
        try {
            Method method = objectClass.getMethod("getId");
            objectId = (int) method.invoke(objectToTransform, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objectId;
    }
}
