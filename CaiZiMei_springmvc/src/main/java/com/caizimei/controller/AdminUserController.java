/*
 * CaiZiMei
 * File: AdminUserController.java
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

import com.caizimei.model.entity.AdminUserBean;
import com.caizimei.model.entity.AdminUserLogBean;
import com.caizimei.model.service.AdminUserLogService;
import com.caizimei.model.service.AdminUserService;

import misc.PrimitiveNumberEditor;

/**
 * admin_user controller
 * 
 * @author 詹晟
 */
@Controller
@SessionAttributes(value = "admin")
public class AdminUserController {

	/**
	 * 注入 AdminUserService
	 */
	@Autowired
	private AdminUserService adminUserService;

	/**
	 * 注入 AdminUserLogService
	 */
	@Autowired
	private AdminUserLogService adminUserLogService;

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
	 * @param au_username-->管理員帳號
	 * @param au_password-->管理員密碼(原碼)
	 * @param model-->Model
	 * @param request-->HttpServletRequest
	 * @return /WEB-INF/views/admin/index.jsp
	 * @return /WEB-INF/views/admin/secure/sign-in.jsp
	 */
	@RequestMapping(path = "/admin/secure/sign-in.do", method = RequestMethod.POST)
	public String signInProcess(@RequestParam(name = "au_username") String au_username,
			@RequestParam(name = "au_password") String au_password, HttpServletRequest request, Model model) {

		AdminUserBean adminUserBean = adminUserService.selectByAdu_username(au_username);

		if (adminUserBean != null) {

			if (adminUserService.signIn(au_username, au_password)) {

				adminUserService.updateAdu_signin_ip(adminUserBean.getAdu_id(), request.getRemoteAddr());
				adminUserService.updateAdu_signin_time(adminUserBean.getAdu_id());
				model.addAttribute("admin", adminUserService.selectByAdu_username(au_username));

				// 寫入日誌
				AdminUserLogBean adminUserLogBean = new AdminUserLogBean();
				adminUserLogBean.setAdul_AdminUserBean(adminUserBean);
				adminUserLogBean.setAdul_operation("登入");
				adminUserLogService.insert(adminUserLogBean);

				return "redirect:/admin/index";
			} else {

				// 密碼錯誤
				model.addAttribute("error", "帳號或密碼錯誤");

				return "admin/secure/sign-in";
			}
		} else {

			// 帳號錯誤
			model.addAttribute("error", "帳號或密碼錯誤");

			return "admin/secure/sign-in";
		}
	}

	/**
	 * 忘記密碼
	 * 
	 * @param au_username-->管理員信箱
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/secure/set-password.jsp
	 */
	@RequestMapping(path = "/admin/secure/forget-password.do", method = RequestMethod.POST)
	public String forgetPasswordProcess(@RequestParam(name = "au_username") String au_username, Model model) {

		int random = (int) (Math.random() * 1000000);
		String au_password_random = String.valueOf(random);

		AdminUserBean adminUserBean = adminUserService.selectByAdu_username(au_username);
		adminUserService.updateAdu_password(adminUserBean.getAdu_id(), au_password_random, adminUserBean.getAdu_salt());

		String to = au_username;
		String from = "chengjhan@gmail.com";
		String subject = "更改密碼";
		String text = au_password_random;
		adminUserService.sendEmail(to, from, subject, text);

		model.addAttribute("admin", adminUserBean);

		return "redirect:/admin/secure/set-password";
	}

	/**
	 * 重設管理員密碼
	 * 
	 * @param au_password-->驗證碼(原碼)
	 * @param au_password_new-->新密碼(原碼)
	 * @param admin-->Session
	 * @param sessionStatus-->SessionStatus
	 * @return /WEB-INF/views/admin/secure/sign-in.jsp
	 * @return /WEB-INF/views/admin/secure/set-password.jsp
	 */
	@RequestMapping(path = "/admin/secure/set-password.do", method = RequestMethod.POST)
	public String setPasswordProcess(@RequestParam(name = "au_password") String au_password,
			@RequestParam(name = "au_password_new") String au_password_new,
			@ModelAttribute("admin") AdminUserBean admin, SessionStatus sessionStatus) {

		String oldHashedPassword = adminUserService.selectByAdu_id(admin.getAdu_id()).getAdu_password();
		String inputOldHashedPassword = adminUserService.getHashedPassword(au_password, admin.getAdu_salt());

		if (oldHashedPassword.equals(inputOldHashedPassword)) {

			adminUserService.updateAdu_password(admin.getAdu_id(), au_password_new, admin.getAdu_salt());

			// 清除 @SessionAttributes
			sessionStatus.setComplete();

			return "redirect:/admin/secure/sign-in";
		} else {
			return "admin/secure/set-password";
		}
	}

