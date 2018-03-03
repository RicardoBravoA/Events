package com.rba.events.util;


import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Ricardo Bravo on 2/03/18.
 */

public class UiUtil {

    public static boolean validEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean validPassword(String password){
        return password.length() > 5;
    }



}
