package com.planc.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.planc.mapper.MemberRowMapper;
import com.planc.model.Member;
import com.planc.utils.CommonUtils;

@Repository
public class MemberDao {
	
	@Autowired
	private JdbcTemplate template = null;
	
	public List<Member> queryAll() throws Throwable {
		try {
			String sql = "select "
					   + "MEMBER_SEQ, MEMBER_ID, MEMBER_NAME, MEMBER_PWD, CREATE_DATE, UPDATE_DATE "
					   + "from MEMBER"
					   ;
			return this.template.query(sql, new MemberRowMapper());
		} catch(Throwable e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Member queryById(String memId) throws Throwable {
		try {
			if(StringUtils.isBlank(memId)) {
				throw new Throwable("Empty memId !!");
			}
			String sql = "select "
					   + "MEMBER_SEQ, MEMBER_ID, MEMBER_NAME, MEMBER_PWD, CREATE_DATE, UPDATE_DATE "
					   + "from MEMBER "
					   + "where MEMBER_ID = ?"
					   ;
			List<Object> valueList = new ArrayList<Object>();
			valueList.add(memId);
			List<Member> dataList = this.template.query(sql, new MemberRowMapper(), valueList.toArray());
			if(dataList!=null && dataList.size()>0) {
				return (Member) dataList.get(0);
			}
			return null;
		} catch(Throwable e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Member queryBySeq(int memSeq) throws Throwable {
		try {
			if(memSeq<=0) {
				throw new Throwable("memSeq lass than 0 !!");
			}
			String sql = "select "
					   + "MEMBER_SEQ, MEMBER_ID, MEMBER_NAME, MEMBER_PWD, CREATE_DATE, UPDATE_DATE "
					   + "from MEMBER "
					   + "where MEMBER_SEQ = ?"
					   ;
			List<Object> valueList = new ArrayList<Object>();
			valueList.add(new Integer(memSeq));
			List<Member> dataList = this.template.query(sql, new MemberRowMapper(), valueList.toArray());
			if(dataList!=null && dataList.size()>0) {
				return (Member) dataList.get(0);
			}
			return null;
		} catch(Throwable e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public int insert(Member member) throws Throwable {
		try {
			String memId = ((member!=null)?member.getId():null);
			String memName = ((member!=null)?member.getName():null);
			String memPwd = ((member!=null)?member.getPwd():null);
			if(StringUtils.isBlank(memId)) {
				throw new Throwable("Empty memId !!");
			} else if(StringUtils.isBlank(memName)) {
				throw new Throwable("Empty memName !!");
			} else if(StringUtils.isBlank(memPwd)) {
				throw new Throwable("Empty memPwd !!");
			}
			String sql = "insert into MEMBER ( "
					   + "MEMBER_ID, MEMBER_NAME, MEMBER_PWD, CREATE_DATE "
					   + ") values ( "
					   + "?, ?, ?, ? "
					   + ")"
					   ;
			List<Object> valueList = new ArrayList<Object>();
			valueList.add(memId);
			valueList.add(memName);
			valueList.add(memPwd);
			valueList.add(CommonUtils.findCurrentTimestamp());
			return this.template.update(sql, valueList.toArray());
		} catch(Throwable e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public int delete(Member member) throws Throwable {
		try {
			String memId = ((member!=null)?member.getId():null);
			if(StringUtils.isBlank(memId)) {
				throw new Throwable("Empty memId !!");
			}
			String sql = "delete from MEMBER where MEMBER_ID = ?";
			List<Object> valueList = new ArrayList<Object>();
			valueList.add(memId);
			return this.template.update(sql, valueList.toArray());
		} catch(Throwable e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public int deleteBySeq(int memSeq) throws Throwable {
		try {
			if(memSeq<=0) {
				throw new Throwable("memSeq lass than 0 !!");
			}
			String sql = "delete from MEMBER where MEMBER_SEQ = ?";
			List<Object> valueList = new ArrayList<Object>();
			valueList.add(new Integer(memSeq));
			return this.template.update(sql, valueList.toArray());
		} catch(Throwable e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public int update(Member member) throws Throwable {
		try {
			String memId = ((member!=null)?member.getId():null);
			String memName = ((member!=null)?member.getName():null);
			String memPwd = ((member!=null)?member.getPwd():null);
			if(StringUtils.isBlank(memId)) {
				throw new Throwable("Empty memId !!");
			}
			boolean isEmptyName = StringUtils.isBlank(memName);
			boolean isEmptyPwd = StringUtils.isBlank(memPwd);
			String sql = "update MEMBER set "
					   + ((!isEmptyName)?"MEMBER_NAME=?, ":"")
					   + ((!isEmptyPwd)?"MEMBER_PWD=?, ":"")
					   + "UPDATE_DATE=? "
					   + "where MEMBER_SEQ = ?"
					   ;
			List<Object> valueList = new ArrayList<Object>();
			if(!isEmptyName) {
				valueList.add(memName);
			}
			if(!isEmptyPwd) {
				valueList.add(memPwd);
			}
			valueList.add(CommonUtils.findCurrentTimestamp());
			valueList.add(Long.parseLong(""+member.getSeq()));
			return this.template.update(sql, valueList.toArray());
		} catch(Throwable e) {
			e.printStackTrace();
			throw e;
		}
	}
	
}
