package com.xh.module.base.entity;

/**
 * table name:  clas
 * author name: 朱华清
 * create time: 2020-05-04 17:28:00
 */ 
public class Clas{

	private Long id;//ID

	private String name;//班级名称

	private Long schoolId;//学校ID

	private String gradeName;//年级名称

	private Long headmasterId;//班主任ID

	private Integer createTime;//创建时间

	private Integer updateTime;//更新时间

	public Clas() {
		super();
	}

	public Clas(Long id,String name,Long schoolId,String gradeName,Long headmasterId,Integer createTime,Integer updateTime) {
		this.id=id;
		this.name=name;
		this.schoolId=schoolId;
		this.gradeName=gradeName;
		this.headmasterId=headmasterId;
		this.createTime=createTime;
		this.updateTime=updateTime;
	}

	public void setId(Long id){
		this.id=id;
	}

	public Long getId(){
		return id;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public void setSchoolId(Long schoolId){
		this.schoolId=schoolId;
	}

	public Long getSchoolId(){
		return schoolId;
	}

	public void setGradeName(String gradeName){
		this.gradeName=gradeName;
	}

	public String getGradeName(){
		return gradeName;
	}

	public void setHeadmasterId(Long headmasterId){
		this.headmasterId=headmasterId;
	}

	public Long getHeadmasterId(){
		return headmasterId;
	}

	public void setCreateTime(Integer createTime){
		this.createTime=createTime;
	}

	public Integer getCreateTime(){
		return createTime;
	}

	public void setUpdateTime(Integer updateTime){
		this.updateTime=updateTime;
	}

	public Integer getUpdateTime(){
		return updateTime;
	}

	@Override
	public String toString() {
		return "clas[" + 
			"id=" + id + 
			", name=" + name + 
			", school_id=" + schoolId + 
			", grade_name=" + gradeName + 
			", headmaster_id=" + headmasterId + 
			", create_time=" + createTime + 
			", update_time=" + updateTime + 
			"]";
	}
}

