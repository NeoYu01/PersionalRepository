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

import com.pland.utils.CommonUtils;

@Entity
@NamedNativeQueries({
	@NamedNativeQuery(
		name = "Member.findByMemId",
		query = "select "
			  + "MEM_SEQ, MEM_ID, MEM_NAME, MEM_PWD, IS_ADMIN, CREATE_DATE, UPDATE_DATE "
			  + "from MEMBER "
			  + "where MEM_ID = ?1",
		resultClass = Member.class
	)
})
@Table(name="MEMBER")
public class Member {
	
	@Transient
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	@Transient
	private int rowNum = -1;
	
	@Id
	@Column(name="MEM_SEQ")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int seq = -1;
	
	@Column(name="MEM_ID")
	private String id = null;
	
	@Column(name="MEM_NAME")
	private String name = null;
	
	@Column(name="MEM_PWD")
	private String pwd = null;
	
	@Column(name="IS_ADMIN")
	private String admin = null;
	
	@Column(name="CREATE_DATE")
	private Date createDate = null;
	
	@Column(name="UPDATE_DATE")
	private Date updateDate = null;
	
	@Transient
	private String errMeg = null;
	
	public Member() {
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
				   + className + "["+seq+"].memAdmin == " + this.admin + "\r\n" 
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
		return "Y".equals(this.admin);
	}
	
	public void resetParam() {
		this.errMeg = null;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
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

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
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
	
}