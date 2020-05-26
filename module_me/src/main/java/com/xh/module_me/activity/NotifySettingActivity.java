package com.xh.module_me.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xh.module.base.BackActivity;
import com.xh.module.base.utils.RouteUtils;
import com.xh.module_me.R;

/**
 * 消息通知
 */
@Route(path = RouteUtils.Me_Activity_Msg_Setting)
public class NotifySettingActivity extends BackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_setting);
    }
}
