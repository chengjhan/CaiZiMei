/*
 * CaiZiMei
 * File: AdminController.java
 * Author: 詹晟
 * Date: 2017/7/17
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

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

import com.czmbeauty.model.entity.AdminBean;
import com.czmbeauty.model.entity.AdminLogBean;
import com.czmbeauty.model.service.AdminLogService;
import com.czmbeauty.model.service.AdminService;

import misc.PrimitiveNumberEditor;

/**
 * admin controller
 * 
 * @author 詹晟
 */
@Controller
@SessionAttributes(value = { "admin", "ad_email", "adminList" })
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
	 * 提供 form backing object 資料轉換
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Integer.class, new PrimitiveNumberEditor(Integer.class, true));
		webDataBinder.registerCustomEditor(Double.class, new PrimitiveNumberEditor(Double.class, true));
		webDataBinder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(simpleDateFormat, true));
	}

	/**
	 * 註冊 - 采姿美管理系統
	 * 
	 * @return /WEB-INF/views/admin/sign-up.jsp
	 */
	@RequestMapping(value = "/admin/sign-up", method = RequestMethod.GET)
	public String signUpView(Model model) {

		// 新增 form backing object
		model.addAttribute("adminBean", new AdminBean());

		return "admin/sign-up";
	}

	/**
	 * 註冊 - submit
	 * 
	 * @param adminBean-->form-backing-object
	 * @param request-->HttpServletRequest
	 * @param model-->Model
	 * @return /WEB-INF/views/index.jsp
	 * @return /WEB-INF/views/admin/sign-up.jsp
	 */
	@RequestMapping(value = "/admin/sign-up.do", method = RequestMethod.POST)
	public String signUpProcess(AdminBean adminBean, HttpServletRequest request, Model model) {

		if (adminService.selectByAd_username(adminBean.getAd_username()) == null) {

			String ad_salt = adminService.getSalt();

			adminBean.setAd_salt(ad_salt);
			adminBean.setAd_password(adminService.getHashedPassword(adminBean.getAd_password(), ad_salt));
			adminBean.setAd_signup_time(new java.util.Date());
			adminBean.setAd_signin_number(1);
			adminBean.setAd_signin_ip(request.getRemoteAddr());
			adminBean.setAd_signin_time(new java.util.Date());
			adminBean.setAd_update_pwd_time(new java.util.Date());
			adminBean.setAd_update_info_time(new java.util.Date());
			adminBean.setAd_status(1);
			adminBean.setAd_status_time(new java.util.Date());
			adminService.signUp(adminBean);

			// 放入 Session
			model.addAttribute("admin", adminBean);

			// 註冊成功
			return "redirect:/";
		} else {

			// 帳號重複
			return "redirect:/admin/sign-up";
		}
	}

	/**
	 * 個人資訊 - 采姿美管理系統
	 * 
	 * @return /WEB-INF/views/admin/profile.jsp
	 */
	@RequestMapping(value = "/admin/profile", method = RequestMethod.GET)
	public String profileView() {

		return "admin/profile";
	}

	/**
	 * 編輯個人資訊 - 采姿美管理系統
	 * 
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/edit.jsp
	 */
	@RequestMapping(value = "/admin/edit", method = RequestMethod.GET)
	public String editView(Model model) {

		model.addAttribute("admin");

		return "admin/edit";
	}

	/**
	 * 編輯個人資訊 - submit
	 * 
	 * @param admin-->Session
	 * @param adminBean-->form-backing-object
	 * @return /WEB-INF/views/admin/profile.jsp
	 */
	@RequestMapping(value = "/admin/edit.do", method = RequestMethod.POST)
	public String editProcess(@ModelAttribute("admin") AdminBean admin, AdminBean adminBean) {

		adminBean.setAd_id(admin.getAd_id());
		adminService.update(adminBean);

		return "redirect:/admin/profile";
	}

	/**
	 * 變更密碼 - 采姿美管理系統
	 * 
	 * @return /WEB-INF/views/admin/change-password.jsp
	 */
	@RequestMapping(value = "/admin/change-password", method = RequestMethod.GET)
	public String changePasswordView() {

		return "admin/change-password";
	}

	/**
	 * 變更密碼 - submit
	 * 
	 * @param ad_password-->舊密碼(原碼)
	 * @param ad_password_new-->新密碼(原碼)
	 * @param admin-->Session
	 * @return /WEB-INF/views/index.jsp
	 * @return /WEB-INF/views/admin/change-password.jsp
	 */
	@RequestMapping(value = "/admin/change-password.do", method = RequestMethod.POST)
	public String changePasswordProcess(@RequestParam(name = "ad_password") String ad_password,
			@RequestParam(name = "ad_password_new") String ad_password_new, @ModelAttribute("admin") AdminBean admin) {

		String oldHashedPassword = adminService.selectByAd_id(admin.getAd_id()).getAd_password();
		String inputOldHashedPassword = adminService.getHashedPassword(ad_password, admin.getAd_salt());

		if (oldHashedPassword.equals(inputOldHashedPassword)) {

			adminService.updateAd_password(admin.getAd_id(), ad_password_new, admin.getAd_salt());

			// 變更成功
			return "redirect:/";
		} else {

			// 密碼輸入錯誤
			return "admin/change-password";
		}
	}

	/**
	 * 登入 - 采姿美管理系統
	 * 
	 * @return /WEB-INF/views/secure/sign-in.jsp
	 */
	@RequestMapping(value = "/secure/sign-in", method = RequestMethod.GET)
	public String signInView() {

		return "secure/sign-in";
	}

	/**
	 * 登入 - submit
	 * 
	 * @param ad_username-->管理員帳號
	 * @param ad_password-->管理員密碼(原碼)
	 * @param request-->HttpServletRequest
	 * @param model-->Model
	 * @return /WEB-INF/views/index.jsp
	 * @return /WEB-INF/views/secure/sign-in.jsp
	 * @return /WEB-INF/views/secure/sign-in.jsp
	 */
	@RequestMapping(value = "/secure/sign-in.do", method = RequestMethod.POST)
	public String signInProcess(@RequestParam(name = "ad_username") String ad_username,
			@RequestParam(name = "ad_password") String ad_password, HttpServletRequest request, Model model) {

		AdminBean adminBean = adminService.selectByAd_username(ad_username);

		if (adminBean != null) {

			if (adminService.signIn(ad_username, ad_password)) {

				// 更新登入資訊
				adminService.updateAd_signin_ip(adminBean.getAd_id(), request.getRemoteAddr());
				adminService.updateAd_signin_time(adminBean.getAd_id());
				model.addAttribute("admin", adminService.selectByAd_username(ad_username));

				// 寫入日誌
				AdminLogBean adminLogBean = new AdminLogBean();
				adminLogBean.setAl_AdminBean(adminBean);
				adminLogBean.setAl_operation("登入");
				adminLogBean.setAl_ip(request.getRemoteAddr());
				adminLogService.insert(adminLogBean);

				// 登入成功
				return "redirect:/";
			} else {

				// 密碼錯誤
				model.addAttribute("error", "帳號或密碼錯誤");

				// 登入失敗
				return "secure/sign-in";
			}
		} else {

			// 帳號錯誤
			model.addAttribute("error", "帳號或密碼錯誤");

			// 登入失敗
			return "secure/sign-in";
		}
	}

	/**
	 * 忘記密碼 - 采姿美管理系統
	 * 
	 * @return /WEB-INF/views/secure/forget-password.jsp
	 */
	@RequestMapping(value = "/secure/forget-password", method = RequestMethod.GET)
	public String forgetPasswordView() {

		return "secure/forget-password";
	}

	/**
	 * 忘記密碼 - submit
	 * 
	 * @param ad_email-->管理員信箱
	 * @param model-->Model
	 * @return /WEB-INF/views/secure/reset-password.jsp
	 * @return /WEB-INF/views/secure/forget-password.jsp
	 */
	@RequestMapping(value = "/secure/forget-password.do", method = RequestMethod.POST)
	public String forgetPasswordProcess(@RequestParam(name = "ad_email") String ad_email, Model model) {

		AdminBean adminBean = adminService.selectByAd_email(ad_email);

		if (adminBean != null) {

			int random = (int) (Math.random() * 1000000);
			String ad_password_random = String.valueOf(random);

			adminService.updateAd_password(adminBean.getAd_id(), ad_password_random, adminBean.getAd_salt());

			String to = adminBean.getAd_email();
			String from = "chengjhan@gmail.com";
			String subject = "采姿美管理系統";
			String text = "您的驗證碼為：" + ad_password_random + "。";
			adminService.sendEmail(to, from, subject, text);

			model.addAttribute("ad_email", to);

			// 發送成功
			return "redirect:/secure/reset-password";
		} else {

			// 信箱輸入錯誤
			return "secure/forget-password";
		}
	}

	/**
	 * 重設密碼 - 采姿美管理系統
	 * 
	 * @return /WEB-INF/views/secure/reset-password.jsp
	 */
	@RequestMapping(value = "/secure/reset-password", method = RequestMethod.GET)
	public String resetPasswordView() {

		return "secure/reset-password";
	}

	/**
	 * 重設密碼 - submit
	 * 
	 * @param ad_password-->驗證碼(原碼)
	 * @param ad_password_new-->新密碼(原碼)
	 * @param ad_email-->Session
	 * @param sessionStatus-->SessionStatus
	 * @return /WEB-INF/views/secure/sign-in.jsp
	 * @return /WEB-INF/views/secure/reset-password.jsp
	 */
	@RequestMapping(value = "/secure/reset-password.do", method = RequestMethod.POST)
	public String resetPasswordProcess(@RequestParam(name = "ad_password") String ad_password,
			@RequestParam(name = "ad_password_new") String ad_password_new, @ModelAttribute("ad_email") String ad_email,
			SessionStatus sessionStatus) {

		AdminBean adminBean = adminService.selectByAd_email(ad_email);

		String oldHashedPassword = adminService.selectByAd_id(adminBean.getAd_id()).getAd_password();
		String inputOldHashedPassword = adminService.getHashedPassword(ad_password, adminBean.getAd_salt());

		if (oldHashedPassword.equals(inputOldHashedPassword)) {

			adminService.updateAd_password(adminBean.getAd_id(), ad_password_new, adminBean.getAd_salt());

			// 清除 @SessionAttributes
			sessionStatus.setComplete();

			// 重設成功
			return "redirect:/secure/sign-in";
		} else {

			// 驗證碼輸入錯誤
			return "secure/reset-password";
		}
	}

	/**
	 * 登出
	 * 
	 * @param admin-->Session
	 * @param request-->HttpServletRequest
	 * @param sessionStatus-->SessionStatus
	 * @return /WEB-INF/views/index.jsp
	 * @return /WEB-INF/views/index.jsp
	 */
	@RequestMapping(value = "/secure/sign-out", method = RequestMethod.GET)
	public String signOutProcess(@ModelAttribute("admin") AdminBean admin, HttpServletRequest request,
			SessionStatus sessionStatus) {

		if (admin != null) {

			// 寫入日誌
			AdminLogBean adminLogBean = new AdminLogBean();
			adminLogBean.setAl_AdminBean(admin);
			adminLogBean.setAl_operation("登出");
			adminLogBean.setAl_ip(request.getRemoteAddr());
			adminLogService.insert(adminLogBean);

			// 清除 @SessionAttributes
			sessionStatus.setComplete();

			// 有登入狀態
			return "redirect:/";
		} else {

			// 未登入狀態
			return "redirect:/";
		}

	}

	/**
	 * 管理員一覽 - 采姿美管理系統
	 * 
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/list.jsp
	 */
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	public String listView(Model model) {

		model.addAttribute("adminList", adminService.selectAll());

		return "admin/list";
	}

	/**
	 * 帳戶開關 - submit
	 * 
	 * @param adminBean-->form-backing-object
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/list.jsp
	 */
	@RequestMapping(value = "/admin/switch", method = RequestMethod.GET)
	public String switchProcess(AdminBean adminBean, Model model) {

		adminService.updateAd_status(adminBean.getAd_id());

		return "redirect:/admin/list";
	}

}
