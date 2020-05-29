package com.xh.moudle_bbs;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.tamsiree.rxkit.RxKeyboardTool;
import com.xh.module.base.BaseFragment;
import com.xh.module.base.adapter.TabFragmentPagerAdapter;
import com.xh.module.base.entity.bbs.BbsUser;
import com.xh.module.base.repository.DataRepository;
import com.xh.module.base.repository.impl.BbsRepository;
import com.xh.module.base.retrofit.IRxJavaCallBack;
import com.xh.module.base.retrofit.ResponseCode;
import com.xh.module.base.retrofit.response.SimpleResponse;
import com.xh.module.base.utils.FragmentUtils;
import com.xh.module.base.utils.LogUtil;
import com.xh.module.base.utils.PathUtils;
import com.xh.module.base.utils.RouteUtils;
import com.xh.module.base.view.TabIconBean;
import com.xh.moudle_bbs.activity.BbsUserInfoActivity;
import com.xh.moudle_bbs.activity.SendArticleActivity;
import com.xh.moudle_bbs.fragment.MyArticleFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.xh.moudle_bbs.activity.BbsUserInfoActivity.USERINFO;

/**
 * 论坛
 */
@Route(path = RouteUtils.Bbs_Fragment_Main)
public class BbsFragment extends BaseFragment {

    @BindView(R2.id.contentLayout)
    CommonTabLayout contentLayout;
    @BindView(R2.id.vp)
    ViewPager vp;

    @BindView(R2.id.iconIv)
    CircleImageView iconIv;
    @BindView(R2.id.searchEt)
    EditText searchEt;
    /**
     * 论坛用户
     */
    BbsUser bbsUser;

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
        getBbsUserInfo();
        searchEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //关闭软键盘
                    RxKeyboardTool.hideSoftInput(searchEt);
                    //do something

                    String text = searchEt.getText().toString().trim();
                    if (TextUtils.isEmpty(text)) {

                        return false;
                    }

                    searchEt.setText("");
                    // TODO: 2020/5/21 0021 关键字搜索
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showFailDialogAndDismiss("没有找到相关信息");
                        }
                    }, 2000);
                    //doSearch();
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * 获取论坛用户信息
     */
    private void getBbsUserInfo() {
        BbsRepository.getInstance().getBbsUserInfo(DataRepository.userInfo.getUid(), new IRxJavaCallBack<SimpleResponse<BbsUser>>() {
            @Override
            public void onSuccess(SimpleResponse<BbsUser> response) {
                if (response.getCode() == ResponseCode.RESULT_OK) {
                    LogUtil.e("TAG", "获取论坛用户成功：" + gson.toJson(response.getData()));
                    bbsUser = response.getData();
                    DataRepository.bbsUser = bbsUser;
                    showUserInfo();
                }
            }

            @Override
            public void onError(Throwable throwable) {
                LogUtil.e("TAG", "获取论坛用户异常：" + throwable.toString());
                showFailDialogAndDismiss("获取论坛用户信息失败");
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("TAG", "code->" + resultCode);
        if (resultCode == Activity.RESULT_FIRST_USER) {
            bbsUser = DataRepository.bbsUser;
            showUserInfo();
        }
    }

    /**
     * 显示头像
     */
    private void showUserInfo() {
        if (bbsUser != null) {
            if (!TextUtils.isEmpty(bbsUser.getHeadImage())) {
                Glide.with(this).load(PathUtils.composePath(bbsUser.getHeadImage())).into(iconIv);
            }
        }
    }

    @OnClick(R2.id.iconIv)
    void onIconIvClick() {
        startUserInfoActivity();
    }

    @OnClick(R2.id.sendArticle)
    void onSendArticleClick() {
        // 如果用户没有注册  需要首先注册用户
        if (bbsUser == null || TextUtils.isEmpty(bbsUser.getName())) {
            startUserInfoActivity();
        } else {
            startSendArticleActivity();
        }
    }

    private void startUserInfoActivity() {
        Intent intent = new Intent(getContext(), BbsUserInfoActivity.class);
        intent.putExtra(USERINFO, bbsUser);
        startActivityForResult(intent, 100);
    }

    private void startSendArticleActivity() {
        Intent intent = new Intent(getContext(), SendArticleActivity.class);
        intent.putExtra(USERINFO, bbsUser);
        startActivityForResult(intent, 100);
    }

    private void initContentLayout() {
        String[] titles = {"推荐", "我的"};
        for (String title : titles) {
            mContentTabEntities.add(new TabIconBean(title, 0, 0));
        }

        List<Fragment> fragmentList = new ArrayList();
//        fragmentList.add(FragmentUtils.getBbsAttentionHomeFragment());
        fragmentList.add(FragmentUtils.getBbsRecommendHomeFragment());
        fragmentList.add(MyArticleFragment.newInstance());

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
