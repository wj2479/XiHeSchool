package com.xh.module_school.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.xh.module.base.entity.ClassDemeanor;
import com.xh.module.base.utils.PathUtils;
import com.xh.module.base.utils.TimeUtils;
import com.xh.module_school.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;
import java.util.List;

/**
 * 班级风采列表
 */
public class ClassDemeanorAdapter extends BaseQuickAdapter<ClassDemeanor, BaseViewHolder> {

    Context mContext;

    public ClassDemeanorAdapter(Context mContext, @Nullable List<ClassDemeanor> data) {
        super(R.layout.adapter_classdemeanor_item, data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder helper, ClassDemeanor item) {
        helper.setText(R.id.tv_title, item.getTitle());

        ImageView imgIv = helper.getView(R.id.iv_img);
        Glide.with(mContext).load(PathUtils.composePath(item.getIndexImage())).into(imgIv);
        long timeStamp = item.getCreateTime() * 1000;
        helper.setText(R.id.tv_introduce, TimeUtils.showTime(new Date(timeStamp), "MM-dd HH:mm"));
    }
}
