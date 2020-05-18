package com.xh.module.base.utils;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;

public class FragmentUtils {

    /**
     * 获取社区板块主界面
     *
     * @return
     */
    public static Fragment getBbsFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(RouteUtils.Bbs_Fragment_Main).navigation();
        return fragment;
    }

    /**
     * 获取教学板块主界面
     *
     * @return
     */
    public static Fragment getTeachFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(RouteUtils.Teach_Fragment_Main).navigation();
        return fragment;
    }

    /**
     * 获取个人中心板块主界面
     *
     * @return
     */
    public static Fragment getMeFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(RouteUtils.Me_Fragment_Main).navigation();
        return fragment;
    }

    /**
     * 获取校园板块主界面
     *
     * @return
     */
    public static Fragment getSchoolFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(RouteUtils.School_Fragment_Main).navigation();
        return fragment;
    }

    /**
     * 获取校园 校园菜单页面
     *
     * @return
     */
    public static Fragment getSchoolMenuFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(RouteUtils.School_Fragment_School_Menu).navigation();
        return fragment;
    }

    /**
     * 获取校园 班主任菜单页面
     *
     * @return
     */
    public static Fragment getSchoolClassTeacherMenuFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(RouteUtils.School_Fragment_Class_Master_Menu).navigation();
        return fragment;
    }

    /**
     * 获取校园  教师菜单页面
     *
     * @return
     */
    public static Fragment getSchoolTeacherMenuFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(RouteUtils.School_Fragment_Teacher_Menu).navigation();
        return fragment;
    }

    /**
     * 获取校园  家长菜单页面
     *
     * @return
     */
    public static Fragment getSchoolFamilyMenuFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(RouteUtils.School_Fragment_Family_Menu).navigation();
        return fragment;
    }


    /**
     * 获取教学  视频直播主页面
     *
     * @return
     */
    public static Fragment getTeachVideoLiveHomeFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(RouteUtils.Teach_Fragment_Video_Live_Home).navigation();
        return fragment;
    }

    /**
     * 获取教学  视频录播主页面
     *
     * @return
     */
    public static Fragment getTeachVideoRecordHomeFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(RouteUtils.Teach_Fragment_Video_Record_Home).navigation();
        return fragment;
    }

    /**
     * 获取论坛 关注主界面
     *
     * @return
     */
    public static Fragment getBbsAttentionHomeFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(RouteUtils.Bbs_Fragment_Attention_Home).navigation();
        return fragment;
    }

    /**
     * 获取论坛 推荐主界面
     *
     * @return
     */
    public static Fragment getBbsRecommendHomeFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(RouteUtils.Bbs_Fragment_Recommend_Home).navigation();
        return fragment;
    }

    /**
     * 获取指定Path的Fragment
     *
     * @return
     */
    public static Fragment getFragment(String path) {
        Fragment fragment = (Fragment) ARouter.getInstance().build(path).navigation();
        return fragment;
    }

}
