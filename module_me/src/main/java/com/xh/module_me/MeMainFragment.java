package com.xh.module_me;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xh.module.base.BaseFragment;
import com.xh.module.base.utils.RouteUtils;


/**
 * 个人中心主页
 */
@Route(path = RouteUtils.Me_Fragment_Main)
public class MeMainFragment extends BaseFragment {

    public MeMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_me_main, container, false);
    }

    @Override
    protected void initView(View rootView) {

    }
}
