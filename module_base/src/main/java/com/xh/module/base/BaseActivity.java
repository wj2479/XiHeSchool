package com.xh.module.base;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.tamsiree.rxkit.RxActivityTool;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * 所有Activity的基类
 */
public class BaseActivity extends AppCompatActivity {

    protected RxPermissions rxPermissions;

    protected Gson gson = new Gson();

    QMUITipDialog tipDialog;

    Handler mHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar();
        rxPermissions = new RxPermissions(this);
        EventBus.getDefault().register(this);
        RxActivityTool.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        RxActivityTool.removeActivity(this);
    }

    protected void setStatusBar() {
        //这里做了两件事情，1.使状态栏透明并使contentView填充到状态栏 2.预留出状态栏的位置，防止界面上的控件离顶部靠的太近。这样就可以实现开头说的第二种情况的沉浸式状态栏了
        StatusBarUtil.setTranslucent(this);
    }

    @Subscribe
    public void onEvent(String event) {
        if (event.equals(Constant.EVENT_FINISH_ALL_ACTIVITY)) {
            finish();
        }
    }

    /**
     * 显示信息提示对话框，不消失
     *
     * @param info
     */
    protected void showInfoDialog(String info) {
        tipDialog = new QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_INFO)
                .setTipWord(info)
                .create();
        tipDialog.show();
    }

    /**
     * 显示信息提示对话框，并消失
     *
     * @param info
     */
    protected void showInfoDialogAndDismiss(String info) {
        showInfoDialog(info);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismissDialog();
            }
        }, 1500);
    }

    /**
     * 显示成功提示对话框，不消失
     *
     * @param info
     */
    protected void showSuccessDialog(String info) {
        tipDialog = new QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                .setTipWord(info)
                .create();
        tipDialog.show();
    }

    /**
     * 显示成功提示对话框，并消失
     *
     * @param info
     */
    protected void showSuccessDialogAndDismiss(String info) {
        showSuccessDialog(info);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismissDialog();
            }
        }, 1500);
    }

    /**
     * 显示失败提示对话框，不消失
     *
     * @param info
     */
    protected void showFailDialog(String info) {
        tipDialog = new QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_FAIL)
                .setTipWord(info)
                .create();
        tipDialog.show();
    }

    /**
     * 显示失败提示对话框，并消失
     *
     * @param info
     */
    protected void showFailDialogAndDismiss(String info) {
        showFailDialog(info);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismissDialog();
            }
        }, 1500);
    }

    /**
     * 显示加载提示对话框，不消失
     *
     * @param info
     */
    protected void showLoadingDialog(String info) {
        tipDialog = new QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord(info)
                .create();
        tipDialog.show();
    }

    /**
     * 显示加载提示对话框，并消失
     *
     * @param info
     */
    protected void showLoadingDialogAndDismiss(String info) {
        showLoadingDialog(info);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismissDialog();
            }
        }, 1500);
    }

    /**
     * 提示对话框消失
     */
    protected void dismissDialog() {
        if (tipDialog != null) {
            tipDialog.dismiss();
            tipDialog = null;
        }
    }

}
