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
import com.xh.module.base.db.DBManager;
import com.xh.module.base.entity.Role;
import com.xh.module.base.entity.School;
import com.xh.module.base.entity.SchoolInformation;
import com.xh.module.base.entity.UserBase;
import com.xh.module.base.repository.DataRepository;
import com.xh.module.base.repository.impl.SchoolRepository;
import com.xh.module.base.retrofit.IRxJavaCallBack;
import com.xh.module.base.retrofit.ResponseCode;
import com.xh.module.base.retrofit.response.SimpleResponse;
import com.xh.module.base.utils.FragmentUtils;
import com.xh.module.base.utils.RouteUtils;
import com.xh.module.base.utils.SharedPreferencesUtil;
import com.xh.module.base.view.TabIconBean;
import com.xh.module_school.activity.MySchoolMailListActivity;
import com.xh.module_school.activity.NewMessageActivity;
import com.xh.module_school.activity.SchoolInfoListActivity;
import com.xh.module_school.activity.SchoolInfomationDetailsActivity;
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

import static com.xh.module_school.activity.SchoolInfomationDetailsActivity.INFOMATION;

/**
 * 学校主页
 */
@Route(path = RouteUtils.School_Fragment_Main)
public class SchoolMainFragment extends BaseFragment implements View.OnClickListener {

    UserBase loginInfo;

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

    /**
     * 资讯显示的数量
     */
    private int showInfoCount = 5;

    /**
     * 首页资讯的列表
     */
    List<SchoolInformation> informationList = new ArrayList<>();

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
        List<SchoolInformation> informations = DBManager.getInstance().getmDaoSession().getSchoolInformationDao().loadAll();
        informationList.addAll(informations);

        banner.setAdapter(new MyBannerAdapter(informationList, getContext()));
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
                SchoolInformation information = (SchoolInformation) data;
                Intent intent = new Intent(getContext(), SchoolInfomationDetailsActivity.class);
                intent.putExtra(INFOMATION, information);
                startActivity(intent);
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

    /**
     * 初始化导航栏
     */
    private void initNavLayout() {
        mNavTabEntities.add(new TabIconBean("校园资讯", R.drawable.ic_infomation, R.drawable.ic_infomation));
        mNavTabEntities.add(new TabIconBean("校长信箱", R.drawable.ic_mail, R.drawable.ic_mail));
        mNavTabEntities.add(new TabIconBean("新消息", R.drawable.ic_new_mes, R.drawable.ic_new_mes));
        navLayout.setTabData(mNavTabEntities);

        navLayout.showDot(1);
        navLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                onTabClick(position);
            }

            @Override
            public void onTabReselect(int position) {
                onTabClick(position);
            }
        });
    }

    /**
     * tab被点击事件
     *
     * @param position
     */
    private void onTabClick(int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(getContext(), SchoolInfoListActivity.class));
                break;
            case 1:
                // 如果是校长身份   就直接进校长信箱列表
                for (Role role : DataRepository.userInfo.getRoles()) {
                    if (role.getId() == Constant.ROLE_CODE_SCHOOL_MASTER) {
                        startActivity(new Intent(getContext(), SchoolMasterMailActivity.class));
                        return;
                    }
                }
                // 其他人可以查看发送的信件
                startActivity(new Intent(getContext(), MySchoolMailListActivity.class));
                break;
            case 2:
                startActivity(new Intent(getContext(), NewMessageActivity.class));
                break;
        }
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
                fragment = FragmentUtils.getSchoolFamilyMenuFragment();
            } else if (title.equals(Constant.roleTypeMap.get(Constant.ROLE_TYPE_SCHOOL))) {
                fragment = FragmentUtils.getSchoolMenuFragment();
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
            SchoolRepository.getInstance().getSchoolInfomationById(schoolId, 1, showInfoCount, new IRxJavaCallBack<SimpleResponse<List<SchoolInformation>>>() {
                @Override
                public void onSuccess(SimpleResponse<List<SchoolInformation>> response) {
                    if (response.getCode() == ResponseCode.RESULT_OK) {
                        informationList.clear();
                        informationList.addAll(response.getData());

                        // 保存数据到本地
                        DBManager.getInstance().getmDaoSession().getSchoolInformationDao().deleteAll();
                        for (SchoolInformation information : informationList) {
                            DBManager.getInstance().getmDaoSession().getSchoolInformationDao().insert(information);
                        }

                        banner.getAdapter().notifyDataSetChanged();
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
                        Postcard postcard = ARouter.getInstance().build(RouteUtils.School_Activity_Scan);
                        LogisticsCenter.completion(postcard);
                        Intent intent = new Intent(getActivity(), postcard.getDestination());
                        intent.putExtras(postcard.getExtras());
                        startActivityForResult(intent, REQUEST_CODE_QRCODE);                    //跳转，code要大于0
                    }
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) {
                    Log.e("TAG", "异常:" + throwable.toString());
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
        if (lock1.isLocked()) {
            lock1.unlock();
        }
        if (lock2.isLocked()) {
            lock2.unlock();
        }
    }
}
