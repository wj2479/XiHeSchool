package com.xh.module.base.retrofit;

import android.util.Log;

import okhttp3.logging.HttpLoggingInterceptor.Logger;

/**
 * @Author wj
 * @Date 2018/10/22
 * @Desc
 * @Url http://www.chuangze.cn
 */

public class HttpLogger implements Logger {
    @Override
    public void log(String s) {
        Log.i("Httplog", s);
    }
}
