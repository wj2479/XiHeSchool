package com.xh.module_school.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.xh.module.base.entity.SchoolmasterMailboxReply;
import com.xh.module.base.repository.DataRepository;

public class MailboxReply extends SchoolmasterMailboxReply implements MultiItemEntity {

    public static final int TYPE_LEFT = 0;
    public static final int TYPE_RIGHT = 1;

    public MailboxReply(SchoolmasterMailboxReply reply) {
        setId(reply.getId());
        setContent(reply.getContent());
        setCreateTime(reply.getCreateTime());
        setReplyId(reply.getReplyId());
        setReplyUid(reply.getReplyUid());
        setSchoolmasterMailboxId(reply.getSchoolmasterMailboxId());
        setState(reply.getState());
    }

    @Override
    public int getItemType() {
        if (getReplyUid().equals(DataRepository.userInfo.getUid())) {
            return TYPE_RIGHT;
        }
        return TYPE_LEFT;
    }
}
