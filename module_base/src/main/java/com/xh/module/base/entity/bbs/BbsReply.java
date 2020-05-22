package com.xh.module.base.entity.bbs;

/**
 * table name:  bbs_reply
 * author name: 朱华清
 * create time: 2020-05-20 09:42:40
 */ 
public class BbsReply{

	private Long id;
	private Long commentId;
	private String content;
	private Long uid;
	private Long createTime;

	public BbsReply() {
		super();
	}

	public BbsReply(Long id,Long commentId,String content,Long uid,Long createTime) {
		this.id=id;
		this.commentId=commentId;
		this.content=content;
		this.uid=uid;
		this.createTime=createTime;
	}

	public void setId(Long id){
		this.id=id;
	}

	public Long getId(){
		return id;
	}

	public void setCommentId(Long commentId){
		this.commentId=commentId;
	}

	public Long getCommentId(){
		return commentId;
	}

	public void setContent(String content){
		this.content=content;
	}

	public String getContent(){
		return content;
	}

	public void setUid(Long uid){
		this.uid=uid;
	}

	public Long getUid(){
		return uid;
	}

	public void setCreateTime(Long createTime){
		this.createTime=createTime;
	}

	public Long getCreateTime(){
		return createTime;
	}

	@Override
	public String toString() {
		return "bbs_reply[" + 
			"id=" + id + 
			", comment_id=" + commentId + 
			", content=" + content + 
			", uid=" + uid + 
			", create_time=" + createTime + 
			"]";
	}
}

