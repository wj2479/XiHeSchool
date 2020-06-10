package com.xh.module.base.entity;

/**
 * table name:  schoolwork_enclosure
 * author name: 朱华清
 * create time: 2020-05-29 15:12:52
 */ 
public class SchoolworkEnclosure{

	private Long id;

	private Long schoolworkId;

	private String url;

	private Integer type;

	public SchoolworkEnclosure() {
		super();
	}

	public SchoolworkEnclosure(Long id,Long schoolworkId,String url,Integer type) {
		this.id=id;
		this.schoolworkId=schoolworkId;
		this.url=url;
		this.type=type;
	}

	public void setId(Long id){
		this.id=id;
	}

	public Long getId(){
		return id;
	}

	public void setSchoolworkId(Long schoolworkId){
		this.schoolworkId=schoolworkId;
	}

	public Long getSchoolworkId(){
		return schoolworkId;
	}

	public void setUrl(String url){
		this.url=url;
	}

	public String getUrl(){
		return url;
	}

	public void setType(Integer type){
		this.type=type;
	}

	public Integer getType(){
		return type;
	}

	@Override
	public String toString() {
		return "schoolwork_enclosure[" + 
			"id=" + id + 
			", schoolwork_id=" + schoolworkId + 
			", url=" + url + 
			", type=" + type + 
			"]";
	}
}

