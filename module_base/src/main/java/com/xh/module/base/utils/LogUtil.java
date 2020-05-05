package com.xh.module.base.utils;

import android.util.Log;

import com.xh.module.BuildConfig;


/**
 * @Description Log日志管理工具
 * @Author wj
 * @Time 2017/7/6 13:32
 */

public class LogUtil {

    public static void e(String tag, String text) {
        if (BuildConfig.DEBUG)
            Log.e(tag, text);
    }

    public static void e(Class cls, String text) {
        if (BuildConfig.DEBUG)
            Log.e(cls.getSimpleName(), text);
    }

    public static void d(String tag, String text) {
        if (BuildConfig.DEBUG)
            Log.d(tag, text);
    }

    public static void d(Class cls, String text) {
        if (BuildConfig.DEBUG)
            Log.d(cls.getSimpleName(), text);
    }

    public static void v(String tag, String text) {
        if (BuildConfig.DEBUG)
            Log.v(tag, text);
    }

    public static void v(Class cls, String text) {
        if (BuildConfig.DEBUG)
            Log.v(cls.getSimpleName(), text);
    }

    public static void i(String tag, String text) {
        if (BuildConfig.DEBUG)
            Log.i(tag, text);
    }

    public static void i(Class cls, String text) {
        if (BuildConfig.DEBUG)
            Log.i(cls.getSimpleName(), text);
    }

    public static void e(Class cls, Throwable throwable) {
        if (BuildConfig.DEBUG)
            Log.e(cls.getSimpleName(), throwable.toString());
    }

    public static void e(String tag, Throwable throwable) {
        if (BuildConfig.DEBUG)
            Log.e(tag, throwable.toString());
    }
}
