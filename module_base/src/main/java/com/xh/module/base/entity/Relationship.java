package com.xh.module.base.entity;

/**
 * table name:  relationship
 * author name: 朱华清
 * create time: 2020-05-06 09:33:07
 */ 
public class Relationship{
	private Long id;//id
	private String name;//名称
	private String gender;//0：女   1：男

	public Relationship() {
		super();
	}

	public Relationship(Long id,String name,String gender) {
		this.id=id;
		this.name=name;
		this.gender=gender;
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

	public void setGender(String gender){
		this.gender=gender;
	}

	public String getGender(){
		return gender;
	}

	@Override
	public String toString() {
		return "relationship[" + 
			"id=" + id + 
			", name=" + name + 
			", gender=" + gender + 
			"]";
	}
}

