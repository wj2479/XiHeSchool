package com.xh.module_school.fragment.role;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xh.module.base.BaseFragment;
import com.xh.module.base.entity.ImageText;
import com.xh.module.base.utils.RouteUtils;
import com.xh.module_school.R;
import com.xh.module_school.R2;
import com.xh.module_school.activity.ActionJudgeActivity;
import com.xh.module_school.activity.DelayStudyActivity;
import com.xh.module_school.activity.HomeWorkAssessActivity;
import com.xh.module_school.activity.LeaveCountActivity;
import com.xh.module_school.activity.StudentAssessActivity;
import com.xh.module_school.activity.StudentResultActivity;
import com.xh.module_school.activity.TeacherHomeWorkActivity;
import com.xh.module_school.adapter.ImageTextAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 教师菜单主界面
 */
@Route(path = RouteUtils.School_Fragment_Teacher_Menu)
public class TeachMenuFragment extends BaseFragment {
    @BindView(R2.id.menuGv)
    GridView menuGv;
    @BindView(R2.id.roleTv)
    TextView roleTv;

    public TeachMenuFragment() {
        // Required empty public constructor
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_role_menu;
    }

    @Override
    protected void initView(View rootView) {
        roleTv.setText("我是老师");
        initGridView();
    }

    private void initGridView() {
        final List<ImageText> imageTextList = new ArrayList<>();

        ImageText it1 = new ImageText("餐厅", R.drawable.ic_dinging_room);
        ImageText it2 = new ImageText("作业管理", R.drawable.ic_work_manager);
        ImageText it3 = new ImageText("作业点评", R.drawable.ic_work_dianping);
        ImageText it4 = new ImageText("行为评价", R.drawable.ic_action_judge);
        ImageText it5 = new ImageText("成绩", R.drawable.ic_chengji_manager);
        ImageText it6 = new ImageText("学生点评", R.drawable.ic_student_judge);
        ImageText it7 = new ImageText("请假统计", R.drawable.ic_qingjia);
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
                ImageText imageText = imageTextList.get(position);
                Log.e("TAG", "点击了->" + imageText.getText());
                switch (imageText.getText()) {
                    case "餐厅":
                        showInfoDialogAndDismiss("该功能暂未开放,敬请期待");
                        break;
                    case "作业管理":
                        startActivity(new Intent(getContext(), TeacherHomeWorkActivity.class));
                        break;
                    case "作业点评":
                        startActivity(new Intent(getContext(), HomeWorkAssessActivity.class));
                        break;
                    case "行为评价":
                        startActivity(new Intent(getContext(), ActionJudgeActivity.class));
                        break;
                    case "成绩":
                        startActivity(new Intent(getContext(), StudentResultActivity.class));
                        break;
                    case "学生点评":
                        startActivity(new Intent(getContext(), StudentAssessActivity.class));
                        break;
                    case "请假统计":
                        startActivity(new Intent(getContext(), LeaveCountActivity.class));
                        break;
                    case "课后延时":
                        startActivity(new Intent(getContext(), DelayStudyActivity.class));
                        break;
                }
            }
        });
    }

}
