package com.xh.module_school.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xh.module.base.BaseFragment;
import com.xh.module.base.utils.RouteUtils;
import com.xh.module.base.entity.ImageText;
import com.xh.module_school.R;
import com.xh.module_school.R2;
import com.xh.module_school.activity.CheckMainActivity;
import com.xh.module_school.adapter.ImageTextAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 班主任菜单主界面
 */
@Route(path = RouteUtils.School_Fragment_Class_Teacher_Menu)
public class ClassTeacherMenuFragment extends BaseFragment {

    @BindView(R2.id.menuGv)
    GridView menuGv;

    public ClassTeacherMenuFragment() {
        // Required empty public constructor
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_class_teacher_menu;
    }

    @Override
    protected void initView(View rootView) {
        initGridView();
    }

    private void initGridView() {
        List<ImageText> imageTextList = new ArrayList<>();

        ImageText it1 = new ImageText("课程表", R.drawable.ic_account);
        ImageText it2 = new ImageText("班级信息", R.drawable.ic_consult);
        ImageText it3 = new ImageText("考勤", R.drawable.ic_help);
        ImageText it4 = new ImageText("班级风采", R.drawable.ic_consult);
        ImageText it5 = new ImageText("行为评价", R.drawable.ic_setting);
        imageTextList.add(it1);
        imageTextList.add(it2);
        imageTextList.add(it3);
        imageTextList.add(it4);
        imageTextList.add(it5);

        ImageTextAdapter adapter = new ImageTextAdapter(getContext(), imageTextList);
        menuGv.setAdapter(adapter);

        menuGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("TAG", "点击了->" + position);

                if (position == 2) {
                    startActivity(new Intent(getContext(), CheckMainActivity.class));
                }
            }
        });
    }
}
