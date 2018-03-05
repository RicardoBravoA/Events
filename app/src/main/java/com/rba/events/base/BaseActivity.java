package com.rba.events.base;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;
import com.rba.events.R;

/**
 * Created by Ricardo Bravo on 2/03/18.
 */

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    private AlertDialog alertDialog = null;
    protected FirebaseAuth firebaseAuth;

    protected void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    protected static void showSnackBar(View view, String text) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show();
    }

    @SuppressLint("InflateParams")
    public void showLoading() {
        if (alertDialog == null) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            dialogBuilder.setView(getLayoutInflater().inflate(R.layout.layout_loading, null));
            alertDialog = dialogBuilder.create();
            alertDialog.show();
        } else {
            alertDialog.show();
        }
    }

    public void hideLoading() {
        if (alertDialog.isShowing()) {
            alertDialog.hide();
        }
    }

}
