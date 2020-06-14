package com.xh.module_me.activity;

import android.content.Intent;
import android.os.Build;
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
import com.tamsiree.rxkit.RxDeviceTool;
import com.xh.module.base.BackActivity;
import com.xh.module.base.entity.pay.BankResult;
import com.xh.module.base.entity.pay.OrderInfo;
import com.xh.module.base.repository.DataRepository;
import com.xh.module.base.repository.impl.PayRepository;
import com.xh.module.base.retrofit.IRxJavaCallBack;
import com.xh.module.base.retrofit.ResponseCode;
import com.xh.module.base.retrofit.response.SimpleResponse;
import com.xh.module.base.utils.DeviceTool;
import com.xh.module_me.R;
import com.xh.module_me.R2;
import com.xh.module_me.adapter.UnpaidOrderAdapter;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的订单
 */
public class UnpaidOrderInfoActivity extends BackActivity {

    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.selectAllCb)
    CheckBox selectAllCb;
    @BindView(R2.id.totalTv)
    TextView totalTv;

    /**
     * 记录未支付订单列表
     */
    List<OrderInfo> dataList = new ArrayList<>();
    UnpaidOrderAdapter adapter;

    /**
     * 当前那一页
     */
    int page = 1;
    /**
     * 每页显示的数量
     */
    int pageSize = 10;

    /**
     * 价格合计
     */
    BigDecimal sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unpaid_order_info);

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
        adapter = new UnpaidOrderAdapter(this, dataList);
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
                compute();
            }
        });

        View emptyView = LayoutInflater.from(this).inflate(R.layout.common_empty, null);
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        TextView tipsTv = emptyView.findViewById(R.id.tv_empty);
        tipsTv.setText("暂无未支付订单数据");
        //添加空视图
        adapter.setEmptyView(emptyView);
    }

    /**
     * 计算数据
     */
    private void compute() {
        sum = new BigDecimal(0.00);
        int selectCount = 0;
        for (OrderInfo orderInfo : dataList) {
            if (orderInfo.isSelected()) {
                sum = sum.add(orderInfo.getAmount());
                selectCount++;
            }
        }

        if (selectCount == dataList.size()) {
            selectAllCb.setChecked(true);
        } else {
            selectAllCb.setChecked(false);
        }

        NumberFormat currency = NumberFormat.getCurrencyInstance(); //建立货币格式化引用
        totalTv.setText(currency.format(sum));
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

        PayRepository.getInstance().getUnpaidOrder(DataRepository.userInfo.getUid(), page, pageSize, new IRxJavaCallBack<SimpleResponse<List<OrderInfo>>>() {
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
        PayRepository.getInstance().getUnpaidOrder(DataRepository.userInfo.getUid(), page, pageSize, new IRxJavaCallBack<SimpleResponse<List<OrderInfo>>>() {
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


    @OnClick(R2.id.selectAllCb)
    void onSelectAll() {
        for (OrderInfo orderInfo : dataList) {
            orderInfo.setSelected(selectAllCb.isChecked());
        }
        adapter.notifyDataSetChanged();
        compute();
    }

    @OnClick(R2.id.payBut)
    void onPayClick() {
        StringBuilder sb = new StringBuilder();
        for (OrderInfo orderInfo : dataList) {
            if (orderInfo.isSelected()) {
                sb.append(orderInfo.getId());
                sb.append(",");
            }
        }

        if (sb.length() == 0) {
            showInfoDialogAndDismiss("您还没有选择订单");
            return;
        }

        String macAddress = RxDeviceTool.getMacAddress(this);
        String orderIds = sb.toString().substring(0, sb.length() - 1);

        PayRepository.getInstance().requestPay("Android", Build.MODEL, macAddress, "119.521273,35.417427",
                DeviceTool.getIMEI(this), "192.168.1.1", DataRepository.userInfo.getUid(), orderIds,
                new IRxJavaCallBack<BankResult>() {
                    @Override
                    public void onSuccess(BankResult bankResult) {
                        Log.e("TAG", "请求支付接口:" + gson.toJson(bankResult));
                        if (bankResult.getEncryptedData().getCode().equals("000000")) {
                            String url = bankResult.getEncryptedData().getData().getTokenUrl();
                            Intent intent = new Intent(UnpaidOrderInfoActivity.this, PayWebPageActivity.class);
                            intent.putExtra("url", url);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.e("TAG", "支付异常:" + throwable.toString());
                        showFailDialogAndDismiss("支付异常");
                    }
                });
    }


}
