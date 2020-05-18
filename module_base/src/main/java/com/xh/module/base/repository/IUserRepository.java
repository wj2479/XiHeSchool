package com.xh.module.base.repository;

import com.xh.module.base.entity.UserBase;
import com.xh.module.base.retrofit.IRxJavaCallBack;
import com.xh.module.base.retrofit.response.SimpleResponse;

/**
 * 登录的统一数据输出接口
 */
public interface IUserRepository {

    void login(String username, String password, IRxJavaCallBack<SimpleResponse<UserBase>> callback);
}
