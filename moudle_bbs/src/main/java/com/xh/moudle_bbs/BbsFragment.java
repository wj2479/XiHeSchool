package com.xh.moudle_bbs;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.xh.module.base.BaseFragment;
import com.xh.module.base.adapter.TabFragmentPagerAdapter;
import com.xh.module.base.utils.FragmentUtils;
import com.xh.module.base.utils.RouteUtils;
import com.xh.module.base.view.TabIconBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 *
 */
@Route(path = RouteUtils.Bbs_Fragment_Main)
public class BbsFragment extends BaseFragment {

    @BindView(R2.id.contentLayout)
    CommonTabLayout contentLayout;
    @BindView(R2.id.vp)
    ViewPager vp;

    private ArrayList<CustomTabEntity> mContentTabEntities = new ArrayList();

    public BbsFragment() {
        // Required empty public constructor
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_bbs;
    }

    @Override
    protected void initView(View rootView) {
//        webView = rootView.findViewById(R.id.webview);
//        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
//        webView.setWebChromeClient(new WebChromeClient());
//        webView.setWebViewClient(new MyWebViewClient(webView));
//        webView.getSettings().setUseWideViewPort(true);
//        webView.getSettings().setLoadWithOverviewMode(true);
//        webView.getSettings().setGeolocationEnabled(true);
//        webView.getSettings().setDomStorageEnabled(true);
//        webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
//
//        webView.loadUrl("http://39.99.168.20:7100/#/pages/bbs/home");
        initContentLayout();
    }


    private void initContentLayout() {
        String[] titles = {"关注", "推荐"};
        for (String title : titles) {
            mContentTabEntities.add(new TabIconBean(title, 0, 0));
        }

        List<Fragment> fragmentList = new ArrayList();
        fragmentList.add(FragmentUtils.getBbsAttentionHomeFragment());
        fragmentList.add(FragmentUtils.getBbsRecommendHomeFragment());

        //viewpager加载adapter
        vp.setAdapter(new TabFragmentPagerAdapter(getChildFragmentManager(), fragmentList, titles));
        initPager();
        contentLayout.setTabData(mContentTabEntities);
    }

    private void initPager() {

        contentLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vp.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                contentLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
