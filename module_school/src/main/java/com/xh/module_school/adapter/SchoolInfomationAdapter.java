package com.xh.module_school.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.xh.module.base.entity.SchoolInformation;
import com.xh.module.base.utils.TimeUtils;
import com.xh.module_school.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;
import java.util.List;

/**
 * 校园资讯列表展示
 */
public class SchoolInfomationAdapter extends BaseQuickAdapter<SchoolInformation, BaseViewHolder> {
    Context mContext;

    public SchoolInfomationAdapter(Context mContext, @Nullable List<SchoolInformation> data) {
        super(R.layout.adapter_school_infomation, data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder helper, SchoolInformation information) {
        helper.setText(R.id.tv_title, information.getTitle());

        ImageView imgIv = helper.getView(R.id.iv_img);
        Glide.with(mContext).load(information.getIndexImage()).into(imgIv);
        long timeStamp = information.getCreateTime() * 1000;
        helper.setText(R.id.tv_introduce, TimeUtils.showTime(new Date(timeStamp), "MM:dd HH:mm"));
    }
}