	/**
	 * 登出
	 * 
	 * @param admin-->Session
	 * @param session-->HttpSession
	 * @param sessionStatus-->SessionStatus
	 * @return /WEB-INF/views/admin/secure/sign-in.jsp
	 */
	@RequestMapping(path = "/admin/secure/sign-out.do", method = RequestMethod.GET)
	public String signOutProcess(@ModelAttribute("admin") AdminUserBean admin, HttpSession session,
			SessionStatus sessionStatus) {

		// 寫入日誌
		AdminUserLogBean adminUserLogBean = new AdminUserLogBean();
		adminUserLogBean.setAdul_AdminUserBean(admin);
		adminUserLogBean.setAdul_operation("登出");
		adminUserLogService.insert(adminUserLogBean);

		// 清除 HttpSession
		if (session.getAttribute("user") != null) {
			session.removeAttribute("user"); // 清除特定 HttpSession
		}
		session.invalidate(); // 清除所有 HttpSession

		// 清除 @SessionAttributes
		sessionStatus.setComplete();

		return "redirect:/admin/index";
	}

	/**
	 * 註冊
	 * 
	 * @param adminUserBean-->AdminUserBean
	 * @param au_username-->管理員帳號
	 * @param au_password-->管理員密碼(原碼)
	 * @param request-->HttpServletRequest
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/index.jsp
	 * @return /WEB-INF/views/admin/adminUser/sign-up.jsp
	 */
	@RequestMapping(path = "/admin/adminUser/sign-up.do", method = RequestMethod.POST)
	public String signUpProcess(AdminUserBean adminUserBean, @RequestParam(name = "au_username") String au_username,
			@RequestParam(name = "au_password") String au_password, HttpServletRequest request, Model model) {

		if (adminUserService.selectByAdu_username(au_username) == null) {

			String au_salt = adminUserService.getSalt();

			adminUserBean.setAdu_salt(au_salt);
			adminUserBean.setAdu_password(adminUserService.getHashedPassword(au_password, au_salt));
			adminUserBean.setAdu_signin_number(1);
			adminUserBean.setAdu_signin_ip(request.getRemoteAddr());
			adminUserBean.setAdu_signin_time(new java.util.Date());
			adminUserBean.setAdu_update_pass_time(new java.util.Date());
			adminUserBean.setAdu_update_info_time(new java.util.Date());
			adminUserService.signUp(adminUserBean);

			model.addAttribute("admin", adminUserBean);

			return "redirect:/admin/index";
		} else {
			return "redirect:/admin/adminUser/sign-up";
		}
	}

	/**
	 * 修改管理員資料
	 * 
	 * @param admin-->Session
	 * @param adminUserBean-->AdminUserBean
	 * @return /WEB-INF/views/admin/index.jsp
	 */
	@RequestMapping(path = "/admin/adminUser/update.do", method = RequestMethod.POST)
	public String updateProcess(@ModelAttribute("admin") AdminUserBean admin, AdminUserBean adminUserBean) {

		adminUserBean.setAdu_id(admin.getAdu_id());
		adminUserService.update(adminUserBean);

		return "redirect:/admin/index";
	}

	/**
	 * 修改管理員密碼
	 * 
	 * @param au_password-->舊密碼(原碼)
	 * @param au_password_new-->新密碼(原碼)
	 * @param admin-->Session
	 * @return /WEB-INF/views/admin/index.jsp
	 * @return /WEB-INF/views/admin/adminUser/update-password.jsp
	 */
	@RequestMapping(path = "/admin/adminUser/updata-password.do", method = RequestMethod.POST)
	public String updatePasswordProcess(@RequestParam(name = "au_password") String au_password,
			@RequestParam(name = "au_password_new") String au_password_new,
			@ModelAttribute("admin") AdminUserBean admin) {

		String oldHashedPassword = adminUserService.selectByAdu_id(admin.getAdu_id()).getAdu_password();
		String inputOldHashedPassword = adminUserService.getHashedPassword(au_password, admin.getAdu_salt());

		if (oldHashedPassword.equals(inputOldHashedPassword)) {

			adminUserService.updateAdu_password(admin.getAdu_id(), au_password_new, admin.getAdu_salt());

			return "redirect:/admin/index";
		} else {
			return "admin/adminUser/update-password";
		}
	}

}
