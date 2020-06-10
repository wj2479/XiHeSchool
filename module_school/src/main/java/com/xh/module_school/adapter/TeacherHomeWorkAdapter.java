package com.xh.module_school.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.xh.module.base.entity.Clas;
import com.xh.module.base.entity.Schoolwork;
import com.xh.module.base.repository.DataRepository;
import com.xh.module.base.utils.TimeUtils;
import com.xh.module_school.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;
import java.util.List;

/**
 * 教师看到的家庭作业
 */
public class TeacherHomeWorkAdapter extends BaseQuickAdapter<Schoolwork, BaseViewHolder> {

    Context mContext;

    public TeacherHomeWorkAdapter(Context mContext, @Nullable List<Schoolwork> data) {
        super(R.layout.adapter_teacher_home_work, data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, Schoolwork schoolwork) {

        if (DataRepository.courseListMap != null) {
            First:
            for (List<Clas> list : DataRepository.courseListMap.values()) {
                Second:
                for (Clas clas : list) {
                    if (schoolwork.getClassId().equals(clas.getId())) {
                        holder.setText(R.id.titleTv, clas.getGradeName() + " " + clas.getName());
                        break First;
                    }
                }
            }
        }

        holder.setText(R.id.infoTv, schoolwork.getContent());

        long timeStamp = schoolwork.getCreateTime() * 1000;
        holder.setText(R.id.timeTv, TimeUtils.showTime(new Date(timeStamp), "MM-dd HH:mm"));

    }
}
