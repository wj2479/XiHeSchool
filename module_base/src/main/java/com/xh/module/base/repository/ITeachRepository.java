package com.xh.module.base.repository;

import com.xh.module.base.entity.VideoBase;
import com.xh.module.base.retrofit.IRxJavaCallBack;
import com.xh.module.base.retrofit.response.SimpleResponse;

import java.util.List;

/**
 * 教学模块相关输出接口
 */
public interface ITeachRepository {

    /**
     * 根据不同条件获取点播的视频列表
     *
     * @param callback
     */
    void getRecordVideoList(int schoolType, String gradeName, long courseId, int page, int pageSize, IRxJavaCallBack<SimpleResponse<List<VideoBase>>> callback);

    /**
     * 获取推荐点播视频列表
     *
     * @param callback
     */
    void getRecommendRecordVideoList(IRxJavaCallBack<SimpleResponse<List<VideoBase>>> callback);

}
