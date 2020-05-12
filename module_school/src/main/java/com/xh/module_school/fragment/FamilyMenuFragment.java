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

        ImageText it1 = new ImageText("餐厅", R.drawable.ic_account);
        ImageText it2 = new ImageText("作业", R.drawable.ic_consult);
        ImageText it3 = new ImageText("笔记", R.drawable.ic_help);
        ImageText it4 = new ImageText("行为评价", R.drawable.ic_help);
        imageTextList.add(it1);
        imageTextList.add(it2);
        imageTextList.add(it3);
        imageTextList.add(it4);

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
