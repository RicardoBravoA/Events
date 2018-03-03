package com.rba.events.login;

/**
 * Created by Ricardo Bravo on 2/03/18.
 */

public interface LoginView {


    void init();

    void showErrorEmail();

    void showErrorPassword();

    void hideErrorEmail();

    void hideErrorPassword();

    void showLoading();

    void hideLoading();

    void login();

    void onResponse();

    void onError();

}
