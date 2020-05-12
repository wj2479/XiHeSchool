package com.xh.module.base.entity;

import java.io.Serializable;
import java.util.List;

public class LoginInfo implements Serializable {

    private static final long serialVersionUID = -4317695951322020196L;

    /**
     * uid : 1
     * userRole : 2
     * registerSource : 1
     * userName : zhuhq
     * nickName : 云轩清影
     * gender : 1
     * birthday : 23238294
     * signature : 好好学习天天向上
     * mobile : 17506331024
     * mobileBindTime : 23238294
     * email : 17506331024@sina.cn
     * emailBindTime : 23238294
     * face : /image/323.jpg
     * face200 : /image/323.jpg
     * srcface : /image/323.jpg
     * createTime : 22342342
     * updateTime : 22342342
     * pushToken : skdfoiwe242
     * realName : 朱华清
     */

    private int uid;
    private int userRole;
    private int registerSource;
    private String userName;
    private String nickName;
    private int gender;
    private int birthday;
    private String signature;
    private String mobile;
    private int mobileBindTime;
    private String email;
    private int emailBindTime;
    private String face;
    private String face200;
    private String srcface;
    private int createTime;
    private int updateTime;
    private String pushToken;
    private String realName;
    private List<Role> roles;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    public int getRegisterSource() {
        return registerSource;
    }

    public void setRegisterSource(int registerSource) {
        this.registerSource = registerSource;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getMobileBindTime() {
        return mobileBindTime;
    }

    public void setMobileBindTime(int mobileBindTime) {
        this.mobileBindTime = mobileBindTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEmailBindTime() {
        return emailBindTime;
    }

    public void setEmailBindTime(int emailBindTime) {
        this.emailBindTime = emailBindTime;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getFace200() {
        return face200;
    }

    public void setFace200(String face200) {
        this.face200 = face200;
    }

    public String getSrcface() {
        return srcface;
    }

    public void setSrcface(String srcface) {
        this.srcface = srcface;
    }

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    public int getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
    }

    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
