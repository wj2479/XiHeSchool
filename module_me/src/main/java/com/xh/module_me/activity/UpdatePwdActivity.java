package com.xh.module_me.activity;

import android.os.Bundle;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xh.module.base.BackActivity;
import com.xh.module.base.utils.RouteUtils;
import com.xh.module_me.R;
import com.xh.module_me.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 修改密码
 */
@Route(path = RouteUtils.Me_Activity_Update_Password)
public class UpdatePwdActivity extends BackActivity {

    @BindView(R2.id.edt_oldPwd)
    EditText oldPwdEt;
    @BindView(R2.id.edt_pwd)
    EditText newPwdEt;
    @BindView(R2.id.edt_pwd_confirm)
    EditText newPwdConfirmEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pwd);

        ButterKnife.bind(this);
    }

    @OnClick(R2.id.bt_comment)
    void onClick() {
        String oldPwd = oldPwdEt.getText().toString().trim();
        String newPwd = newPwdEt.getText().toString().trim();
        String newPwd2 = newPwdConfirmEt.getText().toString().trim();

        if (oldPwd.length() < 6) {
            showInfoDialogAndDismiss("旧密码长度不合法");
            oldPwdEt.requestFocus();
            return;
        }

        if (newPwd.length() < 6) {
            showInfoDialogAndDismiss("新密码长度不合法");
            newPwdEt.requestFocus();
            return;
        }

        if (!newPwd2.equals(newPwd)) {
            showInfoDialogAndDismiss("两次密码输入不同");
            newPwdConfirmEt.requestFocus();
            return;
        }

    }
}
