package com.xh.module_teach.fragment;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xh.module.base.BaseFragment;
import com.xh.module.base.utils.RouteUtils;
import com.xh.module_teach.R;

/**
 * 视频直播主界面
 */
@Route(path = RouteUtils.Teach_Fragment_Video_Live_Home)
public class VideoLiveHomeFragment extends BaseFragment {

    public VideoLiveHomeFragment() {
        // Required empty public constructor
    }


    @Override
    protected int initLayout() {
        return R.layout.fragment_video_live_home;
    }

    @Override
    protected void initView(View rootView) {

    }

}
