package com.xh.module_school.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.xh.module.base.entity.bbs.BbsArticle;
import com.xh.module_school.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * 班级风采列表
 */
public class ClassDemeanorAdapter extends BaseQuickAdapter<BbsArticle, BaseViewHolder> {

    Context mContext;

    public ClassDemeanorAdapter(Context mContext, @Nullable List<BbsArticle> data) {
        super(R.layout.adapter_course_item, data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder helper, BbsArticle courseItem) {
    }
}
