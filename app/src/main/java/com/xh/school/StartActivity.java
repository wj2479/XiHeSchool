package com.xh.school;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.xh.school.ui.login.LoginActivity;

/**
 * 程序启动引导界面
 */
public class StartActivity extends AppCompatActivity {

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
                getHome();
            }

        };
        countDownTimer.start();
    }


    public void getHome() {
        Intent intent = new Intent(this, LoginActivity.class);
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
