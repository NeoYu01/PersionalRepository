package com.planc.model;

import java.util.Date;
import java.util.Objects;

import com.planc.utils.CommonUtils;

public class Member {
	
	private int rowNum = -1;
	
	private long seq = -1;
	
	private String id = null;
	
	private String name = null;
	
	private String pwd = null;
	
	private Date createDate = null;
	
	private Date updateDate = null;
	
	private String errMeg = null;
	
	private boolean memberExist = true;
	
	public Member() {
	}
	
	public Member(int rowNum) {
		this();
		this.rowNum = rowNum;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.seq);
	}

	@Override
	public boolean equals(Object obj) {
		if(this==obj) {
			return true;
		}
		if(obj==null 
				|| this.getClass()!=obj.getClass()) {
			return false;
		}
		Member other = (Member) obj;
		return this.seq == other.seq;
	}
	
	@Override
	public String toString() {
		return this.toString(Math.max(0, this.rowNum));
	}
	
	public String toString(int seq) {
		String className = this.getClass().getSimpleName();
		String str = className + "["+seq+"].memSeq == " + this.seq + "\r\n"
				   + className + "["+seq+"].memId == " + this.id + "\r\n"
				   + className + "["+seq+"].memName == " + this.name + "\r\n"
				   + className + "["+seq+"].memPwd == " + this.pwd + "\r\n" 
				   + className + "["+seq+"].createDate == " + CommonUtils.dateToString(this.createDate) + "\r\n"
				   + className + "["+seq+"].updateDate == " + CommonUtils.dateToString(this.updateDate) + "\r\n"
				   + className + "["+seq+"].memberExist == " + this.memberExist + "\r\n"
				   + className + "["+seq+"].errMeg == " + this.errMeg
				   ;
		return str;
	}
	
	public void print() {
		System.out.println(this);
	}
	
	public void resetParam() {
		this.errMeg = null;
		this.memberExist = true;
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getErrMeg() {
		return errMeg;
	}

	public void setErrMeg(String errMeg) {
		this.errMeg = errMeg;
	}

	public boolean isMemberExist() {
		return memberExist;
	}

	public void setMemberExist(boolean memberExist) {
		this.memberExist = memberExist;
	}

}