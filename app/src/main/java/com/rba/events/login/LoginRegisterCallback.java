package com.rba.events.login;

/**
 * Created by Ricardo Bravo on 2/03/18.
 */

public interface LoginRegisterCallback {

    void onResponse();

    void onError(String error);

    void onFailure();

}
