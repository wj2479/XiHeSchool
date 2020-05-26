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
import com.xh.module_school.activity.CheckMainActivity;
import com.xh.module_school.activity.ClassDemeanorActivity;
import com.xh.module_school.activity.ClassInfoActivity;
import com.xh.module_school.activity.WorkManagerActivity;
import com.xh.module_school.adapter.ImageTextAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 班主任菜单主界面
 */
@Route(path = RouteUtils.School_Fragment_Class_Master_Menu)
public class ClassTeacherMenuFragment extends BaseFragment {

    @BindView(R2.id.menuGv)
    GridView menuGv;
    @BindView(R2.id.roleTv)
    TextView roleTv;

    public ClassTeacherMenuFragment() {
        // Required empty public constructor
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_role_menu;
    }

    @Override
    protected void initView(View rootView) {
        roleTv.setText("我是班主任");
        initGridView();
    }

    private void initGridView() {
        List<ImageText> imageTextList = new ArrayList<>();

        ImageText it2 = new ImageText("班级信息", R.drawable.ic_infomation);
        ImageText it3 = new ImageText("班级安全", R.drawable.ic_aq);
        ImageText it4 = new ImageText("班级风采", R.drawable.ic_style);
        ImageText it5 = new ImageText("行为评价", R.drawable.ic_action_manager);
        ImageText it7 = new ImageText("作业管理", R.drawable.ic_action_manager);
        ImageText it8 = new ImageText("作业点评", R.drawable.ic_action_manager);

        imageTextList.add(it2);
        imageTextList.add(it3);
        imageTextList.add(it4);
        imageTextList.add(it5);
        imageTextList.add(it7);
        imageTextList.add(it8);

        ImageTextAdapter adapter = new ImageTextAdapter(getContext(), imageTextList);
        menuGv.setAdapter(adapter);

        menuGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("TAG", "点击了->" + position);

                switch (position) {
                    case 0:
                        startActivity(new Intent(getContext(), ClassInfoActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getContext(), CheckMainActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getContext(), ClassDemeanorActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(getContext(), ActionJudgeActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(getContext(), WorkManagerActivity.class));
                        break;
                    case 5:
                        break;
                }
            }
        });
    }
}
