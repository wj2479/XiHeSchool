package com.xh.module.base.repository;

import com.xh.module.base.entity.School;
import com.xh.module.base.entity.SchoolInformation;
import com.xh.module.base.entity.SchoolmasterMailbox;
import com.xh.module.base.entity.SchoolmasterMailboxReply;
import com.xh.module.base.retrofit.IRxJavaCallBack;
import com.xh.module.base.retrofit.response.SimpleResponse;

import java.io.File;
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
    void getSchoolInfomationById(long schoolId, int page, int pageSize, IRxJavaCallBack<SimpleResponse<List<SchoolInformation>>> callback);

    /**
     * 上传学校资讯图片
     *
     * @param files
     * @param callback
     */
    void uploadSchoolInfomationImgs(List<File> files, IRxJavaCallBack<SimpleResponse<List<String>>> callback);

    /**
     * 添加学校资讯
     *
     * @param schoolInformation
     * @param callback
     */
    void addSchoolInfomation(SchoolInformation schoolInformation, IRxJavaCallBack<SimpleResponse> callback);

    /**
     * 用户发布校长信件
     *
     * @param mail
     * @param callback
     */
    void publishSchoolMasterMail(SchoolmasterMailbox mail, IRxJavaCallBack<SimpleResponse> callback);

    /**
     * 校长 获取接收到的信件
     *
     * @param schoolId
     * @param page
     * @param pageSize
     * @param callback
     */
    void getReceivedMails(long schoolId, int page, int pageSize, IRxJavaCallBack<SimpleResponse<List<SchoolmasterMailbox>>> callback);

    /**
     * 校长信箱添加回复
     *
     * @param callback
     */
    void addSchoolMailBoxReply(SchoolmasterMailboxReply reply, IRxJavaCallBack<SimpleResponse> callback);

    /**
     * 获取校长信箱回复列表
     *
     * @param mailboxId
     * @param page
     * @param pageSize
     * @param callback
     */
    void getSchoolMailBoxReplys(long mailboxId, int page, int pageSize, IRxJavaCallBack<SimpleResponse<List<SchoolmasterMailboxReply>>> callback);

}
