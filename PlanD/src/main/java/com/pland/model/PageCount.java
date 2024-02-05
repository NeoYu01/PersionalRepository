package com.pland.model;

import java.util.Objects;

public class PageCount {
	
	private String label = null;
	
	private int value = -1;
	
	private String url = null;
	
	public PageCount() {
	}
	
	public PageCount(int value, int pageStep, int maxCount) {
		this(null, value, pageStep, maxCount);
	}
	
	public PageCount(String label, int value, int pageStep, int maxCount) {
		this();
		this.label = label;
		if("<<".equals(this.label)) {
			this.value = 1;
		} else if("<".equals(this.label)) {
			this.value = Math.max(1, value-pageStep);
		} else if(">".equals(this.label)) {
			if((value+pageStep)>=maxCount) {
				this.value = (((maxCount-((maxCount%pageStep==0)?1:0))/pageStep)*pageStep)+1;
			} else {
				this.value = value + pageStep;
			}
		} else if(">>".equals(this.label)) {
			this.value = (((maxCount-((maxCount%pageStep==0)?1:0))/pageStep)*pageStep)+1;
		} else {
			this.label = "" + ((value/pageStep)+1);
			this.value = value;
		}
		this.url = "/chgMemberPage?rowIndex=" + this.value;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.value);
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
		PageCount other = (PageCount) obj;
		return Objects.equals(this.value, other.value);
	}
	
	@Override
	public String toString() {
		return this.toString(0);
	}
	
	public String toString(int seq) {
		String className = this.getClass().getSimpleName();
		String str = className + "["+seq+"].label == " + this.label + "\r\n"
				   + className + "["+seq+"].value == " + this.value + "\r\n"
				   + className + "["+seq+"].url == " + this.url + "\r\n"
				   ;
		return str;
	}
	
	public void print() {
		this.print(0);
	}
	
	public void print(int seq) {
		System.out.println(this.toString(seq));
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
