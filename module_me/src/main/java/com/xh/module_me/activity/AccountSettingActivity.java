package com.xh.module_me.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.xh.module.base.BackActivity;
import com.xh.module.base.entity.LoginInfo;
import com.xh.module.base.utils.SharedPreferencesUtil;
import com.xh.module_me.R;
import com.xh.module_me.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 账号设置
 */
public class AccountSettingActivity extends BackActivity {

    @BindView(R2.id.nickNameTv)
    TextView nickNameTv;
    @BindView(R2.id.phoneTv)
    TextView phoneTv;

    LoginInfo loginInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);

        ButterKnife.bind(this);

        loginInfo = SharedPreferencesUtil.loadLogin(this);

        nickNameTv.setText(loginInfo.getNickName());
        phoneTv.setText(loginInfo.getMobile());
    }

    @OnClick(R2.id.changePwdTv)
    void onChangePwdClick() {
        startActivity(new Intent(this, UpdatePwdActivity.class));
    }

}
