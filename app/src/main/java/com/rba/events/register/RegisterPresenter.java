package com.rba.events.register;

import com.google.firebase.auth.FirebaseAuth;
import com.rba.events.login.LoginRegisterPresenter;

/**
 * Created by Ricardo Bravo on 2/03/18.
 */

class RegisterPresenter extends LoginRegisterPresenter {

    void register(FirebaseAuth firebaseAuth, String email, String password) {
        loginRegisterView.showLoading();
        RegisterInteractor.onRegister(firebaseAuth, email, password, this);
    }

}
