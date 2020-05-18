package com.xh.module_me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xh.module.base.BaseFragment;
import com.xh.module.base.entity.UserBase;
import com.xh.module.base.utils.RouteUtils;
import com.xh.module.base.utils.SharedPreferencesUtil;
import com.xh.module_me.activity.SettingMainActivity;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 个人中心主页
 */
@Route(path = RouteUtils.Me_Fragment_Main)
public class MeMainFragment extends BaseFragment {

    @BindView(R2.id.tv_name)
    TextView nameTv;

    UserBase loginInfo;

    public MeMainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginInfo = SharedPreferencesUtil.loadLogin(getContext());
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_me_main;
    }

    @Override
    protected void initView(View rootView) {
        nameTv.setText(loginInfo.getNickName());
    }

    @OnClick(R2.id.ll_setting)
    void onSettingClick() {
        startActivity(new Intent(getContext(), SettingMainActivity.class));
    }
}
