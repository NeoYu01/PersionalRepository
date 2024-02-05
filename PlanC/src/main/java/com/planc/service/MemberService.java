package com.planc.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planc.dao.MemberDao;
import com.planc.model.Member;

@Service
public class MemberService extends CommonService {
	
	@Autowired
	private MemberDao memberDao = null;
	
	public MemberService() {
		super();
	}
	
	public List<Member> queryAll() throws Throwable {
		try {
			List<Member> members = this.memberDao.queryAll();
			if(members!=null && members.size()>0) {
				System.out.println(super.className+".queryAll.members: ");
				members.forEach(System.out::println);
			} else {
				System.out.println(super.className+".queryAll.members: null");
			}
			return members;
		} catch(Throwable e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Member queryById(String memId) throws Throwable {
		try {
			System.out.println(super.className+".queryById.memId: " + memId);
			if(StringUtils.isBlank(memId)) {
				throw new Throwable("Empty memId !!");
			}
			Member member = this.memberDao.queryById(memId);
			if(member!=null) {
				System.out.println(super.className+".queryById.member: \r\n" + member);
			} else {
				System.out.println(super.className+".queryById.member: null");
			}
			return member;
		} catch(Throwable e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Member queryBySeq(int memSeq) throws Throwable {
		try {
			System.out.println(super.className+".queryBySeq.memSeq: " + memSeq);
			if(memSeq<=0) {
				throw new Throwable("memSeq lass than 0 !!");
			}
			Member member = this.memberDao.queryBySeq(memSeq);
			if(member!=null) {
				System.out.println(super.className+".queryBySeq.member: \r\n" + member);
			} else {
				System.out.println(super.className+".queryBySeq.member: null");
			}
			return member;
		} catch(Throwable e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Member isIdExist(Member member) throws Throwable {
		try {
			if(member==null) {
				return member;
			}
			member.resetParam();
			String memId = member.getId();
			String memName = member.getName();
			String memPwd = member.getPwd();
			String errMsg = null;
			if(StringUtils.isBlank(memId)) {
				errMsg = "請輸入會員ID !!";
			} else if(StringUtils.isBlank(memName)) {
				errMsg = "請輸入會員名稱 !!";
			} else if(StringUtils.isBlank(memPwd)) {
				errMsg = "請輸入會員密碼 !!";
			}
			if(StringUtils.isBlank(errMsg)) {
				Member mem = this.queryById(memId);
				member.setMemberExist(mem!=null);
				if(member.isMemberExist()) {
					member.setErrMeg("會員ID已存在 !!");
				}
			} else {
				member.setErrMeg(errMsg);
			}
			return member;
		} catch(Throwable e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public int insert(Member member) throws Throwable {
		try {
			System.out.println(super.className+".insert.member: \r\n" + member);
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
			return this.memberDao.insert(member);
		} catch(Throwable e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public int delete(Member member) throws Throwable {
		try {
			String memId = ((member!=null)?member.getId():null);
			System.out.println(super.className+".update.memId == " + memId);
			if(StringUtils.isBlank(memId)) {
				throw new Throwable("Empty memId !!");
			}
			return this.memberDao.delete(member);
		} catch(Throwable e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public int deleteBySeq(int memSeq) throws Throwable {
		try {
			System.out.println(super.className+".deleteBySeq.memSeq: " + memSeq);
			if(memSeq<=0) {
				throw new Throwable("memSeq lass than 0 !!");
			}
			return this.memberDao.deleteBySeq(memSeq);
		} catch(Throwable e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Member update(Member member) throws Throwable {
		try {
			if(member==null) {
				return member;
			}
			member.resetParam();
			String memId = member.getId();
			String memName = member.getName();
			String memPwd = member.getPwd();
			String errMsg = null;
			if(StringUtils.isBlank(memId)) {
				errMsg = "請輸入會員ID !!";
			} else if(StringUtils.isBlank(memName)) {
				errMsg = "請輸入會員名稱 !!";
			} else if(StringUtils.isBlank(memPwd)) {
				errMsg = "請輸入會員密碼 !!";
			}
			if(StringUtils.isBlank(errMsg)) {
				this.memberDao.update(member);
			} else {
				member.setErrMeg(errMsg);
			}
			return member;
		} catch(Throwable e) {
			e.printStackTrace();
			throw e;
		}
	}
	
}
