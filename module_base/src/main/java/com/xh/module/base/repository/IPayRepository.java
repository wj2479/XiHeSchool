package com.xh.module.base.repository;

import com.xh.module.base.entity.pay.BankResult;
import com.xh.module.base.entity.pay.OrderInfo;
import com.xh.module.base.entity.pay.OrderPayResult;
import com.xh.module.base.entity.pay.UserRealauth;
import com.xh.module.base.retrofit.IRxJavaCallBack;
import com.xh.module.base.retrofit.response.SimpleResponse;

import java.util.List;

public interface IPayRepository {

    void getBankResult(String openId, String serviceId, String channel_serno, String orgNo, String acct_number, String mac, IRxJavaCallBack<BankResult> callback);

    /**
     * 获取未支付的订单
     *
     * @param uid
     * @param page
     * @param pageSize
     * @param callback
     */
    void getUnpaidOrder(long uid, int page, int pageSize, IRxJavaCallBack<SimpleResponse<List<OrderInfo>>> callback);

    /**
     * 获取支付的订单
     *
     * @param uid
     * @param page
     * @param pageSize
     * @param callback
     */
    void getPaidOrder(long uid, int page, int pageSize, IRxJavaCallBack<SimpleResponse<List<OrderInfo>>> callback);


    /**
     * 发起支付请求
     * 支付 传送给我这些参数  osType（系统名称） phoneType（手机型号 比如MI6） mac(Mac地址) address（gps定位地址） IMEI(用户设备的IMEI) ip(用户当前的ip地址)  uid（当前用户的id） orderIds（订单id，多个用逗号隔开）
     *
     * @param callback
     */
    void requestPay(String osType, String phoneType, String mac, String address, String imei, String ip, long uid, String orderIds, IRxJavaCallBack<BankResult> callback);

    /**
     * 发起钱包请求
     *
     * @param osType
     * @param phoneType
     * @param mac
     * @param address
     * @param imei
     * @param ip
     * @param uid
     * @param callback
     */
    void requestWallet(String osType, String phoneType, String mac, String address, String imei, String ip, long uid, IRxJavaCallBack<BankResult> callback);

    /**
     * 获取用户实名认证状态
     *
     * @param uid
     * @param callback
     */
    void getRealAuthStatus(long uid, IRxJavaCallBack<SimpleResponse<UserRealauth>> callback);

    /**
     * 请求实名认证
     *
     * @param realauth
     * @param callback
     */
    void requestUserRealAuth(UserRealauth realauth, IRxJavaCallBack<SimpleResponse> callback);

    /**
     * 更新订单状态
     *
     * @param callback
     */
    void updateOrderStatus(OrderPayResult orderPayResult, IRxJavaCallBack<SimpleResponse> callback);
}
