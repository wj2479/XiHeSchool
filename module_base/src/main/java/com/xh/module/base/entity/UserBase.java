package com.xh.module.base.entity;

import java.io.Serializable;
import java.util.List;

/**
 * table name:  user_base
 * author name: 朱华清
 * create time: 2020-05-04 17:28:03
 */
public class UserBase implements Serializable {
    private static final long serialVersionUID = -3747843724640458428L;

    private Long uid;//用户ID

    private Byte userRole;//2正常用户 3禁言用户 4虚拟用户 5运营

    private Byte registerSource;//注册来源：1手机号 2邮箱 3用户名 4qq 5微信 6腾讯微博 7新浪微博
    private String userName;//用户账号，必须唯一
    private String nickName;//用户昵称
    private Byte gender;//用户性别 0-female 1-male
    private Long birthday;//用户生日
    private String signature;//用户个人签名
    private String mobile;//手机号码(唯一)
    private Integer mobileBindTime;//手机号码绑定时间
    private String email;//邮箱(唯一)
    private Integer emailBindTime;//邮箱绑定时间
    private String face;//头像
    private String face200;//头像 200x200x80
    private String srcface;//原图头像
    private Integer createTime;//创建时间
    private Integer updateTime;//修改时间
    private String pushToken;//用户设备push_token
    private String realName;//用户的真实姓名

    private List<Role> roles;  // 用户角色

    public UserBase() {
        super();
    }

    public UserBase(Long uid, Byte userRole, Byte registerSource, String userName, String nickName, Byte gender, Long birthday, String signature, String mobile, Integer mobileBindTime, String email, Integer emailBindTime, String face, String face200, String srcface, Integer createTime, Integer updateTime, String pushToken, String realName) {
        this.uid = uid;
        this.userRole = userRole;
        this.registerSource = registerSource;
        this.userName = userName;
        this.nickName = nickName;
        this.gender = gender;
        this.birthday = birthday;
        this.signature = signature;
        this.mobile = mobile;
        this.mobileBindTime = mobileBindTime;
        this.email = email;
        this.emailBindTime = emailBindTime;
        this.face = face;
        this.face200 = face200;
        this.srcface = srcface;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.pushToken = pushToken;
        this.realName = realName;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUserRole(Byte userRole) {
        this.userRole = userRole;
    }

    public Byte getUserRole() {
        return userRole;
    }

    public void setRegisterSource(Byte registerSource) {
        this.registerSource = registerSource;
    }

    public Byte getRegisterSource() {
        return registerSource;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public Byte getGender() {
        return gender;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSignature() {
        return signature;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobileBindTime(Integer mobileBindTime) {
        this.mobileBindTime = mobileBindTime;
    }

    public Integer getMobileBindTime() {
        return mobileBindTime;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmailBindTime(Integer emailBindTime) {
        this.emailBindTime = emailBindTime;
    }

    public Integer getEmailBindTime() {
        return emailBindTime;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getFace() {
        return face;
    }

    public void setFace200(String face200) {
        this.face200 = face200;
    }

    public String getFace200() {
        return face200;
    }

    public void setSrcface(String srcface) {
        this.srcface = srcface;
    }

    public String getSrcface() {
        return srcface;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

    public String getPushToken() {
        return pushToken;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRealName() {
        return realName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "user_base[" +
                "uid=" + uid +
                ", user_role=" + userRole +
                ", register_source=" + registerSource +
                ", user_name=" + userName +
                ", nick_name=" + nickName +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", signature=" + signature +
                ", mobile=" + mobile +
                ", mobile_bind_time=" + mobileBindTime +
                ", email=" + email +
                ", email_bind_time=" + emailBindTime +
                ", face=" + face +
                ", face200=" + face200 +
                ", srcface=" + srcface +
                ", create_time=" + createTime +
                ", update_time=" + updateTime +
                ", push_token=" + pushToken +
                ", real_name=" + realName +
                "]";
    }
}

