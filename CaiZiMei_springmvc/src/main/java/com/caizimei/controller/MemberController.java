/*
 * CaiZiMei
 * File: MemberController.java
 * Author: 詹晟
 * Date: 2017/5/3
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
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

import com.caizimei.model.entity.AgentUserBean;
import com.caizimei.model.entity.MemberBean;
import com.caizimei.model.service.AgentService;
import com.caizimei.model.service.MemberService;

import misc.PrimitiveNumberEditor;

/**
 * member controller
 * 
 * @author 詹晟
 */
@Controller
@SessionAttributes(value = { "user", "agent", "lastSignInIp", "lastSignInTime" })
public class MemberController {

	/**
	 * 注入 MemberService
	 */
	@Autowired
	private MemberService memberService;

	/**
	 * 注入 AgentService
	 */
	@Autowired
	private AgentService agentService;

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
	 * @param m_username-->會員信箱
	 * @param m_password-->會員密碼(原碼)
	 * @param request-->HttpServletRequest
	 * @param model-->Model
	 * @return /WEB-INF/views/user/index.jsp
	 * @return /WEB-INF/views/user/secure/sign-in.jsp
	 */
	@RequestMapping(path = "/user/secure/sign-in.do", method = RequestMethod.POST)
	public String signInProcess(@RequestParam(name = "m_username") String m_username,
			@RequestParam(name = "m_password") String m_password, HttpServletRequest request, Model model) {

		MemberBean memberBean = memberService.selectByM_username(m_username);

		if (memberBean != null) {

			model.addAttribute("lastSignInIp", memberBean.getM_signin_ip());
			model.addAttribute("lastSignInTime", memberBean.getM_signin_time());

			if (memberService.signIn(m_username, m_password)) {

				memberService.updateM_signin_ip(memberBean.getM_id(), request.getRemoteAddr());
				memberService.updateM_signin_time(memberBean.getM_id());
				model.addAttribute("user", memberService.selectByM_username(m_username));

				return "redirect:/user/index";
			} else {

				// 密碼錯誤
				model.addAttribute("error", "信箱或密碼錯誤");

				return "user/secure/sign-in";
			}
		} else {

			// 信箱錯誤
			model.addAttribute("error", "信箱或密碼錯誤");

			return "user/secure/sign-in";
		}
	}

	/**
	 * 忘記密碼
	 * 
	 * @param m_username-->會員信箱
	 * @param model-->Model
	 * @return /WEB-INF/views/user/secure/set-password.jsp
	 */
	@RequestMapping(path = "/user/secure/forget-password.do", method = RequestMethod.POST)
	public String forgetPasswordProcess(@RequestParam(name = "m_username") String m_username, Model model) {

		int random = (int) (Math.random() * 1000000);
		String m_password_random = String.valueOf(random);

		MemberBean memberBean = memberService.selectByM_username(m_username);
		memberService.updateM_password(memberBean.getM_id(), m_password_random, memberBean.getM_salt());

		String to = m_username;
		String from = "chengjhan@gmail.com";
		String subject = "更改密碼";
		String text = m_password_random;
		memberService.sendEmail(to, from, subject, text);

		model.addAttribute("user", memberBean);

		return "redirect:/user/secure/set-password";
	}

	/**
	 * 重設會員密碼
	 * 
	 * @param m_password-->驗證碼(原碼)
	 * @param m_password_new-->新密碼(原碼)
	 * @param user-->Session
	 * @param sessionStatus-->SessionStatus
	 * @return /WEB-INF/views/user/secure/sign-in.jsp
	 * @return /WEB-INF/views/user/secure/set-password.jsp
	 */
	@RequestMapping(path = "/user/secure/set-password.do", method = RequestMethod.POST)
	public String setPasswordProcess(@RequestParam(name = "m_password") String m_password,
			@RequestParam(name = "m_password_new") String m_password_new, @ModelAttribute("user") MemberBean user,
			SessionStatus sessionStatus) {

		String oldHashedPassword = memberService.selectByM_id(user.getM_id()).getM_password();
		String inputOldHashedPassword = memberService.getHashedPassword(m_password, user.getM_salt());

		if (oldHashedPassword.equals(inputOldHashedPassword)) {

			memberService.updateM_password(user.getM_id(), m_password_new, user.getM_salt());

			// 清除 @SessionAttributes
			sessionStatus.setComplete();

			return "redirect:/user/secure/sign-in";
		} else {
			return "user/secure/set-password";
		}
	}

	/**
	 * 登出
	 * 
	 * @param session-->HttpSession
	 * @param sessionStatus-->SessionStatus
	 * @return /WEB-INF/views/user/index.jsp
	 */
	@RequestMapping(path = "/user/secure/sign-out.do", method = RequestMethod.GET)
	public String signOutProcess(HttpSession session, SessionStatus sessionStatus) {

		// 清除 HttpSession
		if (session.getAttribute("user") != null) {
			session.removeAttribute("user"); // 清除特定 HttpSession
		}
		session.invalidate(); // 清除所有 HttpSession

		// 清除 @SessionAttributes
		sessionStatus.setComplete();

		return "redirect:/user/index";
	}

