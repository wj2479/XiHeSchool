package com.xh.module.base.utils;

/**
 * 所有ARouter 模块的路径地址
 * 注意：不同module的一级路径必须不同，否则会导致一个moudle中的一级路径失效！！出现no-match异常
 */
public class RouteUtils {

    //登录模块
    public static final String APP_Activity_Login = "/app/activity/login";
    //修改密码模块
    public static final String Me_Activity_Update_Password = "/me/activity/update/password";
    //消息提醒
    public static final String Me_Activity_Msg_Setting = "/me/activity/message/setting";
    //支付页面
    public static final String Me_Activity_Pay_Webview = "/me/activity/pay/webview";

    //获得Base模块视频播放
    public static final String Base_Activity_Video_Play = "/base/activity/video/play";
    public static final String Base_Activity_Audio_Record = "/base/activity/audio/record";
    public static final String Base_Activity_WebView = "/base/activity/webview";

    //获得BBS模块fragment
    public static final String Bbs_Fragment_Main = "/bbs/main";
    //获得Teach模块fragment
    public static final String Teach_Fragment_Main = "/teach/main";
    //获得Me模块fragment
    public static final String Me_Fragment_Main = "/me/main";


    //获得School模块fragment
    public static final String School_Fragment_Main = "/school/main";
    public static final String School_Fragment_Class_Master_Menu = "/school/clasmaster/menu";
    public static final String School_Fragment_Teacher_Menu = "/school/teacher/menu";
    public static final String School_Fragment_Family_Menu = "/school/family/menu";
    public static final String School_Fragment_School_Menu = "/school/school/menu";
    public static final String School_Fragment_School_Master_Menu = "/school/schoolmaster/menu";
    /**
     * 扫描二维码
     */
    public static final String School_Activity_Scan = "/school/activity/scan";

    public static final String Teach_Fragment_Video_Live_Home = "/teach/video/live";
    public static final String Teach_Fragment_Video_Record_Home = "/teach/video/record";

    public static final String Bbs_Fragment_Attention_Home = "/bbs/attention";
    public static final String Bbs_Fragment_Recommend_Home = "/bbs/recommend";
}
