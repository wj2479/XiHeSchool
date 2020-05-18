package com.xh.module_me.activity;

import android.content.Intent;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.tamsiree.rxkit.RxActivityTool;
import com.xh.module.base.BackActivity;
import com.xh.module.base.Constant;
import com.xh.module.base.utils.RouteUtils;
import com.xh.module.base.utils.SharedPreferencesUtil;
import com.xh.module_me.R;
import com.xh.module_me.R2;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 设置主界面
 */
public class SettingMainActivity extends BackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_main);

        ButterKnife.bind(this);
    }

    @OnClick(R2.id.ll_account_setting)
    void onAccountSettingClick() {
        startActivity(new Intent(this, AccountSettingActivity.class));
    }

    @OnClick(R2.id.ll_notify_setting)
    void onNotifySettingClick() {
        startActivity(new Intent(this, NotifySettingActivity.class));
    }

    // 退出登录
    @OnClick(R2.id.exitLayout)
    void onExitClick() {
        // 使用EventBus关闭所有的Activity
        EventBus.getDefault().post(Constant.EVENT_FINISH_ALL_ACTIVITY);
        // 不同Moudle的RxActivityTool不能互相关闭
        RxActivityTool.finishActivity();
        SharedPreferencesUtil.removeLogin(this);
        SharedPreferencesUtil.remove(this, Constant.SAVE_LOGIN_PASSWORD);
        ARouter.getInstance().build(RouteUtils.APP_Activity_Login).navigation();
    }

}