	/**
	 * 註冊
	 * 
	 * @param memberBean-->MemberBean
	 * @param m_username-->會員信箱
	 * @param m_password-->會員密碼(原碼)
	 * @param m_birth_year-->會員生日(年)
	 * @param m_birth_month-->會員生日(月)
	 * @param m_birth_date-->會員生日(日)
	 * @param m_localphone_front-->會員電話(前碼)
	 * @param m_localphone_back-->會員電話(後碼)
	 * @param m_mobilephone_front-->會員手機(前碼)
	 * @param m_mobilephone_back-->會員手機(後碼)
	 * @param request-->HttpServletRequest
	 * @param model-->Model
	 * @return /WEB-INF/views/user/index.jsp
	 * @return /WEB-INF/views/user/member/sign-up.jsp
	 */
	@RequestMapping(path = "/user/member/sign-up.do", method = RequestMethod.POST)
	public String signUpProcess(MemberBean memberBean, @RequestParam(name = "m_username") String m_username,
			@RequestParam(name = "m_password") String m_password,
			@RequestParam(name = "m_birth_year") String m_birth_year,
			@RequestParam(name = "m_birth_month") String m_birth_month,
			@RequestParam(name = "m_birth_date") String m_birth_date,
			@RequestParam(name = "m_localphone_front") String m_localphone_front,
			@RequestParam(name = "m_localphone_back") String m_localphone_back,
			@RequestParam(name = "m_mobilephone_front") String m_mobilephone_front,
			@RequestParam(name = "m_mobilephone_back") String m_mobilephone_back, HttpServletRequest request,
			Model model) {

		if (memberService.selectByM_username(m_username) == null) {

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
			memberBean.setM_localphone(m_localphone_front + "-" + m_localphone_back);
			memberBean.setM_mobilephone(m_mobilephone_front + "-" + m_mobilephone_back);
			memberBean.setM_signin_number(1);
			memberBean.setM_signin_ip(request.getRemoteAddr());
			memberBean.setM_signin_time(new java.util.Date());
			memberBean.setM_update_pass_time(new java.util.Date());
			memberBean.setM_update_info_time(new java.util.Date());
			memberBean.setM_AgentBean(agentService.selectByA_id(1));
			memberService.signUp(memberBean);

			model.addAttribute("user", memberBean);
			model.addAttribute("lastSignInIp", "第一次登入");
			model.addAttribute("lastSignInTime", "第一次登入");

			String to = memberBean.getM_username();
			String from = "chengjhan@gmail.com";
			String subject = "歡迎加入會員";
			String text = memberBean.getM_firstname() + " 您好";
			memberService.sendEmail(to, from, subject, text);

			return "redirect:/user/index";
		} else {
			return "redirect:/user/member/sign-up";
		}
	}

	/**
	 * 修改資料
	 * 
	 * @param user-->Session
	 * @param memberBean-->MemberBean
	 * @return /WEB-INF/views/user/index.jsp
	 */
	@RequestMapping(path = "/user/member/update.do", method = RequestMethod.POST)
	public String updateProcess(@ModelAttribute("user") MemberBean user, MemberBean newMemberBean) {

		newMemberBean.setM_id(user.getM_id());
		memberService.update(newMemberBean);

		return "redirect:/user/index";
	}

	/**
	 * 修改密碼
	 * 
	 * @param m_password-->舊密碼(原碼)
	 * @param m_password_new-->新密碼(原碼)
	 * @param user-->Session
	 * @return /WEB-INF/views/user/index.jsp
	 * @return /WEB-INF/views/user/member/update-password.jsp
	 */
	@RequestMapping(path = "/user/member/update-password.do", method = RequestMethod.POST)
	public String updatePasswordProcess(@RequestParam(name = "m_password") String m_password,
			@RequestParam(name = "m_password_new") String m_password_new, @ModelAttribute("user") MemberBean user) {

		String oldHashedPassword = memberService.selectByM_id(user.getM_id()).getM_password();
		String inputOldHashedPassword = memberService.getHashedPassword(m_password, user.getM_salt());

		if (oldHashedPassword.equals(inputOldHashedPassword)) {

			memberService.updateM_password(user.getM_id(), m_password_new, user.getM_salt());

			return "redirect:/user/index";
		} else {
			return "user/member/update-password";
		}
	}

	/**
	 * 條件搜尋 (admin)
	 * 
	 * @param memberBean-->MemberBean
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/member/search.jsp
	 */
	@RequestMapping(path = "/admin/member/search.do", method = RequestMethod.GET)
	public String selectByMemberConditionsForAdminProcess(MemberBean memberBean, Model model) {

		model.addAttribute("selectByMemberConditionsForAdmin",
				memberService.selectByMemberConditionsForAdmin(memberBean.getM_username(), memberBean.getM_lastname(),
						memberBean.getM_firstname(), memberBean.getM_mobilephone()));

		return "admin/member/search";
	}

	/**
	 * 條件搜尋 (agent)
	 * 
	 * @param memberBean-->MemberBean
	 * @param agent-->Session
	 * @param model-->Model
	 * @return /WEB-INF/views/agent/member/search.jsp
	 */
	@RequestMapping(path = "/agent/member/search.do", method = RequestMethod.GET)
	public String selectByMemberConditionsForAgentProcess(MemberBean memberBean,
			@ModelAttribute("agent") AgentUserBean agent, Model model) {

		model.addAttribute("selectByMemberConditionsForAgent",
				memberService.selectByMemberConditionsForAgent(memberBean.getM_username(), memberBean.getM_lastname(),
						memberBean.getM_firstname(), memberBean.getM_mobilephone(), agent.getAu_AgentBean().getA_id()));

		return "agent/member/search";
	}

	/**
	 * 驗證信箱是否已使用 (ajax)
	 * 
	 * @param m_username-->會員信箱
	 * @return 1-->已使用
	 * @return 0-->未使用
	 */
	@RequestMapping(path = "/user/member/select-by-username.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String validateUsernameAjaxProcess(String m_username) {

		MemberBean result = memberService.selectByM_username(m_username);

		if (result != null) {
			return "1";
		} else {
			return "0";
		}
	}

}
