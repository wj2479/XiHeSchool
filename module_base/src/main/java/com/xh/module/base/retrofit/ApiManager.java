package com.xh.module.base.retrofit;

import com.xh.module.base.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Description Api请求的管理器
 * @Author wj
 * @Time 2017/7/12 14:29
 */
public class ApiManager {

    private static ApiManager manager;
    private OkHttpClient mClient;

    private SchoolServer schoolServer;

    public static ApiManager getInstance() {
        if (manager == null) {
            manager = new ApiManager();
        }
        return manager;
    }

    private ApiManager() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLogger());
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        JsonInterceptor jsonInterceptor = new JsonInterceptor();

        mClient = new OkHttpClient.Builder()
                .addInterceptor(jsonInterceptor)
                .addInterceptor(loggingInterceptor)
                .connectTimeout(5, TimeUnit.SECONDS)//设置超时时间
                .readTimeout(5, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(30, TimeUnit.SECONDS)//设置写入超时时间
                .build();
    }

    public SchoolServer getSchoolServer() {
        if (schoolServer == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(mClient)
                    .baseUrl(Constant.SERVER_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            schoolServer = retrofit.create(SchoolServer.class);
        }
        return schoolServer;
    }

}
