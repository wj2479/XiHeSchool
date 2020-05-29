package com.xh.module_school.activity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import com.xh.module.base.BackActivity;
import com.xh.module.base.entity.ImageText;
import com.xh.module.base.repository.DataRepository;
import com.xh.module_school.R;
import com.xh.module_school.R2;
import com.xh.module_school.adapter.CircleImageTextAdapter;
import com.xh.module_school.adapter.Image2TextAdapter;
import com.xh.module_school.entity.Image2Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 班级详情
 */
public class ClassInfoActivity extends BackActivity {

    @BindView(R2.id.hmNameIv)
    CircleImageView hmNameIv;
    @BindView(R2.id.hmNameTv)
    TextView hmNameTv;

    @BindView(R2.id.teacherGv)
    GridView teacherGv;
    @BindView(R2.id.teacherTipsTv)
    TextView teacherTipsTv;
    @BindView(R2.id.jobGv)
    GridView jobGv;
    @BindView(R2.id.jobTipsTv)
    TextView jobTipsTv;
    @BindView(R2.id.studentGv)
    GridView studentGv;
    @BindView(R2.id.studentTipsTv)
    TextView studentTipsTv;

    // 任职老师的适配器
    Image2TextAdapter teacherAdapter;
    // 班级职务适配器
    Image2TextAdapter jobAdapter;
    // 学生
    CircleImageTextAdapter studentAdapter;

    List<Image2Text> teacherList = new ArrayList<>();
    List<Image2Text> jobList = new ArrayList<>();
    List<ImageText> studentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_info);

        ButterKnife.bind(this);

        initViews();
        initData();
    }

    private void initViews() {

    }

    private void initData() {
        if (DataRepository.clas == null) {
            getClassInfo();
        }

        Image2Text it1 = new Image2Text("李易峰", "语文老师", R.drawable.touxiang);
        Image2Text it2 = new Image2Text("杨幂", "美术老师", R.drawable.touxiang2);
        Image2Text it3 = new Image2Text("李峰", "体育老师", R.drawable.touxiang);
        Image2Text it4 = new Image2Text("张嘴", "数学老师", R.drawable.touxiang);

        teacherList.add(it1);
        teacherList.add(it2);
        teacherList.add(it3);
        teacherList.add(it4);

        teacherAdapter = new Image2TextAdapter(this, teacherList);
        teacherGv.setAdapter(teacherAdapter);

        Image2Text it5 = new Image2Text("李峰", "班长", R.drawable.touxiang);
        Image2Text it6 = new Image2Text("杨2幂", "副班长", R.drawable.touxiang2);
        Image2Text it7 = new Image2Text("李大峰", "学习委员", R.drawable.touxiang);
        Image2Text it8 = new Image2Text("张二嘴", "体育委员", R.drawable.touxiang);
        Image2Text it9 = new Image2Text("李在嘴", "文艺委员", R.drawable.touxiang2);

        jobList.add(it5);
        jobList.add(it6);
        jobList.add(it7);
        jobList.add(it8);
        jobList.add(it9);

        jobAdapter = new Image2TextAdapter(this, jobList);
        jobGv.setAdapter(jobAdapter);

        getClassStudent();

        ImageText i1 = new ImageText("六六六", R.drawable.touxiang2);
        ImageText i2 = new ImageText("塞班", R.drawable.touxiang);
        ImageText i3 = new ImageText("浣溪沙", R.drawable.touxiang2);
        ImageText i4 = new ImageText("李在嘴", R.drawable.touxiang);

        studentList.add(i1);
        studentList.add(i2);
        studentList.add(i3);
        studentList.add(i4);
        studentList.add(i2);
        studentList.add(i1);
        studentList.add(i2);
        studentList.add(i3);
        studentList.add(i4);
        studentList.add(i2);
        studentList.add(i1);
        studentList.add(i4);
        studentList.add(i2);
        studentList.add(i1);

        studentAdapter = new CircleImageTextAdapter(this, studentList);
        studentGv.setAdapter(studentAdapter);
    }

    /**
     * 获取班级基本信息
     */
    private void getClassInfo() {

    }

    /**
     * 获取班级学生列表
     */
    private void getClassStudent() {

    }

}
