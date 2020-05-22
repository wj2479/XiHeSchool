package com.xh.module_school.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.xh.module_school.R;
import com.xh.module_school.entity.CourseItem;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * 课程对象
 */
public class CourseItemAdapter extends BaseQuickAdapter<CourseItem, BaseViewHolder> {
    Context mContext;

    public CourseItemAdapter(Context mContext, @Nullable List<CourseItem> data) {
        super(R.layout.adapter_course_item, data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder helper, CourseItem courseItem) {
        helper.setText(R.id.timeTv, courseItem.getTime());
        helper.setText(R.id.nameTv, courseItem.getName());
    }

}
