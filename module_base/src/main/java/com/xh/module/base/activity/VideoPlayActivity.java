package com.xh.module.base.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xh.module.R;
import com.xh.module.R2;
import com.xh.module.base.BaseApplication;
import com.xh.module.base.utils.RouteUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JzvdStd;

/**
 * 视频播放的Activity
 */
@Route(path = RouteUtils.Base_Activity_Video_Play)
public class VideoPlayActivity extends AppCompatActivity {

    public static final String PATH = "path";
    public static final String AUTO_PLAY = "auto_play";

    @BindView(R2.id.jz_video)
    JzvdStd Jzvd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);

        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        if (getIntent().hasExtra(PATH)) {
            String path = getIntent().getStringExtra(PATH);
            String proxyUrl = BaseApplication.getProxy(this).getProxyUrl(path);
            //设置视频上显示的文字
            Jzvd.setUp(proxyUrl, "");
            if (getIntent().getBooleanExtra(AUTO_PLAY, false)) {
                Jzvd.startVideo();
            }
        } else {
            finish();
        }
    }

    /**
     * 不要忘了这两个方法
     */
    @Override
    public void onBackPressed() {
        if (cn.jzvd.Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }
}
