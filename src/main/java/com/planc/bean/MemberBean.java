package com.planc.bean;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.planc.model.Member;
import com.planc.service.MemberService;

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
	public ModelAndView deleteForm(@RequestParam("seq") String memSeq) {
		try {
			this.service.deleteBySeq(Integer.parseInt(memSeq));
			return super.addModeParam("/member/ListMembers", "members", this.service.queryAll());
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
			return super.addModeParam("/member/ListMembers", "members", this.service.queryAll());
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
	public ModelAndView cancelEditMemberForm() {
		try {
			return super.addModeParam("/member/ListMembers", "members", this.service.queryAll());
		} catch(Throwable e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
