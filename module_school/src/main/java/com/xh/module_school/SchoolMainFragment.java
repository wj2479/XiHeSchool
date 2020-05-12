package com.xh.module_school;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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
import com.xh.module.base.BaseFragment;
import com.xh.module.base.Constant;
import com.xh.module.base.adapter.TabFragmentPagerAdapter;
import com.xh.module.base.entity.ImageText;
import com.xh.module.base.entity.LoginInfo;
import com.xh.module.base.entity.Role;
import com.xh.module.base.entity.School;
import com.xh.module.base.entity.SchoolInformation;
import com.xh.module.base.repository.DataRepository;
import com.xh.module.base.repository.impl.SchoolRepository;
import com.xh.module.base.retrofit.IRxJavaCallBack;
import com.xh.module.base.retrofit.ResponseCode;
import com.xh.module.base.retrofit.response.SimpleResponse;
import com.xh.module.base.utils.FragmentUtils;
import com.xh.module.base.utils.RouteUtils;
import com.xh.module.base.utils.SharedPreferencesUtil;
import com.xh.module.base.view.TabIconBean;
import com.xh.module_school.activity.NewMessageActivity;
import com.xh.module_school.activity.SchoolInfoListActivity;
import com.xh.module_school.activity.SchoolMasterMailActivity;
import com.xh.module_school.adapter.MyBannerAdapter;
import com.youth.banner.Banner;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.util.BannerUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * 学校主页
 */
@Route(path = RouteUtils.School_Fragment_Main)
public class SchoolMainFragment extends BaseFragment implements View.OnClickListener {

    LoginInfo loginInfo;

    private static final int REQUEST_CODE_QRCODE = 666;

    @BindView(R2.id.navLayout)
    CommonTabLayout navLayout;
    @BindView(R2.id.contentLayout)
    CommonTabLayout contentLayout;
    @BindView(R2.id.vp)
    ViewPager vp;
    @BindView(R2.id.banner)
    Banner banner;
    @BindView(R2.id.titleTv)
    TextView titleTv;

    private ArrayList<CustomTabEntity> mNavTabEntities = new ArrayList();
    private ArrayList<CustomTabEntity> mContentTabEntities = new ArrayList();

    public SchoolMainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginInfo = SharedPreferencesUtil.loadLogin(getContext());
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_school_main;
    }

    @Override
    protected void initView(View rootView) {

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
        navLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(getContext(), SchoolInfoListActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getContext(), SchoolMasterMailActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getContext(), NewMessageActivity.class));
                        break;
                }
            }

            @Override
            public void onTabReselect(int position) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(getContext(), SchoolInfoListActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getContext(), SchoolMasterMailActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getContext(), NewMessageActivity.class));
                        break;
                }
            }
        });
    }

    /**
     * 初始化内容标签
     */
    private void initContentLayout() {
        List<Role> roles = loginInfo.getRoles();
        List<Fragment> fragmentList = new ArrayList();
        List<String> titleList = new ArrayList<>();

        for (Role role : roles) {
            String title = Constant.roleTypeMap.get(role.getType());
            if (!TextUtils.isEmpty(title) && !titleList.contains(title)) {
                titleList.add(title);
            }

            // 如果学校ID不为空
            if (role.getSchool_id() != null) {
                getSchoolById(role.getSchool_id());
                getSchoolInfomationById(role.getSchool_id());
            }
            // 如果班级ID不为空

        }
        String[] titles = new String[titleList.size()];
        Collections.sort(titleList);
        titleList.toArray(titles);

        for (String title : titles) {
            mContentTabEntities.add(new TabIconBean(title, 0, 0));

            Fragment fragment = null;
            if (title.equals(Constant.roleTypeMap.get(Constant.ROLE_TYPE_FAMILY))) {
                fragment = FragmentUtils.getSchoolMenuFragment();
            } else if (title.equals(Constant.roleTypeMap.get(Constant.ROLE_TYPE_SCHOOL))) {
                fragment = FragmentUtils.getSchoolFamilyMenuFragment();
            }

            if (fragment != null) {
                fragmentList.add(fragment);
            }
        }

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

    ReentrantLock lock1 = new ReentrantLock();
    ReentrantLock lock2 = new ReentrantLock();

    /**
     * 获取学校的基本信息
     *
     * @param schoolId
     */
    private void getSchoolById(Long schoolId) {
        if (!lock1.isLocked()) {
            lock1.lock();
            SchoolRepository.getInstance().getSchoolInfoById(schoolId, new IRxJavaCallBack<SimpleResponse<School>>() {
                @Override
                public void onSuccess(SimpleResponse<School> response) {
                    if (response.getCode() == ResponseCode.RESULT_OK) {
                        Log.e("TAG", "获取学校信息:" + gson.toJson(response.getData()));
                        DataRepository.school = response.getData();
                        titleTv.setText(response.getData().getName());
                    }
                    lock1.unlock();
                }

                @Override
                public void onError(Throwable throwable) {
                    Log.e("TAG", "获取学校信息异常:" + throwable.toString());
                    lock1.unlock();
                }
            });

        }
    }

    /**
     * 获取学校的资讯
     */
    private void getSchoolInfomationById(Long schoolId) {
        if (!lock2.isLocked()) {
            lock2.lock();
            SchoolRepository.getInstance().getSchoolInfomationById(2327374242342374L, new IRxJavaCallBack<SimpleResponse<List<SchoolInformation>>>() {
                @Override
                public void onSuccess(SimpleResponse<List<SchoolInformation>> response) {
                    if (response.getCode() == ResponseCode.RESULT_OK) {
                        Log.e("TAG", "获取学校资讯:" + gson.toJson(response.getData()));
                    }
                    lock2.unlock();
                }

                @Override
                public void onError(Throwable throwable) {
                    Log.e("TAG", "获取学校资讯异常:" + throwable.toString());
                    lock2.unlock();
                }
            });
        }
    }

    private void getClasById(String clasId) {

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        lock1.unlock();
        lock2.unlock();
    }
}
