package com.xh.module_school.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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
import com.xh.module.base.Constant;
import com.xh.module.base.entity.Role;
import com.xh.module.base.entity.bbs.BbsArticle;
import com.xh.module.base.repository.DataRepository;
import com.xh.module.base.repository.impl.BbsRepository;
import com.xh.module.base.retrofit.IRxJavaCallBack;
import com.xh.module.base.retrofit.ResponseCode;
import com.xh.module.base.retrofit.response.SimpleResponse;
import com.xh.module_school.R;
import com.xh.module_school.R2;
import com.xh.module_school.adapter.ClassDemeanorAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 班级风采
 */
public class ClassDemeanorActivity extends BackActivity {

    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;

    /**
     * 记录文章列表
     */
    List<BbsArticle> dataList = new ArrayList<>();

    ClassDemeanorAdapter adapter;

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
        setContentView(R.layout.activity_class_demeanor);

        ButterKnife.bind(this);

        initRefresh();
        initRecyclerView();
    }

    private void initRefresh() {
        refreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        refreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能

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
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ClassDemeanorAdapter(this, dataList);
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
        tipsTv.setText("暂无班级风采");
        //添加空视图
        adapter.setEmptyView(emptyView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //筛选出班主任的角色
        for (Role role : DataRepository.userInfo.getRoles()) {
            //班主任的角色 具有发布资讯的权限
            if (role.getId() == Constant.ROLE_CODE_CLASS_MASTER) {
                getMenuInflater().inflate(R.menu.menu_publish_demeanor, menu);
                break;
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.publishDemeanor) {
            startActivity(new Intent(this, PublishClassDemeanorActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * 获取最新的文章
     */
    private void loadNewInfos() {
        BbsRepository.getInstance().getRecommendArticles(1, pageSize, new IRxJavaCallBack<SimpleResponse<List<BbsArticle>>>() {
            @Override
            public void onSuccess(SimpleResponse<List<BbsArticle>> response) {
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
                Log.e("TAG", "获取论坛列表:" + throwable.toString());
                refreshLayout.finishRefresh(500);
            }
        });
    }


    /**
     * 获取更多的文章
     */
    private void loadMoreInfos() {
        BbsRepository.getInstance().getRecommendArticles(page + 1, pageSize, new IRxJavaCallBack<SimpleResponse<List<BbsArticle>>>() {
            @Override
            public void onSuccess(SimpleResponse<List<BbsArticle>> response) {
                if (response.getCode() == ResponseCode.RESULT_OK) {
                    Log.e("TAG", "获取论坛资讯:" + gson.toJson(response.getData()));
                    dataList.addAll(response.getData());
                    adapter.notifyDataSetChanged();
                    page += 1;
                    hasMore();
                } else {
                    Log.e("TAG", "获取论坛失败:" + response.toString());
                }
                refreshLayout.finishLoadMore(500);
            }

            @Override
            public void onError(Throwable throwable) {
                Log.e("TAG", "获取论坛资讯异常:" + throwable.toString());
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
