package com.xh.module.base.entity;

/**
 * table name:  committee
 * author name: 朱华清
 * create time: 2020-05-04 17:28:00
 */ 
public class Committee{
	private Long id;//ID
	private Long clasId;//班级ID
	private Long schoolId;//学校ID
	private String adminUids;//管理员ID 多个逗号隔开
	private String leaderUids;//领导ID 多个逗号隔开
	private String sponsorUids;//收费发起人ID 多个逗号隔开
	private String reviewedUids;//审核人ID，多个逗号隔开
	private Integer createTime;//创建时间

	public Committee() {
		super();
	}

	public Committee(Long id,Long clasId,Long schoolId,String adminUids,String leaderUids,String sponsorUids,String reviewedUids,Integer createTime) {
		this.id=id;
		this.clasId=clasId;
		this.schoolId=schoolId;
		this.adminUids=adminUids;
		this.leaderUids=leaderUids;
		this.sponsorUids=sponsorUids;
		this.reviewedUids=reviewedUids;
		this.createTime=createTime;
	}

	public void setId(Long id){
		this.id=id;
	}

	public Long getId(){
		return id;
	}

	public void setClasId(Long clasId){
		this.clasId=clasId;
	}

	public Long getClasId(){
		return clasId;
	}

	public void setSchoolId(Long schoolId){
		this.schoolId=schoolId;
	}

	public Long getSchoolId(){
		return schoolId;
	}

	public void setAdminUids(String adminUids){
		this.adminUids=adminUids;
	}

	public String getAdminUids(){
		return adminUids;
	}

	public void setLeaderUids(String leaderUids){
		this.leaderUids=leaderUids;
	}

	public String getLeaderUids(){
		return leaderUids;
	}

	public void setSponsorUids(String sponsorUids){
		this.sponsorUids=sponsorUids;
	}

	public String getSponsorUids(){
		return sponsorUids;
	}

	public void setReviewedUids(String reviewedUids){
		this.reviewedUids=reviewedUids;
	}

	public String getReviewedUids(){
		return reviewedUids;
	}

	public void setCreateTime(Integer createTime){
		this.createTime=createTime;
	}

	public Integer getCreateTime(){
		return createTime;
	}

	@Override
	public String toString() {
		return "committee[" + 
			"id=" + id + 
			", clas_id=" + clasId + 
			", school_id=" + schoolId + 
			", admin_uids=" + adminUids + 
			", leader_uids=" + leaderUids + 
			", sponsor_uids=" + sponsorUids + 
			", reviewed_uids=" + reviewedUids + 
			", create_time=" + createTime + 
			"]";
	}
}

