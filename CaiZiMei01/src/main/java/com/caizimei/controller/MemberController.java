package com.caizimei.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.caizimei.model.MemberBean;
import com.caizimei.model.service.MemberService;

import misc.PrimitiveNumberEditor;

@Controller
@SessionAttributes("user")
public class MemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private SimpleDateFormat simpleDateFormat;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Integer.class, new PrimitiveNumberEditor(Integer.class, true));
		webDataBinder.registerCustomEditor(Double.class, new PrimitiveNumberEditor(Double.class, true));
		webDataBinder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(simpleDateFormat, true));
	}

	// 登入
	@RequestMapping(path = "/member/sign-in.controller", method = RequestMethod.POST)
	public String signInProcess(@RequestParam(name = "m_account") String m_account,
			@RequestParam(name = "m_password") String m_password, Model model) {
		if (memberService.signIn(m_account, memberService.passwordToMD5(m_password))) {
			model.addAttribute("user", memberService.selectByM_account(m_account));
			return "index";
		} else {
			return "member.sign-in";
		}
	}

	// 登出
	@RequestMapping(path = "/member/sign-out.controller", method = RequestMethod.GET)
	public String signOutProcess(HttpSession session, SessionStatus sessionStatus) {
		// 清除 HttpSession
		if (session.getAttribute("user") != null) {
			session.removeAttribute("user"); // 清除特定 HttpSession
		}
		session.invalidate(); // 清除所有 HttpSession

		// 清除 @SessionAttributes
		sessionStatus.setComplete();
		return "index";
	}

	// 註冊
	@RequestMapping(path = "/member/sign-up.controller", method = RequestMethod.POST)
	public String signUpProcess(MemberBean memberBean, @RequestParam(name = "m_birth_year") String m_birth_year,
			@RequestParam(name = "m_birth_month") String m_birth_month,
			@RequestParam(name = "m_birth_date") String m_birth_date,
			@RequestParam(name = "m_telephone_front") String m_telephone_front,
			@RequestParam(name = "m_telephone_back") String m_telephone_back, Model model) {
		memberBean.setM_password(memberService.passwordToMD5(memberBean.getM_password()));
		java.util.Date m_birth = null;
		try {
			m_birth = simpleDateFormat.parse(m_birth_year + "-" + m_birth_month + "-" + m_birth_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		memberBean.setM_birth(m_birth);
		memberBean.setM_telephone(m_telephone_front + "-" + m_telephone_back);
		memberBean.setM_signup_time(new java.util.Date());
		memberBean.setM_limit(0);
		memberService.signUp(memberBean);
		memberService.signIn(memberBean.getM_account(), memberBean.getM_password());
		model.addAttribute("user", memberBean);
		return "index";
	}

	// 修改會員資料
	@RequestMapping(path = "/member/update.controller", method = RequestMethod.POST)
	public String updateProcess(MemberBean memberBean, HttpSession session, Model model) {
		MemberBean user = (MemberBean) session.getAttribute("user");
		memberBean.setM_id(user.getM_id());
		memberService.update(memberBean);
		model.addAttribute("user", memberBean);
		return "index";
	}

	// 修改密碼
	@RequestMapping(path = "/member/update-password.controller", method = RequestMethod.POST)
	public String updatePasswordProcess(@RequestParam(name = "m_password") String m_password,
			@RequestParam(name = "m_password_new") String m_password_new, HttpSession session, Model model) {
		MemberBean user = (MemberBean) session.getAttribute("user");
		if (memberService.passwordToMD5(m_password).equals(user.getM_password())) {
			memberService.updateM_password(user.getM_id(), memberService.passwordToMD5(m_password_new));
			model.addAttribute("user", memberService.selectByM_id(user.getM_id()));
			return "index";
		} else {
			return "member.update-password";
		}
	}

	// 條件查詢
	@RequestMapping(path = "/member/select.controller", method = RequestMethod.GET)
	public String selectByConditionsProcess(MemberBean memberBean, Model model) {
		model.addAttribute("selectByConditions", memberService.selectByConditions(memberBean.getM_firstname(),
				memberBean.getM_lastname(), memberBean.getM_telephone(), memberBean.getM_email()));
		return "member.search";
	}

}
