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
import com.xh.module.base.entity.SchoolInformation;
import com.xh.module.base.repository.DataRepository;
import com.xh.module.base.repository.impl.SchoolRepository;
import com.xh.module.base.retrofit.IRxJavaCallBack;
import com.xh.module.base.retrofit.ResponseCode;
import com.xh.module.base.retrofit.response.SimpleResponse;
import com.xh.module_school.R;
import com.xh.module_school.R2;
import com.xh.module_school.adapter.SchoolInfomationAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.xh.module_school.activity.SchoolInfomationDetailsActivity.INFOMATION;

/**
 * 学校资讯列表
 */
public class SchoolInfoListActivity extends BackActivity {
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;

    /**
     * 记录资讯列表
     */
    List<SchoolInformation> informationList = new ArrayList<>();

    SchoolInfomationAdapter infomationAdapter;

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
        setContentView(R.layout.activity_school_info_list);
        ButterKnife.bind(this);

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

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        infomationAdapter = new SchoolInfomationAdapter(this, informationList);
        recyclerView.setAdapter(infomationAdapter);

        infomationAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                if (informationList.size() == 0)
                    return;

                SchoolInformation information = informationList.get(position);
                Intent intent = new Intent(SchoolInfoListActivity.this, SchoolInfomationDetailsActivity.class);
                intent.putExtra(INFOMATION, information);
                startActivity(intent);
            }
        });

        View emptyView = LayoutInflater.from(this).inflate(R.layout.common_empty, null);
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        TextView tipsTv = emptyView.findViewById(R.id.tv_empty);
        tipsTv.setText("暂时没有新的校园资讯");
        //添加空视图
        infomationAdapter.setEmptyView(emptyView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //校长或者班主任的角色 具有发布资讯的权限
        if (DataRepository.role.getId() == Constant.ROLE_CODE_SCHOOL_MASTER || DataRepository.role.getId() == Constant.ROLE_CODE_CLASS_MASTER) {
            getMenuInflater().inflate(R.menu.menu_school_infolist, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.publishInfo) {
            startActivity(new Intent(this, AddSchoolInfomationActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadNewInfos();
    }

    /**
     * 获取最新的资讯
     */
    private void loadNewInfos() {
        SchoolRepository.getInstance().getSchoolInfomationById(DataRepository.school.getId(), 1, pageSize, new IRxJavaCallBack<SimpleResponse<List<SchoolInformation>>>() {
            @Override
            public void onSuccess(SimpleResponse<List<SchoolInformation>> response) {
                if (response.getCode() == ResponseCode.RESULT_OK) {
                    Log.e("TAG", "获取学校资讯:" + gson.toJson(response.getData()));
                    informationList.clear();
                    informationList.addAll(response.getData());
                    infomationAdapter.notifyDataSetChanged();
                    page = 1;
                    hasMore();
                }
                refreshLayout.finishRefresh(500);
            }

            @Override
            public void onError(Throwable throwable) {
                Log.e("TAG", "获取学校资讯异常:" + throwable.toString());
                refreshLayout.finishRefresh(500);
            }
        });
    }

    /**
     * 获取更多的资讯
     */
    private void loadMoreInfos() {
        SchoolRepository.getInstance().getSchoolInfomationById(DataRepository.school.getId(), page + 1, pageSize, new IRxJavaCallBack<SimpleResponse<List<SchoolInformation>>>() {
            @Override
            public void onSuccess(SimpleResponse<List<SchoolInformation>> response) {
                if (response.getCode() == ResponseCode.RESULT_OK) {
                    Log.e("TAG", "获取学校资讯:" + gson.toJson(response.getData()));
                    informationList.addAll(response.getData());
                    infomationAdapter.notifyDataSetChanged();
                    page += 1;
                    hasMore();
                }
                refreshLayout.finishLoadMore(500);
            }

            @Override
            public void onError(Throwable throwable) {
                Log.e("TAG", "获取学校资讯异常:" + throwable.toString());
                refreshLayout.finishLoadMore(500);
            }
        });
    }

    /**
     * 判断是否开启加载更多功能
     */
    private void hasMore() {
        // 如果当前的页数都显示完成 则开启加载更多功能
        if (informationList.size() >= page * pageSize) {
            refreshLayout.setEnableLoadMore(true);//是否启用上拉加载功能
        } else {
            refreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
        }
    }
}
