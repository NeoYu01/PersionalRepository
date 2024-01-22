package com.planc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.planc.model.Account;
import com.planc.utils.CommonUtils;

@SpringBootApplication
public class AccessDBTest implements CommandLineRunner {

	@Autowired
	private JdbcTemplate template = null;
	
	public static void main(String[] args) {
		SpringApplication.run(AccessDBTest.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.setProperty("hsqldb.method_class_names", "net.ucanaccess.*");

//		this.queryAccount();
		
//		this.insertAccount("stevenhou@planb.com", "Steven Hou", "846@wed");
		
//		this.updateAccount("stevenhou@planb.com");
		
//		this.deleteAccount("stevenhou@planb.com");
		
	}

	public void queryAccount() {
		List<Account> accountList = this.template.query(
				"select MEMBER_SEQ, MEMBER_ID, MEMBER_NAME, MEMBER_PWD, CREATE_DATE, UPDATE_DATE from MEMBER",
				new RowMapper<Account>() {
					@Override
					public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
						return new Account(
									rowNum,
									rs.getInt("MEMBER_SEQ"), 
									rs.getString("MEMBER_ID"),
									rs.getString("MEMBER_NAME"), 
									rs.getString("MEMBER_PWD"), 
									rs.getTimestamp("CREATE_DATE"),
									rs.getTimestamp("UPDATE_DATE"));
					}
				});
		accountList.forEach(System.out::println);
	}
	
	public void insertAccount(String memId, String memName, String memPwd) {
		if(StringUtils.isBlank(memId) 
				|| StringUtils.isBlank(memName) 
				|| StringUtils.isBlank(memPwd)) {
			return;
		}
		String sql = "insert into MEMBER ( MEMBER_ID, MEMBER_NAME, MEMBER_PWD, CREATE_DATE ) values ( ?, ?, ?, ? )";
		this.template.update(sql, memId, memName, memPwd, CommonUtils.findCurrentTimestamp());
		System.out.println("insertAccount Success !!");
		this.queryAccount();
	}
	
	public void deleteAccount(String memId) {
		if(StringUtils.isBlank(memId)) {
			return;
		}
		String sql = "delete from MEMBER where MEMBER_ID = ?";
		this.template.update(sql, memId);
		System.out.println("deleteAccount Success !!");
		this.queryAccount();
	}
	
	public void updateAccount(String memId) {
		if(StringUtils.isBlank(memId)) {
			return;
		}
		String sql = "update MEMBER set MEMBER_NAME=?, UPDATE_DATE=? where MEMBER_ID = ?";
		this.template.update(sql, "updateTest", CommonUtils.findCurrentTimestamp(), memId);
		System.out.println("updateAccount Success !!");
		this.queryAccount();
	}
	
}
