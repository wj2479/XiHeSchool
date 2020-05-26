package com.xh.module.base.entity.bbs;

import java.math.BigDecimal;
import java.util.List;

/**
 * table name:  bbs_article
 * author name: 朱华清
 * create time: 2020-05-21 17:43:38
 */
public class BbsArticle {

    private Long id;
    private Long uid;
    private String title;
    private Long typeId;
    private Long moduleId;
    private String content;
    private Long createTime;
    private Integer hits;
    private Integer hotNum;
    private Integer likeNum;
    private Long updateTime;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String address;
    private BbsUser bbsUser;

    private List<BbsArticleImage> bbsArticleImages;

    public BbsArticle() {
        super();
    }

    public BbsArticle(Long id, Long uid, String title, Long typeId, Long moduleId, String content, Long createTime, Integer hits, Integer hotNum, Integer likeNum, Long updateTime, BigDecimal longitude, BigDecimal latitude, String address) {
        this.id = id;
        this.uid = uid;
        this.title = title;
        this.typeId = typeId;
        this.moduleId = moduleId;
        this.content = content;
        this.createTime = createTime;
        this.hits = hits;
        this.hotNum = hotNum;
        this.likeNum = likeNum;
        this.updateTime = updateTime;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public Long getModuleId() {
        return moduleId;
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

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHotNum(Integer hotNum) {
        this.hotNum = hotNum;
    }

    public Integer getHotNum() {
        return hotNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public List<BbsArticleImage> getBbsArticleImages() {
        return bbsArticleImages;
    }

    public void setBbsArticleImages(List<BbsArticleImage> bbsArticleImages) {
        this.bbsArticleImages = bbsArticleImages;
    }

    public BbsUser getBbsUser() {
        return bbsUser;
    }

    public void setBbsUser(BbsUser bbsUser) {
        this.bbsUser = bbsUser;
    }

    @Override
    public String toString() {
        return "bbs_article[" +
                "id=" + id +
                ", uid=" + uid +
                ", title=" + title +
                ", type_id=" + typeId +
                ", module_id=" + moduleId +
                ", content=" + content +
                ", create_time=" + createTime +
                ", hits=" + hits +
                ", hot_num=" + hotNum +
                ", like_num=" + likeNum +
                ", update_time=" + updateTime +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", address=" + address +
                "]";
    }
}

