package com.xh.module_school.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.xh.module.base.entity.ImageText;
import com.xh.module_school.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * 通知类型列表适配器
 */
public class MsgTypeAdapter extends BaseQuickAdapter<ImageText, BaseViewHolder> {
    Context mContext;

    public MsgTypeAdapter(Context mContext, @Nullable List<ImageText> data) {
        super(R.layout.adapter_msg_type, data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, ImageText imageText) {
        holder.setText(R.id.nameTv, imageText.getText());
        ImageView iv = holder.getView(R.id.iconIv);
        Glide.with(mContext).load(imageText.getPath()).into(iv);
    }
}
