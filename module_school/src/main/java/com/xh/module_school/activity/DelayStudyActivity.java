package com.xh.module_school.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xh.module.base.BackActivity;
import com.xh.module_school.R;
import com.xh.module_school.R2;
import com.xh.module_school.adapter.DelayStudyAdapter;
import com.xh.module_school.entity.CourseItem;
import com.xh.module_school.entity.Image2Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 延学主界面
 */
public class DelayStudyActivity extends BackActivity {

    DelayStudyAdapter adapter;

    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;

    List<Image2Text> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delay_study);

        ButterKnife.bind(this);

        Image2Text it5 = new Image2Text("人工智能", "李峰", R.drawable.ai);
        Image2Text it6 = new Image2Text("Python", "王二小", R.drawable.d1123123);
        Image2Text it7 = new Image2Text("演讲与口才", "赵信", R.drawable.yanjiang);
        Image2Text it8 = new Image2Text("声乐基础", "秦晓文", R.drawable.shengyue);
        Image2Text it9 = new Image2Text("围棋", "管彤", R.drawable.weiqi);
        Image2Text it10 = new Image2Text("机器人基础", "小欧", R.drawable.touxiang);
        Image2Text it11 = new Image2Text("美术", "李立", R.drawable.touxiang2);
        list.add(it5);
        list.add(it6);
        list.add(it7);
        list.add(it8);
        list.add(it9);
        list.add(it10);
        list.add(it11);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new DelayStudyAdapter(this, list);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                startActivity(new Intent(DelayStudyActivity.this, CourseDetailsActivity.class));
            }
        });
        recyclerView.setAdapter(adapter);


//        initView();
    }


    private void initView() {

        CourseItem item1 = new CourseItem("15:30-16:15", "机器人基础");
        CourseItem item2 = new CourseItem("16:30-17:15", "编程基础");
        CourseItem item3 = new CourseItem("15:30-16:15", "口语");
        CourseItem item4 = new CourseItem("16:30-17:15", "美术");

        CourseItem item5 = new CourseItem("15:30-16:15", "演讲与口才");
        CourseItem item6 = new CourseItem("16:30-17:15", "Python");
        CourseItem item7 = new CourseItem("15:30-16:15", "人工智能");
        CourseItem item8 = new CourseItem("16:30-17:15", "商务礼仪");

        CourseItem item9 = new CourseItem("15:30-16:15", "中国书法");
        CourseItem item10 = new CourseItem("16:30-17:15", "围棋");
        CourseItem item11 = new CourseItem("15:30-16:15", "声乐基础");
        CourseItem item12 = new CourseItem("16:30-17:15", "舞蹈基础");

        CourseItem[] aa = new CourseItem[]{item1, item3, item5, item7, item9, item11};
        CourseItem[] bb = new CourseItem[]{item2, item4, item6, item8, item10, item12};

//        for (String day : weekDays) {
//            View view = LayoutInflater.from(this).inflate(R.layout.course_recycleview, null);
//            RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//            List<CourseItem> courseItems = new ArrayList<>();
//            courseItems.add(aa[new Random().nextInt(aa.length)]);
//            courseItems.add(bb[new Random().nextInt(bb.length)]);
//
//            CourseItemAdapter itemAdapter = new CourseItemAdapter(this, courseItems);
//            recyclerView.setAdapter(itemAdapter);
//            itemAdapter.setOnItemClickListener(new OnItemClickListener() {
//                @Override
//                public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
//                    startActivity(new Intent(DelayStudyActivity.this, CourseDetailsActivity.class));
//                }
//            });
//            viewList.add(view);
//        }
//        adapter = new ViewpagerAdapter(viewList);
//        vp.setAdapter(adapter);
//
//        tabLayout.setViewPager(vp, weekDays);

        //viewpager加载adapter

//        tabLayout.setTabData(mContentTabEntities);
    }

}
