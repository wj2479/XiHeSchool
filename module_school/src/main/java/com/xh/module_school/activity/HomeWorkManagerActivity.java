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
import com.xh.module.base.Constant;
import com.xh.module.base.entity.ImageText;
import com.xh.module.base.repository.DataRepository;
import com.xh.module_school.R;
import com.xh.module_school.R2;
import com.xh.module_school.adapter.MsgTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作业管理
 */
public class HomeWorkManagerActivity extends BackActivity {

    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_manager);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<ImageText> imageTextList = new ArrayList<>();

        MsgTypeAdapter adapter = new MsgTypeAdapter(this, imageTextList);
        recyclerView.setAdapter(adapter);

        View emptyView = LayoutInflater.from(this).inflate(R.layout.common_empty, null);
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        TextView tipsTv = emptyView.findViewById(R.id.tv_empty);
        tipsTv.setText("您还没有发布过作业");
        //添加空视图
        adapter.setEmptyView(emptyView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //老师身份可以发布作业
        if (DataRepository.role.getId() == Constant.ROLE_CODE_TEACHER) {
            getMenuInflater().inflate(R.menu.menu_publish_work, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.publishWork) {
            startActivity(new Intent(this, PublishHomeWorkActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
