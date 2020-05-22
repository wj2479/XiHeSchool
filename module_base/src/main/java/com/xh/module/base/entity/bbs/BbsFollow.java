package com.xh.module.base.entity.bbs;

/**
 * table name:  bbs_follow
 * author name: 朱华清
 * create time: 2020-05-20 09:42:40
 */ 
public class BbsFollow{

	private Long followId;
	private Long uid;

	public BbsFollow() {
		super();
	}

	public BbsFollow(Long followId,Long uid) {
		this.followId=followId;
		this.uid=uid;
	}

	public void setFollowId(Long followId){
		this.followId=followId;
	}

	public Long getFollowId(){
		return followId;
	}

	public void setUid(Long uid){
		this.uid=uid;
	}

	public Long getUid(){
		return uid;
	}

	@Override
	public String toString() {
		return "bbs_follow[" + 
			"follow_id=" + followId + 
			", uid=" + uid + 
			"]";
	}
}

