package com.example.aleix.projectefinal.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michal.hostienda on 12/05/2015.
 */
public class LoginController {

    SharedPreferences sharedPreferences;
    PersistanceManager persistanceManager;
    private static final String SHARED_PREFERENCES_FILE_NAME = "user_authentication";
    private static final int SHARED_PREFERENCES_FILE_MODE = Context.MODE_PRIVATE;
    private static final String SHARED_PREFERENCES_KEY = "password";

    public LoginController(Activity activity) {
        this.sharedPreferences = activity.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, SHARED_PREFERENCES_FILE_MODE);
        this.persistanceManager = new PersistanceManager(activity);
    }

    //De shared preferences sabrem si l'usuari s'ha loguejat anteriorment o no.
    public boolean validateUser(String userTypedPassword, boolean firstTimeLogging) {
        String hashRepresentationOfTypedPassword = makeHashVersionOfPassword(userTypedPassword);

        boolean keepRunningWhile = true;
        int i = 0;

        boolean userValidatedCorrectly = false;

        if (firstTimeLogging) {
            //Aqui les contrasenyes venen de la base de dades remota. La classe PersistanceManager s'ocupa de recuperar aquesta info. En comptes de new ArrayList s'ha de posar un metode de PersistanceManager.
            List<String> listOfHashedPasswordsFromRemoteDatabase = new ArrayList();
            while (keepRunningWhile && i < listOfHashedPasswordsFromRemoteDatabase.size()) {
                if (listOfHashedPasswordsFromRemoteDatabase.get(i).equalsIgnoreCase(hashRepresentationOfTypedPassword)) {
                    keepRunningWhile = false;
                    userValidatedCorrectly = true;
                }
                i++;
            }
            if(userValidatedCorrectly) {

            }
        } else {

        }
        return userValidatedCorrectly;
    }

    public String makeHashVersionOfPassword(String password) {
        String result = "";
        try {
            byte[] data = password.getBytes("UTF-8");
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
            Log.e("ERROR!", ex.getMessage());
        }
        return result.toUpperCase();
    }

    private void savePasswordLocally(String passwordToSave) {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putString(SHARED_PREFERENCES_KEY, passwordToSave);
        editor.apply();
    }

    private String retrievePasswordSavedLocally() {
        return sharedPreferences.getString(SHARED_PREFERENCES_KEY, null);
    }
}
