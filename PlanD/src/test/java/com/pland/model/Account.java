package com.pland.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import com.pland.utils.CommonUtils;

@Entity
@NamedNativeQueries({
	@NamedNativeQuery(
		name = "Account.findByMemId",
		query = "select "
			  + "MEM_SEQ, MEM_ID, MEM_NAME, MEM_PWD, IS_ADMIN, CREATE_DATE, UPDATE_DATE "
			  + "from MEMBER "
			  + "where MEM_ID = ?1",
		resultClass = Account.class
	)
})
@Table(name="MEMBER")
public class Account {
	
	@Transient
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	@Transient
	private int rowNum = -1;
	
	@Id
	@Column(name="MEM_SEQ")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int memSeq = -1;
	
	@Column(name="MEM_ID")
	private String memId = null;
	
	@Column(name="MEM_NAME")
	private String memName = null;
	
	@Column(name="MEM_PWD")
	private String memPwd = null;
	
	@Column(name="IS_ADMIN")
	private String memAdmin = null;
	
	@Column(name="CREATE_DATE")
	private Date createDate = null;
	
	@Column(name="UPDATE_DATE")
	private Date updateDate = null;
	
	public Account() {
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
				   + className + "["+seq+"].memAdmin == " + this.memAdmin + "\r\n" 
				   + className + "["+seq+"].createDate == " + CommonUtils.dateToString(this.sdf, this.createDate) + "\r\n"
				   + className + "["+seq+"].updateDate == " + CommonUtils.dateToString(this.sdf, this.updateDate)
				   ;
		return str;
	}
	
	public void print() {
		this.print(0);
	}
	
	public void print(int seq) {
		System.out.println(this.toString(seq));
	}
	
	public boolean isAdmin() {
		return "Y".equals(this.memAdmin);
	}
	
	public void appendMemPwd(String pwd) {
		if(StringUtils.isBlank(pwd)) {
			return;
		}
		pwd = pwd.trim();
		if(StringUtils.isBlank(this.memPwd)) {
			this.memPwd = pwd;
		} else {
			this.memPwd += pwd;
		}
	}

	public int getMemSeq() {
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

	public String getMemAdmin() {
		return memAdmin;
	}

	public void setMemAdmin(String memAdmin) {
		this.memAdmin = memAdmin;
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
	
}
