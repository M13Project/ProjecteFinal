package com.example.aleix.projectefinal.Controller;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.example.aleix.projectefinal.Entity.CategoriaLog;
import com.example.aleix.projectefinal.Entity.Producte;
import com.example.aleix.projectefinal.Entity.ProducteLog;

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
import org.joda.time.DateTime;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;
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
            conn.setConnectTimeout(5000);
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
        String responseOfServer = "FAIL";
        HttpURLConnection connection = null;
        DataOutputStream dos = null;
        try {
            byte[] postMessageBytes = postMessage.getBytes("UTF-8");
            int postMessageBytesLength = postMessageBytes.length;
            URL url = new URL(stringUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("charset", "utf-8");
            connection.setRequestProperty("Content-Length", Integer.toString(postMessageBytesLength));
            connection.setRequestProperty("Accept", "application/json");
            connection.setUseCaches(false);
            dos = new DataOutputStream(connection.getOutputStream());
            dos.write(postMessageBytes);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "");
            }
            if (sb.toString() != null) {
                responseOfServer = "OK";
            }
        } catch (Exception ex) {
            LogAndToastMaker.makeErrorLog(ex.getMessage());
        } finally {
            try {
                dos.close();
                connection.disconnect();
            } catch (Exception ex) {
                LogAndToastMaker.makeErrorLog(ex.getMessage());
            }
        }
        return responseOfServer;
    }

    private Object doPutRequest(String stringUrl, String putMessage) {
        String responseOfServer = "FAIL";
        HttpURLConnection connection = null;
        DataOutputStream dos = null;
        try {
            byte[] putMessageBytes = putMessage.getBytes("UTF-8");
            int postMessageBytesLength = putMessageBytes.length;
            URL url = new URL(stringUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("charset", "utf-8");
            connection.setRequestProperty("Content-Length", Integer.toString(postMessageBytesLength));
            connection.setRequestProperty("Accept", "application/json");
            connection.setUseCaches(false);
            dos = new DataOutputStream(connection.getOutputStream());
            dos.write(putMessageBytes);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "");
            }
            if (sb.toString().equalsIgnoreCase("")) {
                responseOfServer = "OK";
            }
        } catch (Exception ex) {
            LogAndToastMaker.makeErrorLog(ex.getMessage());
        } finally {
            try {
                dos.close();
                connection.disconnect();
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        return responseOfServer;
    }

    private Object doDeleteRequest(String stringUrl) {
        String responseOfServer = "FAIL";
        HttpURLConnection connection = null;
        DataOutputStream dos = null;
        try {
            URL url = new URL(stringUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Accept", "application/json");
            connection.setUseCaches(false);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "");
            }
            if (sb.toString().equalsIgnoreCase("")) {
                responseOfServer = "OK";
            }
        } catch (Exception ex) {
            LogAndToastMaker.makeErrorLog(ex.getMessage());
        } finally {
            try {
                dos.close();
                connection.disconnect();
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        return responseOfServer;
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
        String fullResourceURL = GlobalParameterController.SERVER_URL + resourceUrl;
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

    public <T> T getObjectFromServer(Class<T> objectClass, int idOfObjectToRetrieve) {
        T objectRetrieved = null;
        String resourceUrl = objectClass.getSimpleName() + "(" + idOfObjectToRetrieve + ")";
        String serverResponse = getServerResponse(resourceUrl, "GET", null);
        List<Map<String, Object>> foreignObjectInMapFormat = MyJasonEntityConverter.formatJsonInputWithOneEntry(serverResponse);
        List<T> listOfOneObject = MyJasonEntityConverter.getObjectsFromFormattedJson(objectClass, foreignObjectInMapFormat, activity);
        if (!listOfOneObject.isEmpty()) {
            objectRetrieved = listOfOneObject.get(0);
        }
        return objectRetrieved;
    }

    public <T> String sendAnObjectToServer(Class<T> objectClass, T objectToTransform) {
        String transformedObject = MyJasonEntityConverter.getJsonObjectFromEntity(objectClass, objectToTransform);
        String serverResponse = getServerResponse(objectClass.getSimpleName(), "POST", transformedObject);
        return serverResponse;
    }

    public <T> String updateAnObjectFromServer(Class<T> objectClass, T objectToTransform) {
        String transformedObject = MyJasonEntityConverter.getJsonObjectFromEntity(objectClass, objectToTransform);
        String resourceToUpdate = objectClass.getSimpleName() + "(Id=" + getIdOfAnObjectRetrievedFromServer(objectClass, objectToTransform) + ",ComercialId=" + GlobalParameterController.COMERCIAL_AGENT_ID + ")";
        String serverResponse = getServerResponse(resourceToUpdate, "PUT", transformedObject);
        return serverResponse;
    }

    public <T> String deleteAnObjectFromServer(Class<T> objectClass, int idOfObjectToDelete) {
        String resourceToDelete = objectClass.getSimpleName() + "(Id=" + idOfObjectToDelete + ",ComercialId=" + GlobalParameterController.COMERCIAL_AGENT_ID + ")";
        String serverResponse = getServerResponse(resourceToDelete, "DELETE", null);
        return serverResponse;
    }

    public <T> String deleteLogFromServer(Class<T> objectClass, int idOfLogToDelete, String operationType) {
        String resourceToDelete = objectClass.getSimpleName() + "(Id=" + idOfLogToDelete + ",Op='" + operationType + "')";
        String serverResponse = getServerResponse(resourceToDelete, "DELETE", null);
        return serverResponse;
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

    public String getServerDateTime() {
        String serverResponse = getServerResponse("ara", "GET", null);
        String dateTimeInStringFormat = (String) MyJasonEntityConverter.formatJsonInputWithOneEntry(serverResponse).get(0).get("value");
        DateTime dateTimeInDateTimeFormat = null;
        String dateTimeResult = null;
        try{
            dateTimeInDateTimeFormat = new DateTime(dateTimeInStringFormat);
            dateTimeResult = dateTimeInDateTimeFormat.toString();
        } catch(Exception e) {
            LogAndToastMaker.makeErrorLog(e.getMessage());
            dateTimeResult = GlobalParameterController.OPERATION_FAIL;
        }
        return dateTimeResult;
    }
}
