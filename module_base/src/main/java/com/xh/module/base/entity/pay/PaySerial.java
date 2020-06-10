package com.xh.module.base.entity.pay;

import java.math.BigDecimal;

/**
 * 付款流水表
 * table name:  pay_serial
 * author name: 朱华清
 * create time: 2020-06-09 20:30:00
 */
public class PaySerial {

    //	@Column(columnName = "id",allowNull = false,comment = "流水号 主键")
    private Long id;

    //	@Column(columnName = "order_id",allowNull = false,comment = "付款项目id")
    private Long orderId;

    //	@Column(columnName = "pay_uid",allowNull = false,comment = "付款者uid")
    private Long payUid;

    //	@Column(columnName = "pay_status",allowNull = false,comment = "支付状态 0 未支付 1 支付成功 2 支付失败 3 支付中")
    private Integer payStatus;

    //	@Column(columnName = "amount",allowNull = false,comment = "金额")
    private BigDecimal amount;

    //	@Column(columnName = "create_time",allowNull = false,comment = "创建时间")
    private Integer createTime;

    public PaySerial() {
        super();
    }

    public PaySerial(Long id, Long orderId, Long payUid, Integer payStatus, BigDecimal amount, Integer createTime) {
        this.id = id;
        this.orderId = orderId;
        this.payUid = payUid;
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

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setPayUid(Long payUid) {
        this.payUid = payUid;
    }

    public Long getPayUid() {
        return payUid;
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

    @Override
    public String toString() {
        return "pay_serial[" +
                "id=" + id +
                ", order_id=" + orderId +
                ", pay_uid=" + payUid +
                ", pay_status=" + payStatus +
                ", amount=" + amount +
                ", create_time=" + createTime +
                "]";
    }
}

