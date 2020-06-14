package com.xh.module_me.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.qmuiteam.qmui.skin.QMUISkinManager;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.tamsiree.rxkit.RxDeviceTool;
import com.xh.module.base.BaseActivity;
import com.xh.module.base.entity.pay.BankResult;
import com.xh.module.base.entity.pay.UserRealauth;
import com.xh.module.base.repository.DataRepository;
import com.xh.module.base.repository.impl.PayRepository;
import com.xh.module.base.retrofit.IRxJavaCallBack;
import com.xh.module.base.retrofit.ResponseCode;
import com.xh.module.base.retrofit.response.SimpleResponse;
import com.xh.module.base.utils.DeviceTool;
import com.xh.module_me.R;
import com.xh.module_me.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 支付主界面
 */
public class PayMainActivity extends BaseActivity {

    @BindView(R2.id.openIdTv)
    TextView openIdTv;
    @BindView(R2.id.serviceIdTv)
    TextView serviceIdTv;
    @BindView(R2.id.channel_sernoTv)
    TextView channel_sernoTv;
    @BindView(R2.id.orgNoTv)
    TextView orgNoTv;
    @BindView(R2.id.acct_numberTv)
    TextView acct_numberTv;
    public static final String AUTHED = "已认证";
    public static final String NOT_AUTHED = "未认证";
    public static final String WAIT_AUTHED = "待审核";
    @BindView(R2.id.authTv)
    TextView authTv;
    @BindView(R2.id.remainLayout)
    View remainLayout;
    UserRealauth realauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_main);

        ButterKnife.bind(this);

        QMUIStatusBarHelper.setStatusBarLightMode(PayMainActivity.this);
        requestUserRealAuthStatus();
    }

    /**
     * 请求实名认证状态
     */
    private void requestUserRealAuthStatus() {
        PayRepository.getInstance().getRealAuthStatus(DataRepository.userInfo.getUid(), new IRxJavaCallBack<SimpleResponse<UserRealauth>>() {
            @Override
            public void onSuccess(SimpleResponse<UserRealauth> response) {
                Log.e("TAG", "用户实名认证：" + gson.toJson(response));
                if (response.getCode() == ResponseCode.RESULT_OK) {
                    realauth = response.getData();
                    if (realauth.getState() == 1) {    // 认证通过
                        authTv.setHint(AUTHED);
                    } else if (realauth.getState() == 0) {
                        authTv.setHint(WAIT_AUTHED);
                    } else {
                        authTv.setHint(NOT_AUTHED);
                    }
                } else {
                    authTv.setHint(NOT_AUTHED);
                }
            }

            @Override
            public void onError(Throwable throwable) {
                Log.e("TAG", "用户实名认证请求异常：" + throwable.toString());
                authTv.setHint(NOT_AUTHED);
            }
        });
    }

    @OnClick(R2.id.backIv)
    void onBackIvClick() {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 100:
                    authTv.setHint(WAIT_AUTHED);
                    break;
            }
        }
    }

    @OnClick(R2.id.remainLayout)
    void onRemainClick() {
        if (realauth == null) {
            showInfoDialogAndDismiss("请先进行实名认证");
            startActivityForResult(new Intent(this, UploadUserRealAuthActivity.class), 100);
            return;
        }
//        if (realauth.getState() == 0) {
//            showInfoDialogAndDismiss("您已提交审核，请耐心等待");
//            return;
//        }

        remainLayout.setEnabled(false);
        String macAddress = RxDeviceTool.getMacAddress(this);
        PayRepository.getInstance().requestWallet("Android", Build.MODEL, macAddress, "119.521273,35.417427",
                DeviceTool.getIMEI(this), "192.168.1.100", DataRepository.userInfo.getUid(),
                new IRxJavaCallBack<BankResult>() {
                    @Override
                    public void onSuccess(BankResult bankResult) {
                        Log.e("TAG", "请求钱包接口:" + gson.toJson(bankResult));
                        if (bankResult.getEncryptedData().getCode().equals("000000")) {
                            String url = bankResult.getEncryptedData().getData().getTokenUrl();
                            Intent intent = new Intent(PayMainActivity.this, PayWebPageActivity.class);
                            intent.putExtra("url", url);
                            startActivity(intent);
                        }
                        remainLayout.setEnabled(true);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.e("TAG", "钱包接口异常:" + throwable.toString());
                        showFailDialogAndDismiss("进入钱包失败");
                        remainLayout.setEnabled(true);
                    }
                });
    }

    @OnClick(R2.id.authTv)
    void onAuthTvClick() {
        String text = authTv.getHint().toString();
        switch (text) {
            case AUTHED:
                startActivity(new Intent(this, UserRealAuthStatusActivity.class).putExtra("userauth", realauth));
                break;
            case NOT_AUTHED:
                startActivityForResult(new Intent(this, UploadUserRealAuthActivity.class), 100);
                break;
            case WAIT_AUTHED:
                showInfoDialogAndDismiss("已经提交审核，请耐心等待");
                break;
        }
    }

    @OnClick(R2.id.serviceIdTv)
    void onClick2() {
        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(this);
        builder.setTitle("输入")
                .setSkinManager(QMUISkinManager.defaultInstance(this))
                .setDefaultText(serviceIdTv.getText())
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        CharSequence text = builder.getEditText().getText();
                        serviceIdTv.setText(text);
                        dialog.dismiss();
                    }
                })
                .create(R.style.QMUI_Dialog).show();

    }

    @OnClick(R2.id.channel_sernoTv)
    void onClick3() {
        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(this);
        builder.setTitle("输入")
                .setSkinManager(QMUISkinManager.defaultInstance(this))
                .setDefaultText(channel_sernoTv.getText())
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        CharSequence text = builder.getEditText().getText();
                        channel_sernoTv.setText(text);
                        dialog.dismiss();
                    }
                })
                .create(R.style.QMUI_Dialog).show();

    }

    @OnClick(R2.id.orgNoTv)
    void onClick4() {
        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(this);
        builder.setTitle("输入")
                .setSkinManager(QMUISkinManager.defaultInstance(this))
                .setDefaultText(orgNoTv.getText())
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        CharSequence text = builder.getEditText().getText();
                        orgNoTv.setText(text);
                        dialog.dismiss();
                    }
                })
                .create(R.style.QMUI_Dialog).show();
    }

    @OnClick(R2.id.acct_numberTv)
    void onClick5() {
        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(this);
        builder.setTitle("输入")
                .setSkinManager(QMUISkinManager.defaultInstance(this))
                .setDefaultText(acct_numberTv.getText())
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        CharSequence text = builder.getEditText().getText();
                        acct_numberTv.setText(text);
                        dialog.dismiss();
                    }
                })
                .create(R.style.QMUI_Dialog).show();

    }

    @OnClick(R2.id.unpaidOrderLayout)
    void onUnpaidOrderClick() {
        if (realauth == null) {
            showInfoDialogAndDismiss("请先进行实名认证");
            startActivityForResult(new Intent(this, UploadUserRealAuthActivity.class), 100);
            return;
        }
//        if (realauth.getState() == 0) {
//            showInfoDialogAndDismiss("请等待实名认证完成");
//            return;
//        }

        startActivity(new Intent(this, UnpaidOrderInfoActivity.class));
    }

    @OnClick(R2.id.paidOrderLayout)
    void onPaidOrderClick() {
        if (realauth == null) {
            showInfoDialogAndDismiss("请先进行实名认证");
            startActivityForResult(new Intent(this, UploadUserRealAuthActivity.class), 100);
            return;
        }
//        if (realauth.getState() == 0) {
//            showInfoDialogAndDismiss("您已提交审核，请耐心等待");
//            return;
//        }
        startActivity(new Intent(this, PaidOrderActivity.class));
    }
}
