package com.xh.module.base.entity;

/**
 * table name:  parent
 * author name: 朱华清
 * create time: 2020-05-04 17:28:01
 */ 
public class Parent{
	private Long id;//ID
	private Long uid;//用户ID
	private Integer createTime;//创建时间

	public Parent() {
		super();
	}

	public Parent(Long id,Long uid,Integer createTime) {
		this.id=id;
		this.uid=uid;
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

	public void setCreateTime(Integer createTime){
		this.createTime=createTime;
	}

	public Integer getCreateTime(){
		return createTime;
	}

	@Override
	public String toString() {
		return "parent[" + 
			"id=" + id + 
			", uid=" + uid + 
			", create_time=" + createTime + 
			"]";
	}
}

