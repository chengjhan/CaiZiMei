/*
 * CaiZiMei
 * File: AdminController.java
 * Author: 詹晟
 * Date: 2017/7/11
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

import com.caizimei.model.entity.AdminBean;
import com.caizimei.model.entity.AdminLogBean;
import com.caizimei.model.service.AdminLogService;
import com.caizimei.model.service.AdminService;

import misc.PrimitiveNumberEditor;

/**
 * admin controller
 * 
 * @author 詹晟
 */
@Controller
@SessionAttributes(value = "admin")
public class AdminController {

	/**
	 * 注入 AdminService
	 */
	@Autowired
	private AdminService adminService;

	/**
	 * 注入 AdminLogService
	 */
	@Autowired
	private AdminLogService adminLogService;

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
	 * @param a_username-->管理員帳號
	 * @param a_password-->管理員密碼(原碼)
	 * @param request-->HttpServletRequest
	 * @param model-->Model
	 * @return /WEB-INF/views/index.jsp
	 * @return /WEB-INF/views/secure/sign-in.jsp
	 * @return /WEB-INF/views/secure/sign-in.jsp
	 */
	@RequestMapping(path = "/secure/sign-in.do", method = RequestMethod.POST)
	public String signInProcess(@RequestParam(name = "a_username") String a_username,
			@RequestParam(name = "a_password") String a_password, HttpServletRequest request, Model model) {

		AdminBean adminBean = adminService.selectByA_username(a_username);

		if (adminBean != null) {

			if (adminService.signIn(a_username, a_password)) {

				adminService.updateA_signin_ip(adminBean.getA_id(), request.getRemoteAddr());
				adminService.updateA_signin_time(adminBean.getA_id());
				model.addAttribute("admin", adminService.selectByA_username(a_username));

				// 寫入日誌
				AdminLogBean adminLogBean = new AdminLogBean();
				adminLogBean.setAl_AdminBean(adminBean);
				adminLogBean.setAl_operation("登入");
				adminLogBean.setAl_ip(request.getRemoteAddr());
				adminLogService.insert(adminLogBean);

				return "redirect:/index";
			} else {

				// 密碼錯誤
				model.addAttribute("error", "帳號或密碼錯誤");

				return "secure/sign-in";
			}
		} else {

			// 帳號錯誤
			model.addAttribute("error", "帳號或密碼錯誤");

			return "secure/sign-in";
		}
	}

	/**
	 * 忘記密碼
	 * 
	 * @param a_email-->管理員信箱
	 * @param model-->Model
	 * @return /WEB-INF/views/secure/reset-password.jsp
	 * @return /WEB-INF/views/secure/forget-password.jsp
	 */
	@RequestMapping(path = "/secure/forget-password.do", method = RequestMethod.POST)
	public String forgetPasswordProcess(@RequestParam(name = "a_email") String a_email, Model model) {

		AdminBean adminBean = adminService.selectByA_email(a_email);

		if (adminBean != null) {

			int random = (int) (Math.random() * 1000000);
			String a_password_random = String.valueOf(random);

			adminService.updateA_password(adminBean.getA_id(), a_password_random, adminBean.getA_salt());

			String to = adminBean.getA_email();
			String from = "chengjhan@gmail.com";
			String subject = "變更密碼";
			String text = a_password_random;
			adminService.sendEmail(to, from, subject, text);

			return "redirect:/secure/reset-password";
		}

		return "secure/forget-password";
	}

	/**
	 * 重設密碼
	 * 
	 * @param a_password-->驗證碼(原碼)
	 * @param a_password_new-->新密碼(原碼)
	 * @param admin-->Session
	 * @param sessionStatus-->SessionStatus
	 * @return /WEB-INF/views/secure/sign-in.jsp
	 * @return /WEB-INF/views/secure/reset-password.jsp
	 */
	@RequestMapping(path = "/secure/reset-password.do", method = RequestMethod.POST)
	public String resetPasswordProcess(@RequestParam(name = "a_password") String a_password,
			@RequestParam(name = "a_password_new") String a_password_new, @ModelAttribute("admin") AdminBean admin,
			SessionStatus sessionStatus) {

		String oldHashedPassword = adminService.selectByA_id(admin.getA_id()).getA_password();
		String inputOldHashedPassword = adminService.getHashedPassword(a_password, admin.getA_salt());

		if (oldHashedPassword.equals(inputOldHashedPassword)) {

			adminService.updateA_password(admin.getA_id(), a_password_new, admin.getA_salt());

			// 清除 @SessionAttributes
			sessionStatus.setComplete();

			return "redirect:/secure/sign-in";
		} else {
			return "secure/reset-password";
		}
	}

