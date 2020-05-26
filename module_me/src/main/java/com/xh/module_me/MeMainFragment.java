package com.xh.module_me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.xh.module.base.BaseFragment;
import com.xh.module.base.entity.School;
import com.xh.module.base.entity.UserBase;
import com.xh.module.base.utils.PathUtils;
import com.xh.module.base.utils.RouteUtils;
import com.xh.module.base.utils.SharedPreferencesUtil;
import com.xh.module_me.activity.AboutActivity;
import com.xh.module_me.activity.FeedbackActivity;
import com.xh.module_me.activity.SettingMainActivity;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * 个人中心主页
 */
@Route(path = RouteUtils.Me_Fragment_Main)
public class MeMainFragment extends BaseFragment {

    @BindView(R2.id.tv_name)
    TextView nameTv;
    @BindView(R2.id.profile_image)
    CircleImageView circleImageView;
    @BindView(R2.id.tv_job)
    TextView jobTv;

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
        nameTv.setText(loginInfo.getRealName());

        Glide.with(this).load(PathUtils.composePath(loginInfo.getFace())).error(R.drawable.graduated).into(circleImageView);
    }

    @Subscribe()
    public void onSchoolInfo(School school) {
        jobTv.setText(school.getName());
    }

    @OnClick(R2.id.ll_setting)
    void onSettingClick() {
        startActivity(new Intent(getContext(), SettingMainActivity.class));
    }

    @OnClick(R2.id.ll_about)
    void onAboutClick() {
        startActivity(new Intent(getContext(), AboutActivity.class));
    }

    @OnClick(R2.id.ll_feedback)
    void onFeedbackClick() {
        startActivity(new Intent(getContext(), FeedbackActivity.class));
    }

    @OnClick(R2.id.ll_version)
    void onVersionClick() {
        showInfoDialogAndDismiss("当前已是最新版本");
    }
}
