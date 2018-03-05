package com.rba.events.login;

/**
 * Created by Ricardo Bravo on 2/03/18.
 */

public interface LoginRegisterView {

    void init();

    void showErrorEmail();

    void showErrorPassword();

    void hideErrorEmail();

    void hideErrorPassword();

    void showLoading();

    void hideLoading();

    void validData();

    void onResponse();

    void onError(String error);

    void onFailure();

    void showInternetMessage();

    void nextActivity();

}
