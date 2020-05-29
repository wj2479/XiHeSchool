package com.xh.module.base.entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * table name:  video_base
 * author name: 朱华清
 * create time: 2020-05-27 21:11:16
 */
@Entity
public class VideoBase implements Parcelable {
    @Id
    private Long id;
    private Integer schoolType;   //学校类型 0 幼儿园 1 小学 2 初中
    private String gradeName;     //年级
    private Long courseId;          // 课程ID
    private String url;              //视频地址
    private String indexImage;    //轮播图   广告用
    private String thumbnail;     // 缩略图
    private String title;         //标题
    private String content;       //视频内容简介
    private Integer playSum;      //点播次数
    private Integer commentSum;   //评论次数
    private Integer collectSum;   //收藏次数
    private Integer supportSum;   //点赞数
    private Integer opposeSum;    // 踩数
    private String label;          // 标签 多个逗号隔开
    private Long createUid;       //上传者
    private Long createTime;      //上传时间

    protected VideoBase(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        if (in.readByte() == 0) {
            schoolType = null;
        } else {
            schoolType = in.readInt();
        }
        gradeName = in.readString();
        if (in.readByte() == 0) {
            courseId = null;
        } else {
            courseId = in.readLong();
        }
        url = in.readString();
        indexImage = in.readString();
        thumbnail = in.readString();
        title = in.readString();
        content = in.readString();
        if (in.readByte() == 0) {
            playSum = null;
        } else {
            playSum = in.readInt();
        }
        if (in.readByte() == 0) {
            commentSum = null;
        } else {
            commentSum = in.readInt();
        }
        if (in.readByte() == 0) {
            collectSum = null;
        } else {
            collectSum = in.readInt();
        }
        if (in.readByte() == 0) {
            supportSum = null;
        } else {
            supportSum = in.readInt();
        }
        if (in.readByte() == 0) {
            opposeSum = null;
        } else {
            opposeSum = in.readInt();
        }
        label = in.readString();
        if (in.readByte() == 0) {
            createUid = null;
        } else {
            createUid = in.readLong();
        }
        if (in.readByte() == 0) {
            createTime = null;
        } else {
            createTime = in.readLong();
        }
    }

    @Generated(hash = 973709965)
    public VideoBase(Long id, Integer schoolType, String gradeName, Long courseId,
            String url, String indexImage, String thumbnail, String title,
            String content, Integer playSum, Integer commentSum, Integer collectSum,
            Integer supportSum, Integer opposeSum, String label, Long createUid,
            Long createTime) {
        this.id = id;
        this.schoolType = schoolType;
        this.gradeName = gradeName;
        this.courseId = courseId;
        this.url = url;
        this.indexImage = indexImage;
        this.thumbnail = thumbnail;
        this.title = title;
        this.content = content;
        this.playSum = playSum;
        this.commentSum = commentSum;
        this.collectSum = collectSum;
        this.supportSum = supportSum;
        this.opposeSum = opposeSum;
        this.label = label;
        this.createUid = createUid;
        this.createTime = createTime;
    }

    @Generated(hash = 1985131699)
    public VideoBase() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        if (schoolType == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(schoolType);
        }
        dest.writeString(gradeName);
        if (courseId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(courseId);
        }
        dest.writeString(url);
        dest.writeString(indexImage);
        dest.writeString(thumbnail);
        dest.writeString(title);
        dest.writeString(content);
        if (playSum == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(playSum);
        }
        if (commentSum == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(commentSum);
        }
        if (collectSum == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(collectSum);
        }
        if (supportSum == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(supportSum);
        }
        if (opposeSum == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(opposeSum);
        }
        dest.writeString(label);
        if (createUid == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(createUid);
        }
        if (createTime == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(createTime);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<VideoBase> CREATOR = new Creator<VideoBase>() {
        @Override
        public VideoBase createFromParcel(Parcel in) {
            return new VideoBase(in);
        }

        @Override
        public VideoBase[] newArray(int size) {
            return new VideoBase[size];
        }
    };

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setSchoolType(Integer schoolType) {
        this.schoolType = schoolType;
    }

    public Integer getSchoolType() {
        return schoolType;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
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

    public void setPlaySum(Integer playSum) {
        this.playSum = playSum;
    }

    public Integer getPlaySum() {
        return playSum;
    }

    public void setCommentSum(Integer commentSum) {
        this.commentSum = commentSum;
    }

    public Integer getCommentSum() {
        return commentSum;
    }

    public void setCollectSum(Integer collectSum) {
        this.collectSum = collectSum;
    }

    public Integer getCollectSum() {
        return collectSum;
    }

    public void setSupportSum(Integer supportSum) {
        this.supportSum = supportSum;
    }

    public Integer getSupportSum() {
        return supportSum;
    }

    public void setOpposeSum(Integer opposeSum) {
        this.opposeSum = opposeSum;
    }

    public Integer getOpposeSum() {
        return opposeSum;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setCreateUid(Long createUid) {
        this.createUid = createUid;
    }

    public Long getCreateUid() {
        return createUid;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "VideoBase{" +
                "id=" + id +
                ", schoolType=" + schoolType +
                ", gradeName='" + gradeName + '\'' +
                ", courseId=" + courseId +
                ", url='" + url + '\'' +
                ", indexImage='" + indexImage + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", playSum=" + playSum +
                ", commentSum=" + commentSum +
                ", collectSum=" + collectSum +
                ", supportSum=" + supportSum +
                ", opposeSum=" + opposeSum +
                ", label='" + label + '\'' +
                ", createUid=" + createUid +
                ", createTime=" + createTime +
                '}';
    }
}

