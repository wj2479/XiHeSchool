package com.xh.module_teach.fragment;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xh.module.base.BaseFragment;
import com.xh.module.base.utils.RouteUtils;
import com.xh.module_teach.R;
import com.xh.module_teach.R2;

import butterknife.BindView;

/**
 * 视频录播主界面
 */
@Route(path = RouteUtils.Teach_Fragment_Video_Record_Home)
public class VideoRecordHomeFragment extends BaseFragment {

    @BindView(R2.id.yearSpinner)
    Spinner yearSpinner;
    @BindView(R2.id.courseSpinner)
    Spinner courseSpinner;
    @BindView(R2.id.classSpinner)
    Spinner classSpinner;

    public VideoRecordHomeFragment() {
        // Required empty public constructor
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_video_record_home;
    }

    @Override
    protected void initView(View rootView) {

        // 建立数据源
        String[] mItems = getResources().getStringArray(R.array.years);
        // 建立Adapter并且绑定数据源
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, mItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //绑定 Adapter到控件
        yearSpinner.setAdapter(adapter);

        courseSpinner.setAdapter(adapter);
        classSpinner.setAdapter(adapter);

    }

}
