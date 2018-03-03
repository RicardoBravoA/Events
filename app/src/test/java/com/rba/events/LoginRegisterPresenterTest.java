package com.rba.events;

import com.rba.events.login.LoginInteractor;
import com.rba.events.login.LoginRegisterPresenter;
import com.rba.events.login.LoginRegisterView;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by Ricardo Bravo on 3/03/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class LoginRegisterPresenterTest {

    @Mock
    LoginInteractor loginInteractor;
    @Mock
    LoginRegisterView loginRegisterView;
    @Mock
    private LoginRegisterPresenter loginRegisterPresenter;

    @Before
    public void setUp() throws Exception {
        loginRegisterPresenter.attach(loginRegisterView);
    }

    @Test
    public void validEmailTrue(){
        String email = "ricardo@gmailcom";
        //Assert.assertTrue(loginRegisterPresenter.validEmail(email));
    }


    @After
    public void onDetach() throws Exception {
        loginRegisterPresenter.detach();
    }


}
