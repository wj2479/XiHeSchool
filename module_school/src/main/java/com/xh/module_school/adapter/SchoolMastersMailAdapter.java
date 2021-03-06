package com.xh.module_school.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.xh.module.base.entity.SchoolmasterMailbox;
import com.xh.module.base.utils.PathUtils;
import com.xh.module.base.utils.TimeUtils;
import com.xh.module_school.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;
import java.util.List;

/**
 * 校长端  校长信箱列表展示
 */
public class SchoolMastersMailAdapter extends BaseQuickAdapter<SchoolmasterMailbox, BaseViewHolder> {
    Context mContext;

    public SchoolMastersMailAdapter(Context mContext, @Nullable List<SchoolmasterMailbox> data) {
        super(R.layout.adapter_school_master_mails, data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder helper, SchoolmasterMailbox information) {
        if (information.getAnonymous() == 1) {
            helper.setText(R.id.tv_user, "匿名用户");
        } else {
            helper.setText(R.id.tv_user, information.getCreateUid() + "");
        }

        helper.setText(R.id.tv_title, information.getTitle());
        helper.setText(R.id.tv_content, information.getContent());

        ImageView iconIv = helper.getView(R.id.iv_icon);
        if (information.getUserBase() != null) {
            Glide.with(mContext).load(PathUtils.composePath(information.getUserBase().getFace())).error(R.drawable.ic_default_user).into(iconIv);
        }

        long timeStamp = information.getCreateTime() * 1000;
        helper.setText(R.id.tv_time, TimeUtils.showTime(new Date(timeStamp), "MM-dd HH:mm"));
    }
}
