package com.xh.module.base.entity;

import java.util.Date;
/**
 * table name:  t_user
 * author name: 朱华清
 * create time: 2020-05-04 17:28:02
 */ 
public class TUser{

	private Integer id;//主键
	private String userName;//用户名
	private String passWord;//密码
	private Integer age;//年龄
	private Date brithdy;//生日

	public TUser() {
		super();
	}

	public TUser(Integer id,String userName,String passWord,Integer age,Date brithdy) {
		this.id=id;
		this.userName=userName;
		this.passWord=passWord;
		this.age=age;
		this.brithdy=brithdy;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return id;
	}

	public void setUserName(String userName){
		this.userName=userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setPassWord(String passWord){
		this.passWord=passWord;
	}

	public String getPassWord(){
		return passWord;
	}

	public void setAge(Integer age){
		this.age=age;
	}

	public Integer getAge(){
		return age;
	}

	public void setBrithdy(Date brithdy){
		this.brithdy=brithdy;
	}

	public Date getBrithdy(){
		return brithdy;
	}

	@Override
	public String toString() {
		return "t_user[" + 
			"id=" + id + 
			", user_name=" + userName + 
			", pass_word=" + passWord + 
			", age=" + age + 
			", brithdy=" + brithdy + 
			"]";
	}
}

