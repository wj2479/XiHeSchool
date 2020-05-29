package com.xh.module_school.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.xh.module_school.R;
import com.xh.module_school.entity.Image2Text;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;


public class DelayStudyAdapter extends BaseQuickAdapter<Image2Text, BaseViewHolder> {

    Context mContext;

    public DelayStudyAdapter(Context mContext, @Nullable List<Image2Text> data) {
        super(R.layout.adapter_delay_study_item, data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, Image2Text image2Text) {
        ImageView iv = holder.getView(R.id.thumbnailIv);
        Glide.with(mContext).load(image2Text.getPath()).into(iv);

        holder.setText(R.id.titleTv, image2Text.getTitle());
        holder.setText(R.id.personTv, image2Text.getContent());
        holder.setText(R.id.infoTv, "简介:" + image2Text.getTitle() + "基本介绍，欢迎大家来报名");
        holder.setText(R.id.countTv, new Random().nextInt(100) + "人");

    }
}
