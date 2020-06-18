package com.xh.module_me.activity;

import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.luck.picture.lib.tools.ToastUtils;
import com.xh.module.base.activity.WebviewActivity;
import com.xh.module.base.db.DBManager;
import com.xh.module.base.entity.pay.OrderPayResult;
import com.xh.module.base.repository.impl.PayRepository;
import com.xh.module.base.retrofit.IRxJavaCallBack;
import com.xh.module.base.retrofit.ResponseCode;
import com.xh.module.base.retrofit.response.SimpleResponse;
import com.xh.module.base.utils.RouteUtils;

/**
 * 支付页面
 */
@Route(path = RouteUtils.Me_Activity_Pay_Webview)
public class PayWebPageActivity extends WebviewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 注册 js发送给android发送消息的方法
     */
    @Override
    protected void registerMethods() {
        super.registerMethods();
        /**
         * 支付成功
         */
        webView.registerHandler("paysuccess", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                ToastUtils.s(PayWebPageActivity.this, "支付成功:" + data);
                try {
                    OrderPayResult payResult = gson.fromJson(data, OrderPayResult.class);
                    PayRepository.getInstance().updateOrderStatus(payResult, new IRxJavaCallBack<SimpleResponse>() {
                        @Override
                        public void onSuccess(SimpleResponse simpleResponse) {
                            Log.e("PAY", "更新订单状态：" + gson.toJson(simpleResponse));
                            if (simpleResponse.getCode() == ResponseCode.RESULT_OK) {
                            } else {
                                payResult.setTimeStamp(System.currentTimeMillis());
                                insertOrderStatus2DB(payResult);
                            }
                        }

                        @Override
                        public void onError(Throwable throwable) {
                            // 如果上传失败，记录失败的消息，适时上传
                            payResult.setTimeStamp(System.currentTimeMillis());
                            insertOrderStatus2DB(payResult);
                        }
                    });

                    if (function != null) {
                        function.onCallBack("success");
                    }
                } catch (Exception e) {
                    if (function != null) {
                        function.onCallBack("fail");
                    }
                }
            }
        });

        /**
         * 支付失败
         */
        webView.registerHandler("payfail", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                ToastUtils.s(PayWebPageActivity.this, "支付失败:" + data);
                try {
                    OrderPayResult payResult = gson.fromJson(data, OrderPayResult.class);
                    PayRepository.getInstance().updateOrderStatus(payResult, new IRxJavaCallBack<SimpleResponse>() {
                        @Override
                        public void onSuccess(SimpleResponse simpleResponse) {
                            Log.e("PAY", "更新订单状态结果：" + gson.toJson(simpleResponse));
                            if (simpleResponse.getCode() == ResponseCode.RESULT_OK) {
                            } else {
                                payResult.setTimeStamp(System.currentTimeMillis());
                                insertOrderStatus2DB(payResult);
                            }
                        }

                        @Override
                        public void onError(Throwable throwable) {
                            // 如果上传失败，记录失败的消息，适时上传
                            payResult.setTimeStamp(System.currentTimeMillis());
                            insertOrderStatus2DB(payResult);
                        }
                    });

                    if (function != null) {
                        function.onCallBack("success");
                    }
                } catch (Exception e) {
                    if (function != null) {
                        function.onCallBack("fail");
                    }
                }


                if (function != null) {
                    function.onCallBack("success");
                }
            }
        });
    }

    /**
     * 插入订单支付失败结果到服务器
     *
     * @param payResult
     */
    private void insertOrderStatus2DB(OrderPayResult payResult) {
        if (!DBManager.getInstance().getmDaoSession().getOrderPayResultDao().hasKey(payResult)) {
            DBManager.getInstance().getmDaoSession().getOrderPayResultDao().insert(payResult);
        }
    }
}
