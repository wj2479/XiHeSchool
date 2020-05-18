package com.xh.module.base.entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * table name:  school_information
 * author name: 朱华清
 * create time: 2020-05-09 11:01:32
 */
@Entity
public class SchoolInformation implements Parcelable {

    @Id
    private Long id;//ID
    private Long schoolId;//学校id
    private String indexImage;//内容
    private String title;//标题
    private String content;//内容
    private Long createTime;//认证时间
    private Long createUid;//发布人

    public SchoolInformation() {
        super();
    }

    @Generated(hash = 727637189)
    public SchoolInformation(Long id, Long schoolId, String indexImage,
            String title, String content, Long createTime, Long createUid) {
        this.id = id;
        this.schoolId = schoolId;
        this.indexImage = indexImage;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.createUid = createUid;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setIndexImage(String indexImage) {
        this.indexImage = indexImage;
    }

    public String getIndexImage() {
        return indexImage;
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

    @Override
    public String toString() {
        return "school_information[" +
                "id=" + id +
                ", school_id=" + schoolId +
                ", index_image=" + indexImage +
                ", title=" + title +
                ", content=" + content +
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
        dest.writeValue(this.schoolId);
        dest.writeString(this.indexImage);
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeValue(this.createTime);
        dest.writeValue(this.createUid);
    }

    protected SchoolInformation(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.schoolId = (Long) in.readValue(Long.class.getClassLoader());
        this.indexImage = in.readString();
        this.title = in.readString();
        this.content = in.readString();
        this.createTime = (Long) in.readValue(Long.class.getClassLoader());
        this.createUid = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Creator<SchoolInformation> CREATOR = new Creator<SchoolInformation>() {
        @Override
        public SchoolInformation createFromParcel(Parcel source) {
            return new SchoolInformation(source);
        }

        @Override
        public SchoolInformation[] newArray(int size) {
            return new SchoolInformation[size];
        }
    };
}

