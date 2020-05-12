package com.xh.moudle_bbs.fragment;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xh.module.base.BaseFragment;
import com.xh.module.base.utils.RouteUtils;
import com.xh.moudle_bbs.R;

/**
 * 关注列表
 */
@Route(path = RouteUtils.Bbs_Fragment_Attention_Home)
public class AttentionFragment extends BaseFragment {

    public AttentionFragment() {
        // Required empty public constructor
    }


    @Override
    protected int initLayout() {
        return R.layout.fragment_attention;
    }

    @Override
    protected void initView(View rootView) {

    }

}
