package com.xh.module.base.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 班级风采
 */
public class ClassDemeanor implements Parcelable {

    private Long id;

    private Long clasId;

    private String indexImage;

    private String title;

    private String content;

    private Long createTime;

    private Long createUid;

    public ClassDemeanor() {
    }

    protected ClassDemeanor(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        if (in.readByte() == 0) {
            clasId = null;
        } else {
            clasId = in.readLong();
        }
        indexImage = in.readString();
        title = in.readString();
        content = in.readString();
        if (in.readByte() == 0) {
            createTime = null;
        } else {
            createTime = in.readLong();
        }
        if (in.readByte() == 0) {
            createUid = null;
        } else {
            createUid = in.readLong();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        if (clasId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(clasId);
        }
        dest.writeString(indexImage);
        dest.writeString(title);
        dest.writeString(content);
        if (createTime == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(createTime);
        }
        if (createUid == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(createUid);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ClassDemeanor> CREATOR = new Creator<ClassDemeanor>() {
        @Override
        public ClassDemeanor createFromParcel(Parcel in) {
            return new ClassDemeanor(in);
        }

        @Override
        public ClassDemeanor[] newArray(int size) {
            return new ClassDemeanor[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClasId() {
        return clasId;
    }

    public void setClasId(Long clasId) {
        this.clasId = clasId;
    }

    public String getIndexImage() {
        return indexImage;
    }

    public void setIndexImage(String indexImage) {
        this.indexImage = indexImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUid() {
        return createUid;
    }

    public void setCreateUid(Long createUid) {
        this.createUid = createUid;
    }
}
