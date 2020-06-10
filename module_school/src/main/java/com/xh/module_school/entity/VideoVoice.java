package com.xh.module_school.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class VideoVoice implements MultiItemEntity {

    public static final String MIMETYPE_VIDEO = "video";
    public static final String MIMETYPE_VOICE = "voice";
    public static final String MIMETYPE_IMAGE = "image";

    public static final int TYPE_VIDEO = 0;
    public static final int TYPE_VOICE = 1;

    /**
     * original path
     */
    private String path;

    /**
     * 类型
     */
    private String mimeType;

    /**
     * 长度
     */
    private long duration;

    /**
     * 大小
     */
    private long size;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @Override
    public int getItemType() {

        if (mimeType.equals(MIMETYPE_VIDEO)) {
            return TYPE_VIDEO;
        } else {
            return TYPE_VOICE;
        }
    }
}
