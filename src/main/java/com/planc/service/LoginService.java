package com.planc.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planc.dao.LoginDao;
import com.planc.model.Login;
import com.planc.model.Member;

@Service
public class LoginService extends CommonService {
	
	@Autowired
	private LoginDao loginDao = null;
	
	public LoginService() {
		super();
	}
	
	public Login loginCheck(Login login) throws Throwable {
		try {
			if(login!=null) {
				login.resetParam();
			}
			System.out.println(super.clasName+".loginCheck.login: \r\n" + login);
			String loginId = ((login!=null)?login.getId():null);
			String loginPwd = ((login!=null)?login.getPwd():null);
			String errMsg = null;
			if(StringUtils.isBlank(loginId)) {
				errMsg = "請輸入帳號 !!";
			} else if(StringUtils.isBlank(loginPwd)) {
				errMsg = "請輸入密碼 !!";
			}
			if(!StringUtils.isBlank(errMsg)) {
				login.setErrMeg(errMsg);
				return login;
			}
			Member member = this.loginDao.queryById(loginId);
			if(member!=null) {
				System.out.println(super.clasName+".loginCheck.member: \r\n" + member);
				login.setName(member.getName());
				if(!StringUtils.equals(login.getPwd(), member.getPwd())) {
					errMsg = "密碼錯誤 !!";
				}
			} else {
				System.out.println(super.clasName+".loginCheck.member: null");
				errMsg = "查無此帳號 !!";
				login.setMemberExist(false);
			}
			login.setErrMeg(errMsg);
			return login;
		} catch(Throwable e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public List<Member> listMembers() throws Throwable {
		try {
			List<Member> members = this.loginDao.queryAll();
			if(members!=null && members.size()>0) {
				System.out.println(super.clasName+".listMembers.members: ");
				members.forEach(System.out::println);
			} else {
				System.out.println(super.clasName+".listMembers.members: null");
			}
			return members;
		} catch(Throwable e) {
			e.printStackTrace();
			throw e;
		}
	}
	
}
