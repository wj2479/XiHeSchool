package com.xh.module_teach.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xh.module.base.BaseFragment;
import com.xh.module.base.entity.VideoBase;
import com.xh.module.base.repository.impl.TeachRepository;
import com.xh.module.base.retrofit.IRxJavaCallBack;
import com.xh.module.base.retrofit.ResponseCode;
import com.xh.module.base.retrofit.response.SimpleResponse;
import com.xh.module.base.utils.RouteUtils;
import com.xh.module_teach.R;
import com.xh.module_teach.R2;
import com.xh.module_teach.activity.RecordVideoDetailsActivity;
import com.xh.module_teach.adapter.RecordVideoAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 视频录播主界面
 */
@Route(path = RouteUtils.Teach_Fragment_Video_Record_Home)
public class VideoRecordHomeFragment extends BaseFragment {

    @BindView(R2.id.yearSpinner)
    Spinner yearSpinner;
    @BindView(R2.id.courseSpinner)
    Spinner courseSpinner;
    @BindView(R2.id.classSpinner)
    Spinner classSpinner;

    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;

    /**
     * 记录点播视频列表
     */
    List<VideoBase> dataList = new ArrayList<>();

    RecordVideoAdapter adapter;

    /**
     * 当前那一页
     */
    int page = 1;
    /**
     * 每页显示的数量
     */
    int pageSize = 10;

    public VideoRecordHomeFragment() {
        // Required empty public constructor
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_video_record_home;
    }

    @Override
    protected void initView(View rootView) {
        // 建立数据源
        String[] mItems = getResources().getStringArray(R.array.years);
        // 建立Adapter并且绑定数据源
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, mItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //绑定 Adapter到控件
        yearSpinner.setAdapter(adapter);

        courseSpinner.setAdapter(adapter);
        classSpinner.setAdapter(adapter);

        initRefresh();
        initRecyclerView();

        loadNewInfos();

    }

    private void initRefresh() {
        refreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
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

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecordVideoAdapter(getContext(), dataList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                if (dataList.size() == 0)
                    return;
                Intent intent = new Intent(getContext(), RecordVideoDetailsActivity.class);
                intent.putExtra(RecordVideoDetailsActivity.VIDEO, dataList.get(position));
                startActivity(intent);
            }
        });

        View emptyView = LayoutInflater.from(getContext()).inflate(R.layout.common_empty, null);
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        TextView tipsTv = emptyView.findViewById(R.id.tv_empty);
        tipsTv.setText("还没有人发布过视频");
        //添加空视图
        adapter.setEmptyView(emptyView);
    }

    /**
     * 获取最新的视频
     */
    private void loadNewInfos() {
        TeachRepository.getInstance().getRecordVideoList(0, "", 0, 1, pageSize, new IRxJavaCallBack<SimpleResponse<List<VideoBase>>>() {
            @Override
            public void onSuccess(SimpleResponse<List<VideoBase>> response) {
                Log.e("TAG", "获取点播视频列表:" + gson.toJson(response));

                dataList.clear();
                if (response.getCode() == ResponseCode.RESULT_OK) {
                    dataList.addAll(response.getData());
                }
                adapter.notifyDataSetChanged();
                page = 1;
                hasMore();
                refreshLayout.finishRefresh(500);
            }

            @Override
            public void onError(Throwable throwable) {
                Log.e("TAG", "获取点播视频列表:" + throwable.toString());
                refreshLayout.finishRefresh(500);
            }
        });
    }

    /**
     * 获取更多的文章
     */
    private void loadMoreInfos() {
        TeachRepository.getInstance().getRecordVideoList(1, "", 1L, page + 1, pageSize, new IRxJavaCallBack<SimpleResponse<List<VideoBase>>>() {
            @Override
            public void onSuccess(SimpleResponse<List<VideoBase>> response) {
                if (response.getCode() == ResponseCode.RESULT_OK) {
                    Log.e("TAG", "获取视频列表:" + gson.toJson(response.getData()));
                    dataList.addAll(response.getData());
                    adapter.notifyDataSetChanged();
                    page += 1;
                    hasMore();
                } else {
                    Log.e("TAG", "获取视频列表:" + response.toString());
                }
                refreshLayout.finishLoadMore(500);
            }

            @Override
            public void onError(Throwable throwable) {
                Log.e("TAG", "获取视频列表异常:" + throwable.toString());
                refreshLayout.finishLoadMore(500);
            }
        });
    }

    /**
     * 判断是否开启加载更多功能
     */
    private void hasMore() {
        // 如果当前的页数都显示完成 则开启加载更多功能
        if (dataList.size() >= page * pageSize) {
            refreshLayout.setEnableLoadMore(true);//是否启用上拉加载功能
        } else {
            refreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
        }
    }

}
