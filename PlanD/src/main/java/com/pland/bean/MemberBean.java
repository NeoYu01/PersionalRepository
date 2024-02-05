package com.pland.bean;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pland.model.Member;
import com.pland.service.MemberService;

@Controller
public class MemberBean extends CommonBean {
	
	@Autowired
	private MemberService service = null;
	
	public MemberBean() {
		super();
	}
	
	@GetMapping("/addMember")
    public ModelAndView showForm() {
        return super.addModeParam("member/AddMember", new Member());
    }
	
	@PostMapping("/addMember")
    public ModelAndView submitForm(HttpServletRequest request, @ModelAttribute("member") Member member, ModelMap model) {
		try {
			member.setId(super.genMemId(member.getId()));
			member = this.service.isIdExist(member);
			String errMsg = ((member!=null)?member.getErrMeg():null);
			if(!StringUtils.isBlank(errMsg)) {
				super.setSessionAttr(request, "oriMember", member);
				model.put(PAGE_PARAMETER_ERRMSG, errMsg);
				model.put(PAGE_PARAMETER_BACKPAGE, "/reAddMember");
				return super.addModeParam("ErrorPage", model);
			}
			this.service.insert(member);
			model.put(PAGE_PARAMETER_ERRMSG, "註冊成功 !! 請重新登入 !!");
			model.put(PAGE_PARAMETER_BACKPAGE, "/");
			return super.addModeParam("ErrorPage", model);
		} catch(Throwable e) {
			e.printStackTrace();
		}
		return null;
    }
	
	@GetMapping("/editMember")
	public ModelAndView editForm(@RequestParam("seq") String memSeq) {
		try {
			return super.addModeParam("member/EditMember", "member", this.service.queryBySeq(Integer.parseInt(memSeq)));
		} catch(Throwable e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/deleteMember")
	public ModelAndView deleteForm(HttpServletRequest request, @RequestParam("seq") String memSeq) {
		try {
			this.service.deleteBySeq(Integer.parseInt(memSeq));
			List<Integer> memSeqs = this.service.listMemSeqs();
			int sIndex = super.findCurrentRowIndex(request);
			super.setSessionAttr(request, "pageCounts", super.genPageCounts(sIndex, ((memSeqs!=null)?memSeqs.size():0)));
			//Index had changed to get new index
			sIndex = super.findCurrentRowIndex(request);
			return super.addModeParam("/member/ListMembers", "members", this.service.queryByPageRow(sIndex, sIndex+PAGE_PER_ROWS-1, memSeqs));
		} catch(Throwable e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@PostMapping("/updateMember")
    public ModelAndView updateForm(HttpServletRequest request, @ModelAttribute("member") Member member, ModelMap model) {
		try {
			member = this.service.update(member);
			String errMsg = ((member!=null)?member.getErrMeg():null);
			if(!StringUtils.isBlank(errMsg)) {
				super.setSessionAttr(request, "oriMember", member);
				model.put(PAGE_PARAMETER_ERRMSG, errMsg);
				model.put(PAGE_PARAMETER_BACKPAGE, "/reEditMember");
				return super.addModeParam("ErrorPage", model);
			}
			String loginId = (String) super.getSessionAttr(request, "loginId");
			if(StringUtils.equals(member.getId(), loginId)) {
				super.setSessionAttr(request, "loginName", member.getName());
			}
			int sIndex = super.findCurrentRowIndex(request);
			return super.addModeParam("/member/ListMembers", "members", this.service.queryByPageRow(sIndex, sIndex+PAGE_PER_ROWS-1, this.service.listMemSeqs()));
		} catch(Throwable e) {
			e.printStackTrace();
		}
		return null;
    }
	
	@GetMapping("/reAddMember")
	public ModelAndView reAddMemberForm(HttpServletRequest request) {
		return super.addModeParam("member/AddMember", "member", (Member)super.getSessionAttr(request, "oriMember"));
	}
	
	@GetMapping("/reEditMember")
	public ModelAndView reEditMemberForm(HttpServletRequest request) {
		return super.addModeParam("member/EditMember", "member", (Member)super.getSessionAttr(request, "oriMember"));
	}
	
	@GetMapping("/cancelEditMember")
	public ModelAndView cancelEditMemberForm(HttpServletRequest request) {
		try {
			int sIndex = super.findCurrentRowIndex(request);
			return super.addModeParam("/member/ListMembers", "members", this.service.queryByPageRow(sIndex, sIndex+PAGE_PER_ROWS-1, this.service.listMemSeqs()));
		} catch(Throwable e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/chgMemberPage")
	public ModelAndView changeMemberPage(HttpServletRequest request, @RequestParam("rowIndex") int rowIndex) {
		try {
			List<Integer> memSeqs = this.service.listMemSeqs();
			super.setSessionAttr(request, "pageCounts", super.genPageCounts(rowIndex, ((memSeqs!=null)?memSeqs.size():0)));
			return super.addModeParam("/member/ListMembers", "members", this.service.queryByPageRow(rowIndex, rowIndex+PAGE_PER_ROWS-1, memSeqs));
		} catch(Throwable e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
