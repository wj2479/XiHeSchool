package com.xh.module.base.repository;

import com.xh.module.base.entity.bbs.BbsArticle;
import com.xh.module.base.entity.bbs.BbsUser;
import com.xh.module.base.retrofit.IRxJavaCallBack;
import com.xh.module.base.retrofit.response.SimpleResponse;

import java.io.File;
import java.util.List;

/**
 * 论坛模块的相关接口
 */
public interface IBbsRepository {

    /**
     * 获取论坛用户信息
     * <p>
     * 论坛用户与系统账号不同，论坛用户需要有论坛昵称 头像 等属性
     *
     * @param userId   用户的ID
     * @param callback
     */
    void getBbsUserInfo(long userId, IRxJavaCallBack<SimpleResponse<BbsUser>> callback);

    /**
     * 更新论坛用户信息
     *
     * @param bbsUser
     * @param callback
     */
    void updateUserInfo(BbsUser bbsUser, IRxJavaCallBack<SimpleResponse> callback);

    /**
     * 添加论坛用户
     *
     * @param bbsUser
     * @param callback
     */
    void addBbsUser(BbsUser bbsUser, IRxJavaCallBack<SimpleResponse> callback);

    /**
     * 发布帖子
     */
    void publishArticle(BbsArticle article, List<File> files, IRxJavaCallBack<SimpleResponse> callback);

    /**
     * 删除帖子
     *
     * @param articleId
     * @param callback
     */
    void deletaArticle(long articleId, IRxJavaCallBack<SimpleResponse> callback);

    /**
     * 获取推荐文章列表
     */
    void getRecommendArticles(int page, int pageSize, IRxJavaCallBack<SimpleResponse<List<BbsArticle>>> callback);

    /**
     * 根据用户ID获取推荐文章列表
     */
    void getArticlesByUserId(long userId, int page, int pageSize, IRxJavaCallBack<SimpleResponse<List<BbsArticle>>> callback);

    /**
     * 上传论坛图片
     *
     * @param files
     * @param callback
     */
    void uploadBbsImgs(List<File> files, IRxJavaCallBack<SimpleResponse<List<String>>> callback);
}
