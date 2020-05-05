package com.xh.module.base.utils;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;

public class FragmentUtils {

    /**
     * 获取社区板块主界面
     * @return
     */
    public static Fragment getBbsFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(RouteUtils.Bbs_Fragment_Main).navigation();
        return fragment;
    }

    /**
     * 获取教学板块主界面
     * @return
     */
    public static Fragment getTeachFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(RouteUtils.Teach_Fragment_Main).navigation();
        return fragment;
    }

    /**
     * 获取个人中心板块主界面
     * @return
     */
    public static Fragment getMeFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(RouteUtils.Me_Fragment_Main).navigation();
        return fragment;
    }

    /**
     * 获取校园板块主界面
     * @return
     */
    public static Fragment getSchoolFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(RouteUtils.School_Fragment_Main).navigation();
        return fragment;
    }

    /**
     * 获取校园 班主任菜单页面
     * @return
     */
    public static Fragment getSchoolClassTeacherMenuFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(RouteUtils.School_Fragment_Class_Teacher_Menu).navigation();
        return fragment;
    }
    /**
     * 获取校园  教师菜单页面
     * @return
     */
    public static Fragment getSchoolTeacherMenuFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(RouteUtils.School_Fragment_Teacher_Menu).navigation();
        return fragment;
    }
    /**
     * 获取校园  家长菜单页面
     * @return
     */
    public static Fragment getSchoolFamilyMenuFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(RouteUtils.School_Fragment_Family_Menu).navigation();
        return fragment;
    }
}
