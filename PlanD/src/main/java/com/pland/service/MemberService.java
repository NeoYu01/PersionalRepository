package com.pland.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pland.model.Login;
import com.pland.model.Member;
import com.pland.repository.MemberRepository;

@Service
public class MemberService extends CommonService {
	
	@Autowired
	private MemberRepository repository = null;
	
	public MemberService() {
		super();
	}
	
	public Login loginCheck(Login login) throws Throwable {
		try {
			if(login!=null) {
				login.resetParam();
			}
			System.out.println(super.className+".loginCheck.login: \r\n" + login);
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
			Member member = this.repository.findByMemId(loginId);
			if(member!=null) {
				System.out.println(super.className+".loginCheck.member: \r\n" + member);
				login.setName(member.getName());
				if(!StringUtils.equals(login.getPwd(), member.getPwd())) {
					errMsg = "密碼錯誤 !!";
				}
			} else {
				System.out.println(super.className+".loginCheck.member: null");
				errMsg = "查無此帳號 !!";
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
			List<Member> members = super.findAll(this.repository);
			if(members!=null && members.size()>0) {
				System.out.println(super.className+".listMembers.members: ");
				members.forEach(System.out::println);
			} else {
				System.out.println(super.className+".listMembers.members: null");
			}
			return members;
		} catch(Throwable e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public List<Integer> listMemSeqs() throws Throwable {
		try {
			return this.repository.findMemSeqs();
		} catch(Throwable e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public List<Member> queryAll() throws Throwable {
		try {
			List<Member> members = super.findAll(this.repository);
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
			Member member = this.repository.findByMemId(memId);
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
			Member member = super.findById(this.repository, memSeq);
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
	
	public List<Member> queryByPageRow(int sIndex, int eIndex, List<Integer> seqs) throws Throwable {
		try {
			if(sIndex<=0 || eIndex<=0 || sIndex>=eIndex
					|| seqs==null ||seqs.size()==0) {
				return null;
			}
			List<Integer> memSeqs = new ArrayList<Integer>();
			for(int i=sIndex; i<=eIndex; i++) {
				if(i>seqs.size()) {
					break;
				}
				Integer memSeq = (Integer) seqs.get(i-1);
				if(memSeq==null || memSeq.intValue()<=0) {
					continue;
				}
				memSeqs.add(memSeq);
			}
			return super.findAllByIds(this.repository, memSeqs);
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
				if(mem!=null) {
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
	
	public void insert(Member member) throws Throwable {
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
			member.setCreateDate(new Date());
			member.setUpdateDate(null);
			this.repository.save(member);
		} catch(Throwable e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void deleteBySeq(int memSeq) throws Throwable {
		try {
			System.out.println(super.className+".deleteBySeq.memSeq: " + memSeq);
			if(memSeq<=0) {
				throw new Throwable("memSeq lass than 0 !!");
			}
			this.repository.deleteById(new Integer(memSeq));
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
				member.setUpdateDate(new Date());
				this.repository.save(member);
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
