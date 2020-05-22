package com.xh.module.base.entity.bbs;

/**
 * table name:  bbs_article_type
 * author name: 朱华清
 * create time: 2020-05-20 09:42:39
 */ 
public class BbsArticleType{

	private Long id;
	private String name;
	private Long createTime;

	public BbsArticleType() {
		super();
	}

	public BbsArticleType(Long id,String name,Long createTime) {
		this.id=id;
		this.name=name;
		this.createTime=createTime;
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

	public void setCreateTime(Long createTime){
		this.createTime=createTime;
	}

	public Long getCreateTime(){
		return createTime;
	}

	@Override
	public String toString() {
		return "bbs_article_type[" + 
			"id=" + id + 
			", name=" + name + 
			", create_time=" + createTime + 
			"]";
	}
}

