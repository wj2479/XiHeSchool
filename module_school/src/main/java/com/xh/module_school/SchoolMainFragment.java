package com.xh.module_school;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.xh.module.base.BaseFragment;
import com.xh.module.base.utils.RouteUtils;


/**
 * 学校主页
 */
@Route(path = RouteUtils.School_Fragment_Main)
public class SchoolMainFragment extends BaseFragment {

    BridgeWebView webView;

    public SchoolMainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_school_main, container, false);
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

        webView.loadUrl("http://39.99.168.20:7100/#/pages/school/home");
    }
}
