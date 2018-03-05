package com.rba.events.register;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.rba.events.login.LoginRegisterCallback;

/**
 * Created by Ricardo Bravo on 2/03/18.
 */

class RegisterInteractor {

    static void onRegister(FirebaseAuth firebaseAuth, String email, String password,
                           final LoginRegisterCallback loginRegisterCallback) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            loginRegisterCallback.onResponse();
                        } else {
                            if (task.getException() != null && task.getException().getMessage() != null) {
                                loginRegisterCallback.onError(task.getException().getMessage());
                            } else {
                                loginRegisterCallback.onFailure();
                            }

                        }

                    }
                });
    }

}
