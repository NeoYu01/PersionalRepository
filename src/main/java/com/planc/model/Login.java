package com.planc.model;

import java.util.Objects;

public class Login {
	
	private String id = null;
	
	private String name = null;
	
	private String pwd = null;
	
	private String errMeg = null;
	
	private boolean memberExist = true;
	
	public Login() {
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.id);
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
		Login other = (Login) obj;
		return Objects.equals(this.id, other.id);
	}
	
	@Override
	public String toString() {
		String className = this.getClass().getSimpleName();
		String str = className + ".id == " + this.id + "\r\n"
				   + className + ".name == " + this.name + "\r\n"
				   + className + ".pwd == " + this.pwd + "\r\n"
				   + className + ".memberExist == " + this.memberExist + "\r\n"
				   + className + ".errMeg == " + this.errMeg
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
