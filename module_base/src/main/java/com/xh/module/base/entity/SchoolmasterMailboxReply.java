package com.xh.module.base.entity;

/**
 * table name:  schoolmaster_mailbox_reply
 * author name: 朱华清
 * create time: 2020-05-14 16:13:24
 */
public class SchoolmasterMailboxReply {

    private Long id;//ID
    private String content;//内容
    private Long schoolmasterMailboxId;//信箱ID
    private Long replyId;//回复ID
    private Integer state;//0:未读 1 已读
    private Long createTime;//认证时间
    private Long replyUid;//发布人

    public SchoolmasterMailboxReply() {
        super();
    }

    public SchoolmasterMailboxReply(Long id, String content, Long schoolmasterMailboxId, Long replyId, Integer state, Long createTime, Long replyUid) {
        this.id = id;
        this.content = content;
        this.schoolmasterMailboxId = schoolmasterMailboxId;
        this.replyId = replyId;
        this.state = state;
        this.createTime = createTime;
        this.replyUid = replyUid;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setSchoolmasterMailboxId(Long schoolmasterMailboxId) {
        this.schoolmasterMailboxId = schoolmasterMailboxId;
    }

    public Long getSchoolmasterMailboxId() {
        return schoolmasterMailboxId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    public Long getReplyId() {
        return replyId;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getState() {
        return state;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setReplyUid(Long replyUid) {
        this.replyUid = replyUid;
    }

    public Long getReplyUid() {
        return replyUid;
    }

    @Override
    public String toString() {
        return "schoolmaster_mailbox_reply[" +
                "id=" + id +
                ", content=" + content +
                ", schoolmaster_mailbox_id=" + schoolmasterMailboxId +
                ", reply_id=" + replyId +
                ", state=" + state +
                ", create_time=" + createTime +
                ", reply_uid=" + replyUid +
                "]";
    }
}

