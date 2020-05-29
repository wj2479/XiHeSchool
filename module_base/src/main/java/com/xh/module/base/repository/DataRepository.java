package com.xh.module.base.repository;

import com.xh.module.base.entity.Clas;
import com.xh.module.base.entity.Role;
import com.xh.module.base.entity.School;
import com.xh.module.base.entity.UserBase;
import com.xh.module.base.entity.bbs.BbsUser;

/**
 * 记录APP的一些数据
 */
public class DataRepository {

    /**
     * 用户登录信息
     */
    public static UserBase userInfo;
    /**
     * 论坛用户信息
     */
    public static BbsUser bbsUser;
    /**
     * 当前学校信息
     */
    public static School school;
    /**
     * 当前登录的角色
     */
    public static Role role;
    /**
     * 当前班级信息
     */
    public static Clas clas;

}
