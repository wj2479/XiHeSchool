package com.xh.module_school.activity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.xh.module.base.BackActivity;
import com.xh.module_school.R;
import com.xh.module_school.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 考勤界面
 */
public class CheckMainActivity extends BackActivity {

    @BindView(R2.id.webview)
    BridgeWebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_main);

        ButterKnife.bind(this);

        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.setWebChromeClient(new WebChromeClient());
//        webView.setWebViewClient(new MyWebViewClient(webView) );
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setGeolocationEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setMediaPlaybackRequiresUserGesture(false);

        webView.loadUrl("http://iot.shunshiwei.com:8077/#/index?wristbandNo=C88022F2F328");
    }
}
