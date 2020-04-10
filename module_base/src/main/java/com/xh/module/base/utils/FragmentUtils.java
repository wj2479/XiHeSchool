package com.xh.module.base.utils;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;

public class FragmentUtils {

    public static Fragment getHomeFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(RouteUtils.Home_Fragment_Main).navigation();
        return fragment;
    }
}
