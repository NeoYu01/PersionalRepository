package com.pland.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pland.model.Account;
import com.pland.repository.AccountRepository;
import com.pland.utils.CommonUtils;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository repository = null;
	
	public List<Account> findAll() {
		return CommonUtils.iterableToList(this.repository.findAll());
	}
	
	public Account findBySeq(int seq) {
		if(seq<=0) {
			return null;
		}
		return CommonUtils.optionalToObj(this.repository.findById(new Integer(""+seq)));
	}
	
	public Account findByMemId(String memId) {
		if(StringUtils.isBlank(memId)) {
			return null;
		}
		return this.repository.findByMemId(memId);
	}
	
	public List<Account> findAllBySeqs(int... seqs) {
		if(seqs==null || seqs.length==0) {
			return null;
		}
		List<Integer> ids = new ArrayList<Integer>();
		for(int seq : seqs) {
			if(seq<=0) {
				continue;
			}
			ids.add(new Integer(""+seq));
		}
		return ((ids==null || ids.size()==0)?null:CommonUtils.iterableToList(this.repository.findAllById(ids)));
	}
	
	public List<Integer> findMemSeqs() {
		return this.repository.findMemSeqs();
	}
	
	public Account insert(Account account) {
		if(account!=null) {
			account.setCreateDate(new Date());
			account.setUpdateDate(null);
			return this.repository.save(account);
		}
		return account;
	}
	
	public List<Account> insertAll(List<Account> accounts) {
		if(accounts==null || accounts.size()==0) {
			return accounts;
		}
		return CommonUtils.iterableToList(this.repository.saveAll(accounts));
	}
	
	public Account update(Account account) {
		if(account!=null) {
			account.setUpdateDate(new Date());
			return this.repository.save(account);
		}
		return account;
	}
	
	public void updatePwd(String pwd, String memId) {
		if(StringUtils.isBlank(pwd) 
				|| StringUtils.isBlank(memId)) {
			return;
		}
		this.repository.updatePwdByMemId(pwd, memId);
	}
	
	public void deleteAll() {
		this.repository.deleteAll();
	}
	
	//TODO
	public void delete(Account account) {
		if(account!=null) {
			this.repository.delete(account);
		}
	}

	//TODO
	public void delete(List<Account> accounts) {
		if(accounts!=null && accounts.size()>0) {
			this.repository.deleteAll(accounts);
		}
	}
	
	public void deleteBySeq(int seq) {
		if(seq>0) {
			this.repository.deleteById(new Integer(""+seq));
		}
	}
	
	public void delteByMemId(String memId) {
		if(!StringUtils.isBlank(memId)) {
			this.repository.delteByMemId(memId);
		}
	}
	
	public boolean exists(int seq) {
		return seq>0 && this.repository.existsById(new Integer(""+seq));
	}
	
	public long count() {
		return this.repository.count();
	}

}
