package com.xh.module_teach.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.jaeger.library.StatusBarUtil;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xh.module.base.BaseApplication;
import com.xh.module.base.entity.VideoBase;
import com.xh.module.base.utils.PathUtils;
import com.xh.module_teach.R;
import com.xh.module_teach.R2;
import com.xh.module_teach.adapter.RecordVideoAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JzvdStd;

/**
 * 点播视频播放
 */
public class RecordVideoDetailsActivity extends AppCompatActivity {

    public static final String VIDEO = "VIDEO";

    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.jz_video)
    JzvdStd Jzvd;
    @BindView(R2.id.introTv)
    TextView contentTv;

    VideoBase videoBase;

    /**
     * 记录点播视频列表
     */
    List<VideoBase> dataList = new ArrayList<>();
    RecordVideoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_video_details);

        ButterKnife.bind(this);

        initRefresh();
        initRecyclerView();

        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.white), 0);
        QMUIStatusBarHelper.setStatusBarLightMode(this);
        initData();
    }

    private void initData() {
        if (getIntent().hasExtra(VIDEO)) {
            videoBase = getIntent().getParcelableExtra(VIDEO);
            String path = PathUtils.composePath(videoBase.getUrl());
            String proxyUrl = BaseApplication.getProxy(this).getProxyUrl(path);
            //设置视频上显示的文字
            Jzvd.setUp(proxyUrl, videoBase.getTitle());
            contentTv.setText(videoBase.getContent());
        } else {
            finish();
        }

    }

    private void initRefresh() {
        refreshLayout.setEnableRefresh(false);//是否启用下拉刷新功能
        refreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMoreInfos();
            }
        });

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                loadNewInfos();
            }
        });
    }

    private void loadMoreInfos() {
    }

    private void loadNewInfos() {
    }


    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecordVideoAdapter(this, dataList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                if (dataList.size() == 0)
                    return;
            }
        });

        View emptyView = LayoutInflater.from(this).inflate(R.layout.common_empty, null);
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        TextView tipsTv = emptyView.findViewById(R.id.tv_empty);
        tipsTv.setText("还没有人发表评论");
        //添加空视图
        adapter.setEmptyView(emptyView);
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
        cn.jzvd.Jzvd.releaseAllVideos();
    }
}
