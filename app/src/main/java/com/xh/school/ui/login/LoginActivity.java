package com.xh.school.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.xh.module.base.BaseActivity;
import com.xh.module.base.entity.LoginInfo;
import com.xh.module.base.utils.SharedPreferencesUtil;
import com.xh.school.ForgetPwdActivity;
import com.xh.school.MainActivity;
import com.xh.school.R;


public class LoginActivity extends BaseActivity {

    private LoginViewModel loginViewModel;

    private ProgressDialog pDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = findViewById(R.id.et_username);
        final EditText passwordEditText = findViewById(R.id.et_password);
        final Button loginButton = findViewById(R.id.bt_login);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(true);
        //这里是设置进度条的风格,HORIZONTAL是水平进度条,SPINNER是圆形进度条
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //这里设置的是是否显示进度,设为false才是显示的哦！
        pDialog.setIndeterminate(true);

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
//                pDialog.dismiss();
                if (loginResult == null) {
                    return;
                }

                if (loginResult.getError() != null) {
                }
                if (loginResult.getSuccess() != null) {
                    LoginInfo loginInfo = loginResult.getSuccess();
                    SharedPreferencesUtil.saveLogin(LoginActivity.this, loginInfo);
                    goMainActivity();
                }
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog.setMessage("正在登录...");
                pDialog.show();
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        });

    }


    /**
     * 进入到主界面
     */
    private void goMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }

    /**
     * 忘记密码
     *
     * @param view
     */
    public void forgetpwd(View view) {
        Intent intent = new Intent(this, ForgetPwdActivity.class);
        startActivity(intent);
    }
}
