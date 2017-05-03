/*
 * CaiZiMei
 * File: AgentUserController.java
 * Author: 詹晟
 * Date: 2017/5/3
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.controller;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.caizimei.model.entity.AgentUserBean;
import com.caizimei.model.service.AgentService;
import com.caizimei.model.service.AgentUserService;

import misc.PrimitiveNumberEditor;

/**
 * agent_user controller
 * 
 * @author 詹晟
 */
@Controller
@SessionAttributes(value = "agent")
public class AgentUserController {

	/**
	 * 注入 AgentUserService
	 */
	@Autowired
	private AgentUserService agentUserService;

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
	 * @param au_username-->代理商使用者帳號
	 * @param au_password-->代理商使用者密碼(原碼)
	 * @param request-->HttpServletRequest
	 * @param model-->Model
	 * @return /WEB-INF/views/agent/index.jsp
	 * @return /WEB-INF/views/agent/secure/sign-in.jsp
	 */
	@RequestMapping(path = "/agent/secure/sign-in.do", method = RequestMethod.POST)
	public String signInProcess(@RequestParam(name = "au_username") String au_username,
			@RequestParam(name = "au_password") String au_password, HttpServletRequest request, Model model) {

		AgentUserBean agentUserBean = agentUserService.selectByAu_username(au_username);

		if (agentUserBean != null) {

			if (agentUserService.signIn(au_username, au_password)) {

				agentUserService.updateAu_signin_ip(agentUserBean.getAu_id(), request.getRemoteAddr());
				agentUserService.updateAu_signin_time(agentUserBean.getAu_id());
				model.addAttribute("agent", agentUserService.selectByAu_username(au_username));

				return "redirect:/agent/index";
			} else {

				// 密碼錯誤
				model.addAttribute("error", "帳號或密碼錯誤");

				return "agent/secure/sign-in";
			}
		} else {

			// 帳號錯誤
			model.addAttribute("error", "帳號或密碼錯誤");

			return "agent/secure/sign-in";
		}
	}

	/**
	 * 忘記密碼
	 * 
	 * @param au_email-->代理商使用者信箱
	 * @param model-->Model
	 * @return /WEB-INF/views/agent/secure/set-password.jsp
	 */
	@RequestMapping(path = "/agent/secure/forget-password.do", method = RequestMethod.POST)
	public String forgetPasswordProcess(@RequestParam(name = "au_email") String au_email, Model model) {

		int random = (int) (Math.random() * 1000000);
		String au_password_random = String.valueOf(random);

		AgentUserBean agentUserBean = agentUserService.selectByAu_email(au_email);
		agentUserService.updateAu_password(agentUserBean.getAu_id(), au_password_random, agentUserBean.getAu_salt());

		String to = au_email;
		String from = "chengjhan@gmail.com";
		String subject = "更改密碼";
		String text = au_password_random;
		agentUserService.sendEmail(to, from, subject, text);

		model.addAttribute("agent", agentUserBean);

		return "redirect:/agent/secure/set-password";
	}

	/**
	 * 重設密碼
	 * 
	 * @param au_password-->驗證碼(原碼)
	 * @param au_password_new-->新密碼(原碼)
	 * @param agent-->Session
	 * @param sessionStatus-->SessionStatus
	 * @return /WEB-INF/views/agent/secure/sign-in.jsp
	 * @return /WEB-INF/views/agent/secure/set-password.jsp
	 */
	@RequestMapping(path = "/agent/secure/set-password.do", method = RequestMethod.POST)
	public String setPasswordProcess(@RequestParam(name = "au_password") String au_password,
			@RequestParam(name = "au_password_new") String au_password_new,
			@ModelAttribute("agent") AgentUserBean agent, SessionStatus sessionStatus) {

		String oldHashedPassword = agentUserService.selectByAu_id(agent.getAu_id()).getAu_password();
		String inputOldHashedPassword = agentUserService.getHashedPassword(au_password, agent.getAu_salt());

		if (oldHashedPassword.equals(inputOldHashedPassword)) {

			agentUserService.updateAu_password(agent.getAu_id(), au_password_new, agent.getAu_salt());

			// 清除 @SessionAttributes
			sessionStatus.setComplete();

			return "redirect:/agent/secure/sign-in";
		} else {
			return "agent/secure/set-password";
		}
	}

