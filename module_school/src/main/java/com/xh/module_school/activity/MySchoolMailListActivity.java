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
import com.xh.module.base.BackActivity;
import com.xh.module.base.entity.SchoolInformation;
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

/**
 * 我发出的校长信息列表
 */
public class MySchoolMailListActivity extends BackActivity {
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
        setContentView(R.layout.activity_my_school_mail_list);

        ButterKnife.bind(this);

        refreshLayout.setEnableRefresh(false);//是否启用下拉刷新功能
        refreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adpter = new SchoolMastersMailAdapter(this, dataList);
        recyclerView.setAdapter(adpter);

        adpter.setOnItemClickListener(new OnItemClickListener() {
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
        tipsTv.setText("您还没有发送过信件");
        //添加空视图
        adpter.setEmptyView(emptyView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_publish_schoolmaster_mail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.publish) {
            startActivity(new Intent(this, PublishSchoolMailActivity.class));
        }
        return super.onOptionsItemSelected(item);
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
                    page = 1;
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
}
