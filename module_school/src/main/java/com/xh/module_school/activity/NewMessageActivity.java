package com.xh.module_school.activity;

import android.os.Bundle;

import com.xh.module.base.BackActivity;
import com.xh.module_school.R;

import butterknife.ButterKnife;

/**
 * 新消息
 */
public class NewMessageActivity extends BackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);
        ButterKnife.bind(this);
    }
}
