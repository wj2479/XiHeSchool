package com.xh.module.base.entity;

/**
 * table name:  user_info_update
 * author name: 朱华清
 * create time: 2020-05-04 17:28:03
 */ 
public class UserInfoUpdate{
	private Long id;//ID
	private Long uid;//用户ID
	private String attributeName;//属性名
	private String attributeOldVal;//属性对应旧的值
	private String attributeNewVal;//属性对应新的值
	private Integer updateTime;//修改时间

	public UserInfoUpdate() {
		super();
	}

	public UserInfoUpdate(Long id,Long uid,String attributeName,String attributeOldVal,String attributeNewVal,Integer updateTime) {
		this.id=id;
		this.uid=uid;
		this.attributeName=attributeName;
		this.attributeOldVal=attributeOldVal;
		this.attributeNewVal=attributeNewVal;
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

	public void setAttributeName(String attributeName){
		this.attributeName=attributeName;
	}

	public String getAttributeName(){
		return attributeName;
	}

	public void setAttributeOldVal(String attributeOldVal){
		this.attributeOldVal=attributeOldVal;
	}

	public String getAttributeOldVal(){
		return attributeOldVal;
	}

	public void setAttributeNewVal(String attributeNewVal){
		this.attributeNewVal=attributeNewVal;
	}

	public String getAttributeNewVal(){
		return attributeNewVal;
	}

	public void setUpdateTime(Integer updateTime){
		this.updateTime=updateTime;
	}

	public Integer getUpdateTime(){
		return updateTime;
	}

	@Override
	public String toString() {
		return "user_info_update[" + 
			"id=" + id + 
			", uid=" + uid + 
			", attribute_name=" + attributeName + 
			", attribute_old_val=" + attributeOldVal + 
			", attribute_new_val=" + attributeNewVal + 
			", update_time=" + updateTime + 
			"]";
	}
}

