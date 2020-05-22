package com.xh.module_school.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.xh.module.base.BackActivity;
import com.xh.module.base.adapter.ViewpagerAdapter;
import com.xh.module_school.R;
import com.xh.module_school.R2;
import com.xh.module_school.adapter.CourseItemAdapter;
import com.xh.module_school.entity.CourseItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 课程表
 */
public class CourseTableActivity extends BackActivity {

    @BindArray(R2.array.weekdays)
    String[] weekDays;
    @BindView(R2.id.tabLayout)
    SlidingTabLayout tabLayout;
    @BindView(R2.id.vp)
    ViewPager vp;

    ViewpagerAdapter adapter;
    ArrayList<View> viewList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_table);

        ButterKnife.bind(this);

        initView();
    }

    /**
     * 初始化内容标签
     */
    private void initView() {

        List<CourseItem> courseItems = new ArrayList<>();
        CourseItem item1 = new CourseItem("08:00-08:45", "语文");
        CourseItem item2 = new CourseItem("09:00-09:45", "数学");
        CourseItem item3 = new CourseItem("10:10-10:55", "化学");
        CourseItem item4 = new CourseItem("11:10-11:55", "美术");

        CourseItem item5 = new CourseItem("13:30-14:15", "数学");
        CourseItem item6 = new CourseItem("14:30-15:15", "语文");
        CourseItem item7 = new CourseItem("15:30-16:15", "英语");
        CourseItem item8 = new CourseItem("16:30-17:15", "物理");

        courseItems.add(item1);
        courseItems.add(item2);
        courseItems.add(item3);
        courseItems.add(item4);
        courseItems.add(item5);
        courseItems.add(item6);
        courseItems.add(item7);
        courseItems.add(item8);


        for (String day : weekDays) {
            View view = LayoutInflater.from(this).inflate(R.layout.course_recycleview, null);
            RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            CourseItemAdapter itemAdapter = new CourseItemAdapter(this, courseItems);
            recyclerView.setAdapter(itemAdapter);
            viewList.add(view);
        }
        adapter = new ViewpagerAdapter(viewList);
        vp.setAdapter(adapter);

        tabLayout.setViewPager(vp, weekDays);

        //viewpager加载adapter

//        tabLayout.setTabData(mContentTabEntities);
    }
}
