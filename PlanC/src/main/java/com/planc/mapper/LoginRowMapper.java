package com.planc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.planc.model.Member;

public class LoginRowMapper implements RowMapper<Member> {
	
	public LoginRowMapper() {
	}

	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		if(rs==null || rs.isClosed()) {
			return null;
		}
		Member member = new Member(rowNum);
		member.setId(rs.getString("MEMBER_ID"));
		member.setName(rs.getString("MEMBER_NAME"));
		member.setPwd(rs.getString("MEMBER_PWD"));
		return member;
	}

}
