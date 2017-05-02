/*
 * CaiZiMei
 * File: EmployeeController.java
 * Author: 詹晟
 * Date: 2017/5/1
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

import com.caizimei.model.entity.EmployeeBean;
import com.caizimei.model.service.AgentService;
import com.caizimei.model.service.AgentUserService;

import misc.PrimitiveNumberEditor;

/**
 * employee controller
 * 
 * @author 詹晟
 */
@Controller
@SessionAttributes(value = "agent")
public class EmployeeController {

	/**
	 * 注入 EmployeeService
	 */
	@Autowired
	private AgentUserService employeeService;

	/**
	 * 注入 CompanyService
	 */
	@Autowired
	private AgentService companyService;

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
	 * @param e_username-->員工帳號
	 * @param e_password-->員工密碼(原碼)
	 * @param model-->Model
	 * @param request-->HttpServletRequest
	 * @return /WEB-INF/views/agent/index.jsp
	 * @return /WEB-INF/views/agent/secure/sign-in.jsp
	 */
	@RequestMapping(path = "/agent/secure/sign-in.do", method = RequestMethod.POST)
	public String signInProcess(@RequestParam(name = "e_username") String e_username,
			@RequestParam(name = "e_password") String e_password, HttpServletRequest request, Model model) {

		EmployeeBean employeeBean = employeeService.selectByE_username(e_username);

		if (employeeBean != null) {

			if (employeeService.signIn(e_username, e_password)) {

				employeeService.updateE_signin_ip(employeeBean.getE_id(), request.getRemoteAddr());
				employeeService.updateE_signin_time(employeeBean.getE_id());
				model.addAttribute("agent", employeeService.selectByE_username(e_username));

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
	 * @param e_username-->員工信箱
	 * @param model-->Model
	 * @return /WEB-INF/views/agent/secure/set-password.jsp
	 */
	@RequestMapping(path = "/agent/secure/forget-password.do", method = RequestMethod.POST)
	public String forgetPasswordProcess(@RequestParam(name = "e_username") String e_username, Model model) {

		int random = (int) (Math.random() * 1000000);
		String e_password_random = String.valueOf(random);

		EmployeeBean employeeBean = employeeService.selectByE_username(e_username);
		employeeService.updateE_password(employeeBean.getE_id(), e_password_random, employeeBean.getE_salt());

		String to = e_username;
		String from = "chengjhan@gmail.com";
		String subject = "更改密碼";
		String text = e_password_random;
		employeeService.sendEmail(to, from, subject, text);

		model.addAttribute("agent", employeeBean);

		return "redirect:/agent/secure/set-password";
	}

	/**
	 * 重設員工密碼
	 * 
	 * @param e_password-->驗證碼(原碼)
	 * @param e_password_new-->新密碼(原碼)
	 * @param agent-->Session
	 * @param sessionStatus-->SessionStatus
	 * @return /WEB-INF/views/agent/secure/sign-in.jsp
	 * @return /WEB-INF/views/agent/secure/set-password.jsp
	 */
	@RequestMapping(path = "/agent/secure/set-password.do", method = RequestMethod.POST)
	public String setPasswordProcess(@RequestParam(name = "e_password") String e_password,
			@RequestParam(name = "e_password_new") String e_password_new, @ModelAttribute("agent") EmployeeBean agent,
			SessionStatus sessionStatus) {

		String oldHashedPassword = employeeService.selectByE_id(agent.getE_id()).getE_password();
		String inputOldHashedPassword = employeeService.getHashedPassword(e_password, agent.getE_salt());

		if (oldHashedPassword.equals(inputOldHashedPassword)) {

			employeeService.updateE_password(agent.getE_id(), e_password_new, agent.getE_salt());

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
	 * @param agent-->Session
	 * @param session-->HttpSession
	 * @param sessionStatus-->SessionStatus
	 * @return /WEB-INF/views/agent/secure/sign-in.jsp
	 */
	@RequestMapping(path = "/agent/secure/sign-out.do", method = RequestMethod.GET)
	public String signOutProcess(@ModelAttribute("agent") EmployeeBean agent, HttpSession session,
			SessionStatus sessionStatus) {

		// 清除 HttpSession
		if (session.getAttribute("user") != null) {
			session.removeAttribute("user"); // 清除特定 HttpSession
		}
		session.invalidate(); // 清除所有 HttpSession

		// 清除 @SessionAttributes
		sessionStatus.setComplete();

		return "redirect:/agent/index";
	}

	/**
	 * 註冊
	 * 
	 * @param employeeBean-->EmployeeBean
	 * @param e_username-->員工帳號
	 * @param e_password-->員工密碼(原碼)
	 * @return /WEB-INF/views/admin/index.jsp
	 * @return /WEB-INF/views/admin/employee/sign-up.jsp
	 */
	@RequestMapping(path = "/agent/employee/sign-up.do", method = RequestMethod.POST)
	public String signUpProcess(EmployeeBean employeeBean, @RequestParam(name = "e_username") String e_username,
			@RequestParam(name = "e_password") String e_password, @RequestParam(name = "e_com_id") Integer e_com_id) {

		if (employeeService.selectByE_username(e_username) == null) {

			String e_salt = employeeService.getSalt();

			employeeBean.setE_salt(e_salt);
			employeeBean.setE_password(employeeService.getHashedPassword(e_password, e_salt));
			employeeBean.setE_signin_number(0);
			employeeBean.setE_update_pass_time(new java.util.Date());
			employeeBean.setE_update_info_time(new java.util.Date());
			employeeBean.setE_CompanyBean(companyService.selectByCom_id(e_com_id));
			employeeService.signUp(employeeBean);

			return "redirect:/admin/index";
		} else {
			return "redirect:/admin/employee/sign-up";
		}
	}

	/**
	 * 修改員工資料
	 * 
	 * @param agent-->Session
	 * @param employeeBean-->EmployeeBean
	 * @return /WEB-INF/views/agent/index.jsp
	 */
	@RequestMapping(path = "/agent/employee/update.do", method = RequestMethod.POST)
	public String updateProcess(@ModelAttribute("agent") EmployeeBean agent, EmployeeBean employeeBean) {

		employeeBean.setE_id(agent.getE_id());
		employeeService.update(employeeBean);

		return "redirect:/agent/index";
	}

	/**
	 * 修改員工密碼
	 * 
	 * @param e_password-->舊密碼(原碼)
	 * @param e_password_new-->新密碼(原碼)
	 * @param agent-->Session
	 * @return /WEB-INF/views/agent/index.jsp
	 * @return /WEB-INF/views/agent/employee/update-password.jsp
	 */
	@RequestMapping(path = "/agent/employee/update-password.do", method = RequestMethod.POST)
	public String updatePasswordProcess(@RequestParam(name = "e_password") String e_password,
			@RequestParam(name = "e_password_new") String e_password_new, @ModelAttribute("agent") EmployeeBean agent) {

		String oldHashedPassword = employeeService.selectByE_id(agent.getE_id()).getE_password();
		String inputOldHashedPassword = employeeService.getHashedPassword(e_password, agent.getE_salt());

		if (oldHashedPassword.equals(inputOldHashedPassword)) {

			employeeService.updateE_password(agent.getE_id(), e_password_new, agent.getE_salt());

			return "redirect:/agent/index";
		} else {
			return "agent/employee/update-password";
		}
	}

}
