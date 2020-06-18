package com.xh.module_school.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xh.module.base.BaseFragment;
import com.xh.module.base.entity.ImageText;
import com.xh.module.base.utils.RouteUtils;
import com.xh.module_school.R;
import com.xh.module_school.R2;
import com.xh.module_school.activity.ActionJudgeActivity;
import com.xh.module_school.activity.CheckMainActivity;
import com.xh.module_school.activity.CourseTableActivity;
import com.xh.module_school.activity.DelayStudyActivity;
import com.xh.module_school.activity.LeaveCountActivity;
import com.xh.module_school.adapter.ImageTextAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 家长主界面
 */
@Route(path = RouteUtils.School_Fragment_Family_Menu)
public class FamilyMenuFragment extends BaseFragment {
    @BindView(R2.id.menuGv)
    GridView menuGv;

    public FamilyMenuFragment() {
        // Required empty public constructor
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_family_menu;
    }

    @Override
    protected void initView(View rootView) {
        initGridView();
    }

    private void initGridView() {
        final List<ImageText> imageTextList = new ArrayList<>();

        ImageText it1 = new ImageText("家委会", R.drawable.ic_family_org);
        ImageText it2 = new ImageText("学校餐厅", R.drawable.ic_dinging_room);
        ImageText it3 = new ImageText("学生课程表", R.drawable.ic_course_table);
        ImageText it4 = new ImageText("行为评价", R.drawable.ic_action_judge);
        ImageText it5 = new ImageText("出入记录", R.drawable.ic_student_judge);
        ImageText it6 = new ImageText("请假管理", R.drawable.ic_qingjia);
        ImageText it7 = new ImageText("考勤记录", R.drawable.ic_kaoqin_record);
        ImageText it8 = new ImageText("课后延时", R.drawable.ic_study_online);
        imageTextList.add(it1);
        imageTextList.add(it2);
        imageTextList.add(it3);
        imageTextList.add(it4);
        imageTextList.add(it5);
        imageTextList.add(it6);
        imageTextList.add(it7);
        imageTextList.add(it8);

        ImageTextAdapter adapter = new ImageTextAdapter(getContext(), imageTextList);
        menuGv.setAdapter(adapter);

        menuGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("TAG", "点击了->" + position);
                ImageText imageText = imageTextList.get(position);
                switch (imageText.getText()) {
                    case "家委会":
                        break;
                    case "学校餐厅":
                        showInfoDialogAndDismiss("该功能暂未开放,敬请期待");
                        break;
                    case "学生课程表":
                        startActivity(new Intent(getContext(), CourseTableActivity.class));
                        break;
                    case "行为评价":
                        startActivity(new Intent(getContext(), ActionJudgeActivity.class));
                        break;
                    case "出入记录":
                        startActivity(new Intent(getContext(), CheckMainActivity.class));
                        break;
                    case "请假管理":
                        startActivity(new Intent(getContext(), LeaveCountActivity.class));
                        break;
                    case "考勤记录":
                        startActivity(new Intent(getContext(), CheckMainActivity.class));
                        break;
                    case "课后延时":
                        startActivity(new Intent(getContext(), DelayStudyActivity.class));
                        break;
                }
            }
        });
    }

}
