package com.xh.module.base.entity;

/**
 * table name:  user_realauth
 * author name: 朱华清
 * create time: 2020-05-04 17:28:04
 */ 
public class UserRealauth{
	private Long id;//ID
	private Long uid;//用户ID
	private String realName;//姓名
	private String identityCard;//身份证
	private String nativePlace;//籍贯
	private Integer createTime;//认证时间

	public UserRealauth() {
		super();
	}

	public UserRealauth(Long id,Long uid,String realName,String identityCard,String nativePlace,Integer createTime) {
		this.id=id;
		this.uid=uid;
		this.realName=realName;
		this.identityCard=identityCard;
		this.nativePlace=nativePlace;
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

	public void setRealName(String realName){
		this.realName=realName;
	}

	public String getRealName(){
		return realName;
	}

	public void setIdentityCard(String identityCard){
		this.identityCard=identityCard;
	}

	public String getIdentityCard(){
		return identityCard;
	}

	public void setNativePlace(String nativePlace){
		this.nativePlace=nativePlace;
	}

	public String getNativePlace(){
		return nativePlace;
	}

	public void setCreateTime(Integer createTime){
		this.createTime=createTime;
	}

	public Integer getCreateTime(){
		return createTime;
	}

	@Override
	public String toString() {
		return "user_realauth[" + 
			"id=" + id + 
			", uid=" + uid + 
			", real_name=" + realName + 
			", identity_card=" + identityCard + 
			", native_place=" + nativePlace + 
			", create_time=" + createTime + 
			"]";
	}
}

