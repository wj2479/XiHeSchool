package com.xh.module.base.retrofit;

import com.xh.module.base.entity.ClassDemeanor;
import com.xh.module.base.entity.ClassId;
import com.xh.module.base.entity.School;
import com.xh.module.base.entity.SchoolInformation;
import com.xh.module.base.entity.SchoolmasterMailbox;
import com.xh.module.base.entity.SchoolmasterMailboxReply;
import com.xh.module.base.entity.Schoolwork;
import com.xh.module.base.entity.TeacherClass;
import com.xh.module.base.entity.UserBase;
import com.xh.module.base.entity.VideoBase;
import com.xh.module.base.entity.bbs.BbsArticle;
import com.xh.module.base.entity.bbs.BbsUser;
import com.xh.module.base.entity.pay.BankResult;
import com.xh.module.base.entity.pay.OrderInfo;
import com.xh.module.base.entity.pay.UserRealauth;
import com.xh.module.base.retrofit.response.SimpleResponse;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * 服务器接口接口
 * Created by wj on 2020/04/29.
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
     * 重置密码
     *
     * @param requestBody
     * @return
     */
    @HTTP(method = "DELETE", path = "userAuth_", hasBody = true)
    Observable<SimpleResponse> resetPassword(@Body RequestBody requestBody);

    /**
     * 用户登录
     *
     * @param requestBody
     * @return
     */
    @PUT("userAuth_")
    Observable<SimpleResponse> updatePassword(@Body RequestBody requestBody);

    /**
     * 发送验证码
     *
     * @param requestBody
     * @return
     */
    @POST("smsVerify")
    Observable<SimpleResponse> sendVerify(@Body RequestBody requestBody);

    /**
     * 根据ID 获取学校基本信息
     *
     * @param requestBody
     * @return
     */
    @PUT("school_")
    Observable<SimpleResponse<School>> getSchoolInfoById(@Body RequestBody requestBody);

    /**
     * 根据ID  获取学校资讯
     *
     * @param
     * @return
     */
    @GET("schoolinfo_")
    Observable<SimpleResponse<List<SchoolInformation>>> getSchoolInfomationById(@Query("schoolId") long schoolId, @Query("page") int page, @Query("pagesize") int pageSize);

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
    Observable<SimpleResponse<List<SchoolmasterMailbox>>> getReceivedSchoolMasterMails(@Query("schoolId") long schoolId, @Query("page") int page, @Query("pagesize") int pageSize);

    /**
     * 根据ID  用户 获取发出的信件
     *
     * @param
     * @param schoolId
     * @return
     */
    @GET("mailbox_")
    Observable<SimpleResponse<List<SchoolmasterMailbox>>> getSendSchoolMasterMails(@Query("schoolId") long schoolId, @Query("uid") long uid, @Query("page") int page, @Query("pagesize") int pageSize);

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

    /**
     * 获取论坛用户信息
     *
     * @param uid
     * @return
     */
    @GET("bbsUser")
    Observable<SimpleResponse<BbsUser>> getBbsUserInfo(@Query("uid") long uid);

    /**
     * 获取论坛用户信息
     *
     * @param requestBody
     * @return
     */
    @POST("bbsUser")
    Observable<SimpleResponse> addBbsUser(@Body RequestBody requestBody);

    /**
     * 更新论坛用户信息
     *
     * @param requestBody
     * @return
     */
    @PUT("bbsUser")
    Observable<SimpleResponse<BbsUser>> updateBbsUserInfo(@Body RequestBody requestBody);

    /**
     * 发布文章
     *
     * @param requestBody
     * @return
     */
    @POST("bbsArticle")
    Observable<SimpleResponse> publishArticle(@Body RequestBody requestBody);

    /**
     * 删除帖子
     *
     * @param requestBody
     * @return
     */
    @HTTP(method = "DELETE", path = "bbsArticle", hasBody = true)
    Observable<SimpleResponse> deleteArticle(@Body RequestBody requestBody);

    /**
     * 获取文章列表
     *
     * @param page
     * @param pageSize
     * @return
     */
    @GET("bbsArticle_")
    Observable<SimpleResponse<List<BbsArticle>>> getBbsArticles(@Query("page") int page, @Query("pagesize") int pageSize);

    /**
     * 根据用户获取文章列表
     *
     * @param requestBody
     * @return
     */
    @POST("bbsArticle_")
    Observable<SimpleResponse<List<BbsArticle>>> getBbsArticlesByUserId(@Body RequestBody requestBody);

    /**
     * 上传论坛图片
     *
     * @param requestBody 图片文件
     * @return
     */
    @POST("bbsUpload")
    Observable<SimpleResponse<List<String>>> uploadBbsImage(@Body RequestBody requestBody);

    /**
     * 添加班级风采
     *
     * @param requestBody
     * @return
     */
    @POST("clasInformation")
    Observable<SimpleResponse> addClassDemeanor(@Body RequestBody requestBody);

    /**
     * 上传班级风采图片
     *
     * @param requestBody 图片文件
     * @return
     */
    @POST("clasInformationUpload")
    Observable<SimpleResponse<List<String>>> uploadClassDemeanorImage(@Body RequestBody requestBody);

    /**
     * 获取班级风采列表
     *
     * @param
     * @return
     */
    @GET("clasInformation_")
    Observable<SimpleResponse<List<ClassDemeanor>>> getClassDemeanorById(@Query("clasId") long clasId, @Query("page") int page, @Query("pagesize") int pageSize);

    /**
     * 获取视频列表
     *
     * @param
     * @return
     */
    @GET("videoBase_")
    Observable<SimpleResponse<List<VideoBase>>> getRecordVideoList(@Query("page") int page, @Query("pagesize") int pageSize);

    /**
     * 获取视频列表
     *
     * @param
     * @return
     */
    @POST("videoBase_")
    Observable<SimpleResponse<List<VideoBase>>> getRecommendRecordVideoList(@Body RequestBody requestBody);

    /**
     * 获取授课老师 授课及班级信息
     *
     * @param
     * @return
     */
    @GET("teacher_")
    Observable<SimpleResponse<List<List<TeacherClass>>>> getTeacherClassInfoById(@Query("id") long uid);

    /**
     * 添加学校作业
     *
     * @param
     **/
    @POST("schoolwork")
    Observable<SimpleResponse<List<ClassId>>> addHomeWork(@Body RequestBody requestBody);

    /**
     * 添加学校作业附件
     *
     * @param
     **/
    @PUT("schoolworkEnclosure_")
    Observable<SimpleResponse> addHomeWorkAnnex(@Body RequestBody requestBody);

    /**
     * 添加学校作业附件
     *
     * @param
     **/
    @GET("schoolwork_")
    Observable<SimpleResponse<List<Schoolwork>>> getHomeWorkByClasId(@Query("clasId") long clasId, @Query("date") String date);

    /**
     * 获取付款的请求
     *
     * @param
     **/
    @POST("bank")
    Observable<BankResult> requestBank(@Body RequestBody requestBody);

    /**
     * 根据用户查询未支付的订单列表
     *
     * @param uid
     * @param page
     * @param pageSize
     * @return
     */
    @GET("orderInfo_")
    Observable<SimpleResponse<List<OrderInfo>>> getUnpaidOrder(@Query("uid") long uid, @Query("page") int page, @Query("pagesize") int pageSize);

    /**
     * 根据用户查询已支付的订单列表
     *
     * @return
     */
    @POST("orderInfo_")
    Observable<SimpleResponse<List<OrderInfo>>> getPaidOrder(@Body RequestBody requestBody);

    /**
     * 根据用户查询未支付的订单列表
     *
     * @return
     */
    @PUT("bank")
    Observable<BankResult> requestWallet(@Body RequestBody requestBody);

    /**
     * 根据用户实名认证状态
     *
     * @return
     */
    @GET("userRealauth")
    Observable<SimpleResponse<UserRealauth>> getRealAuthStatus(@Query("uid") long uid);

    /**
     * 发起实名认证请求
     *
     * @param requestBody
     * @return
     */
    @POST("userRealauth")
    Observable<SimpleResponse> requestUserRealAuth(@Body RequestBody requestBody);

    /**
     * 更新订单状态
     *
     * @param requestBody
     * @return
     */
    @POST("notify")
    Observable<SimpleResponse> updateOrderStatus(@Body RequestBody requestBody);
}
