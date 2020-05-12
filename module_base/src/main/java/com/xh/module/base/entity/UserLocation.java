package com.xh.module.base.entity;

import java.math.BigDecimal;
/**
 * table name:  user_location
 * author name: 朱华清
 * create time: 2020-05-04 17:28:03
 */ 
public class UserLocation{
	private Long uid;//用户ID

	private String currNation;//所在地国
	private String currProvince;//所在地省
	private String currCity;//所在地市
	private String currDistrict;//所在地地区
	private String location;//具体地址
	private BigDecimal longitude;//经度
	private BigDecimal latitude;//纬度
	private Integer updateTime;//修改时间

	public UserLocation() {
		super();
	}

	public UserLocation(Long uid,String currNation,String currProvince,String currCity,String currDistrict,String location,BigDecimal longitude,BigDecimal latitude,Integer updateTime) {
		this.uid=uid;
		this.currNation=currNation;
		this.currProvince=currProvince;
		this.currCity=currCity;
		this.currDistrict=currDistrict;
		this.location=location;
		this.longitude=longitude;
		this.latitude=latitude;
		this.updateTime=updateTime;
	}

	public void setUid(Long uid){
		this.uid=uid;
	}

	public Long getUid(){
		return uid;
	}

	public void setCurrNation(String currNation){
		this.currNation=currNation;
	}

	public String getCurrNation(){
		return currNation;
	}

	public void setCurrProvince(String currProvince){
		this.currProvince=currProvince;
	}

	public String getCurrProvince(){
		return currProvince;
	}

	public void setCurrCity(String currCity){
		this.currCity=currCity;
	}

	public String getCurrCity(){
		return currCity;
	}

	public void setCurrDistrict(String currDistrict){
		this.currDistrict=currDistrict;
	}

	public String getCurrDistrict(){
		return currDistrict;
	}

	public void setLocation(String location){
		this.location=location;
	}

	public String getLocation(){
		return location;
	}

	public void setLongitude(BigDecimal longitude){
		this.longitude=longitude;
	}

	public BigDecimal getLongitude(){
		return longitude;
	}

	public void setLatitude(BigDecimal latitude){
		this.latitude=latitude;
	}

	public BigDecimal getLatitude(){
		return latitude;
	}

	public void setUpdateTime(Integer updateTime){
		this.updateTime=updateTime;
	}

	public Integer getUpdateTime(){
		return updateTime;
	}

	@Override
	public String toString() {
		return "user_location[" + 
			"uid=" + uid + 
			", curr_nation=" + currNation + 
			", curr_province=" + currProvince + 
			", curr_city=" + currCity + 
			", curr_district=" + currDistrict + 
			", location=" + location + 
			", longitude=" + longitude + 
			", latitude=" + latitude + 
			", update_time=" + updateTime + 
			"]";
	}
}

