package com.planc.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import com.planc.utils.CommonUtils;

public class Account {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	private int rowNum = -1;
	
	private int memSeq = -1;
	
	private String memId = null;
	
	private String memName = null;
	
	private String memPwd = null;
	
	private Date createDate = null;
	
	private Date updateDate = null;
	
	public Account() {
	}
	
	public Account(int rowNum, int memSeq, String memId, String memName, String memPwd, Timestamp createDate, Timestamp updateDate) {
		this.rowNum = rowNum;
		this.memSeq = memSeq;
		this.memId = memId;
		this.memName = memName;
		this.memPwd = memPwd;
		this.createDate = CommonUtils.timeStampToDate(createDate);
		this.updateDate = CommonUtils.timeStampToDate(updateDate);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.memSeq);
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
		Account other = (Account) obj;
		return this.memSeq == other.memSeq;
	}
	
	@Override
	public String toString() {
		return this.toString(Math.max(0, this.rowNum));
	}
	
	public String toString(int seq) {
		String className = this.getClass().getSimpleName();
		String str = className + "["+seq+"].memSeq == " + this.memSeq + "\r\n"
				   + className + "["+seq+"].memId == " + this.memId + "\r\n"
				   + className + "["+seq+"].memName == " + this.memName + "\r\n"
				   + className + "["+seq+"].memPwd == " + this.memPwd + "\r\n" 
				   + className + "["+seq+"].createDate == " + CommonUtils.dateToString(this.sdf, this.createDate) + "\r\n"
				   + className + "["+seq+"].updateDate == " + CommonUtils.dateToString(this.sdf, this.updateDate)
				   ;
		return str;
	}

	public int getSeq() {
		return memSeq;
	}

	public void setMemSeq(int memSeq) {
		this.memSeq = memSeq;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemPwd() {
		return memPwd;
	}

	public void setMemPwd(String memPwd) {
		this.memPwd = memPwd;
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

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	
}
