package com.rba.events;

import com.rba.events.login.LoginActivity;
import com.rba.events.login.LoginRegisterPresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.mockito.Mockito.verify;

/**
 * Created by Ricardo Bravo on 3/03/18.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class LoginActivityTest {

    private LoginActivity loginActivity;
    @Mock
    LoginRegisterPresenter loginRegisterPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        loginActivity = new LoginActivity();
    }

    @Test
    public void testEmpty() throws Exception {
        verify(loginRegisterPresenter).attach(loginActivity);
        //Assert.assertThat("abc").;
        //assertThat(loginActivity.etEmail.getText().toString()).isEmpty();
    }


}