	/**
	 * 登出
	 * 
	 * @param admin-->Session
	 * @param request-->HttpServletRequest
	 * @param session-->HttpSession
	 * @param sessionStatus-->SessionStatus
	 * @return /WEB-INF/views/index.jsp
	 */
	@RequestMapping(path = "/secure/sign-out.do", method = RequestMethod.GET)
	public String signOutProcess(@ModelAttribute("admin") AdminBean admin, HttpServletRequest request,
			HttpSession session, SessionStatus sessionStatus) {

		// 寫入日誌
		AdminLogBean adminLogBean = new AdminLogBean();
		adminLogBean.setAl_AdminBean(admin);
		adminLogBean.setAl_operation("登出");
		adminLogBean.setAl_ip(request.getRemoteAddr());
		adminLogService.insert(adminLogBean);

		// 清除 HttpSession
		if (session.getAttribute("admin") != null) {
			session.removeAttribute("admin"); // 清除特定 HttpSession
		}
		session.invalidate(); // 清除所有 HttpSession

		// 清除 @SessionAttributes
		sessionStatus.setComplete();

		return "redirect:/index";
	}

	/**
	 * 註冊
	 * 
	 * @param adminBean-->AdminBean
	 * @param a_username-->管理員帳號
	 * @param a_password-->管理員密碼(原碼)
	 * @param request-->HttpServletRequest
	 * @param model-->Model
	 * @return /WEB-INF/views/index.jsp
	 * @return /WEB-INF/views/admin/sign-up.jsp
	 */
	@RequestMapping(path = "/admin/sign-up.do", method = RequestMethod.POST)
	public String signUpProcess(AdminBean adminBean, @RequestParam(name = "a_username") String a_username,
			@RequestParam(name = "a_password") String a_password, HttpServletRequest request, Model model) {

		if (adminService.selectByA_username(a_username) == null) {

			String a_salt = adminService.getSalt();

			adminBean.setA_salt(a_salt);
			adminBean.setA_password(adminService.getHashedPassword(a_password, a_salt));
			adminBean.setA_signup_time(new java.util.Date());
			adminBean.setA_signin_number(1);
			adminBean.setA_signin_ip(request.getRemoteAddr());
			adminBean.setA_signin_time(new java.util.Date());
			adminBean.setA_update_pwd_time(new java.util.Date());
			adminBean.setA_update_info_time(new java.util.Date());
			adminService.signUp(adminBean);

			model.addAttribute("admin", adminBean);

			return "redirect:/index";
		} else {
			return "redirect:/admin/sign-up";
		}
	}

	/**
	 * 編輯個人資料
	 * 
	 * @param admin-->Session
	 * @param adminBean-->AdminBean
	 * @return /WEB-INF/views/admin/profile.jsp
	 */
	@RequestMapping(path = "/admin/edit.do", method = RequestMethod.POST)
	public String editProcess(@ModelAttribute("admin") AdminBean admin, AdminBean adminBean) {

		adminBean.setA_id(admin.getA_id());
		adminService.update(adminBean);

		return "redirect:/admin/profile";
	}

	/**
	 * 變更密碼
	 * 
	 * @param a_password-->舊密碼(原碼)
	 * @param a_password_new-->新密碼(原碼)
	 * @param admin-->Session
	 * @return /WEB-INF/views/index.jsp
	 * @return /WEB-INF/views/admin/change-password.jsp
	 */
	@RequestMapping(path = "/admin/change-password.do", method = RequestMethod.POST)
	public String changePasswordProcess(@RequestParam(name = "a_password") String a_password,
			@RequestParam(name = "a_password_new") String a_password_new, @ModelAttribute("admin") AdminBean admin) {

		String oldHashedPassword = adminService.selectByA_id(admin.getA_id()).getA_password();
		String inputOldHashedPassword = adminService.getHashedPassword(a_password, admin.getA_salt());

		if (oldHashedPassword.equals(inputOldHashedPassword)) {

			adminService.updateA_password(admin.getA_id(), a_password_new, admin.getA_salt());

			return "redirect:/index";
		} else {
			return "admin/change-password";
		}
	}

}
