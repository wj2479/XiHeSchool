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
 * 校长角色主界面
 */
@Route(path = RouteUtils.School_Fragment_School_Master_Menu)
public class SchoolMasterFragment extends BaseFragment {
    @BindView(R2.id.menuGv)
    GridView menuGv;
    @BindView(R2.id.roleTv)
    TextView roleTv;

    public SchoolMasterFragment() {
        // Required empty public constructor
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_role_menu;
    }

    @Override
    protected void initView(View rootView) {
        roleTv.setText("我是校长");
        initGridView();
    }

    private void initGridView() {
        List<ImageText> imageTextList = new ArrayList<>();

        ImageText it1 = new ImageText("学校餐厅", R.drawable.ic_dinging_room);
        ImageText it2 = new ImageText("校园安全", R.drawable.ic_aq);
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
