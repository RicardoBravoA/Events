package com.rba.events.login;

import com.google.firebase.auth.FirebaseAuth;
import com.rba.events.base.BasePresenter;
import com.rba.events.util.UiUtil;

/**
 * Created by Ricardo Bravo on 2/03/18.
 */

public class LoginPresenter implements BasePresenter<LoginView>, LoginCallback {

    private LoginView loginView = null;

    @Override
    public void attach(LoginView view) {
        loginView = view;
    }

    boolean validEmail(String email) {

        if (!UiUtil.validEmail(email)) {
            loginView.showErrorEmail();
            return false;
        }

        return true;
    }

    boolean validPassword(String password) {

        if (!UiUtil.validPassword(password)) {
            loginView.showErrorPassword();
            return false;
        }

        return true;
    }

    void login(FirebaseAuth firebaseAuth, String email, String password) {
        loginView.showLoading();
        LoginInteractor.onLogin(firebaseAuth, email, password, this);
    }

    @Override
    public void detach() {
        if (loginView != null) {
            loginView = null;
        }
    }

    @Override
    public void onResponse() {
        loginView.onResponse();
        loginView.hideLoading();
    }

    @Override
    public void onError() {
        loginView.hideLoading();
        loginView.onError();
    }

}
