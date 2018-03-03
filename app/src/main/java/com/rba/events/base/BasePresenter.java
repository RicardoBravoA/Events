package com.rba.events.base;

/**
 * Created by Ricardo Bravo on 2/03/18.
 */

public interface BasePresenter<V> {

    void attach(V view);

    void detach();

}
