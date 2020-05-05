package com.xh.module_teach;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;

import androidx.annotation.ColorInt;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.xh.module.base.BaseFragment;
import com.xh.module.base.utils.RouteUtils;


/**
 * 班级主页面
 */
@Route(path = RouteUtils.Teach_Fragment_Main)
public class TeachMainFragment extends BaseFragment {

    BridgeWebView webView;

    public TeachMainFragment() {
        // Required empty public constructor
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_teach_main;
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

        webView.loadUrl("http://39.99.168.20:7100/#/pages/teaching/home");

//        setStatusTextColor(true, getActivity().getWindow(), ContextCompat.getColor(getContext(), R.color.Black));
    }

    /**
     * 设置状态栏字体颜色
     *
     * @param isDark
     */
    @SuppressLint("ResourceAsColor")
    public void setStatusTextColor(boolean isDark, Window window, @ColorInt int color) {
        if (isDark) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //黑色字体
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                window.setStatusBarColor(color);
            }
        } else {
            //白色字体
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        }
    }
}
