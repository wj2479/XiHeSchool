package com.xh.module_school.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xh.module.base.BackActivity;
import com.xh.module.base.entity.ImageText;
import com.xh.module_school.R;
import com.xh.module_school.R2;
import com.xh.module_school.adapter.SchoolInfomationAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 学校资讯列表
 */
public class SchoolInfoListActivity extends BackActivity {

    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;

    List<ImageText> imageTextList = new ArrayList<>();

    SchoolInfomationAdapter infomationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_info_list);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        infomationAdapter = new SchoolInfomationAdapter(this, imageTextList);
        recyclerView.setAdapter(infomationAdapter);

        View emptyView = LayoutInflater.from(this).inflate(R.layout.common_empty, null);
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        TextView tipsTv = emptyView.findViewById(R.id.tv_empty);
        tipsTv.setText("暂无数据");
        //添加空视图
        infomationAdapter.setEmptyView(emptyView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_school_infolist, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.publishInfo) {
            startActivity(new Intent(this, AddSchoolInfomationActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
