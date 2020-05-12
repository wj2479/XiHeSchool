package com.xh.module_school.fragment;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xh.module.base.BaseFragment;
import com.xh.module.base.utils.RouteUtils;
import com.xh.module_school.R;

/**
 * 校园的主界面
 */

@Route(path = RouteUtils.School_Fragment_School_Menu)
public class SchoolMenuFragment extends BaseFragment {

    public SchoolMenuFragment() {
        // Required empty public constructor
    }


    @Override
    protected int initLayout() {
        return R.layout.fragment_school_menu;
    }

    @Override
    protected void initView(View rootView) {

    }

}
