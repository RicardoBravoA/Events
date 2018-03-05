package com.rba.events.register;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.rba.events.R;
import com.rba.events.base.BaseActivity;
import com.rba.events.login.LoginRegisterView;
import com.rba.events.util.NetworkUtil;
import com.rba.events.util.UiUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnTextChanged;

import static android.view.View.GONE;

/**
 * Created by Ricardo Bravo on 2/03/18.
 */

public class RegisterActivity extends BaseActivity implements LoginRegisterView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.til_password)
    TextInputLayout tilPassword;
    @BindView(R.id.et_password)
    AppCompatEditText etPassword;
    @BindView(R.id.til_email)
    TextInputLayout tilEmail;
    @BindView(R.id.et_email)
    AppCompatEditText etEmail;
    @BindView(R.id.ll_register)
    LinearLayout llRegister;
    @BindView(R.id.btn_enter)
    AppCompatButton btnEnter;
    @BindView(R.id.cb_remember)
    AppCompatCheckBox cbRemember;
    private RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
    }

    @Override
    public void init() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        registerPresenter = new RegisterPresenter();
        registerPresenter.attach(this);
        firebaseAuth = FirebaseAuth.getInstance();
        btnEnter.setText(R.string.title_activity_register);
        cbRemember.setVisibility(GONE);
    }

    @Override
    public void showErrorEmail() {
        tilEmail.setErrorEnabled(true);
        tilEmail.setError(getString(R.string.error_email));
        requestFocus(etEmail);
    }

    @Override
    public void showErrorPassword() {
        tilPassword.setErrorEnabled(true);
        tilPassword.setError(getString(R.string.error_password));
        requestFocus(etPassword);
    }

    @Override
    public void hideErrorEmail() {
        if (tilEmail.isErrorEnabled()) {
            tilEmail.setErrorEnabled(false);
        }
    }

    @Override
    public void hideErrorPassword() {
        if (tilPassword.isErrorEnabled()) {
            tilPassword.setErrorEnabled(false);
        }
    }

    @Override
    public void validData() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (!registerPresenter.validEmail(email)) {
            return;
        }

        if (!registerPresenter.validPassword(password)) {
            return;
        }

        if (NetworkUtil.isOnline(this)) {
            registerPresenter.register(firebaseAuth, email, password);
        } else {
            showInternetMessage();
        }

    }

    @OnTextChanged(R.id.et_email)
    void onEmailTextChanged() {
        if (tilEmail.isErrorEnabled()) {
            tilEmail.setErrorEnabled(false);
        }
    }

    @OnTextChanged(R.id.et_password)
    void onPasswordTextChanged() {
        if (tilPassword.isErrorEnabled()) {
            tilPassword.setErrorEnabled(false);
        }
    }

    @OnEditorAction(R.id.et_password)
    boolean onPasswordEditorAction(int actionId) {

        if (actionId == EditorInfo.IME_ACTION_GO) {
            UiUtil.hideKeyboard(this, etPassword);
            validData();
            return true;
        }

        return false;
    }

    @Override
    public void onResponse() {
        showSnackBar(llRegister, getString(R.string.message_register));
    }

    @Override
    public void onError(String error) {
        showSnackBar(llRegister, error);
    }

    @Override
    public void onFailure() {
        showSnackBar(llRegister, getString(R.string.error));
    }

    @Override
    public void showInternetMessage() {
        showSnackBar(llRegister, getString(R.string.message_internet));
    }

    @Override
    public void nextActivity() {
        //Only for login
    }

    @OnClick(R.id.btn_enter)
    void onClickLogin() {
        validData();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        registerPresenter.detach();
    }
}
