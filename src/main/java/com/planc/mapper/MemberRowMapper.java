package com.planc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.planc.model.Member;
import com.planc.utils.CommonUtils;

public class MemberRowMapper implements RowMapper<Member> {
	
	public MemberRowMapper() {
	}

	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		if(rs==null || rs.isClosed()) {
			return null;
		}
		Member member = new Member(rowNum);
		member.setSeq(rs.getInt("MEMBER_SEQ"));
		member.setId(rs.getString("MEMBER_ID"));
		member.setName(rs.getString("MEMBER_NAME"));
		member.setPwd(rs.getString("MEMBER_PWD"));
		member.setCreateDate(CommonUtils.timeStampToDate(rs.getTimestamp("CREATE_DATE")));
		member.setUpdateDate(CommonUtils.timeStampToDate(rs.getTimestamp("UPDATE_DATE")));
		return member;
	}
	
}
