package com.xh.module.base.entity;

/**
 * table name:  course
 * author name: 朱华清
 * create time: 2020-05-05 16:11:20
 */ 
public class Course{
	private Long id;//主键
	private String courseName;//课程名称
	private Integer type;//0:幼儿园  1：小学  2：初中

	public Course() {
		super();
	}

	public Course(Long id,String courseName,Integer type) {
		this.id=id;
		this.courseName=courseName;
		this.type=type;
	}

	public void setId(Long id){
		this.id=id;
	}

	public Long getId(){
		return id;
	}

	public void setCourseName(String courseName){
		this.courseName=courseName;
	}

	public String getCourseName(){
		return courseName;
	}

	public void setType(Integer type){
		this.type=type;
	}

	public Integer getType(){
		return type;
	}

	@Override
	public String toString() {
		return "course[" + 
			"id=" + id + 
			", course_name=" + courseName + 
			", type=" + type + 
			"]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Course course = (Course) o;

		return id != null ? id.equals(course.id) : course.id == null;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}

