package com.xh.module_me.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.xh.module.base.BackActivity;
import com.xh.module.base.entity.pay.UserRealauth;
import com.xh.module_me.R;
import com.xh.module_me.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 用户实名认证状态
 */
public class UserRealAuthStatusActivity extends BackActivity {

    @BindView(R2.id.nameTv)
    TextView nameTv;
    @BindView(R2.id.cardTv)
    TextView cardTv;
    @BindView(R2.id.genderTv)
    TextView genderTv;
    @BindView(R2.id.dateTv)
    TextView dateTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_real_auth_status);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        if (getIntent().hasExtra("userauth")) {
            UserRealauth realauth = (UserRealauth) getIntent().getSerializableExtra("userauth");
            nameTv.setText(realauth.getRealName());

            if (realauth.getGender() == 0) {
                genderTv.setText("女");
            } else if (realauth.getGender() == 1) {
                genderTv.setText("男");
            }
            cardTv.setText(realauth.getIdentityCard());
            dateTv.setText(realauth.getExpiryDate() + "");
        }

    }
}
