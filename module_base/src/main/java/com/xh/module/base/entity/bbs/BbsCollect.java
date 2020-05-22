package com.xh.module.base.entity.bbs;

/**
 * table name:  bbs_collect
 * author name: 朱华清
 * create time: 2020-05-20 09:42:39
 */ 
public class BbsCollect{

	private Long artId;
	private Long uid;

	public BbsCollect() {
		super();
	}

	public BbsCollect(Long artId,Long uid) {
		this.artId=artId;
		this.uid=uid;
	}

	public void setArtId(Long artId){
		this.artId=artId;
	}

	public Long getArtId(){
		return artId;
	}

	public void setUid(Long uid){
		this.uid=uid;
	}

	public Long getUid(){
		return uid;
	}

	@Override
	public String toString() {
		return "bbs_collect[" + 
			"art_id=" + artId + 
			", uid=" + uid + 
			"]";
	}
}

