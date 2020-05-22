package com.xh.school.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.xh.module.base.BackActivity;
import com.xh.module.base.utils.LogUtil;
import com.xh.school.R;
import com.xh.school.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 网页显示的Activity
 */
public class WebviewActivity extends BackActivity {

    public static final String URL = "url";

    @BindView(R2.id.webView)
    BridgeWebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        ButterKnife.bind(this);

        initWebview();

        registerMethods();

        if (getIntent().hasExtra(URL)) {
            String url = getIntent().getStringExtra(URL);
            webView.loadUrl(url);
        } else {
            finish();
        }
    }

    /**
     * 初始化webview
     */
    private void initWebview() {
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setGeolocationEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setMediaPlaybackRequiresUserGesture(false);

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                setTitle(title);
            }
        });
    }

    /**
     * 注册 js发送给android发送消息的方法
     */
    protected void registerMethods() {
        /**
         * 显示消息对话框
         */
        webView.registerHandler("showInfoDialog", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                if (!TextUtils.isEmpty(data)) {
                    showInfoDialog(data);
                }
                if (function != null) {
                    function.onCallBack("success");
                }
            }
        });

        /**
         * 显示成功对话框
         */
        webView.registerHandler("showSuccessDialog", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                if (!TextUtils.isEmpty(data)) {
                    showSuccessDialog(data);
                }
                if (function != null) {
                    function.onCallBack("success");
                }
            }
        });

        /**
         * 显示失败对话框
         */
        webView.registerHandler("showFailDialog", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                if (!TextUtils.isEmpty(data)) {
                    showFailDialog(data);
                }
                if (function != null) {
                    function.onCallBack("success");
                }
            }
        });

        /**
         * 显示加载对话框
         */
        webView.registerHandler("showLoadingDialog", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                if (!TextUtils.isEmpty(data)) {
                    showLoadingDialog(data);
                }
                if (function != null) {
                    function.onCallBack("success");
                }
            }
        });

        /**
         * 对话框消失
         */
        webView.registerHandler("dismissDialog", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                if (!TextUtils.isEmpty(data)) {
                    dismissDialog();
                }
                if (function != null) {
                    function.onCallBack("success");
                }
            }
        });

        /**
         * 打印日志
         */
        webView.registerHandler("logi", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                if (!TextUtils.isEmpty(data)) {
                    LogUtil.i("Console", data);
                }
                if (function != null) {
                    function.onCallBack("success");
                }
            }
        });


        /**
         * 打印错误
         */
        webView.registerHandler("loge", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                if (!TextUtils.isEmpty(data)) {
                    LogUtil.e("Console", data);
                }
                if (function != null) {
                    function.onCallBack("success");
                }
            }
        });

        /**
         * 跳转到新页面
         */
        webView.registerHandler("navigate", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                if (!TextUtils.isEmpty(data)) {
                    Intent intent = new Intent("com.xh.school.web");
                    intent.putExtra("url", data);
                    startActivity(intent);
                }

                //返回给html的消息
                if (function != null)
                    function.onCallBack("success");
            }
        });

        /**
         * 关闭当前页面
         */
        webView.registerHandler("finish", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                if (!TextUtils.isEmpty(data)) {
                    finish();
                }
                if (function != null) {
                    function.onCallBack("success");
                }
            }
        });

    }
}
