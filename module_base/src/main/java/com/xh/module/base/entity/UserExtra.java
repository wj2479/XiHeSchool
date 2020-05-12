package com.xh.module.base.entity;

/**
 * table name:  user_extra
 * author name: 朱华清
 * create time: 2020-05-04 17:28:03
 */ 
public class UserExtra{
	private Long uid;//用户 ID
	private String vendor;//手机厂商：apple|htc|samsung，很少用
	private String clientName;//客户端名称，如hjskang
	private String clientVersion;//客户端版本号，如7.0.1
	private String osName;//设备号:android|ios
	private String osVersion;//系统版本号:2.2|2.3|4.0|5.1
	private String deviceName;//设备型号，如:iphone6s、u880、u8800
	private String deviceId;//设备ID
	private String idfa;//苹果设备的IDFA
	private String idfv;//苹果设备的IDFV
	private String market;//来源
	private Integer createTime;//添加时间
	private Integer updateTime;//更新时间
	private String extend1;//扩展字段1
	private String extend2;//扩展字段2
	private String extend3;//扩展字段3

	public UserExtra() {
		super();
	}

	public UserExtra(Long uid,String vendor,String clientName,String clientVersion,String osName,String osVersion,String deviceName,String deviceId,String idfa,String idfv,String market,Integer createTime,Integer updateTime,String extend1,String extend2,String extend3) {
		this.uid=uid;
		this.vendor=vendor;
		this.clientName=clientName;
		this.clientVersion=clientVersion;
		this.osName=osName;
		this.osVersion=osVersion;
		this.deviceName=deviceName;
		this.deviceId=deviceId;
		this.idfa=idfa;
		this.idfv=idfv;
		this.market=market;
		this.createTime=createTime;
		this.updateTime=updateTime;
		this.extend1=extend1;
		this.extend2=extend2;
		this.extend3=extend3;
	}

	public void setUid(Long uid){
		this.uid=uid;
	}

	public Long getUid(){
		return uid;
	}

	public void setVendor(String vendor){
		this.vendor=vendor;
	}

	public String getVendor(){
		return vendor;
	}

	public void setClientName(String clientName){
		this.clientName=clientName;
	}

	public String getClientName(){
		return clientName;
	}

	public void setClientVersion(String clientVersion){
		this.clientVersion=clientVersion;
	}

	public String getClientVersion(){
		return clientVersion;
	}

	public void setOsName(String osName){
		this.osName=osName;
	}

	public String getOsName(){
		return osName;
	}

	public void setOsVersion(String osVersion){
		this.osVersion=osVersion;
	}

	public String getOsVersion(){
		return osVersion;
	}

	public void setDeviceName(String deviceName){
		this.deviceName=deviceName;
	}

	public String getDeviceName(){
		return deviceName;
	}

	public void setDeviceId(String deviceId){
		this.deviceId=deviceId;
	}

	public String getDeviceId(){
		return deviceId;
	}

	public void setIdfa(String idfa){
		this.idfa=idfa;
	}

	public String getIdfa(){
		return idfa;
	}

	public void setIdfv(String idfv){
		this.idfv=idfv;
	}

	public String getIdfv(){
		return idfv;
	}

	public void setMarket(String market){
		this.market=market;
	}

	public String getMarket(){
		return market;
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

	public void setExtend1(String extend1){
		this.extend1=extend1;
	}

	public String getExtend1(){
		return extend1;
	}

	public void setExtend2(String extend2){
		this.extend2=extend2;
	}

	public String getExtend2(){
		return extend2;
	}

	public void setExtend3(String extend3){
		this.extend3=extend3;
	}

	public String getExtend3(){
		return extend3;
	}

	@Override
	public String toString() {
		return "user_extra[" + 
			"uid=" + uid + 
			", vendor=" + vendor + 
			", client_name=" + clientName + 
			", client_version=" + clientVersion + 
			", os_name=" + osName + 
			", os_version=" + osVersion + 
			", device_name=" + deviceName + 
			", device_id=" + deviceId + 
			", idfa=" + idfa + 
			", idfv=" + idfv + 
			", market=" + market + 
			", create_time=" + createTime + 
			", update_time=" + updateTime + 
			", extend1=" + extend1 + 
			", extend2=" + extend2 + 
			", extend3=" + extend3 + 
			"]";
	}
}

