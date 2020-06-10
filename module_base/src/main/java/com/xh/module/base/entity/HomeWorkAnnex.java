package com.xh.module.base.entity;

/**
 * 家庭作业附件
 */
public class HomeWorkAnnex {

    /**
     * schoolworkId : 222
     * url : http://www.baidu.com
     * type : 1
     */
    String schoolworkId;
    String url;
    int type;

    public String getSchoolworkId() {
        return schoolworkId;
    }

    public void setSchoolworkId(String schoolworkId) {
        this.schoolworkId = schoolworkId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
