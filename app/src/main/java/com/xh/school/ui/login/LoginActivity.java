package com.xh.school.ui.login;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xh.module.base.BaseActivity;
import com.xh.module.base.Constant;
import com.xh.module.base.entity.UserBase;
import com.xh.module.base.repository.DataRepository;
import com.xh.module.base.utils.LogUtil;
import com.xh.module.base.utils.RouteUtils;
import com.xh.module.base.utils.SharedPreferencesUtil;
import com.xh.school.MainActivity;
import com.xh.school.R;
import com.xh.school.activity.ForgetPwdActivity;

/**
 * 登录界面
 */
@Route(path = RouteUtils.APP_Activity_Login)
public class LoginActivity extends BaseActivity {

    private LoginViewModel loginViewModel;

    private Drawable mShowDrawable;//显示密码的图片
    private Drawable mHideDrawable;//隐藏密码的图片

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        mShowDrawable = getResources().getDrawable(R.drawable.ic_password_visable);
        mHideDrawable = getResources().getDrawable(R.drawable.ic_password_invisable);
        mShowDrawable.setBounds(0, 0, mShowDrawable.getIntrinsicWidth(), mShowDrawable.getIntrinsicHeight());
        mHideDrawable.setBounds(0, 0, mHideDrawable.getIntrinsicWidth(), mHideDrawable.getIntrinsicHeight());

        final EditText usernameEditText = findViewById(R.id.et_username);
        final EditText passwordEditText = findViewById(R.id.et_password);
        final Button loginButton = findViewById(R.id.bt_login);

        //获取保存的用户名
        String savedUserName = SharedPreferencesUtil.get(LoginActivity.this, Constant.SAVE_LOGIN_USERNAME);
        if (!TextUtils.isEmpty(savedUserName)) {
            usernameEditText.setText(savedUserName);
            usernameEditText.setSelection(savedUserName.length());
        }

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
//                pDialog.dismiss();

                dismissDialog();
                if (loginResult == null) {
                    return;
                }

                if (loginResult.getError() != null) {
                    showFailDialogAndDismiss(loginResult.getError());
                    LogUtil.e("TAG", "登录失败:" + loginResult.getError());
                    return;
                }
                if (loginResult.getSuccess() != null) {
                    UserBase loginInfo = loginResult.getSuccess();

                    LogUtil.e("TAG", "登陆成功:" + gson.toJson(loginInfo));
                    SharedPreferencesUtil.saveLogin(LoginActivity.this, loginInfo);
                    DataRepository.userInfo = loginInfo;
                    // 保存登录的用户名
                    SharedPreferencesUtil.save(LoginActivity.this, Constant.SAVE_LOGIN_USERNAME, usernameEditText.getText().toString());
                    SharedPreferencesUtil.save(LoginActivity.this, Constant.SAVE_LOGIN_PASSWORD, passwordEditText.getText().toString());
                    goMainActivity();
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                pDialog.setMessage("正在登录...");
//                pDialog.show();

                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (TextUtils.isEmpty(username)) {
                    showInfoDialogAndDismiss("用户名不能为空");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    showInfoDialogAndDismiss("密码不能为空");
                    return;
                }

                showLoadingDialog("正在登录");
                loginViewModel.login(username, password);
            }
        });

        passwordEditText.setTag(false);

        passwordEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // et.getCompoundDrawables()得到一个长度为4的数组，分别表示左右上下四张图片
                Drawable drawable = passwordEditText.getCompoundDrawables()[2];
                //如果右边没有图片，不再处理
                if (drawable == null)
                    return false;
                //如果不是按下事件，不再处理
                if (event.getAction() != MotionEvent.ACTION_UP)
                    return false;
                if (event.getX() > passwordEditText.getWidth()
                        - passwordEditText.getPaddingRight()
                        - drawable.getIntrinsicWidth()) {

                    if (passwordEditText.getTag().equals(false)) {
                        passwordEditText.setTag(true);
                        passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        passwordEditText.setCompoundDrawables(passwordEditText.getCompoundDrawables()[0], passwordEditText.getCompoundDrawables()[1], mShowDrawable, passwordEditText.getCompoundDrawables()[3]);
                    } else {
                        passwordEditText.setTag(false);
                        passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        passwordEditText.setCompoundDrawables(passwordEditText.getCompoundDrawables()[0], passwordEditText.getCompoundDrawables()[1], mHideDrawable, passwordEditText.getCompoundDrawables()[3]);
                    }
                }
                return false;

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
