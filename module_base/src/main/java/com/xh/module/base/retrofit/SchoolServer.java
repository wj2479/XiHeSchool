package com.xh.module.base.retrofit;

import com.xh.module.base.entity.LoginInfo;
import com.xh.module.base.entity.School;
import com.xh.module.base.entity.SchoolInformation;
import com.xh.module.base.retrofit.response.SimpleResponse;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * 服务器接口接口
 * Created by wj on 2017/12/29.
 */
public interface SchoolServer {

    /**
     * 用户登录
     *
     * @param requestBody
     * @return
     */
    @POST("userAuth_")
    Observable<SimpleResponse<LoginInfo>> login(@Body RequestBody requestBody);

    /**
     * 根据ID 获取学校基本信息
     *
     * @param requestBody
     * @return
     */
    @PUT("school_")
    Observable<SimpleResponse<School>> getschoolInfoById(@Body RequestBody requestBody);

    /**
     * 根据ID  获取学校资讯
     *
     * @param
     * @return
     */
    @GET("schoolinfo_")
    Observable<SimpleResponse<List<SchoolInformation>>> getschoolInfomationById(@Query("schoolId") long schoolId);


    /**
     * 上传资讯图片
     *
     * @param files 图片文件
     * @return
     */
    @Multipart
    @POST("infoupload")
    Observable<SimpleResponse<List<String>>> uploadInfoImage(@Part MultipartBody.Part[] files);

}
