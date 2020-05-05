package com.xh.module.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
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
    }
}
