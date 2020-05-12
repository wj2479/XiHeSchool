package com.xh.module.base.entity;

/**
 * table name:  user_login_log
 * author name: 朱华清
 * create time: 2020-05-04 17:28:03
 */ 
public class UserLoginLog{
	private Long id;//
	private Long uid;//用户uid
	private Byte type;//登录方式 第三方/邮箱/手机等
	private Byte command;//操作类型 1登陆成功  2登出成功 3登录失败 4登出失败
	private String version;//客户端版本号
	private String client;//客户端
	private String deviceId;//登录时设备号
	private String lastip;//登录ip
	private String os;//手机系统
	private String osver;//系统版本
	private String text;//
	private Integer createTime;//操作时间

	public UserLoginLog() {
		super();
	}

	public UserLoginLog(Long id,Long uid,Byte type,Byte command,String version,String client,String deviceId,String lastip,String os,String osver,String text,Integer createTime) {
		this.id=id;
		this.uid=uid;
		this.type=type;
		this.command=command;
		this.version=version;
		this.client=client;
		this.deviceId=deviceId;
		this.lastip=lastip;
		this.os=os;
		this.osver=osver;
		this.text=text;
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

	public void setType(Byte type){
		this.type=type;
	}

	public Byte getType(){
		return type;
	}

	public void setCommand(Byte command){
		this.command=command;
	}

	public Byte getCommand(){
		return command;
	}

	public void setVersion(String version){
		this.version=version;
	}

	public String getVersion(){
		return version;
	}

	public void setClient(String client){
		this.client=client;
	}

	public String getClient(){
		return client;
	}

	public void setDeviceId(String deviceId){
		this.deviceId=deviceId;
	}

	public String getDeviceId(){
		return deviceId;
	}

	public void setLastip(String lastip){
		this.lastip=lastip;
	}

	public String getLastip(){
		return lastip;
	}

	public void setOs(String os){
		this.os=os;
	}

	public String getOs(){
		return os;
	}

	public void setOsver(String osver){
		this.osver=osver;
	}

	public String getOsver(){
		return osver;
	}

	public void setText(String text){
		this.text=text;
	}

	public String getText(){
		return text;
	}

	public void setCreateTime(Integer createTime){
		this.createTime=createTime;
	}

	public Integer getCreateTime(){
		return createTime;
	}

	@Override
	public String toString() {
		return "user_login_log[" + 
			"id=" + id + 
			", uid=" + uid + 
			", type=" + type + 
			", command=" + command + 
			", version=" + version + 
			", client=" + client + 
			", device_id=" + deviceId + 
			", lastip=" + lastip + 
			", os=" + os + 
			", osver=" + osver + 
			", text=" + text + 
			", create_time=" + createTime + 
			"]";
	}
}

