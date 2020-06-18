package com.xh.module.base.entity.pay;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 订单支付结果
 */
@Entity
public class OrderPayResult {
    /**
     * 订单编号
     */
    @Id
    String channel_serno;
    /**
     * 订单状态
     */
    String orderStatus;
    /**
     * 服务ID
     */
    String serviceId;
    /**
     * 订单更新时间戳
     */
    long timeStamp;

    @Generated(hash = 1030320841)
    public OrderPayResult(String channel_serno, String orderStatus,
                          String serviceId, long timeStamp) {
        this.channel_serno = channel_serno;
        this.orderStatus = orderStatus;
        this.serviceId = serviceId;
        this.timeStamp = timeStamp;
    }

    @Generated(hash = 1724094849)
    public OrderPayResult() {
    }

    public String getChannel_serno() {
        return this.channel_serno;
    }

    public void setChannel_serno(String channel_serno) {
        this.channel_serno = channel_serno;
    }

    public String getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getServiceId() {
        return this.serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }


}
