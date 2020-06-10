package com.xh.module_me.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.TextView;

import com.qmuiteam.qmui.skin.QMUISkinManager;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.tamsiree.rxkit.RxDeviceTool;
import com.xh.module.base.BaseActivity;
import com.xh.module.base.Constant;
import com.xh.module.base.entity.pay.BankResult;
import com.xh.module.base.repository.DataRepository;
import com.xh.module.base.repository.impl.PayRepository;
import com.xh.module.base.retrofit.IRxJavaCallBack;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_main);

        ButterKnife.bind(this);

        QMUIStatusBarHelper.setStatusBarLightMode(PayMainActivity.this);
    }

    @OnClick(R2.id.backIv)
    void onBackIvClick() {
        finish();
    }

    @OnClick(R2.id.remainLayout)
    void onRemainClick() {
        String macAddress = RxDeviceTool.getMacAddress(this);
        PayRepository.getInstance().requestWallet("Android", Build.MODEL, macAddress, "119.521273,35.417427",
                DeviceTool.getIMEI(this), "192.168.1.100", DataRepository.userInfo.getUid(),
                new IRxJavaCallBack<BankResult>() {
                    @Override
                    public void onSuccess(BankResult bankResult) {
                        Log.e("TAG", "支付接口:" + gson.toJson(bankResult));
                        if (bankResult.getEncryptedData().getCode().equals("000000")) {
                            String url = bankResult.getEncryptedData().getData().getTokenUrl();
                            Intent intent = new Intent(Constant.INTENT_ACTION_WEB);
                            intent.putExtra("url", url);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.e("TAG", "出错了:" + throwable.toString());
                    }
                });
    }

    @OnClick(R2.id.openIdTv)
    void onClick1() {
        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(this);
        builder.setTitle("输入")
                .setSkinManager(QMUISkinManager.defaultInstance(this))
                .setDefaultText(openIdTv.getText())
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
                        openIdTv.setText(text);
                        dialog.dismiss();
                    }
                })
                .create(R.style.QMUI_Dialog).show();

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
        startActivity(new Intent(this, UnpaidOrderInfoActivity.class));
    }

    @OnClick(R2.id.paidOrderLayout)
    void onPaidOrderClick() {
        startActivity(new Intent(this, PaidOrderActivity.class));
    }
}
