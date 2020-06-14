package com.xh.module.base.entity.pay;

import java.io.Serializable;

/**
 * 用户实名认证
 * table name:  user_realauth
 * author name: 朱华清
 * create time: 2020-06-11 11:19:17
 */
public class UserRealauth implements Serializable {

    private static final long serialVersionUID = 3289626079764305334L;

    //	@Column(columnName = "uid",allowNull = false,comment = "用户ID")
    private Long uid;

    //	@Column(columnName = "real_name",allowNull = false,comment = "身份证姓名")
    private String realName;

    //	@Column(columnName = "gender",allowNull = false,comment = "用户性别 0-female 1-male")
    private Byte gender;

    //	@Column(columnName = "identity_card",allowNull = false,comment = "身份证号码")
    private String identityCard;

    //	@Column(columnName = "expiry_date",allowNull = true,comment = "身份证有效期")
    private Integer expiryDate;

    //	@Column(columnName = "image_coin",allowNull = true,comment = "身份证正面照")
    private String imageCoin;

    //	@Column(columnName = "image_flip",allowNull = true,comment = "身份证反面照")
    private String imageFlip;

    //	@Column(columnName = "address",allowNull = true,comment = "住址")
    private String address;

    //	@Column(columnName = "state",allowNull = false,comment = "状态 0:未确认 1:确认通过")
    private Integer state;

    //	@Column(columnName = "create_time",allowNull = false,comment = "认证时间")
    private Integer createTime;

    public UserRealauth() {
        super();
    }

    public UserRealauth(Long uid, String realName, Byte gender, String identityCard, Integer expiryDate, String imageCoin, String imageFlip, String address, Integer state, Integer createTime) {
        this.uid = uid;
        this.realName = realName;
        this.gender = gender;
        this.identityCard = identityCard;
        this.expiryDate = expiryDate;
        this.imageCoin = imageCoin;
        this.imageFlip = imageFlip;
        this.address = address;
        this.state = state;
        this.createTime = createTime;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public Integer getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Integer expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getImageCoin() {
        return imageCoin;
    }

    public void setImageCoin(String imageCoin) {
        this.imageCoin = imageCoin;
    }

    public String getImageFlip() {
        return imageFlip;
    }

    public void setImageFlip(String imageFlip) {
        this.imageFlip = imageFlip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "user_realauth[" +
                "uid=" + uid +
                ", real_name=" + realName +
                ", gender=" + gender +
                ", identity_card=" + identityCard +
                ", expiry_date=" + expiryDate +
                ", image_coin=" + imageCoin +
                ", image_flip=" + imageFlip +
                ", address=" + address +
                ", state=" + state +
                ", create_time=" + createTime +
                "]";
    }
}

