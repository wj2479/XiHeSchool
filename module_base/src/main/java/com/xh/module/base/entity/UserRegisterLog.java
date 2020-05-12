package com.xh.module.base.entity;

/**
 * table name:  user_register_log
 * author name: 朱华清
 * create time: 2020-05-04 17:28:04
 */
public class UserRegisterLog {
    private Long id;//ID
    private Long uid;//用户ID
    private Byte registerMethod;//注册方式1手机号 2邮箱 3用户名 4qq 5微信 6腾讯微博 7新浪微博
    private Integer registerTime;//注册时间
    private String registerIp;//注册IP
    private String registerClient;//注册客户端

    public UserRegisterLog() {
        super();
    }

    public UserRegisterLog(Long id, Long uid, Byte registerMethod, Integer registerTime, String registerIp, String registerClient) {
        this.id = id;
        this.uid = uid;
        this.registerMethod = registerMethod;
        this.registerTime = registerTime;
        this.registerIp = registerIp;
        this.registerClient = registerClient;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getUid() {
        return uid;
    }

    public void setRegisterMethod(Byte registerMethod) {
        this.registerMethod = registerMethod;
    }

    public Byte getRegisterMethod() {
        return registerMethod;
    }

    public void setRegisterTime(Integer registerTime) {
        this.registerTime = registerTime;
    }

    public Integer getRegisterTime() {
        return registerTime;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterClient(String registerClient) {
        this.registerClient = registerClient;
    }

    public String getRegisterClient() {
        return registerClient;
    }

    @Override
    public String toString() {
        return "user_register_log[" +
                "id=" + id +
                ", uid=" + uid +
                ", register_method=" + registerMethod +
                ", register_time=" + registerTime +
                ", register_ip=" + registerIp +
                ", register_client=" + registerClient +
                "]";
    }
}

