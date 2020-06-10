package com.xh.module.base.entity.pay;

import java.math.BigDecimal;

/**
 * 订单表
 * table name:  order_info
 * author name: 朱华清
 * create time: 2020-06-09 20:30:00
 */
public class OrderInfo {

    //	@Column(columnName = "id",allowNull = false,comment = "主键（订单号）")
    private Long id;

    //	@Column(columnName = "item_id",allowNull = false,comment = "付款项目id")
    private Long itemId;

    //	@Column(columnName = "buyer_uid",allowNull = false,comment = "购买者uid")
    private Long buyerUid;

    //	@Column(columnName = "pay_status",allowNull = false,comment = "支付状态 0 未支付 1 支付成功 2 支付失败 3 支付中")
    private Integer payStatus;

    //	@Column(columnName = "amount",allowNull = false,comment = "金额")
    private BigDecimal amount;

    //	@Column(columnName = "create_time",allowNull = false,comment = "创建时间")
    private Integer createTime;

    //付款信息
    private PayItem payItem;

    /**
     * 是否被选中
     */
    private boolean isSelected = false;

    public OrderInfo() {
        super();
    }

    public OrderInfo(Long id, Long itemId, Long buyerUid, Integer payStatus, BigDecimal amount, Integer createTime) {
        this.id = id;
        this.itemId = itemId;
        this.buyerUid = buyerUid;
        this.payStatus = payStatus;
        this.amount = amount;
        this.createTime = createTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setBuyerUid(Long buyerUid) {
        this.buyerUid = buyerUid;
    }

    public Long getBuyerUid() {
        return buyerUid;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public PayItem getPayItem() {
        return payItem;
    }

    public void setPayItem(PayItem payItem) {
        this.payItem = payItem;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public String toString() {
        return "order_info[" +
                "id=" + id +
                ", item_id=" + itemId +
                ", buyer_uid=" + buyerUid +
                ", pay_status=" + payStatus +
                ", amount=" + amount +
                ", create_time=" + createTime +
                "]";
    }
}

