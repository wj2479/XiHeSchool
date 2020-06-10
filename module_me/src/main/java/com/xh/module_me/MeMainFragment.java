package com.xh.module_me;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.qmuiteam.qmui.skin.QMUISkinManager;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.xh.module.base.BaseFragment;
import com.xh.module.base.entity.Role;
import com.xh.module.base.entity.School;
import com.xh.module.base.entity.UserBase;
import com.xh.module.base.repository.DataRepository;
import com.xh.module.base.utils.PathUtils;
import com.xh.module.base.utils.RouteUtils;
import com.xh.module.base.utils.SharedPreferencesUtil;
import com.xh.module_me.activity.AboutActivity;
import com.xh.module_me.activity.FeedbackActivity;
import com.xh.module_me.activity.PayMainActivity;
import com.xh.module_me.activity.SettingMainActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R2.id.tv_role)
    TextView roleTv;

    UserBase loginInfo;

    public MeMainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginInfo = DataRepository.userInfo;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_me_main;
    }

    @Override
    protected void initView(View rootView) {
        nameTv.setText(loginInfo.getRealName());
        roleTv.setText(DataRepository.role.getName());
        Glide.with(this).load(PathUtils.composePath(loginInfo.getFace())).error(R.drawable.graduated).into(circleImageView);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
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

    @OnClick(R2.id.roleLayout)
    void onSwitchRoleClick() {
        showRoleChoiceDialog();
    }

    @OnClick(R2.id.ll_pay_layout)
    void onPayClick() {
        Intent intent = new Intent(getContext(), PayMainActivity.class);
        startActivity(intent);
    }

    /**
     * 显示角色切换对话框
     */
    private void showRoleChoiceDialog() {
        if (loginInfo.getRoles() == null) {
            showInfoDialogAndDismiss("获取身份信息失败");
            return;
        }

        int checkedIndex = -1;

        List<String> roleList = new ArrayList<>();
        for (int i = 0; i < loginInfo.getRoles().size(); i++) {
            Role role = loginInfo.getRoles().get(i);
            roleList.add(role.getName());
            if (role.getId().equals(DataRepository.role.getId())) {
                checkedIndex = i;
            }
        }

        String[] items = new String[roleList.size()];
        items = roleList.toArray(items);

        new QMUIDialog.CheckableDialogBuilder(getActivity())
                .setTitle("请选择要切换的身份")
                .setCheckedIndex(checkedIndex)
                .setSkinManager(QMUISkinManager.defaultInstance(getContext()))
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Role role = loginInfo.getRoles().get(which);
                        // 保存角色
                        SharedPreferencesUtil.saveRole(getContext(), role);
                        EventBus.getDefault().post(role);
                        roleTv.setText(role.getName());
                        showSuccessDialogAndDismiss("身份已经切换到 " + role.getName());
                    }
                })
                .create(R.style.QMUI_Dialog).show();
    }

}
