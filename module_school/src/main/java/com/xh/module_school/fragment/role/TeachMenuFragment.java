package com.xh.module_school.fragment.role;

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
        List<ImageText> imageTextList = new ArrayList<>();

        ImageText it1 = new ImageText("餐厅", R.drawable.ic_dinging_room);
        ImageText it2 = new ImageText("作业", R.drawable.ic_school_work);
        ImageText it3 = new ImageText("笔记", R.drawable.ic_course_table);
        imageTextList.add(it1);
        imageTextList.add(it2);
        imageTextList.add(it3);

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
