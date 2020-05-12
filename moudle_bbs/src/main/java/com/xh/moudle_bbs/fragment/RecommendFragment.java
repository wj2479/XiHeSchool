package com.xh.moudle_bbs.fragment;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xh.module.base.BaseFragment;
import com.xh.module.base.utils.RouteUtils;
import com.xh.moudle_bbs.R;

/**
 * 推荐主界面
 */
@Route(path = RouteUtils.Bbs_Fragment_Recommend_Home)
public class RecommendFragment extends BaseFragment {

    public RecommendFragment() {
        // Required empty public constructor
    }


    @Override
    protected int initLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView(View rootView) {

    }

}
