package com.xh.module.base.entity.bbs;

/**
 * table name:  bbs_module
 * author name: 朱华清
 * create time: 2020-05-20 09:42:40
 */ 
public class BbsModule{

	private Long id;
	private String name;
	private String code;
	private Long createTime;

	public BbsModule() {
		super();
	}

	public BbsModule(Long id,String name,String code,Long createTime) {
		this.id=id;
		this.name=name;
		this.code=code;
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

	public void setCode(String code){
		this.code=code;
	}

	public String getCode(){
		return code;
	}

	public void setCreateTime(Long createTime){
		this.createTime=createTime;
	}

	public Long getCreateTime(){
		return createTime;
	}

	@Override
	public String toString() {
		return "bbs_module[" + 
			"id=" + id + 
			", name=" + name + 
			", code=" + code + 
			", create_time=" + createTime + 
			"]";
	}
}

