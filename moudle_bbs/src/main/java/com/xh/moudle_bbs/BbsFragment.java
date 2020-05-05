package com.xh.moudle_bbs;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.xh.module.base.BaseFragment;
import com.xh.module.base.utils.RouteUtils;


/**
 *
 */
@Route(path = RouteUtils.Bbs_Fragment_Main)
public class BbsFragment extends BaseFragment {

    BridgeWebView webView;

    public BbsFragment() {
        // Required empty public constructor
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_bbs;
    }

    @Override
    protected void initView(View rootView) {
        webView = rootView.findViewById(R.id.webview);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.setWebChromeClient(new WebChromeClient());
//        webView.setWebViewClient(new MyWebViewClient(webView) );
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setGeolocationEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setMediaPlaybackRequiresUserGesture(false);

        webView.loadUrl("http://39.99.168.20:7100/#/pages/bbs/home");
    }
}
