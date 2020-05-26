package com.xh.module.base.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * table name:  schoolmaster_mailbox
 * author name: 朱华清
 * create time: 2020-05-14 16:13:24
 */
public class SchoolmasterMailbox implements Parcelable {

    private Long id;//ID
    private String title;//标题
    private String content;//内容
    private Long schoolId;//用户ID
    private Integer state;//0:未读 1 已读
    private Long createTime;//认证时间
    private Long createUid;//发布人
    private Integer anonymous;  // 0正常发送 1匿名发送
    private UserBase userBase;

    public SchoolmasterMailbox() {
        super();
    }

    public SchoolmasterMailbox(Long id, String title, String content, Long schoolId, Integer state, Long createTime, Long createUid) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.schoolId = schoolId;
        this.state = state;
        this.createTime = createTime;
        this.createUid = createUid;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getState() {
        return state;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateUid(Long createUid) {
        this.createUid = createUid;
    }

    public Long getCreateUid() {
        return createUid;
    }

    public Integer getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(Integer anonymous) {
        this.anonymous = anonymous;
    }

    public UserBase getUserBase() {
        return userBase;
    }

    public void setUserBase(UserBase userBase) {
        this.userBase = userBase;
    }

    @Override
    public String toString() {
        return "schoolmaster_mailbox[" +
                "id=" + id +
                ", title=" + title +
                ", content=" + content +
                ", school_id=" + schoolId +
                ", state=" + state +
                ", create_time=" + createTime +
                ", create_uid=" + createUid +
                "]";
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeValue(this.schoolId);
        dest.writeValue(this.state);
        dest.writeValue(this.createTime);
        dest.writeValue(this.createUid);
        dest.writeValue(this.anonymous);
    }

    protected SchoolmasterMailbox(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.title = in.readString();
        this.content = in.readString();
        this.schoolId = (Long) in.readValue(Long.class.getClassLoader());
        this.state = (Integer) in.readValue(Integer.class.getClassLoader());
        this.createTime = (Long) in.readValue(Long.class.getClassLoader());
        this.createUid = (Long) in.readValue(Long.class.getClassLoader());
        this.anonymous = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<SchoolmasterMailbox> CREATOR = new Creator<SchoolmasterMailbox>() {
        @Override
        public SchoolmasterMailbox createFromParcel(Parcel source) {
            return new SchoolmasterMailbox(source);
        }

        @Override
        public SchoolmasterMailbox[] newArray(int size) {
            return new SchoolmasterMailbox[size];
        }
    };
}

