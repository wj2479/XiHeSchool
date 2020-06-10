package com.xh.module.base.entity;

import java.util.List;

/**
 * table name:  schoolwork
 * author name: 朱华清
 * create time: 2020-05-30 13:53:46
 */ 
public class Schoolwork{

	private Long id;
	private Long classId;
	private Long courseId;
	private String title;
	private String content;
	private Long createUid;
	private Long createTime;
	private Long dueTime;

	private List<SchoolworkEnclosure> schoolworkEnclosures;

	private Course course;

	private UserBase userBase;

	public Schoolwork() {
		super();
	}

	public Schoolwork(Long id,Long classId,Long courseId,String title,String content,Long createUid,Long createTime,Long dueTime) {
		this.id=id;
		this.classId=classId;
		this.courseId=courseId;
		this.title=title;
		this.content=content;
		this.createUid=createUid;
		this.createTime=createTime;
		this.dueTime=dueTime;
	}

	public void setId(Long id){
		this.id=id;
	}

	public Long getId(){
		return id;
	}

	public void setClassId(Long classId){
		this.classId=classId;
	}

	public Long getClassId(){
		return classId;
	}

	public void setCourseId(Long courseId){
		this.courseId=courseId;
	}

	public Long getCourseId(){
		return courseId;
	}

	public void setTitle(String title){
		this.title=title;
	}

	public String getTitle(){
		return title;
	}

	public void setContent(String content){
		this.content=content;
	}

	public String getContent(){
		return content;
	}

	public void setCreateUid(Long createUid){
		this.createUid=createUid;
	}

	public Long getCreateUid(){
		return createUid;
	}

	public void setCreateTime(Long createTime){
		this.createTime=createTime;
	}

	public Long getCreateTime(){
		return createTime;
	}

	public void setDueTime(Long dueTime){
		this.dueTime=dueTime;
	}

	public Long getDueTime(){
		return dueTime;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public UserBase getUserBase() {
		return userBase;
	}

	public void setUserBase(UserBase userBase) {
		this.userBase = userBase;
	}


	public List<SchoolworkEnclosure> getSchoolworkEnclosures() {
		return schoolworkEnclosures;
	}

	public void setSchoolworkEnclosures(List<SchoolworkEnclosure> schoolworkEnclosures) {
		this.schoolworkEnclosures = schoolworkEnclosures;
	}

	@Override
	public String toString() {
		return "schoolwork[" + 
			"id=" + id + 
			", class_id=" + classId + 
			", course_id=" + courseId + 
			", title=" + title + 
			", content=" + content + 
			", create_uid=" + createUid + 
			", create_time=" + createTime + 
			", due_time=" + dueTime + 
			"]";
	}
}

