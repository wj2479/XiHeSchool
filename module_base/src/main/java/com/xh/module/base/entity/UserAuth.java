package com.xh.module.base.entity;

/**
 * table name:  user_auth
 * author name: 朱华清
 * create time: 2020-05-04 17:28:02
 */ 
public class UserAuth{
	private Long id;//
	private Long uid;//用户id
	private Byte identityType;//1手机号 2邮箱 3用户名 4qq 5微信 6腾讯微博 7新浪微博
	private String identifier;//手机号 邮箱 用户名或第三方应用的唯一标识
	private String certificate;//密码凭证(站内的保存密码，站外的不保存或保存token)
	private Integer createTime;//绑定时间
	private Integer updateTime;//更新绑定时间

	public UserAuth() {
		super();
	}

	public UserAuth(Long id,Long uid,Byte identityType,String identifier,String certificate,Integer createTime,Integer updateTime) {
		this.id=id;
		this.uid=uid;
		this.identityType=identityType;
		this.identifier=identifier;
		this.certificate=certificate;
		this.createTime=createTime;
		this.updateTime=updateTime;
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

	public void setIdentityType(Byte identityType){
		this.identityType=identityType;
	}

	public Byte getIdentityType(){
		return identityType;
	}

	public void setIdentifier(String identifier){
		this.identifier=identifier;
	}

	public String getIdentifier(){
		return identifier;
	}

	public void setCertificate(String certificate){
		this.certificate=certificate;
	}

	public String getCertificate(){
		return certificate;
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
		return "user_auth[" + 
			"id=" + id + 
			", uid=" + uid + 
			", identity_type=" + identityType + 
			", identifier=" + identifier + 
			", certificate=" + certificate + 
			", create_time=" + createTime + 
			", update_time=" + updateTime + 
			"]";
	}
}

