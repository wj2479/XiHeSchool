package com.xh.module.base.entity;

/**
 * table name:  student
 * author name: 朱华清
 * create time: 2020-05-04 17:28:02
 */ 
public class Student{
	private Long id;//ID
	private Long uid;//用户ID
	private Long schoolId;//学校ID
	private Long clasId;//班级ID
	private Integer createTime;//创建时间

	public Student() {
		super();
	}

	public Student(Long id,Long uid,Long schoolId,Long clasId,Integer createTime) {
		this.id=id;
		this.uid=uid;
		this.schoolId=schoolId;
		this.clasId=clasId;
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

	public void setSchoolId(Long schoolId){
		this.schoolId=schoolId;
	}

	public Long getSchoolId(){
		return schoolId;
	}

	public void setClasId(Long clasId){
		this.clasId=clasId;
	}

	public Long getClasId(){
		return clasId;
	}

	public void setCreateTime(Integer createTime){
		this.createTime=createTime;
	}

	public Integer getCreateTime(){
		return createTime;
	}

	@Override
	public String toString() {
		return "student[" + 
			"id=" + id + 
			", uid=" + uid + 
			", school_id=" + schoolId + 
			", clas_id=" + clasId + 
			", create_time=" + createTime + 
			"]";
	}
}

