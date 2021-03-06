package com.xh.module.base;

import java.util.HashMap;
import java.util.Map;

/**
 * 记录系统的常量
 */
public class Constant {

    /**
     * 服务器Api接口
     */
//    public static final String SERVER_URL = "http://192.168.1.105:7777/";//测试服务器
    public static final String SERVER_URL = "http://www.rzszhxy.com:7777/";//正式服务器

    public static final String EVENT_FINISH_ALL_ACTIVITY = "EVENT_FINISH_ALL_ACTIVITY";

    /**
     * 七牛云相关密钥
     */
    public static final String QINIU_AccessKey = "DRUZLUbK5M9FkPfQipSfKGhY341jC0MW2yHAKZLl";
    public static final String QINIU_SecretKey = "cYHst1bfUgEtgfntW6m08R3Zd6G36LNnggcIBKxk";
    public static final String QINIU_Bucket = "app-xihe";
    // 七牛云绑定的空间域名
    public static final String QINIU_Server_Host = "http://qb88ih4rg.bkt.clouddn.com/";

    /**
     * 保存的用户名
     */
    public static final String SAVE_LOGIN_USERNAME = "SAVE_LOGIN_USERNAME";

    /**
     * 保存的密码
     */
    public static final String SAVE_LOGIN_PASSWORD = "SAVE_LOGIN_PASSWORD";

    /**
     * 家长类型
     */
    public static final int ROLE_TYPE_FAMILY = 0;
    /**
     * 学校类型
     */
    public static final int ROLE_TYPE_SCHOOL = 1;

    /**
     * 校长身份
     */
    public static final int ROLE_CODE_SCHOOL_MASTER = 4;

    /**
     * 班主任身份
     */
    public static final int ROLE_CODE_CLASS_MASTER = 1;

    /**
     * 教师身份
     */
    public static final int ROLE_CODE_TEACHER = 2;

    /**
     * 家长身份
     */
    public static final int ROLE_CODE_PARENT = 3;

    /**
     * 验证码类型  重置密码
     */
    public static final int SMS_CODE_RESET_PASSWORD = 1;


    /**
     * 用户角色类型对照表
     */
    public static Map<Integer, String> roleTypeMap = new HashMap<>();

    static {
        roleTypeMap.put(ROLE_TYPE_FAMILY, "家长");
        roleTypeMap.put(ROLE_TYPE_SCHOOL, "学校");
    }
}
