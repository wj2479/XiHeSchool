package com.xh.module_school;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.core.LogisticsCenter;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.xh.module.base.BaseFragment;
import com.xh.module.base.Constant;
import com.xh.module.base.db.DBManager;
import com.xh.module.base.entity.Clas;
import com.xh.module.base.entity.Role;
import com.xh.module.base.entity.School;
import com.xh.module.base.entity.SchoolInformation;
import com.xh.module.base.entity.TeacherClass;
import com.xh.module.base.entity.UserBase;
import com.xh.module.base.repository.DataRepository;
import com.xh.module.base.repository.impl.SchoolRepository;
import com.xh.module.base.retrofit.IRxJavaCallBack;
import com.xh.module.base.retrofit.ResponseCode;
import com.xh.module.base.retrofit.response.SimpleResponse;
import com.xh.module.base.utils.FragmentUtils;
import com.xh.module.base.utils.PathUtils;
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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
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
    FrameLayout contentLayout;
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

        // 获取保存的角色
        DataRepository.role = getSeceltRole();
        Log.e("TAG", "保存的角色:" + gson.toJson(DataRepository.role));
        onSelectRole(DataRepository.role);

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
        addDefaultSchoolInfo();
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

    /**
     * 初始化导航栏
     */
    private void initNavLayout() {
        mNavTabEntities.add(new TabIconBean("校园资讯", R.drawable.ic_infomation, R.drawable.ic_infomation));
        mNavTabEntities.add(new TabIconBean("校长信箱", R.drawable.ic_mail, R.drawable.ic_mail));
        mNavTabEntities.add(new TabIconBean("新消息", R.drawable.ic_new_mes, R.drawable.ic_new_mes));
        navLayout.setTabData(mNavTabEntities);

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
                if (DataRepository.role.getId().equals(Constant.ROLE_CODE_SCHOOL_MASTER)) {
                    // 如果是校长身份   就直接进校长信箱列表
                    startActivity(new Intent(getContext(), SchoolMasterMailActivity.class));
                } else {
                    // 其他人可以查看发送的信件
                    startActivity(new Intent(getContext(), MySchoolMailListActivity.class));
                }
                break;
            case 2:
                startActivity(new Intent(getContext(), NewMessageActivity.class));
                break;
        }
    }

    /**
     * 切换角色
     *
     * @param role
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSelectRole(Role role) {
        Log.e("TAG", "切换角色" + gson.toJson(role));

        DataRepository.role = role;
        // 切换学校之后，如果跟当前学校是不同的ID 需要重新获取学校信息
        if (DataRepository.school == null ||
                (DataRepository.school != null && !DataRepository.school.getId().equals(role.getSchool_id()))) {
            getSchoolInfoById(role.getSchool_id());
        }

        // 如果是老师角色
        if (role.getId() == Constant.ROLE_CODE_TEACHER) {
            // 如果班级信息还没有初始化
            if (DataRepository.courseListMap == null) {
                getTeacherClassInfoById(715679245308788736L);
            }
        }

        // 如果是班主任
        if (role.getId() == Constant.ROLE_CODE_CLASS_MASTER) {
            if (DataRepository.clas == null) {
                getClassInfoById();
            }
        }

        initContentLayout(role);
    }

    /**
     * 获取班级的基本信息
     */
    private void getClassInfoById() {
    }

    /**
     * 获取教师班级及授课信息
     *
     * @param id
     */
    private void getTeacherClassInfoById(Long id) {
        SchoolRepository.getInstance().getTeacherClassInfoById(id, new IRxJavaCallBack<SimpleResponse<List<List<TeacherClass>>>>() {
            @Override
            public void onSuccess(SimpleResponse<List<List<TeacherClass>>> response) {
                if (response.getCode() == ResponseCode.RESULT_OK) {
                    DataRepository.courseListMap = new HashMap<>();

                    for (List<TeacherClass> datum : response.getData()) {
                        for (TeacherClass teacherClass : datum) {
                            if (DataRepository.courseListMap.containsKey(teacherClass.getCourse())) {
                                List<Clas> clasList = DataRepository.courseListMap.get(teacherClass.getCourse());
                                clasList.add(teacherClass.getClas());
                            } else {
                                List<Clas> clasList = new LinkedList<>();
                                clasList.add(teacherClass.getClas());
                                DataRepository.courseListMap.put(teacherClass.getCourse(), clasList);
                            }
                        }
                    }
                    Log.e("TAG", "获取老师信息:" + DataRepository.courseListMap.toString());
                } else {
                    Log.e("TAG", "获取老师信息出错:" + gson.toJson(response));
                }
            }

            @Override
            public void onError(Throwable throwable) {
                Log.e("TAG", "获取老师信息异常:" + throwable.toString());
            }
        });
    }

    /**
     * 获取选择的Role
     *
     * @return
     */
    private Role getSeceltRole() {
        // 获取保存的角色信息
        Role role = SharedPreferencesUtil.getRole(getContext());
        List<Role> roles = loginInfo.getRoles();
        if (role == null) {
            role = roles.get(0);
            SharedPreferencesUtil.saveRole(getContext(), role);
        } else {
            boolean isHave = false;
            // 判断当前用户是否还包含这角色
            for (Role infoRole : loginInfo.getRoles()) {
                if (role.getId().equals(infoRole.getId())) {
                    isHave = true;
                    break;
                }
            }

            if (!isHave) {
                role = roles.get(0);
                SharedPreferencesUtil.saveRole(getContext(), role);
            }
        }
        return role;
    }

    /**
     * 初始化内容布局
     */
    private void initContentLayout(Role role) {
        switch (role.getId().intValue()) {
            case Constant.ROLE_CODE_CLASS_MASTER:     // 班主任
                getChildFragmentManager().beginTransaction().replace(R.id.contentLayout, FragmentUtils.getSchoolClassTeacherMenuFragment()).commitNowAllowingStateLoss();
                break;
            case Constant.ROLE_CODE_TEACHER:    // 授课老师
                getChildFragmentManager().beginTransaction().replace(R.id.contentLayout, FragmentUtils.getSchoolTeacherMenuFragment()).commitNowAllowingStateLoss();
                break;
            case Constant.ROLE_CODE_PARENT:   // 学生家长
                getChildFragmentManager().beginTransaction().replace(R.id.contentLayout, FragmentUtils.getSchoolFamilyMenuFragment()).commitNowAllowingStateLoss();
                break;
            case Constant.ROLE_CODE_SCHOOL_MASTER:   // 校长
                getChildFragmentManager().beginTransaction().replace(R.id.contentLayout, FragmentUtils.getFragment(RouteUtils.School_Fragment_School_Master_Menu)).commitNowAllowingStateLoss();
                break;
        }
    }

    /**
     * 获取学校的信息及资讯消息
     *
     * @param schoolId
     */
    private void getSchoolInfoById(Long schoolId) {
        if (schoolId != null) {
            getSchoolById(schoolId);
            getSchoolInfomationById(schoolId);
        }
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
                        EventBus.getDefault().post(DataRepository.school);
                    } else {
                        Log.e("TAG", "获取学校信息出错:" + gson.toJson(response));
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
                    Log.e("TAG", "获取学校资讯:" + gson.toJson(response));
                    informationList.clear();

                    addDefaultSchoolInfo();
                    if (response.getCode() == ResponseCode.RESULT_OK) {
                        informationList.addAll(response.getData());

                        // 保存数据到本地
                        DBManager.getInstance().getmDaoSession().getSchoolInformationDao().deleteAll();
                        for (SchoolInformation information : informationList) {
                            DBManager.getInstance().getmDaoSession().getSchoolInformationDao().insert(information);
                        }
                    }

                    banner.getAdapter().notifyDataSetChanged();
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

    /**
     * 添加默认的
     */
    private void addDefaultSchoolInfo() {
        SchoolInformation info = new SchoolInformation();
        info.setTitle("欢迎体验西禾学堂新版本");
        info.setIndexImage(PathUtils.composePath("/information/2634258d-c317-4b9f-85e8-95c2d821cd7b.jpeg"));
        info.setContent("西禾学堂APP上线了，欢迎大家体验，如果您对本APP有好的意见或者建议，请在“个人”-“意见反馈”里留言，感谢您的使用。");
        informationList.add(info);
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