	/**
	 * 登出
	 * 
	 * @param session-->HttpSession
	 * @param sessionStatus-->SessionStatus
	 * @return /WEB-INF/views/agent/index.jsp
	 */
	@RequestMapping(path = "/agent/secure/sign-out.do", method = RequestMethod.GET)
	public String signOutProcess(HttpSession session, SessionStatus sessionStatus) {

		// 清除 HttpSession
		if (session.getAttribute("agent") != null) {
			session.removeAttribute("agent"); // 清除特定 HttpSession
		}
		session.invalidate(); // 清除所有 HttpSession

		// 清除 @SessionAttributes
		sessionStatus.setComplete();

		return "redirect:/agent/index";
	}

	/**
	 * 註冊
	 * 
	 * @param agentUserBean-->AgentUserBean
	 * @param au_username-->代理商使用者帳號
	 * @param au_password-->代理商使用者密碼(原碼)
	 * @param au_a_id-->代理商流水號
	 * @return /WEB-INF/views/admin/index.jsp
	 * @return /WEB-INF/views/admin/agent-user/sign-up.jsp
	 */
	@RequestMapping(path = "/admin/agent-user/sign-up.do", method = RequestMethod.POST)
	public String signUpProcess(AgentUserBean agentUserBean, @RequestParam(name = "au_username") String au_username,
			@RequestParam(name = "au_password") String au_password, @RequestParam(name = "au_a_id") Integer au_a_id) {

		if (agentUserService.selectByAu_username(au_username) == null) {

			String au_salt = agentUserService.getSalt();

			agentUserBean.setAu_AgentBean(agentService.selectByA_id(au_a_id));
			agentUserBean.setAu_salt(au_salt);
			agentUserBean.setAu_password(agentUserService.getHashedPassword(au_password, au_salt));
			agentUserBean.setAu_signin_number(0);
			agentUserBean.setAu_update_pass_time(new java.util.Date());
			agentUserBean.setAu_update_info_time(new java.util.Date());
			agentUserService.signUp(agentUserBean);

			return "redirect:/admin/index";
		} else {
			return "redirect:/admin/agent-user/sign-up";
		}
	}

	/**
	 * 修改資料
	 * 
	 * @param agent-->Session
	 * @param agentUserBean-->AgentUserBean
	 * @return /WEB-INF/views/agent/user/profile.jsp
	 */
	@RequestMapping(path = "/agent/user/update.do", method = RequestMethod.POST)
	public String updateProcess(@ModelAttribute("agent") AgentUserBean agent, AgentUserBean agentUserBean) {

		agentUserBean.setAu_id(agent.getAu_id());
		agentUserService.update(agentUserBean);

		return "redirect:/agent/user/profile";
	}

	/**
	 * 修改密碼
	 * 
	 * @param au_password-->舊密碼(原碼)
	 * @param au_password_new-->新密碼(原碼)
	 * @param agent-->Session
	 * @return /WEB-INF/views/agent/index.jsp
	 * @return /WEB-INF/views/agent/user/update-password.jsp
	 */
	@RequestMapping(path = "/agent/agentUser/update-password.do", method = RequestMethod.POST)
	public String updatePasswordProcess(@RequestParam(name = "au_password") String au_password,
			@RequestParam(name = "au_password_new") String au_password_new,
			@ModelAttribute("agent") AgentUserBean agent) {

		String oldHashedPassword = agentUserService.selectByAu_id(agent.getAu_id()).getAu_password();
		String inputOldHashedPassword = agentUserService.getHashedPassword(au_password, agent.getAu_salt());

		if (oldHashedPassword.equals(inputOldHashedPassword)) {

			agentUserService.updateAu_password(agent.getAu_id(), au_password_new, agent.getAu_salt());

			return "redirect:/agent/index";
		} else {
			return "agent/user/update-password";
		}
	}

}
