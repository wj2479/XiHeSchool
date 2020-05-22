package com.xh.module.base.entity.bbs;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 论坛用户
 * table name:  bbs_user
 * author name: 朱华清
 * create time: 2020-05-20 09:42:40
 */
public class BbsUser implements Parcelable {

    private Long uid;
    private String name;
    private String email;
    private Integer state;
    private Integer experience;
    private Integer score;
    private String signature;
    private String homepageLink;
    private String headImage;
    private Long createTime;
    private Long updateTime;

    public BbsUser() {
        super();
    }

    public BbsUser(Long uid, String name, String email, Integer state, Integer experience, Integer score, String signature, String homepageLink, String headImage, Long createTime, Long updateTime) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.state = state;
        this.experience = experience;
        this.score = score;
        this.signature = signature;
        this.homepageLink = homepageLink;
        this.headImage = headImage;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    protected BbsUser(Parcel in) {
        if (in.readByte() == 0) {
            uid = null;
        } else {
            uid = in.readLong();
        }
        name = in.readString();
        email = in.readString();
        if (in.readByte() == 0) {
            state = null;
        } else {
            state = in.readInt();
        }
        if (in.readByte() == 0) {
            experience = null;
        } else {
            experience = in.readInt();
        }
        if (in.readByte() == 0) {
            score = null;
        } else {
            score = in.readInt();
        }
        signature = in.readString();
        homepageLink = in.readString();
        headImage = in.readString();
        if (in.readByte() == 0) {
            createTime = null;
        } else {
            createTime = in.readLong();
        }
        if (in.readByte() == 0) {
            updateTime = null;
        } else {
            updateTime = in.readLong();
        }
    }

    public static final Creator<BbsUser> CREATOR = new Creator<BbsUser>() {
        @Override
        public BbsUser createFromParcel(Parcel in) {
            return new BbsUser(in);
        }

        @Override
        public BbsUser[] newArray(int size) {
            return new BbsUser[size];
        }
    };

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getUid() {
        return uid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getState() {
        return state;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getScore() {
        return score;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSignature() {
        return signature;
    }

    public void setHomepageLink(String homepageLink) {
        this.homepageLink = homepageLink;
    }

    public String getHomepageLink() {
        return homepageLink;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    @Override
    public String toString() {
        return "bbs_user[" +
                "uid=" + uid +
                ", name=" + name +
                ", email=" + email +
                ", state=" + state +
                ", experience=" + experience +
                ", score=" + score +
                ", signature=" + signature +
                ", homepage_link=" + homepageLink +
                ", head_image=" + headImage +
                ", create_time=" + createTime +
                ", update_time=" + updateTime +
                "]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (uid == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(uid);
        }
        dest.writeString(name);
        dest.writeString(email);
        if (state == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(state);
        }
        if (experience == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(experience);
        }
        if (score == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(score);
        }
        dest.writeString(signature);
        dest.writeString(homepageLink);
        dest.writeString(headImage);
        if (createTime == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(createTime);
        }
        if (updateTime == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(updateTime);
        }
    }
}

