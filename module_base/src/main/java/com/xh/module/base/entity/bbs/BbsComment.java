package com.xh.module.base.entity.bbs;

/**
 * table name:  bbs_comment
 * author name: 朱华清
 * create time: 2020-05-20 09:42:39
 */ 
public class BbsComment{

	private Long id;
	private String content;
	private Long artId;
	private Long uid;
	private Long createTime;

	public BbsComment() {
		super();
	}

	public BbsComment(Long id,String content,Long artId,Long uid,Long createTime) {
		this.id=id;
		this.content=content;
		this.artId=artId;
		this.uid=uid;
		this.createTime=createTime;
	}

	public void setId(Long id){
		this.id=id;
	}

	public Long getId(){
		return id;
	}

	public void setContent(String content){
		this.content=content;
	}

	public String getContent(){
		return content;
	}

	public void setArtId(Long artId){
		this.artId=artId;
	}

	public Long getArtId(){
		return artId;
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
		return "bbs_comment[" + 
			"id=" + id + 
			", content=" + content + 
			", art_id=" + artId + 
			", uid=" + uid + 
			", create_time=" + createTime + 
			"]";
	}
}

