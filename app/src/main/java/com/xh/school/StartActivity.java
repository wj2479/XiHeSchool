package com.xh.school;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.xh.module.base.Constant;
import com.xh.module.base.entity.UserBase;
import com.xh.module.base.repository.DataRepository;
import com.xh.module.base.repository.impl.UserRepository;
import com.xh.module.base.retrofit.IRxJavaCallBack;
import com.xh.module.base.retrofit.ResponseCode;
import com.xh.module.base.retrofit.response.SimpleResponse;
import com.xh.module.base.utils.LogUtil;
import com.xh.module.base.utils.SharedPreferencesUtil;
import com.xh.school.ui.login.LoginActivity;

/**
 * 程序启动引导界面
 */
public class StartActivity extends AppCompatActivity {

    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏以及状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /**标题是属于View的，所以窗口所有的修饰部分被隐藏后标题依然有效,需要去掉标题**/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_start);

        initCount();
    }

    CountDownTimer countDownTimer = null;
    private int countTime = 3;

    /**
     * 倒计时
     */
    private void initCount() {
        countDownTimer = new CountDownTimer(countTime * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.e("TAG", "倒计时:" + countTime--);
            }

            /**
             *倒计时结束后调用的
             */
            @Override
            public void onFinish() {
                // 获取保存的用户名  密码
                String savedUserName = SharedPreferencesUtil.get(StartActivity.this, Constant.SAVE_LOGIN_USERNAME);
                String savedPassWord = SharedPreferencesUtil.get(StartActivity.this, Constant.SAVE_LOGIN_PASSWORD);

                if (!TextUtils.isEmpty(savedUserName) && !TextUtils.isEmpty(savedPassWord)) {
                    UserRepository.getInstance().login(savedUserName, savedPassWord, new IRxJavaCallBack<SimpleResponse<UserBase>>() {
                        @Override
                        public void onSuccess(SimpleResponse<UserBase> simpleResponse) {
                            if (simpleResponse.getCode() == ResponseCode.RESULT_OK) {
                                UserBase loginInfo = simpleResponse.getData();
                                LogUtil.e("TAG", "自动登陆成功:" + gson.toJson(loginInfo));
                                SharedPreferencesUtil.saveLogin(StartActivity.this, loginInfo);
                                DataRepository.userInfo = loginInfo;
                                getMain();
                            } else {
                                getLogin();
                            }
                        }

                        @Override
                        public void onError(Throwable throwable) {
                            LogUtil.e("TAG", "自动登陆异常:" + throwable.toString());
                            getLogin();
                        }
                    });
                } else {
                    getLogin();
                }
            }

        };
        countDownTimer.start();
    }

    public void getLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void getMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }
}
