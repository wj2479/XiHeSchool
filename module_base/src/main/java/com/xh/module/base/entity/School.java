package com.xh.module.base.entity;

/**
 * table name:  school
 * author name: 朱华清
 * create time: 2020-05-08 16:19:59
 */ 
public class School{

	private Long id;//ID
	private String simpleName;//学校简称
	private String name;//学校名称
	private Integer type;//学校类型 0 幼儿园 1 小学 2 初中
	private String logo;//学校LOGO图
	private String background;//学校APP背景图
	private String contactName;//学校联系人姓名
	private String contactMobile;//学校联系人手机
	private String telephone;//学校座机
	private String address;//学校详细地址
	private String introduction;//学校简介
	private String latitudeLongitude;//经纬度（中间用,隔开）
	private Integer createTime;//创建时间
	private Integer updateTime;//修改时间
	private String qrcodePath;//学校二维码地址

	public School() {
		super();
	}

	public School(Long id,String simpleName,String name,Integer type,String logo,String background,String contactName,String contactMobile,String telephone,String address,String introduction,String latitudeLongitude,Integer createTime,Integer updateTime,String qrcodePath) {
		this.id=id;
		this.simpleName=simpleName;
		this.name=name;
		this.type=type;
		this.logo=logo;
		this.background=background;
		this.contactName=contactName;
		this.contactMobile=contactMobile;
		this.telephone=telephone;
		this.address=address;
		this.introduction=introduction;
		this.latitudeLongitude=latitudeLongitude;
		this.createTime=createTime;
		this.updateTime=updateTime;
		this.qrcodePath=qrcodePath;
	}

	public void setId(Long id){
		this.id=id;
	}

	public Long getId(){
		return id;
	}

	public void setSimpleName(String simpleName){
		this.simpleName=simpleName;
	}

	public String getSimpleName(){
		return simpleName;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public void setType(Integer type){
		this.type=type;
	}

	public Integer getType(){
		return type;
	}

	public void setLogo(String logo){
		this.logo=logo;
	}

	public String getLogo(){
		return logo;
	}

	public void setBackground(String background){
		this.background=background;
	}

	public String getBackground(){
		return background;
	}

	public void setContactName(String contactName){
		this.contactName=contactName;
	}

	public String getContactName(){
		return contactName;
	}

	public void setContactMobile(String contactMobile){
		this.contactMobile=contactMobile;
	}

	public String getContactMobile(){
		return contactMobile;
	}

	public void setTelephone(String telephone){
		this.telephone=telephone;
	}

	public String getTelephone(){
		return telephone;
	}

	public void setAddress(String address){
		this.address=address;
	}

	public String getAddress(){
		return address;
	}

	public void setIntroduction(String introduction){
		this.introduction=introduction;
	}

	public String getIntroduction(){
		return introduction;
	}

	public void setLatitudeLongitude(String latitudeLongitude){
		this.latitudeLongitude=latitudeLongitude;
	}

	public String getLatitudeLongitude(){
		return latitudeLongitude;
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

	public void setQrcodePath(String qrcodePath){
		this.qrcodePath=qrcodePath;
	}

	public String getQrcodePath(){
		return qrcodePath;
	}

	@Override
	public String toString() {
		return "school[" + 
			"id=" + id + 
			", simple_name=" + simpleName + 
			", name=" + name + 
			", type=" + type + 
			", logo=" + logo + 
			", background=" + background + 
			", contact_name=" + contactName + 
			", contact_mobile=" + contactMobile + 
			", telephone=" + telephone + 
			", address=" + address + 
			", introduction=" + introduction + 
			", latitude_longitude=" + latitudeLongitude + 
			", create_time=" + createTime + 
			", update_time=" + updateTime + 
			", qrcode_path=" + qrcodePath + 
			"]";
	}
}

