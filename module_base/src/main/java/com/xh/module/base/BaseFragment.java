package com.xh.module.base;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 所有Fragment的基类
 */
public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;
    protected RxPermissions rxPermissions;

    protected Gson gson = new Gson();

    QMUITipDialog tipDialog;

    protected  Handler mHandler = new Handler();

    protected abstract int initLayout();

    protected abstract void initView(View rootView);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rxPermissions = new RxPermissions(this);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(initLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String event) {
    }

    /**
     * 显示信息提示对话框，不消失
     *
     * @param info
     */
    protected void showInfoDialog(String info) {
        tipDialog = new QMUITipDialog.Builder(getContext())
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
        tipDialog = new QMUITipDialog.Builder(getContext())
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
        tipDialog = new QMUITipDialog.Builder(getContext())
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
        tipDialog = new QMUITipDialog.Builder(getContext())
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
