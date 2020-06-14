package com.xh.module.base.entity.pay;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

/**
 * 订单支付结果
 */
@Entity
public class OrderPayResult {
    /**
     * 订单编号
     */
    @Unique
    String channek_serno;
    /**
     * 订单状态
     */
    int orderStatus;
    /**
     * 服务ID
     */
    String serviceId;
    /**
     * 订单更新时间戳
     */
    long timeStamp;

    @Generated(hash = 801318563)
    public OrderPayResult(String channek_serno, int orderStatus, String serviceId,
                          long timeStamp) {
        this.channek_serno = channek_serno;
        this.orderStatus = orderStatus;
        this.serviceId = serviceId;
        this.timeStamp = timeStamp;
    }

    @Generated(hash = 1724094849)
    public OrderPayResult() {
    }

    public String getChannek_serno() {
        return channek_serno;
    }

    public void setChannek_serno(String channek_serno) {
        this.channek_serno = channek_serno;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
