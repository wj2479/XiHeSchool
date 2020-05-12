package com.xh.module_school.activity;

import android.os.Bundle;

import com.xh.module.base.BackActivity;
import com.xh.module_school.R;

import butterknife.ButterKnife;

/**
 * 校长信箱
 */
public class SchoolMasterMailActivity extends BackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_master_mail);

        ButterKnife.bind(this);
    }
}
