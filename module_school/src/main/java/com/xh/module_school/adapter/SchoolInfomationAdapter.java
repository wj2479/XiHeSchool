package com.xh.module_school.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.xh.module.base.entity.ImageText;
import com.xh.module_school.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 *
 */
public class SchoolInfomationAdapter extends BaseQuickAdapter<ImageText, BaseViewHolder> {
    Context mContext;

    public SchoolInfomationAdapter(Context mContext, @Nullable List<ImageText> data) {
        super(R.layout.adapter_school_infomation, data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, ImageText imageText) {

    }
}
