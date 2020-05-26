package com.xh.module.base.repository;

import com.xh.module.base.entity.UserBase;
import com.xh.module.base.retrofit.IRxJavaCallBack;
import com.xh.module.base.retrofit.response.SimpleResponse;

/**
 * 用户相关的统一数据输出接口
 */
public interface IUserRepository {

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @param callback
     */
    void login(String username, String password, IRxJavaCallBack<SimpleResponse<UserBase>> callback);

    /**
     * 修改密码
     *
     * @param username
     * @param oldPwd
     * @param newPwd
     * @param callback
     */
    void updatePassword(String username, String oldPwd, String newPwd, IRxJavaCallBack<SimpleResponse> callback);

    /**
     * 发送验证码
     *
     * @param mobile
     * @param type     类型
     * @param callback
     */
    void sendSmsCode(String mobile, int type, IRxJavaCallBack<SimpleResponse> callback);

    /**
     * 重置密码
     *
     * @param mobile
     * @param code
     * @param newPwd
     * @param callback
     */
    void resetPassword(String mobile, String code, String newPwd, IRxJavaCallBack<SimpleResponse> callback);
}
