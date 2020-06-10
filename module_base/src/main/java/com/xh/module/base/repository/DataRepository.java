package com.xh.module.base.repository;

import com.xh.module.base.entity.Clas;
import com.xh.module.base.entity.Course;
import com.xh.module.base.entity.Role;
import com.xh.module.base.entity.School;
import com.xh.module.base.entity.UserBase;
import com.xh.module.base.entity.bbs.BbsUser;

import java.util.List;
import java.util.Map;

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
     * 班主任 的班级信息
     */
    public static Clas clas;
    /**
     * 当前授课老师任课与班级信息对照表
     */
    public static Map<Course, List<Clas>> courseListMap;
    /**
     * 七牛云请求token
     */
    public static String qiniuToken;

    /**
     * 设备唯一的标识
     */
    public static String imei = "";

}
