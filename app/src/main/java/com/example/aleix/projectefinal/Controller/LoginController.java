package com.example.aleix.projectefinal.Controller;

import android.util.Log;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michal.hostienda on 12/05/2015.
 */
public class LoginController {

    public LoginController() {

    }

    //De shared preferences sabrem si l'usuari s'ha loguejat anteriorment o no.
    public boolean validateUser(String userTypedPassword, boolean firstTimeLogging) {
        String hashRepresentationOfTypedPassword = makeHashVersionOfPassword(userTypedPassword);

        boolean keepRunningWhile = true;
        int i = 0;

        boolean userValidatedCorrectly = false;

        if (firstTimeLogging) {
            //Aquí les contrasenyes venen de la base de dades remota. La classe PersistanceManager s'ocupa de recuperar aquesta info. En comptes de new ArrayList s'ha de posar un mètode de PersistanceManager.
            List<String> listOfHashedPasswordsFromRemoteDatabase = new ArrayList();
            while (keepRunningWhile && i < listOfHashedPasswordsFromRemoteDatabase.size()) {
                if (listOfHashedPasswordsFromRemoteDatabase.get(i).equalsIgnoreCase(hashRepresentationOfTypedPassword)) {
                    keepRunningWhile = false;
                    userValidatedCorrectly = true;
                }
                i++;
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
}
