package com.xh.module.base.entity;

/**
 * table name:  teacher
 * author name: 朱华清
 * create time: 2020-05-04 17:28:02
 */ 
public class Teacher{
	private Long id;//ID
	private Long uid;//用户ID
	private Integer createTime;//创建时间
	private Integer isHeadmaster;//是否是班主任 0 不是 1 是
	private Integer updateTime;//更新时间

	public Teacher() {
		super();
	}

	public Teacher(Long id,Long uid,Integer createTime,Integer isHeadmaster,Integer updateTime) {
		this.id=id;
		this.uid=uid;
		this.createTime=createTime;
		this.isHeadmaster=isHeadmaster;
		this.updateTime=updateTime;
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

	public void setIsHeadmaster(Integer isHeadmaster){
		this.isHeadmaster=isHeadmaster;
	}

	public Integer getIsHeadmaster(){
		return isHeadmaster;
	}

	public void setUpdateTime(Integer updateTime){
		this.updateTime=updateTime;
	}

	public Integer getUpdateTime(){
		return updateTime;
	}

	@Override
	public String toString() {
		return "teacher[" + 
			"id=" + id + 
			", uid=" + uid + 
			", create_time=" + createTime + 
			", is_headmaster=" + isHeadmaster + 
			", update_time=" + updateTime + 
			"]";
	}
}

