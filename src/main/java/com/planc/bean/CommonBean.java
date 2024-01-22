package com.planc.bean;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

public class CommonBean {
	
	protected final String PAGE_PARAMETER_ERRMSG = "errMsg";
	protected final String PAGE_PARAMETER_BACKPAGE = "backPage";
	
	protected final String MEMBER_ID_DOMAIN = "@planc.com";
	
	protected CommonBean() {
	}
	
	protected HttpSession getSession(HttpServletRequest request) {
		return ((request!=null)?request.getSession():null);
	}
	
	protected Object getSessionAttr(HttpServletRequest request, String name) {
		if(StringUtils.isBlank(name)) {
			return null;
		}
		HttpSession session = this.getSession(request);
		return ((session!=null)?session.getAttribute(name):null);
	}
	
	protected void setSessionAttr(HttpServletRequest request, String name, Object obj) {
		if(StringUtils.isBlank(name)) {
			return;
		}
		HttpSession session = this.getSession(request);
		if(session!=null) {
			session.setAttribute(name, obj);
		}
	}
	
	protected void setSessionAttr(HttpServletRequest request, Map<String,Object> dataMap) {
		if(dataMap==null || dataMap.size()==0) {
			return;
		}
		HttpSession session = this.getSession(request);
		if(session!=null) {
			String[] keys = dataMap.keySet().toArray(new String[0]);
			for(String key : keys) {
				if(StringUtils.isBlank(key)) {
					continue;
				}
				session.setAttribute(key, dataMap.get(key));
			}
		}
	}
	
	protected void setSessionAttr(HttpServletRequest request, ModelMap modelMap) {
		if(modelMap==null || modelMap.size()==0) {
			return;
		}
		HttpSession session = this.getSession(request);
		if(session!=null) {
			String[] keys = modelMap.keySet().toArray(new String[0]);
			for(String key : keys) {
				if(StringUtils.isBlank(key)) {
					continue;
				}
				session.setAttribute(key, modelMap.get(key));
			}
		}
	}
	
	protected ModelAndView addModeParam(String path, Object value) {
		if(StringUtils.isBlank(path)) {
			return null;
		}
		ModelAndView model = new ModelAndView(path);
		model.addObject(value);
		return model;
	}
	
	protected ModelAndView addModeParam(String path, String name, Object value) {
		if(StringUtils.isBlank(path) 
				|| StringUtils.isBlank(name)) {
			return null;
		}
		ModelAndView model = new ModelAndView(path);
		model.addObject(name, value);
		return model;
	}
	
	protected String genMemId(String memId) {
		if(StringUtils.isBlank(memId)) {
			return memId;
		}
		return memId.trim().toLowerCase().replace(MEMBER_ID_DOMAIN, "") + MEMBER_ID_DOMAIN;
	}

}
