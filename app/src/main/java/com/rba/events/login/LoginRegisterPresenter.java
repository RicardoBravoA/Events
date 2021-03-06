package com.rba.events.login;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.rba.events.base.BasePresenter;
import com.rba.events.model.entity.UserEntity;
import com.rba.events.util.UiUtil;

/**
 * Created by Ricardo Bravo on 2/03/18.
 */

public class LoginRegisterPresenter implements BasePresenter<LoginRegisterView>, LoginRegisterCallback {

    protected LoginRegisterView loginRegisterView = null;

    @Override
    public void attach(LoginRegisterView view) {
        loginRegisterView = view;
    }

    public boolean validEmail(String email) {

        if (!UiUtil.validEmail(email)) {
            loginRegisterView.showErrorEmail();
            return false;
        }

        return true;
    }

    public boolean validPassword(String password) {

        if (!UiUtil.validPassword(password)) {
            loginRegisterView.showErrorPassword();
            return false;
        }

        return true;
    }

    void login(FirebaseAuth firebaseAuth, String email, String password) {
        loginRegisterView.showLoading();
        LoginInteractor.onLogin(firebaseAuth, email, password, this);
    }

    void validSession(Context context, String uid) {
        if (LoginInteractor.isSession(context, uid)) {
            loginRegisterView.nextActivity();
        }
    }

    void addSession(Context context, UserEntity userEntity) {
        LoginInteractor.addSession(context, userEntity);
    }

    void deleteSession(Context context) {
        LoginInteractor.deleteSession(context);
    }

    @Override
    public void detach() {
        if (loginRegisterView != null) {
            loginRegisterView = null;
        }
    }

    @Override
    public void onResponse() {
        loginRegisterView.onResponse();
        loginRegisterView.hideLoading();
    }

    @Override
    public void onError(String error) {
        loginRegisterView.hideLoading();
        loginRegisterView.onError(error);
    }

    @Override
    public void onFailure() {
        loginRegisterView.hideLoading();
        loginRegisterView.onFailure();
    }
}
