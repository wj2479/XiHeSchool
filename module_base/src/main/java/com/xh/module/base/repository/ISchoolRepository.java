package com.xh.module.base.repository;

import com.xh.module.base.entity.School;
import com.xh.module.base.entity.SchoolInformation;
import com.xh.module.base.retrofit.IRxJavaCallBack;
import com.xh.module.base.retrofit.response.SimpleResponse;

import java.util.List;

/**
 * 学校模块的输出接口
 */
public interface ISchoolRepository {

    /**
     * 根据ID 获取学校的信息
     *
     * @param schoolId
     * @param callback
     */
    void getSchoolInfoById(long schoolId, IRxJavaCallBack<SimpleResponse<School>> callback);

    /**
     * 根据ID 获取学校的资讯
     *
     * @param schoolId
     * @param callback
     */
    void getSchoolInfomationById(long schoolId, IRxJavaCallBack<SimpleResponse<List<SchoolInformation>>> callback);
}
