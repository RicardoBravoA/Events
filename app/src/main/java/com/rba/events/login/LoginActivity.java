package com.rba.events.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.rba.events.MainActivity;
import com.rba.events.R;
import com.rba.events.base.BaseActivity;
import com.rba.events.register.RegisterActivity;
import com.rba.events.util.UiUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnTextChanged;

/**
 * Created by Ricardo Bravo on 2/03/18.
 */

public class LoginActivity extends BaseActivity implements LoginRegisterView {

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
    @BindView(R.id.ll_login)
    LinearLayout llLogin;
    private LoginRegisterPresenter loginRegisterPresenter;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

    }

    @Override
    public void init() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        loginRegisterPresenter = new LoginRegisterPresenter();
        loginRegisterPresenter.attach(this);
        firebaseAuth = FirebaseAuth.getInstance();
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

        if (!loginRegisterPresenter.validEmail(email)) {
            return;
        }

        if (!loginRegisterPresenter.validPassword(password)) {
            return;
        }

        loginRegisterPresenter.login(firebaseAuth, email, password);

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

        if(actionId == EditorInfo.IME_ACTION_GO){
            UiUtil.hideKeyboard(this, etPassword);
            validData();
            return true;
        }

        return false;
    }

    @Override
    public void onResponse() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onError(String error) {
        showSnackBar(llLogin, error);
    }

    @Override
    public void onFailure() {
        showSnackBar(llLogin, getString(R.string.error));
    }

    @OnClick(R.id.btn_enter)
    void onClickLogin() {
        validData();
    }

    @OnClick(R.id.tv_register)
    void onClickRegister() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginRegisterPresenter.detach();
    }
}
