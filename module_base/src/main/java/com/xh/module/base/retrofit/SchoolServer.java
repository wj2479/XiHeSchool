package com.xh.module.base.retrofit;

import com.xh.module.base.entity.LoginInfo;
import com.xh.module.base.retrofit.response.SimpleResponse;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 服务器接口接口
 * Created by wj on 2017/12/29.
 */
public interface SchoolServer {

    @POST("userAuth_")
    Observable<SimpleResponse<LoginInfo>> login(@Body RequestBody requestBody);

}
