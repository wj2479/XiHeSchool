package com.xh.module_school.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xh.module.base.BackActivity;
import com.xh.module.base.entity.SchoolmasterMailbox;
import com.xh.module.base.repository.DataRepository;
import com.xh.module.base.repository.impl.SchoolRepository;
import com.xh.module.base.retrofit.IRxJavaCallBack;
import com.xh.module.base.retrofit.ResponseCode;
import com.xh.module.base.retrofit.response.SimpleResponse;
import com.xh.module_school.R;
import com.xh.module_school.R2;
import com.xh.module_school.adapter.SchoolMastersMailAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.xh.module_school.activity.SchoolMailDetailsActivity.MAILBOX;

/**
 * 校长信箱
 */
public class SchoolMasterMailActivity extends BackActivity {
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;

    /**
     * 记录信箱列表
     */
    List<SchoolmasterMailbox> dataList = new ArrayList<>();

    SchoolMastersMailAdapter adpter;

    /**
     * 当前那一页
     */
    int page = 1;
    /**
     * 每页显示的数量
     */
    int pageSize = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_master_mail);

        ButterKnife.bind(this);

        refreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        refreshLayout.setEnableLoadMore(true);//是否启用上拉加载功能

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                Log.e("TAG", "加载更多");
                loadMoreInfos();
            }
        });

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Log.e("TAG", "刷新");
                loadNewInfos();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adpter = new SchoolMastersMailAdapter(this, dataList);
        recyclerView.setAdapter(adpter);

        adpter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                if (dataList.size() == 0)
                    return;
                SchoolmasterMailbox mailbox = dataList.get(position);
                Intent intent = new Intent(SchoolMasterMailActivity.this, SchoolMailDetailsActivity.class);
                intent.putExtra(MAILBOX, mailbox);
                startActivity(intent);
            }
        });

        View emptyView = LayoutInflater.from(this).inflate(R.layout.common_empty, null);
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        TextView tipsTv = emptyView.findViewById(R.id.tv_empty);
        tipsTv.setText("暂无信件");
        //添加空视图
        adpter.setEmptyView(emptyView);

    }

    @Override
    protected void onStart() {
        super.onStart();
        loadNewInfos();
    }

    /**
     * 获取最新的信件
     */
    private void loadNewInfos() {
        SchoolRepository.getInstance().getReceivedSchoolMasterMails(DataRepository.school.getId(), 1, pageSize, new IRxJavaCallBack<SimpleResponse<List<SchoolmasterMailbox>>>() {
            @Override
            public void onSuccess(SimpleResponse<List<SchoolmasterMailbox>> response) {
                if (response.getCode() == ResponseCode.RESULT_OK) {
                    Log.e("TAG", "获取校长信件:" + gson.toJson(response.getData()));
                    dataList.clear();
                    dataList.addAll(response.getData());

                    adpter.notifyDataSetChanged();
                    page = 1;
                }
                refreshLayout.finishRefresh(1000);
            }

            @Override
            public void onError(Throwable throwable) {
                Log.e("TAG", "获取校长信件异常:" + throwable.toString());
                refreshLayout.finishRefresh(1000);
            }
        });
    }

    /**
     * 获取更多的信件
     */
    private void loadMoreInfos() {
        SchoolRepository.getInstance().getReceivedSchoolMasterMails(DataRepository.school.getId(), page + 1, pageSize, new IRxJavaCallBack<SimpleResponse<List<SchoolmasterMailbox>>>() {
            @Override
            public void onSuccess(SimpleResponse<List<SchoolmasterMailbox>> response) {
                if (response.getCode() == ResponseCode.RESULT_OK) {
                    Log.e("TAG", "获取校长信件:" + gson.toJson(response.getData()));
                    page += 1;
                }
                refreshLayout.finishLoadMore(1000);
            }

            @Override
            public void onError(Throwable throwable) {
                Log.e("TAG", "获取校长信件异常:" + throwable.toString());
                refreshLayout.finishLoadMore(1000);
            }
        });
    }
}
