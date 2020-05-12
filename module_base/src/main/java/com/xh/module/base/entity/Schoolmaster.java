package com.xh.module.base.entity;

/**
 * table name:  schoolmaster
 * author name: 朱华清
 * create time: 2020-05-04 17:28:02
 */ 
public class Schoolmaster{
	private Long id;//ID
	private Long schoolId;//学校ID
	private Integer createTime;//创建时间

	public Schoolmaster() {
		super();
	}

	public Schoolmaster(Long id,Long schoolId,Integer createTime) {
		this.id=id;
		this.schoolId=schoolId;
		this.createTime=createTime;
	}

	public void setId(Long id){
		this.id=id;
	}

	public Long getId(){
		return id;
	}

	public void setSchoolId(Long schoolId){
		this.schoolId=schoolId;
	}

	public Long getSchoolId(){
		return schoolId;
	}

	public void setCreateTime(Integer createTime){
		this.createTime=createTime;
	}

	public Integer getCreateTime(){
		return createTime;
	}

	@Override
	public String toString() {
		return "schoolmaster[" + 
			"id=" + id + 
			", school_id=" + schoolId + 
			", create_time=" + createTime + 
			"]";
	}
}

