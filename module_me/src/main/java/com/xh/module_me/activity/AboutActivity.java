package com.xh.module_me.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.tamsiree.rxkit.RxAppTool;
import com.xh.module.base.BackActivity;
import com.xh.module_me.R;
import com.xh.module_me.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 关于
 */
public class AboutActivity extends BackActivity {

    @BindView(R2.id.versionTv)
    TextView versionTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ButterKnife.bind(this);

        versionTv.setText(RxAppTool.getAppVersionName(this));
    }
}
