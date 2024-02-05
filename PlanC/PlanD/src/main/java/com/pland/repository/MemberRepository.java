package com.pland.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.pland.model.Member;

public interface MemberRepository extends CrudRepository<Member,Integer> {
	
	public Member findByMemId(String memeId);
	
	@Query(value="select MEM_SEQ from MEMBER order by MEM_SEQ", nativeQuery=true)
	public List<Integer> findMemSeqs();
	
	@Modifying
	@Transactional
	@Query(value="delete from MEMBER where MEM_ID = ?1", nativeQuery=true)
	public void delteByMemId(String memId);
	
}
