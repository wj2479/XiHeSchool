package com.xh.module.base.repository.impl;

import com.google.gson.Gson;
import com.xh.module.base.entity.pay.BankResult;
import com.xh.module.base.entity.pay.OrderInfo;
import com.xh.module.base.repository.IPayRepository;
import com.xh.module.base.retrofit.ApiManager;
import com.xh.module.base.retrofit.IRxJavaCallBack;
import com.xh.module.base.retrofit.response.SimpleResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 支付相关接口
 */
public class PayRepository implements IPayRepository {

    private static PayRepository INSTANCE = null;

    Gson gson = new Gson();

    private PayRepository() {
    }

    public static PayRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PayRepository();
        }
        return INSTANCE;
    }

    @Override
    public void getBankResult(String openId, String serviceId, String channel_serno, String orgNo, String acct_number, String mac, IRxJavaCallBack<BankResult> callback) {
        JSONObject json = new JSONObject();
        try {
            json.put("openId", openId);
            json.put("serviceId", serviceId);
            json.put("channel_serno", channel_serno);
            json.put("orgNo", orgNo);
            json.put("acct_number", acct_number);
            json.put("mac", mac);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());
        ApiManager.getInstance().getSchoolServer().requestBank(requestBody)
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BankResult>() {
                               @Override
                               public void accept(BankResult bankResult) throws Exception {
                                   if (callback != null) {
                                       callback.onSuccess(bankResult);
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   if (callback != null) {
                                       callback.onError(throwable);
                                   }
                               }
                           }
                );
    }

    @Override
    public void getUnpaidOrder(long uid, int page, int pageSize, IRxJavaCallBack<SimpleResponse<List<OrderInfo>>> callback) {
        ApiManager.getInstance().getSchoolServer().getUnpaidOrder(uid, page, pageSize)
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SimpleResponse<List<OrderInfo>>>() {
                               @Override
                               public void accept(SimpleResponse<List<OrderInfo>> response) throws Exception {
                                   if (callback != null) {
                                       callback.onSuccess(response);
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   if (callback != null) {
                                       callback.onError(throwable);
                                   }
                               }
                           }
                );

    }

    @Override
    public void requestPay(String osType, String phoneType, String mac, String address, String imei, String ip, long uid, String orderIds, IRxJavaCallBack<BankResult> callback) {
        JSONObject json = new JSONObject();
        try {
            json.put("osType", osType);
            json.put("phoneType", phoneType);
            json.put("mac", mac);
            json.put("address", address);
            json.put("imei", imei);
            json.put("ip", ip);
            json.put("uid", uid);
            json.put("orderIds", orderIds);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());
        ApiManager.getInstance().getSchoolServer().requestBank(requestBody)
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BankResult>() {
                               @Override
                               public void accept(BankResult bankResult) throws Exception {
                                   if (callback != null) {
                                       callback.onSuccess(bankResult);
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   if (callback != null) {
                                       callback.onError(throwable);
                                   }
                               }
                           }
                );
    }

    @Override
    public void requestWallet(String osType, String phoneType, String mac, String address, String imei, String ip, long uid, IRxJavaCallBack<BankResult> callback) {
        JSONObject json = new JSONObject();
        try {
            json.put("osType", osType);
            json.put("phoneType", phoneType);
            json.put("mac", mac);
            json.put("address", address);
            json.put("imei", imei);
            json.put("ip", ip);
            json.put("uid", uid);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());
        ApiManager.getInstance().getSchoolServer().requestBank(requestBody)
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BankResult>() {
                               @Override
                               public void accept(BankResult bankResult) throws Exception {
                                   if (callback != null) {
                                       callback.onSuccess(bankResult);
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   if (callback != null) {
                                       callback.onError(throwable);
                                   }
                               }
                           }
                );
    }
}
