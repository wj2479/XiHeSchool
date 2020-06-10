package com.xh.module_me.activity;

import android.os.Bundle;

import com.xh.module.base.BackActivity;
import com.xh.module_me.R;

import butterknife.ButterKnife;

/**
 * 已支付订单
 */
public class PaidOrderActivity extends BackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paid_order);
        ButterKnife.bind(this);
    }
}
