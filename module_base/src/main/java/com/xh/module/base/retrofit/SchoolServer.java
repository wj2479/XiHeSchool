package com.xh.module.base.retrofit;

import com.xh.module.base.entity.School;
import com.xh.module.base.entity.SchoolInformation;
import com.xh.module.base.entity.SchoolmasterMailbox;
import com.xh.module.base.entity.SchoolmasterMailboxReply;
import com.xh.module.base.entity.UserBase;
import com.xh.module.base.retrofit.response.SimpleResponse;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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
    Observable<SimpleResponse<UserBase>> login(@Body RequestBody requestBody);

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
    Observable<SimpleResponse<List<SchoolInformation>>> getschoolInfomationById(@Query("schoolId") long schoolId, @Query("page") int page, @Query("pagesize") int pageSize);

    /**
     * 上传资讯图片
     *
     * @param requestBody 图片文件
     * @return
     */
    @POST("infoupload")
    Observable<SimpleResponse<List<String>>> uploadInfoImage(@Body RequestBody requestBody);

    /**
     * 添加学校资讯
     *
     * @param requestBody
     * @return
     */
    @POST("schoolinfo")
    Observable<SimpleResponse> addSchoolInfomation(@Body RequestBody requestBody);


    /**
     * 发布校长信箱信件
     *
     * @param requestBody
     * @return
     */
    @POST("mailbox")
    Observable<SimpleResponse> publishSchoolMasterMail(@Body RequestBody requestBody);

    /**
     * 根据ID  校长 获取接收到的信件
     *
     * @param
     * @return
     */
    @GET("mailbox_")
    Observable<SimpleResponse<List<SchoolmasterMailbox>>> getReceivedMails(@Query("schoolId") long schoolId, @Query("page") int page, @Query("pagesize") int pageSize);

    /**
     * 根据ID  用户 获取发出的信件
     *
     * @param
     * @return
     */
    @GET("mailbox2")
    Observable<SimpleResponse<List<SchoolmasterMailbox>>> getSendMails(@Query("schoolId") long uid, @Query("page") int page, @Query("pagesize") int pageSize);

    /**
     * 添加校长信件回复
     *
     * @param requestBody
     * @return
     */
    @POST("mailboxreply")
    Observable<SimpleResponse> addSchoolMailBoxReply(@Body RequestBody requestBody);

    /**
     * 根据信件ID  获取回复列表
     *
     * @param mailboxId
     * @param page
     * @param pageSize
     * @return
     */
    @GET("mailboxreply_")
    Observable<SimpleResponse<List<SchoolmasterMailboxReply>>> getSchoolMailBoxReplys(@Query("mailboxId") long mailboxId, @Query("page") int page, @Query("pagesize") int pageSize);
}
