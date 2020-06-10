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
import com.tamsiree.rxkit.RxTimeTool;
import com.xh.module.base.BackActivity;
import com.xh.module.base.Constant;
import com.xh.module.base.entity.Clas;
import com.xh.module.base.entity.Schoolwork;
import com.xh.module.base.repository.DataRepository;
import com.xh.module.base.repository.impl.SchoolRepository;
import com.xh.module.base.retrofit.IRxJavaCallBack;
import com.xh.module.base.retrofit.ResponseCode;
import com.xh.module.base.retrofit.response.SimpleResponse;
import com.xh.module_school.R;
import com.xh.module_school.R2;
import com.xh.module_school.adapter.TeacherHomeWorkAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 教师作业列表
 */
public class TeacherHomeWorkActivity extends BackActivity {

    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.dateTv)
    TextView dateTv;

    TeacherHomeWorkAdapter adapter;

    List<Schoolwork> dataList = new ArrayList<>();

    // 当前老师 负责的班级
    List<Clas> clasList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_manager);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TeacherHomeWorkAdapter(this, dataList);
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
        tipsTv.setText("您还没有发布过作业");
        //添加空视图
        adapter.setEmptyView(emptyView);

        String date = RxTimeTool.getCurTimeString(new SimpleDateFormat("yyyy-MM-dd"));
        dateTv.setText(date);
        initClassInfo();
    }

    /**
     * 初始化班级信息
     */
    void initClassInfo() {
        if (DataRepository.courseListMap != null) {
            for (List<Clas> list : DataRepository.courseListMap.values()) {
                for (Clas clas : list) {
                    if (!clasList.contains(clas)) {
                        clasList.add(clas);
                    }
                }
            }
        }
    }

    /**
     * 根据班级ID 查询 作业信息
     */
    private void getHomeWorkByClasId() {
        for (Clas clas : clasList) {
            Log.e("TAG", "班级：" + clas.getId() + " " + clas.getGradeName() + clas.getName());
            SchoolRepository.getInstance().getHomeWorkByClasId(clas.getId(), dateTv.getText().toString(), new IRxJavaCallBack<SimpleResponse<List<Schoolwork>>>() {
                @Override
                public void onSuccess(SimpleResponse<List<Schoolwork>> listSimpleResponse) {
                    Log.e("TAG", "成功：" + gson.toJson(listSimpleResponse));
                    if (listSimpleResponse.getCode() == ResponseCode.RESULT_OK) {
                        dataList.addAll(listSimpleResponse.getData());
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onError(Throwable throwable) {
                    Log.e("TAG", "异常:" + throwable.toString());
                }
            });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        dataList.clear();
        getHomeWorkByClasId();
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
