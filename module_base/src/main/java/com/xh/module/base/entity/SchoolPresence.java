package com.xh.module.base.entity;

/**
 * table name:  school_presence
 * author name: 朱华清
 * create time: 2020-05-04 17:28:01
 */ 
public class SchoolPresence{
	private Long id;//ID
	private Long schoolId;//学校简称
	private String background;//风采图片
	private Integer createTime;//上传时间

	public SchoolPresence() {
		super();
	}

	public SchoolPresence(Long id,Long schoolId,String background,Integer createTime) {
		this.id=id;
		this.schoolId=schoolId;
		this.background=background;
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

	public void setBackground(String background){
		this.background=background;
	}

	public String getBackground(){
		return background;
	}

	public void setCreateTime(Integer createTime){
		this.createTime=createTime;
	}

	public Integer getCreateTime(){
		return createTime;
	}

	@Override
	public String toString() {
		return "school_presence[" + 
			"id=" + id + 
			", school_id=" + schoolId + 
			", background=" + background + 
			", create_time=" + createTime + 
			"]";
	}
}

