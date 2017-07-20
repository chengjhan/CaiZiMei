/*
 * CaiZiMei
 * File: AdminController.java
 * Author: 詹晟
 * Date: 2017/7/20
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import static com.czmbeauty.common.constants.MailConstants.FORGET_PASSWORD_MAIL_FORM;
import static com.czmbeauty.common.constants.MailConstants.FORGET_PASSWORD_MAIL_SUBJECT;
import static com.czmbeauty.common.constants.ModelAttributeConstants.ADMIN;
import static com.czmbeauty.common.constants.ModelAttributeConstants.ADMIN_BEAN;
import static com.czmbeauty.common.constants.ModelAttributeConstants.ADMIN_EMAIL;
import static com.czmbeauty.common.constants.ModelAttributeConstants.ADMIN_LIST;
import static com.czmbeauty.common.constants.ModelAttributeConstants.ERROR;
import static com.czmbeauty.common.constants.PageNameConstants.ADMIN_CHANGE_PASSWORD_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.ADMIN_EDIT_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.ADMIN_FORGET_PASSWORD_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.ADMIN_LIST_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.ADMIN_PROFILE_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.ADMIN_RESET_PASSWORD_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.ADMIN_SIGN_IN_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.ADMIN_SIGN_UP_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.INDEX_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.REDIRECT;

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

import com.czmbeauty.common.editor.PrimitiveNumberEditor;
import com.czmbeauty.common.mail.SendMail;
import com.czmbeauty.common.util.CryptographicHashFunction;
import com.czmbeauty.model.entity.AdminBean;
import com.czmbeauty.model.entity.AdminLogBean;
import com.czmbeauty.model.service.AdminLogService;
import com.czmbeauty.model.service.AdminService;

/**
 * admin controller
 * 
 * @author 詹晟
 */
