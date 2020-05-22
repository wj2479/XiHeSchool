package com.xh.module.base.entity.bbs;

/**
 * table name:  bbs_love
 * author name: 朱华清
 * create time: 2020-05-20 09:42:40
 */ 
public class BbsLove{

	private Long id;
	private Long uid;
	private Integer type;
	private Long createTime;

	public BbsLove() {
		super();
	}

	public BbsLove(Long id,Long uid,Integer type,Long createTime) {
		this.id=id;
		this.uid=uid;
		this.type=type;
		this.createTime=createTime;
	}

	public void setId(Long id){
		this.id=id;
	}

	public Long getId(){
		return id;
	}

	public void setUid(Long uid){
		this.uid=uid;
	}

	public Long getUid(){
		return uid;
	}

	public void setType(Integer type){
		this.type=type;
	}

	public Integer getType(){
		return type;
	}

	public void setCreateTime(Long createTime){
		this.createTime=createTime;
	}

	public Long getCreateTime(){
		return createTime;
	}

	@Override
	public String toString() {
		return "bbs_love[" + 
			"id=" + id + 
			", uid=" + uid + 
			", type=" + type + 
			", create_time=" + createTime + 
			"]";
	}
}

