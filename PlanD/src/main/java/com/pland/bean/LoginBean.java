package com.pland.bean;

import java.util.List;

//import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pland.model.Login;
import com.pland.model.Member;
import com.pland.service.MemberService;

@Controller
public class LoginBean extends CommonBean {
	
	@Autowired
	private MemberService service = null;
	
	public LoginBean() {
		super();
	}
	
	@GetMapping("/")
	public ModelAndView initForm(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return super.addModeParam("Login", new Login());
	}
	
	@PostMapping("/loginCheck")
    public ModelAndView submitForm(HttpServletRequest request, @ModelAttribute("login") Login login, ModelMap model) {
		try {
			login = this.service.loginCheck(login);
			System.out.println("LoginBean.submitForm.login: \r\n" + login);
			String errMsg = ((login!=null)?login.getErrMeg():null);
			if(!StringUtils.isBlank(errMsg)) {
				super.setSessionAttr(request, "oriLogin", login);
				model.put(PAGE_PARAMETER_ERRMSG, errMsg);
				model.put(PAGE_PARAMETER_BACKPAGE, "/reLogin");
				return super.addModeParam("ErrorPage", model);
			}
			Member member = this.service.queryById(login.getId());
			if(member!=null) {
				super.setSessionAttr(request, "loginId", member.getId());
				super.setSessionAttr(request, "loginName", member.getName());
				super.setSessionAttr(request, "loginRole", ((member.isAdmin())?"admin":"member"));
			}
			List<Integer> memSeqs = this.service.listMemSeqs();
			super.setSessionAttr(request, "pageCounts", super.genPageCounts(1, ((memSeqs!=null)?memSeqs.size():0)));
			return super.addModeParam("/member/ListMembers", "members", this.service.queryByPageRow(1, PAGE_PER_ROWS, memSeqs));
		} catch(Throwable e) {
			e.printStackTrace();
		}
		return null;
    }
	
	@GetMapping("/reLogin")
	public ModelAndView reLoginForm(HttpServletRequest request) {
		return super.addModeParam("Login", "login", (Login)super.getSessionAttr(request, "oriLogin"));
	}

}