/*
 * CaiZiMei
 * File: MemberController.java
 * Author: 詹晟
 * Date: 2017/3/18
 * Version: 1.0
 * Since: JDK 1.8
 */
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.caizimei.model.MemberBean;
import com.caizimei.model.service.MemberService;

import misc.PrimitiveNumberEditor;

/**
 * member controller
 * 
 * @author 詹晟
 */
@Controller
@SessionAttributes("user")
public class MemberController {

	/**
	 * 注入 MemberService
	 */
	@Autowired
	private MemberService memberService;

	/**
	 * 注入 SimpleDateFormat
	 */
	@Autowired
	private SimpleDateFormat simpleDateFormat;

	/**
	 * 提供 form-backing bean 資料轉換
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Integer.class, new PrimitiveNumberEditor(Integer.class, true));
		webDataBinder.registerCustomEditor(Double.class, new PrimitiveNumberEditor(Double.class, true));
		webDataBinder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(simpleDateFormat, true));
	}

	/**
	 * 登入
	 * 
	 * @param m_username-->帳號
	 * @param m_password-->密碼(原碼)
	 * @return /index.jsp
	 * @return /member/sign-in.jsp
	 */
	@RequestMapping(path = "/member/sign-in.controller", method = RequestMethod.POST)
	public String signInProcess(@RequestParam(name = "m_username") String m_username,
			@RequestParam(name = "m_password") String m_password, Model model) {
		if (memberService.signIn(m_username, m_password)) {
			model.addAttribute("user", memberService.selectByM_username(m_username));
			return "index";
		} else {
			return "member.sign-in";
		}
	}

	/**
	 * 登出
	 * 
	 * @return /index.jsp
	 */
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

	/**
	 * 註冊
	 * 
	 * @param memberBean-->MemberBean
	 * @param m_password-->密碼(原碼)
	 * @param m_birth_year-->生日(年)
	 * @param m_birth_month-->生日(月)
	 * @param m_birth_date-->生日(日)
	 * @param m_telephone_front-->電話(前碼)
	 * @param m_telephone_back-->電話(後碼)
	 * @return /index.jsp
	 */
	@RequestMapping(path = "/member/sign-up.controller", method = RequestMethod.POST)
	public String signUpProcess(MemberBean memberBean, @RequestParam(name = "m_password") String m_password,
			@RequestParam(name = "m_birth_year") String m_birth_year,
			@RequestParam(name = "m_birth_month") String m_birth_month,
			@RequestParam(name = "m_birth_date") String m_birth_date,
			@RequestParam(name = "m_telephone_front") String m_telephone_front,
			@RequestParam(name = "m_telephone_back") String m_telephone_back, Model model) {
		String m_salt = memberService.getSalt();
		memberBean.setM_salt(m_salt);
		memberBean.setM_password(memberService.getHashedPassword(m_password, m_salt));
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
		memberService.signIn(memberBean.getM_username(), m_password);
		model.addAttribute("user", memberBean);
		return "index";
	}

	/**
	 * 驗證帳號是否已使用 (ajax)
	 * 
	 * @param m_username-->帳號
	 * @return 1-->已使用
	 * @return 0-->未使用
	 */
	@RequestMapping(path = "/member/select-username.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String validateUsernameAjaxProcess(String m_username) {
		MemberBean result = memberService.selectByM_username(m_username);
		if (result != null) {
			return "1";
		} else {
			return "0";
		}
	}

	/**
	 * 修改會員資料
	 * 
	 * @param user-->Session
	 * @param memberBean-->MemberBean
	 * @return /index.jsp
	 */
	@RequestMapping(path = "/member/update.controller", method = RequestMethod.POST)
	public String updateProcess(@ModelAttribute("user") MemberBean user, MemberBean memberBean) {
		memberBean.setM_id(user.getM_id());
		memberService.update(memberBean);
		return "index";
	}

	/**
	 * 修改密碼
	 * 
	 * @param m_password-->舊密碼(原碼)
	 * @param m_password_new-->新密碼(原碼)
	 * @param user-->Session
	 * @return /index.jsp
	 * @return /member/update-password.jsp
	 */
	@RequestMapping(path = "/member/update-password.controller", method = RequestMethod.POST)
	public String updatePasswordProcess(@RequestParam(name = "m_password") String m_password,
			@RequestParam(name = "m_password_new") String m_password_new, @ModelAttribute("user") MemberBean user) {
		if (memberService.getHashedPassword(m_password, user.getM_salt())
				.equals(memberService.selectByM_id(user.getM_id()).getM_password())) {
			memberService.updateM_password(user.getM_id(), m_password_new, user.getM_salt());
			return "index";
		} else {
			return "member.update-password";
		}
	}

	/**
	 * 條件查詢
	 * 
	 * @param memberBean-->MemberBean
	 * @return /member/search.jsp
	 */
	@RequestMapping(path = "/member/select.controller", method = RequestMethod.GET)
	public String selectByConditionsProcess(MemberBean memberBean, Model model) {
		model.addAttribute("selectByConditions", memberService.selectByConditions(memberBean.getM_firstname(),
				memberBean.getM_lastname(), memberBean.getM_telephone(), memberBean.getM_email()));
		return "member.search";
	}

}
