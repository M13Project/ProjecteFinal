package com.example.aleix.projectefinal.Controller;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Michal on 11/05/2015.
 */
public class JsonDataParser {

    public JsonDataParser() {

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
           // this.jsonParsed.setText(outputData);
        } catch (Exception e) {
            Log.e("Error", "Error in onPostExecte: " + e.getMessage());
        }
        return mappedAttributes;
    }
}
