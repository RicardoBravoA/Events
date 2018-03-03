package com.rba.events.login;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Ricardo Bravo on 2/03/18.
 */

class LoginInteractor {

    static void onLogin(FirebaseAuth firebaseAuth, String email, String password,
                               final LoginCallback loginCallback) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            loginCallback.onResponse();
                        } else {
                            loginCallback.onError();
                        }

                    }
                });
    }

}
