package com.xh.module_school.fragment;

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
        List<ImageText> imageTextList = new ArrayList<>();

        ImageText it1 = new ImageText("家委会", R.drawable.ic_family_org);
        ImageText it2 = new ImageText("学校餐厅", R.drawable.ic_dinging_room);
        ImageText it3 = new ImageText("学生课程表", R.drawable.ic_course_table);
        ImageText it4 = new ImageText("行为评价", R.drawable.ic_action_manager);
        ImageText it5 = new ImageText("亲友管理", R.drawable.ic_action_manager);
        ImageText it6 = new ImageText("请假管理", R.drawable.ic_action_manager);
        ImageText it7 = new ImageText("考勤记录", R.drawable.ic_action_manager);
        ImageText it8 = new ImageText("商城", R.drawable.ic_action_manager);
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
            }
        });
    }

}
