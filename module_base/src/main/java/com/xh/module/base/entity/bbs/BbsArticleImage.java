package com.xh.module.base.entity.bbs;


/**
 * table name:  bbs_article_image
 * author name: 朱华清
 * create time: 2020-05-21 20:00:37
 */
public class BbsArticleImage {
    private Long id;

    private Long articleId;

    private String address;

    public BbsArticleImage() {
        super();
    }

    public BbsArticleImage(Long id, Long articleId, String address) {
        this.id = id;
        this.articleId = articleId;
        this.address = address;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "bbs_article_image[" +
                "id=" + id +
                ", article_id=" + articleId +
                ", address=" + address +
                "]";
    }
}