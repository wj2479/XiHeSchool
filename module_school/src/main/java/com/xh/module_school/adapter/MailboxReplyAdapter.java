package com.xh.module_school.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.xh.module.base.utils.TimeUtils;
import com.xh.module_school.R;
import com.xh.module_school.entity.MailboxReply;

import java.util.Date;
import java.util.List;

/**
 * 信件回复
 */
public class MailboxReplyAdapter extends BaseMultiItemQuickAdapter<MailboxReply, BaseViewHolder> {

    Context mContext;

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MailboxReplyAdapter(List<MailboxReply> data) {
        super(data);
        addItemType(MailboxReply.TYPE_LEFT, R.layout.adapter_speak_content_left);
        addItemType(MailboxReply.TYPE_RIGHT, R.layout.adapter_speak_content_right);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final MailboxReply reply) {
        helper.setText(R.id.contentTv, reply.getContent());

        long timeStamp = reply.getCreateTime() * 1000;
        helper.setText(R.id.timeTv, TimeUtils.showTime(new Date(timeStamp), "MM-dd HH:mm"));
    }

}