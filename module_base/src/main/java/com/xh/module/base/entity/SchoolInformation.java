package com.xh.module.base.entity;

/**
 * table name:  school_information
 * author name: 朱华清
 * create time: 2020-05-09 11:01:32
 */ 
public class SchoolInformation{

	private Long id;//ID
	private Long schoolId;//学校id
	private String indexImage;//内容
	private String title;//标题
	private String content;//内容
	private Integer createTime;//认证时间
	private Long createUid;//发布人

	public SchoolInformation() {
		super();
	}

	public SchoolInformation(Long id,Long schoolId,String indexImage,String title,String content,Integer createTime,Long createUid) {
		this.id=id;
		this.schoolId=schoolId;
		this.indexImage=indexImage;
		this.title=title;
		this.content=content;
		this.createTime=createTime;
		this.createUid=createUid;
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

	public void setIndexImage(String indexImage){
		this.indexImage=indexImage;
	}

	public String getIndexImage(){
		return indexImage;
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

	public void setCreateTime(Integer createTime){
		this.createTime=createTime;
	}

	public Integer getCreateTime(){
		return createTime;
	}

	public void setCreateUid(Long createUid){
		this.createUid=createUid;
	}

	public Long getCreateUid(){
		return createUid;
	}

	@Override
	public String toString() {
		return "school_information[" + 
			"id=" + id + 
			", school_id=" + schoolId + 
			", index_image=" + indexImage + 
			", title=" + title + 
			", content=" + content + 
			", create_time=" + createTime + 
			", create_uid=" + createUid + 
			"]";
	}
}

