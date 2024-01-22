package com.planc.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.planc.mapper.LoginRowMapper;
import com.planc.mapper.MemberRowMapper;
import com.planc.model.Member;

@Repository
public class LoginDao {
	
	@Autowired
	private JdbcTemplate template = null;
	
	public Member queryById(String loginId) throws Throwable {
		try {
			if(StringUtils.isBlank(loginId)) {
				throw new Throwable("Empty loginId !!");
			}
			String sql = "select "
					   + "MEMBER_ID, MEMBER_NAME, MEMBER_PWD "
					   + "from MEMBER "
					   + "where MEMBER_ID = ?"
					   ;
			List<Object> valueList = new ArrayList<Object>();
			valueList.add(loginId);
			List<Member> dataList = this.template.query(sql, new LoginRowMapper(), valueList.toArray());
			if(dataList!=null && dataList.size()>0) {
				return (Member) dataList.get(0);
			}
			return null;
		} catch(Throwable e) {
			e.printStackTrace();
			throw e;
		}
	}
	
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
	
}
