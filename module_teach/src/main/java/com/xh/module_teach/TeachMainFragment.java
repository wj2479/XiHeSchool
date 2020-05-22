package com.xh.module_teach;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.tamsiree.rxkit.RxKeyboardTool;
import com.xh.module.base.BaseFragment;
import com.xh.module.base.adapter.TabFragmentPagerAdapter;
import com.xh.module.base.entity.ImageText;
import com.xh.module.base.utils.FragmentUtils;
import com.xh.module.base.utils.RouteUtils;
import com.xh.module.base.view.TabIconBean;
import com.xh.module_teach.adapter.MyBannerAdapter;
import com.youth.banner.Banner;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.util.BannerUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 班级主页面
 */
@Route(path = RouteUtils.Teach_Fragment_Main)
public class TeachMainFragment extends BaseFragment {

    @BindView(R2.id.contentLayout)
    CommonTabLayout contentLayout;
    @BindView(R2.id.vp)
    ViewPager vp;
    @BindView(R2.id.banner)
    Banner banner;
    @BindView(R2.id.searchEt)
    EditText searchEt;

    private ArrayList<CustomTabEntity> mContentTabEntities = new ArrayList();

    public TeachMainFragment() {
        // Required empty public constructor
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_teach_main;
    }

    @Override
    protected void initView(View rootView) {
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
//        webView.loadUrl("http://39.99.168.20:7100/#/pages/teaching/home");

//        setStatusTextColor(true, getActivity().getWindow(), ContextCompat.getColor(getContext(), R.color.Black));

        initSearch();
        initBanner();
        initContentLayout();
    }

    private void initSearch() {
        searchEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //点击搜索的时候隐藏软键盘
                    RxKeyboardTool.hideSoftInput(searchEt);
                    searchEt.setText("");
                    // 在这里写搜索的操作,一般都是网络请求数据
                    return true;
                }

                return false;
            }
        });
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

    private void initContentLayout() {
        String[] titles = {"直播", "录播"};
        for (String title : titles) {
            mContentTabEntities.add(new TabIconBean(title, 0, 0));
        }

        List<Fragment> fragmentList = new ArrayList();
//        fragmentList.add(FragmentUtils.getTeachVideoLiveHomeFragment());
        fragmentList.add(FragmentUtils.getTeachVideoRecordHomeFragment());

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
