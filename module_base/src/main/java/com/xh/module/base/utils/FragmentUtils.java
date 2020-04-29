package com.xh.module.base.utils;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;

public class FragmentUtils {

    public static Fragment getBbsFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(RouteUtils.Bbs_Fragment_Main).navigation();
        return fragment;
    }

    public static Fragment getTeachFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(RouteUtils.Teach_Fragment_Main).navigation();
        return fragment;
    }

    public static Fragment getMeFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(RouteUtils.Me_Fragment_Main).navigation();
        return fragment;
    }

    public static Fragment getSchoolFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(RouteUtils.School_Fragment_Main).navigation();
        return fragment;
    }
}
