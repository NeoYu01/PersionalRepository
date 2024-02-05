package com.pland.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.pland.utils.CommonUtils;

public class CommonService {
	
	protected String className = this.getClass().getSimpleName();
	
	protected CommonService() {
	}
	
	private <T> List<T> iterableToList(Iterable<T> iterables) {
		return CommonUtils.iterableToList(iterables);
	}
	
	private <T> T optionalToObj(Optional<T> optional) {
		return ((optional.isPresent())?optional.get():null);
	}
	
	protected <T> List<T> findAll(CrudRepository<T,Integer> repository) {
		if(repository==null) {
			return null;
		}
		return this.iterableToList(repository.findAll());
	}
	
	protected <T> T findById(CrudRepository<T,Integer> repository, int id) {
		if(id<=0 
				|| repository==null) {
			return null;
		}
		return this.optionalToObj(repository.findById(new Integer(""+id)));
	}
	
	protected <T> T findById(CrudRepository<T,Integer> repository, Integer id) {
		if(id==null || id.intValue()<=0 
				|| repository==null) {
			return null;
		}
		return this.optionalToObj(repository.findById(id));
	}
	
	protected <T> List<T> findAllByIds(CrudRepository<T,Integer> repository, List<Integer> ids) {
		if(ids==null || ids.size()==0 
				|| repository==null) {
			return null;
		}
		return this.iterableToList(repository.findAllById(ids));
	}
	
}
