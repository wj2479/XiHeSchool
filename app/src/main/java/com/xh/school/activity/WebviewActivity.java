package com.xh.school.activity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.xh.module.base.BackActivity;
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
}