@Controller
@SessionAttributes(value = { ADMIN, ADMIN_EMAIL })
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
	 * 注入 SendMail
	 */
	@Autowired
	private SendMail sendMail;

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
		model.addAttribute(ADMIN_BEAN, new AdminBean());

		return ADMIN_SIGN_UP_PAGE;
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

			String ad_salt = CryptographicHashFunction.getSalt();

			adminBean.setAd_salt(ad_salt);
			adminBean.setAd_password(CryptographicHashFunction.getHashedPassword(adminBean.getAd_password(), ad_salt));
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
			model.addAttribute(ADMIN, adminBean);

			// 註冊成功
			return INDEX_PAGE;
		} else {

			// 帳號重複
			return REDIRECT + ADMIN_SIGN_UP_PAGE;
		}
	}

	/**
	 * 個人資訊 - 采姿美管理系統
	 * 
	 * @return /WEB-INF/views/admin/profile.jsp
	 */
	@RequestMapping(value = "/admin/profile", method = RequestMethod.GET)
	public String profileView() {

		return ADMIN_PROFILE_PAGE;
	}

	/**
	 * 編輯個人資訊 - 采姿美管理系統
	 * 
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/edit.jsp
	 */
	@RequestMapping(value = "/admin/edit", method = RequestMethod.GET)
	public String editView(Model model) {

		model.addAttribute(ADMIN);

		return ADMIN_EDIT_PAGE;
	}

	/**
	 * 編輯個人資訊 - submit
	 * 
	 * @param admin-->Session
	 * @param adminBean-->form-backing-object
	 * @return /WEB-INF/views/admin/profile.jsp
	 */
	@RequestMapping(value = "/admin/edit.do", method = RequestMethod.POST)
	public String editProcess(@ModelAttribute(ADMIN) AdminBean admin, AdminBean adminBean) {

		adminBean.setAd_id(admin.getAd_id());
		adminService.update(adminBean);

		return REDIRECT + ADMIN_PROFILE_PAGE;
	}

	/**
	 * 變更密碼 - 采姿美管理系統
	 * 
	 * @return /WEB-INF/views/admin/change-password.jsp
	 */
	@RequestMapping(value = "/admin/change-password", method = RequestMethod.GET)
	public String changePasswordView() {

		return ADMIN_CHANGE_PASSWORD_PAGE;
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
			@RequestParam(name = "ad_password_new") String ad_password_new, @ModelAttribute(ADMIN) AdminBean admin) {

		String oldHashedPassword = adminService.selectByAd_id(admin.getAd_id()).getAd_password();
		String inputOldHashedPassword = CryptographicHashFunction.getHashedPassword(ad_password, admin.getAd_salt());

		if (oldHashedPassword.equals(inputOldHashedPassword)) {

			adminService.updateAd_password(admin.getAd_id(), ad_password_new, admin.getAd_salt());

			// 變更成功
			return INDEX_PAGE;
		} else {

			// 密碼輸入錯誤
			return ADMIN_CHANGE_PASSWORD_PAGE;
		}
	}

	/**
	 * 登入 - 采姿美管理系統
	 * 
	 * @return /WEB-INF/views/secure/sign-in.jsp
	 */
	@RequestMapping(value = "/secure/sign-in", method = RequestMethod.GET)
	public String signInView() {

		return ADMIN_SIGN_IN_PAGE;
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
				model.addAttribute(ADMIN, adminService.selectByAd_username(ad_username));

				// 寫入日誌
				AdminLogBean adminLogBean = new AdminLogBean();
				adminLogBean.setAl_AdminBean(adminBean);
				adminLogBean.setAl_operation("登入");
				adminLogBean.setAl_ip(request.getRemoteAddr());
				adminLogService.insert(adminLogBean);

				// 登入成功
				return INDEX_PAGE;
			} else {

				// 密碼錯誤
				model.addAttribute(ERROR, "帳號或密碼錯誤");

				// 登入失敗
				return ADMIN_SIGN_IN_PAGE;
			}
		} else {

			// 帳號錯誤
			model.addAttribute(ERROR, "帳號或密碼錯誤");

			// 登入失敗
			return ADMIN_SIGN_IN_PAGE;
		}
	}

	/**
	 * 忘記密碼 - 采姿美管理系統
	 * 
	 * @return /WEB-INF/views/secure/forget-password.jsp
	 */
	@RequestMapping(value = "/secure/forget-password", method = RequestMethod.GET)
	public String forgetPasswordView() {

		return ADMIN_FORGET_PASSWORD_PAGE;
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
	public String forgetPasswordProcess(@RequestParam(name = ADMIN_EMAIL) String ad_email, Model model) {

		AdminBean adminBean = adminService.selectByAd_email(ad_email);

		if (adminBean != null) {

			int random = (int) (Math.random() * 1000000);
			String ad_password_random = String.valueOf(random);

			adminService.updateAd_password(adminBean.getAd_id(), ad_password_random, adminBean.getAd_salt());

			String to = adminBean.getAd_email();
			String from = FORGET_PASSWORD_MAIL_FORM;
			String subject = FORGET_PASSWORD_MAIL_SUBJECT;
			String text = "您的驗證碼為：" + ad_password_random + "。";
			sendMail.sendMail(to, from, subject, text);

			model.addAttribute(ADMIN_EMAIL, to);

			// 發送成功
			return REDIRECT + ADMIN_RESET_PASSWORD_PAGE;
		} else {

			// 信箱輸入錯誤
			return ADMIN_FORGET_PASSWORD_PAGE;
		}
	}

	/**
	 * 重設密碼 - 采姿美管理系統
	 * 
	 * @return /WEB-INF/views/secure/reset-password.jsp
	 */
	@RequestMapping(value = "/secure/reset-password", method = RequestMethod.GET)
	public String resetPasswordView() {

		return ADMIN_RESET_PASSWORD_PAGE;
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
			@RequestParam(name = "ad_password_new") String ad_password_new,
			@ModelAttribute(ADMIN_EMAIL) String ad_email, SessionStatus sessionStatus) {

		AdminBean adminBean = adminService.selectByAd_email(ad_email);

		String oldHashedPassword = adminService.selectByAd_id(adminBean.getAd_id()).getAd_password();
		String inputOldHashedPassword = CryptographicHashFunction.getHashedPassword(ad_password,
				adminBean.getAd_salt());

		if (oldHashedPassword.equals(inputOldHashedPassword)) {

			adminService.updateAd_password(adminBean.getAd_id(), ad_password_new, adminBean.getAd_salt());

			// 清除 @SessionAttributes
			sessionStatus.setComplete();

			// 重設成功
			return REDIRECT + ADMIN_SIGN_IN_PAGE;
		} else {

			// 驗證碼輸入錯誤
			return ADMIN_RESET_PASSWORD_PAGE;
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
	public String signOutProcess(@ModelAttribute(ADMIN) AdminBean admin, HttpServletRequest request,
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
			return INDEX_PAGE;
		} else {

			// 未登入狀態
			return INDEX_PAGE;
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

		model.addAttribute(ADMIN_LIST, adminService.selectAll());

		return ADMIN_LIST_PAGE;
	}

	/**
	 * 帳戶開關 - submit
	 * 
	 * @param adminBean-->form-backing-object-->GET-->ad_id
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/list.jsp
	 */
	@RequestMapping(value = "/admin/switch", method = RequestMethod.GET)
	public String switchProcess(AdminBean adminBean, Model model) {

		adminService.updateAd_status(adminBean.getAd_id());

		return REDIRECT + ADMIN_LIST_PAGE;
	}

}
