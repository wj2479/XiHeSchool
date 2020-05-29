package com.xh.module_teach;

import android.content.Intent;
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
import com.xh.module.base.db.DBManager;
import com.xh.module.base.entity.VideoBase;
import com.xh.module.base.repository.impl.TeachRepository;
import com.xh.module.base.retrofit.IRxJavaCallBack;
import com.xh.module.base.retrofit.ResponseCode;
import com.xh.module.base.retrofit.response.SimpleResponse;
import com.xh.module.base.utils.FragmentUtils;
import com.xh.module.base.utils.RouteUtils;
import com.xh.module.base.view.TabIconBean;
import com.xh.module_teach.activity.RecordVideoDetailsActivity;
import com.xh.module_teach.adapter.RecommendVideoBannerAdapter;
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

    /**
     * 记录推荐点播视频列表
     */
    List<VideoBase> dataList = new ArrayList<>();

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

        getRecommendRecordVideoList();
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
     * 获取推荐点播视频列表
     */
    private void getRecommendRecordVideoList() {

        TeachRepository.getInstance().getRecommendRecordVideoList(new IRxJavaCallBack<SimpleResponse<List<VideoBase>>>() {
            @Override
            public void onSuccess(SimpleResponse<List<VideoBase>> response) {
                Log.e("TAG", "获取推荐点播视频列表:" + gson.toJson(response));
                if (response.getCode() == ResponseCode.RESULT_OK) {
                    dataList.clear();
                    dataList.addAll(response.getData());
                    banner.getAdapter().notifyDataSetChanged();

                    DBManager.getInstance().getmDaoSession().getVideoBaseDao().deleteAll();
                    for (VideoBase videoBase : dataList) {
                        DBManager.getInstance().getmDaoSession().getVideoBaseDao().insertOrReplace(videoBase);
                    }
                }
            }

            @Override
            public void onError(Throwable throwable) {
                Log.e("TAG", "获取点播视频列表:" + throwable.toString());
            }
        });
    }


    /**
     * 初始化导航
     */
    private void initBanner() {
        List<VideoBase> saveList = DBManager.getInstance().getmDaoSession().getVideoBaseDao().loadAll();
        dataList.addAll(saveList);

        banner.setAdapter(new RecommendVideoBannerAdapter(dataList, getContext()));
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

                Intent intent = new Intent(getContext(), RecordVideoDetailsActivity.class);
                intent.putExtra(RecordVideoDetailsActivity.VIDEO, dataList.get(position));
                startActivity(intent);
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
