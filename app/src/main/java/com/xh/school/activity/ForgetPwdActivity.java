package com.xh.school.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.tamsiree.rxkit.RxRegTool;
import com.xh.module.base.BackActivity;
import com.xh.module.base.Constant;
import com.xh.module.base.repository.impl.UserRepository;
import com.xh.module.base.retrofit.IRxJavaCallBack;
import com.xh.module.base.retrofit.ResponseCode;
import com.xh.module.base.retrofit.response.SimpleResponse;
import com.xh.module.base.utils.LogUtil;
import com.xh.school.R;
import com.xh.school.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 忘记密码找回
 */
public class ForgetPwdActivity extends BackActivity {

    @BindView(R2.id.edt_mobile)
    EditText mobileEt;
    @BindView(R2.id.edt_code)
    EditText codeEt;
    @BindView(R2.id.edt_pwd_set)
    EditText pwdsetEt;
    @BindView(R2.id.edt_pwd_confirm)
    EditText pwdconfirmEt;

    @BindView(R2.id.tv_send)
    TextView sendTv;

    CountDownTimer countDownTimer = null;
    private int countTime = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);
        ButterKnife.bind(this);
    }

    @OnClick(R2.id.tv_send)
    void onSendClick() {
        String mobile = mobileEt.getText().toString();
        if (!RxRegTool.isMobileSimple(mobile)) {
            showInfoDialogAndDismiss("请输入正确的手机号");
            return;
        }

        sendTv.setEnabled(false);

        UserRepository.getInstance().sendSmsCode(mobile, Constant.SMS_CODE_RESET_PASSWORD, new IRxJavaCallBack<SimpleResponse>() {
            @Override
            public void onSuccess(SimpleResponse simpleResponse) {
                Log.e("TAG", "发送验证码" + simpleResponse);
                if (simpleResponse.getCode() == ResponseCode.RESULT_OK) {
                    startCount();
                } else {
                    showFailDialogAndDismiss(simpleResponse.getMsg());
                    sendTv.setEnabled(true);
                }
            }

            @Override
            public void onError(Throwable throwable) {
                showFailDialogAndDismiss("发送验证码失败,请稍后再试");
                sendTv.setEnabled(true);
            }
        });
    }

    /**
     * 倒计时
     */
    private void startCount() {
        countDownTimer = new CountDownTimer(countTime * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String formStr = String.format("%d秒后重新发送", (int) (millisUntilFinished / 1000));
                sendTv.setText(formStr);
            }

            /**
             *倒计时结束后调用的
             */
            @Override
            public void onFinish() {
                sendTv.setText("发送验证码");
                sendTv.setEnabled(true);
            }

        };
        countDownTimer.start();
    }

    private void stopCount() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }

    @OnClick(R2.id.bt_comment)
    void onSubmitClick() {
        String mobile = mobileEt.getText().toString().trim();
        String code = codeEt.getText().toString().trim();
        String newPwd = pwdsetEt.getText().toString().trim();
        String newPwd2 = pwdconfirmEt.getText().toString().trim();

        if (!RxRegTool.isMobileSimple(mobile)) {
            showInfoDialogAndDismiss("手机号码不合法");
            mobileEt.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(code)) {
            showInfoDialogAndDismiss("请输入验证码");
            codeEt.requestFocus();
            return;
        }

        if (newPwd.length() < 6) {
            showInfoDialogAndDismiss("新密码长度不合法");
            pwdsetEt.requestFocus();
            return;
        }
        if (newPwd2.length() < 6) {
            showInfoDialogAndDismiss("确认密码长度不合法");
            pwdconfirmEt.requestFocus();
            return;
        }

        if (!newPwd2.equals(newPwd)) {
            showInfoDialogAndDismiss("两次密码输入不同");
            return;
        }

        UserRepository.getInstance().resetPassword(mobile, code, newPwd, new IRxJavaCallBack<SimpleResponse>() {
            @Override
            public void onSuccess(SimpleResponse simpleResponse) {
                if (simpleResponse.getCode() == ResponseCode.RESULT_OK) {
                    showSuccessDialogAndFinish("密码重置成功");
                } else {
                    showFailDialogAndDismiss(simpleResponse.getMsg());
                }
            }

            @Override
            public void onError(Throwable throwable) {
                LogUtil.e("TAG", "异常：" + throwable.toString());
                showFailDialogAndDismiss("密码重置失败，请稍后再试");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopCount();
    }
}
