package com.xh.module.base.entity.pay;

/**
 * 银行商户信息
 * table name:  bank_merchant
 * author name: 朱华清
 * create time: 2020-06-09 20:29:59
 */
public class BankMerchant {

    //	@Column(columnName = "id",allowNull = false,comment = "主键")
    private Long id;
    //	@Column(columnName = "appChaId",allowNull = false,comment = "商户编号")
    private String appchaid;
    //	@Column(columnName = "name",allowNull = false,comment = "商户名称")
    private String name;
    //	@Column(columnName = "type",allowNull = false,comment = "0:个人 1:公户")
    private Integer type;
    //	@Column(columnName = "account_number",allowNull = false,comment = "账号")
    private String accountNumber;
    //	@Column(columnName = "account_name",allowNull = false,comment = "账号名称")
    private String accountName;
    //	@Column(columnName = "owner",allowNull = false,comment = "个人就是用户uid，公户就是学校id")
    private Long owner;
    //	@Column(columnName = "create_uid",allowNull = false,comment = "创建人uid")
    private Long createUid;
    //	@Column(columnName = "create_time",allowNull = false,comment = "创建时间")
    private Integer createTime;
    //	@Column(columnName = "unit",allowNull = false,comment = "单位")
    private String unit;
    //	@Column(columnName = "sjgsjg",allowNull = false,comment = "上机归属机构")
    private String sjgsjg;
    //	@Column(columnName = "jgh",allowNull = false,comment = "机构号")
    private String jgh;
    //	@Column(columnName = "ejjgmc",allowNull = false,comment = "二级机构名称")
    private String ejjgmc;
    //	@Column(columnName = "ejzhjgh",allowNull = false,comment = "二级支行机构号")
    private String ejzhjgh;

    public BankMerchant() {
        super();
    }

    public BankMerchant(Long id, String appchaid, String name, Integer type, String accountNumber, String accountName, Long owner, Long createUid, Integer createTime, String unit, String sjgsjg, String jgh, String ejjgmc, String ejzhjgh) {
        this.id = id;
        this.appchaid = appchaid;
        this.name = name;
        this.type = type;
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.owner = owner;
        this.createUid = createUid;
        this.createTime = createTime;
        this.unit = unit;
        this.sjgsjg = sjgsjg;
        this.jgh = jgh;
        this.ejjgmc = ejjgmc;
        this.ejzhjgh = ejzhjgh;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setAppchaid(String appchaid) {
        this.appchaid = appchaid;
    }

    public String getAppchaid() {
        return appchaid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }

    public Long getOwner() {
        return owner;
    }

    public void setCreateUid(Long createUid) {
        this.createUid = createUid;
    }

    public Long getCreateUid() {
        return createUid;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setSjgsjg(String sjgsjg) {
        this.sjgsjg = sjgsjg;
    }

    public String getSjgsjg() {
        return sjgsjg;
    }

    public void setJgh(String jgh) {
        this.jgh = jgh;
    }

    public String getJgh() {
        return jgh;
    }

    public void setEjjgmc(String ejjgmc) {
        this.ejjgmc = ejjgmc;
    }

    public String getEjjgmc() {
        return ejjgmc;
    }

    public void setEjzhjgh(String ejzhjgh) {
        this.ejzhjgh = ejzhjgh;
    }

    public String getEjzhjgh() {
        return ejzhjgh;
    }

    @Override
    public String toString() {
        return "bank_merchant[" +
                "id=" + id +
                ", appChaId=" + appchaid +
                ", name=" + name +
                ", type=" + type +
                ", account_number=" + accountNumber +
                ", account_name=" + accountName +
                ", owner=" + owner +
                ", create_uid=" + createUid +
                ", create_time=" + createTime +
                ", unit=" + unit +
                ", sjgsjg=" + sjgsjg +
                ", jgh=" + jgh +
                ", ejjgmc=" + ejjgmc +
                ", ejzhjgh=" + ejzhjgh +
                "]";
    }
}

