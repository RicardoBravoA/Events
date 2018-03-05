package com.rba.events.login;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.rba.events.model.entity.UserEntity;
import com.rba.events.storage.UserTable;

/**
 * Created by Ricardo Bravo on 2/03/18.
 */

class LoginInteractor {

    static void onLogin(FirebaseAuth firebaseAuth, String email, String password,
                        final LoginRegisterCallback loginRegisterCallback) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
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

    static void addSession(Context context, UserEntity userEntity) {
        UserTable userTable = new UserTable(context);
        userTable.addUser(userEntity);
    }

    static void deleteSession(Context context) {
        UserTable userTable = new UserTable(context);
        userTable.deleteUser();
    }

    static boolean isSession(Context context, String uid) {
        UserTable userTable = new UserTable(context);
        return userTable.validUser(uid);
    }

}
