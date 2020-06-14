package com.xh.module_me.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xh.module.base.BackActivity;
import com.xh.module.base.entity.pay.OrderInfo;
import com.xh.module.base.repository.DataRepository;
import com.xh.module.base.repository.impl.PayRepository;
import com.xh.module.base.retrofit.IRxJavaCallBack;
import com.xh.module.base.retrofit.ResponseCode;
import com.xh.module.base.retrofit.response.SimpleResponse;
import com.xh.module_me.R;
import com.xh.module_me.R2;
import com.xh.module_me.adapter.PaidOrderAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 已支付订单
 */
public class PaidOrderActivity extends BackActivity {

    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;

    /**
     * 记录支付订单列表
     */
    List<OrderInfo> dataList = new ArrayList<>();
    PaidOrderAdapter adapter;

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
        setContentView(R.layout.activity_paid_order);
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
        adapter = new PaidOrderAdapter(this, dataList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {

            }
        });

        adapter.addChildClickViewIds(R.id.checkbox);
        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                CheckBox cb = (CheckBox) view;
                if (cb.isChecked()) {
                    dataList.get(position).setSelected(true);
                } else {
                    dataList.get(position).setSelected(false);
                }
            }
        });

        View emptyView = LayoutInflater.from(this).inflate(R.layout.common_empty, null);
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        TextView tipsTv = emptyView.findViewById(R.id.tv_empty);
        tipsTv.setText("暂无订单信息");
        //添加空视图
        adapter.setEmptyView(emptyView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadNewInfos();
    }

    /**
     * 获取最新的未支付订单
     */
    private void loadNewInfos() {
        if (DataRepository.userInfo == null)
            return;

        PayRepository.getInstance().getPaidOrder(DataRepository.userInfo.getUid(), page, pageSize, new IRxJavaCallBack<SimpleResponse<List<OrderInfo>>>() {
            @Override
            public void onSuccess(SimpleResponse<List<OrderInfo>> response) {
                Log.e("PAY", gson.toJson(response));
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
                Log.e("PAY", "订单异常:" + throwable.toString());
                refreshLayout.finishRefresh(500);
            }
        });
    }

    /**
     * 获取更多的未支付订单
     */
    private void loadMoreInfos() {
        PayRepository.getInstance().getPaidOrder(DataRepository.userInfo.getUid(), page, pageSize, new IRxJavaCallBack<SimpleResponse<List<OrderInfo>>>() {
            @Override
            public void onSuccess(SimpleResponse<List<OrderInfo>> response) {
                Log.e("PAY", gson.toJson(response));
                if (response.getCode() == ResponseCode.RESULT_OK) {
                    Log.e("PAY", "获取订单:" + gson.toJson(response.getData()));
                    dataList.addAll(response.getData());
                    adapter.notifyDataSetChanged();
                    page += 1;
                    hasMore();
                } else {
                    Log.e("PAY", "订单失败:");
                }
                refreshLayout.finishLoadMore(500);
            }

            @Override
            public void onError(Throwable throwable) {
                refreshLayout.finishRefresh(500);
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
