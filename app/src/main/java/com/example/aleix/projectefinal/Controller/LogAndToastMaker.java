package com.example.aleix.projectefinal.Controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Michal.hostienda on 14/05/2015.
 */
public class LogAndToastMaker {

    public static void makeErrorLog(String errorMessage) {
        Log.e("ERROR!", errorMessage);
    }

    public static void makeInfoLog(String infoMessage) {
        Log.i("INFO!", infoMessage);
    }

    public static void makeToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
