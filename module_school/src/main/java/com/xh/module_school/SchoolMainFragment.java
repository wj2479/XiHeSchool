package com.xh.module_school;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.core.LogisticsCenter;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.xh.module.base.BaseFragment;
import com.xh.module.base.adapter.TabFragmentPagerAdapter;
import com.xh.module.base.utils.FragmentUtils;
import com.xh.module.base.utils.RouteUtils;
import com.xh.module.base.view.TabIconBean;
import com.xh.module_school.adapter.MyBannerAdapter;
import com.youth.banner.Banner;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.util.BannerUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;


/**
 * 学校主页
 */
@Route(path = RouteUtils.School_Fragment_Main)
public class SchoolMainFragment extends BaseFragment implements View.OnClickListener {

    private static final int REQUEST_CODE_QRCODE = 666;

    BridgeWebView webView;

    CommonTabLayout navLayout, contentLayout;
    ViewPager vp;
    Banner banner;

    private ArrayList<CustomTabEntity> mNavTabEntities = new ArrayList();
    private ArrayList<CustomTabEntity> mContentTabEntities = new ArrayList();

    public SchoolMainFragment() {
        // Required empty public constructor
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_school_main;
    }

    @Override
    protected void initView(View rootView) {
        banner = rootView.findViewById(R.id.banner);
        navLayout = rootView.findViewById(R.id.navLayout);
        contentLayout = rootView.findViewById(R.id.contentLayout);
        vp = rootView.findViewById(R.id.vp);

        rootView.findViewById(R.id.scanIv).setOnClickListener(this);

        initBanner();
        initNavLayout();
        initContentLayout();

//        webView = rootView.findViewById(R.id.webview);
//        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
//        webView.setWebChromeClient(new WebChromeClient());
////        webView.setWebViewClient(new MyWebViewClient(webView) );
//        webView.getSettings().setUseWideViewPort(true);
//        webView.getSettings().setLoadWithOverviewMode(true);
//        webView.getSettings().setGeolocationEnabled(true);
//        webView.getSettings().setDomStorageEnabled(true);
//        webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
//
//        webView.loadUrl("http://39.99.168.20:7100/#/pages/school/home");
    }

    /**
     * 初始化导航
     */
    private void initBanner() {
        List<ImageText> imageTextList = new ArrayList<>();
        ImageText it1 = new ImageText("课程表", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1588572722737&di=e237b7eeacc68961ea3aa57a6cec9a81&imgtype=0&src=http%3A%2F%2Fa0.att.hudong.com%2F64%2F76%2F20300001349415131407760417677.jpg");
        ImageText it2 = new ImageText("班级信息", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1588572722737&di=4b86a60f254a6f816e16fa0eec67e6d7&imgtype=0&src=http%3A%2F%2Fb2-q.mafengwo.net%2Fs5%2FM00%2F91%2F06%2FwKgB3FH_RVuATULaAAH7UzpKp6043.jpeg");
        ImageText it3 = new ImageText("考勤", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1588572722736&di=ce7a36dc30fdc03d55c3ac332532ec07&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Frushidao%2Fpics%2Fhv1%2F20%2F108%2F1744%2F113431160.jpg");
        ImageText it4 = new ImageText("班级风采", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1588572722735&di=a9083588be329da7b3d5f0cce849d6b5&imgtype=0&src=http%3A%2F%2Ffile02.16sucai.com%2Fd%2Ffile%2F2014%2F1021%2F3a6943761bf142b41742813f386c98ce.jpg");
        ImageText it5 = new ImageText("行为评价", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1588572722735&di=b93965c0f1bf3e8383b842276e98480c&imgtype=0&src=http%3A%2F%2Ffile02.16sucai.com%2Fd%2Ffile%2F2014%2F0829%2Fb871e1addf5f8e96f3b390ece2b2da0d.jpg");
        imageTextList.add(it1);
        imageTextList.add(it2);
        imageTextList.add(it3);
        imageTextList.add(it4);
        imageTextList.add(it5);

        banner.setAdapter(new MyBannerAdapter(imageTextList, getContext()));
        banner.setIndicator(new CircleIndicator(getContext()));
        banner.setIndicatorSelectedColorRes(R.color.themecolor);
        banner.setIndicatorNormalColorRes(R.color.white);
        banner.setIndicatorGravity(IndicatorConfig.Direction.CENTER);
        banner.setIndicatorSpace((20));
        banner.setIndicatorMargins(new IndicatorConfig.Margins((int) BannerUtils.dp2px(10)));
        banner.setIndicatorWidth(10, 20);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(Object data, int position) {
                Log.e("TAG", "点击事件");
            }
        });
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


    private void initNavLayout() {
        mNavTabEntities.add(new TabIconBean("资讯", R.drawable.ic_infomation, R.drawable.ic_infomation));
        mNavTabEntities.add(new TabIconBean("校长信箱", R.drawable.ic_mail, R.drawable.ic_mail));
        mNavTabEntities.add(new TabIconBean("新消息", R.drawable.ic_new_mes, R.drawable.ic_new_mes));
        navLayout.setTabData(mNavTabEntities);

        navLayout.showDot(1);
    }


    private void initContentLayout() {
        String[] titles = {"班主任", "教师", "家长"};
        for (String title : titles) {
            mContentTabEntities.add(new TabIconBean(title, 0, 0));
        }

        List<Fragment> fragmentList = new ArrayList();
        fragmentList.add(FragmentUtils.getSchoolClassTeacherMenuFragment());
        fragmentList.add(FragmentUtils.getSchoolTeacherMenuFragment());
        fragmentList.add(FragmentUtils.getSchoolFamilyMenuFragment());

        //viewpager加载adapter
        vp.setAdapter(new TabFragmentPagerAdapter(getChildFragmentManager(), fragmentList, titles));
        initPager();
        contentLayout.setTabData(mContentTabEntities);
    }

    @Override
    public void onStart() {
        super.onStart();
        banner.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stop();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.scanIv) {
            rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
                @Override
                public void accept(Boolean bool) {
                    if (bool) {
                        // fragment的回调有些不同
                        Postcard postcard = ARouter.getInstance().build(RouteUtils.Activity_Scan);
                        LogisticsCenter.completion(postcard);
                        Intent intent = new Intent(getActivity(), postcard.getDestination());
                        intent.putExtras(postcard.getExtras());
                        startActivityForResult(intent, REQUEST_CODE_QRCODE);                    //跳转，code要大于0
                    }
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) {

                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_QRCODE) {
            String result = data.getStringExtra("result");
            Log.e("TAG", "扫描的结果为:" + result);
        }
    }

}
