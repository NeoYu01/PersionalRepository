package com.pland.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.pland.model.PageCount;

public class CommonBean {
	
	protected final String PAGE_PARAMETER_ERRMSG = "errMsg";
	protected final String PAGE_PARAMETER_BACKPAGE = "backPage";
	
	protected final int PAGE_PER_ROWS = 10;
	
	protected final String MEMBER_ID_DOMAIN = "@pland.com";
	
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
	
	protected List<PageCount> genPageCounts(int rowIndex, int maxCount) {
		try {
			List<PageCount> pageCounts = new ArrayList<PageCount>();
			pageCounts.add(new PageCount("<<", rowIndex, PAGE_PER_ROWS, maxCount));
			pageCounts.add(new PageCount("<", rowIndex-((rowIndex>maxCount)?PAGE_PER_ROWS:0), PAGE_PER_ROWS, maxCount));
			int step = Math.max(1, Math.min(5, (maxCount/PAGE_PER_ROWS)+((maxCount%PAGE_PER_ROWS==0)?0:1)));
			for(int i=1; i<=step; i++) {
				pageCounts.add(new PageCount(((rowIndex>maxCount)?rowIndex-PAGE_PER_ROWS:rowIndex+((i-1)*PAGE_PER_ROWS)), PAGE_PER_ROWS, maxCount));
				int nextRowIndex = rowIndex + (i*PAGE_PER_ROWS);
				if(nextRowIndex>maxCount) {
					break;
				}
			}
			pageCounts.add(new PageCount(">", rowIndex, PAGE_PER_ROWS, maxCount));
			pageCounts.add(new PageCount(">>", rowIndex, PAGE_PER_ROWS, maxCount));
			return pageCounts;
		} catch(Throwable e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	protected int findCurrentRowIndex(HttpServletRequest request) {
		try {
			List<PageCount> pageCounts = (List<PageCount>) this.getSessionAttr(request, "pageCounts");
			int sIndex = 1;
			if(pageCounts!=null && pageCounts.size()>0) {
				for(PageCount pageCount : pageCounts) {
					String label = ((pageCount!=null)?pageCount.getLabel():null);
					if(!StringUtils.isBlank(label) 
							&& ",<<,<,>,>>,".indexOf(","+label+",")==-1) {
						sIndex = pageCount.getValue();
						break;
					}
				}
			}
			return sIndex;
		} catch(Throwable e) {
			e.printStackTrace();
		}
		return -1;
	}

}