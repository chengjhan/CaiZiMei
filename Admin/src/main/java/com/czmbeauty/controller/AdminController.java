/*
 * CaiZiMei
 * File: AdminController.java
 * Author: 詹晟
 * Date: 2017/7/30
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
import static com.czmbeauty.common.constants.PageNameConstants.NEXT_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.REDIRECT;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.czmbeauty.common.mail.SendMail;
import com.czmbeauty.model.entity.AdminBean;
import com.czmbeauty.model.entity.AdminLogBean;
import com.czmbeauty.model.service.AdminLogService;
import com.czmbeauty.model.service.AdminService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * admin controller
 * 
 * @author 詹晟
 */
@Controller
@SessionAttributes(value = { ADMIN, ADMIN_EMAIL, NEXT_PAGE })
public class AdminController {

	private static final Logger logger = Logger.getLogger(AdminController.class);

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
	 * 注入 SendMail
	 */
	@Autowired
	private SendMail sendMail;

	/**
	 * 註冊 - 初期處理
	 * 
	 * @param model
	 *            Model
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
	 * @param adminBean
	 *            AdminBean --> form backing object
	 * @return /WEB-INF/views/index.jsp
	 * @return /WEB-INF/views/admin/sign-up.jsp
	 */
	// 得到 <form:form modelAttribute="adminBean"> 表單新增的資料
	@RequestMapping(value = "/admin/sign-up.do", method = RequestMethod.POST)
	public String signUpProcess(AdminBean adminBean) {

		if (adminService.selectByAd_username(adminBean.getAd_username()) == null) {

			adminService.signUp(adminBean);

			// 註冊成功
			return INDEX_PAGE;
		} else {

			// 帳號重複，註冊失敗
			return REDIRECT + ADMIN_SIGN_UP_PAGE;
		}
	}

	/**
	 * 個人資訊 - 初期處理
	 * 
	 * @return /WEB-INF/views/admin/profile.jsp
	 */
	@RequestMapping(value = "/admin/profile", method = RequestMethod.GET)
	public String profileView() {

		return ADMIN_PROFILE_PAGE;
	}

	/**
	 * 編輯個人資訊 - 初期處理
	 * 
	 * @return /WEB-INF/views/admin/edit.jsp
	 */
	// <form:form modelAttribute="admin"> 表單得到 Session 的資料，自動回填
	@RequestMapping(value = "/admin/edit", method = RequestMethod.GET)
	public String editView() {

		return ADMIN_EDIT_PAGE;
	}

	/**
	 * 編輯個人資訊 - submit
	 * 
	 * @param admin
	 *            AdminBean --> form backing object
	 * @return /WEB-INF/views/admin/profile.jsp
	 */
	// 得到 <form:form modelAttribute="admin"> 表單更新的資料
	@RequestMapping(value = "/admin/edit.do", method = RequestMethod.POST)
	public String editProcess(@ModelAttribute(ADMIN) AdminBean admin) {

		adminService.update(admin);

		return REDIRECT + ADMIN_PROFILE_PAGE;
	}

	/**
	 * 變更密碼 - 初期處理
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
	 * @param admin
	 *            AdminBean --> Session
	 * @param ad_password_old
	 *            String --> 舊密碼(原碼)
	 * @param ad_password_new
	 *            String --> 新密碼(原碼)
	 * @return /WEB-INF/views/index.jsp
	 * @return /WEB-INF/views/admin/change-password.jsp
	 */
	@RequestMapping(value = "/admin/change-password.do", method = RequestMethod.POST)
	public String changePasswordProcess(@ModelAttribute(ADMIN) AdminBean admin,
			@RequestParam(name = "ad_password_old") String ad_password_old,
			@RequestParam(name = "ad_password_new") String ad_password_new) {

		if (adminService.updateAd_password(admin, ad_password_old, ad_password_new) != null) {

			// 變更成功
			return INDEX_PAGE;
		} else {

			// 密碼輸入錯誤，變更失敗
			return ADMIN_CHANGE_PASSWORD_PAGE;
		}
	}

	/**
	 * 登入 - 初期處理
	 * 
	 * @param next
	 *            SigninInterceptor --> GET --> 原請求畫面
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/secure/sign-in.jsp
	 */
	@RequestMapping(value = "/secure/sign-in", method = RequestMethod.GET)
	public String signInView(String next, Model model) {

		logger.info("進入登入畫面: " + ADMIN_SIGN_IN_PAGE);

		// 若經過 SigninInterceptor
		if (next != null) {

			logger.info("原請求畫面: " + next);

			// 放入 Session
			model.addAttribute(NEXT_PAGE, next);
		}
		return ADMIN_SIGN_IN_PAGE;
	}

	/**
	 * 登入 - submit
	 * 
	 * @param ad_username
	 *            String --> 管理員帳號
	 * @param ad_password
	 *            String --> 管理員密碼(原碼)
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/next
	 * @return /WEB-INF/views/index.jsp
	 * @return /WEB-INF/views/secure/sign-in.jsp
	 */
	@RequestMapping(value = "/secure/sign-in.do", method = RequestMethod.POST)
	public String signInProcess(@RequestParam(name = "ad_username") String ad_username,
			@RequestParam(name = "ad_password") String ad_password, HttpServletRequest request, Model model) {

		AdminBean adminBean = adminService.signIn(ad_username, ad_password);

		if (adminBean != null) {

			// 更新登入資訊
			adminBean.setAd_signin_number(adminBean.getAd_signin_number() + 1);
			adminBean.setAd_signin_ip(request.getRemoteAddr());
			adminBean.setAd_signin_time(new java.util.Date());

			// 放入 Session
			model.addAttribute(ADMIN, adminBean);

			// 寫入日誌
			AdminLogBean adminLogBean = new AdminLogBean();
			adminLogBean.setAl_AdminBean(adminBean);
			adminLogBean.setAl_operation("登入");
			adminLogBean.setAl_ip(request.getRemoteAddr());
			adminLogService.insert(adminLogBean);

			HttpSession session = request.getSession();
			String next = (String) session.getAttribute(NEXT_PAGE);

			// 若經過 SigninInterceptor
			if (next != null) {

				logger.info("登入成功，導向原請求畫面: " + next);

				// 登入成功，導向原請求畫面
				return REDIRECT.concat(next);
			} else {

				logger.info("登入成功，導向畫面: index");

				// 登入成功
				return INDEX_PAGE;
			}
		} else {

			model.addAttribute(ERROR, "帳號或密碼錯誤");

			logger.error("登入失敗，導向畫面: " + ADMIN_SIGN_IN_PAGE);

			// 登入失敗
			return ADMIN_SIGN_IN_PAGE;
		}
	}

	/**
	 * 忘記密碼 - 初期處理
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
	 * @param ad_email
	 *            String --> 管理員信箱
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/secure/reset-password.jsp
	 * @return /WEB-INF/views/secure/forget-password.jsp
	 */
	@RequestMapping(value = "/secure/forget-password.do", method = RequestMethod.POST)
	public String forgetPasswordProcess(@RequestParam(name = ADMIN_EMAIL) String ad_email, Model model) {

		AdminBean adminBean = adminService.selectByAd_email(ad_email);

		if (adminBean != null) {

			int random = (int) (Math.random() * 1000000);
			String ad_password_random = String.format("%06d", random);

			adminService.updateAd_password(adminBean, ad_password_random);

			String to = adminBean.getAd_email();
			String from = FORGET_PASSWORD_MAIL_FORM;
			String subject = FORGET_PASSWORD_MAIL_SUBJECT;
			String text = "您的驗證碼為：" + ad_password_random + "。";
			sendMail.sendMail(to, from, subject, text);

			// 將管理員 email 放入 Session
			model.addAttribute(ADMIN_EMAIL, to);

			// 發送成功
			return REDIRECT + ADMIN_RESET_PASSWORD_PAGE;
		} else {

			// 信箱輸入錯誤
			return ADMIN_FORGET_PASSWORD_PAGE;
		}
	}

	/**
	 * 重設密碼 - 初期處理
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
	 * @param ad_email
	 *            String --> Session
	 * @param ad_password_random
	 *            String --> 驗證碼(原碼)
	 * @param ad_password_new
	 *            String --> 新密碼(原碼)
	 * @param sessionStatus
	 *            SessionStatus
	 * @return /WEB-INF/views/secure/sign-in.jsp
	 * @return /WEB-INF/views/secure/reset-password.jsp
	 */
	@RequestMapping(value = "/secure/reset-password.do", method = RequestMethod.POST)
	public String resetPasswordProcess(@ModelAttribute(ADMIN_EMAIL) String ad_email,
			@RequestParam(name = "ad_password_random") String ad_password_random,
			@RequestParam(name = "ad_password_new") String ad_password_new, SessionStatus sessionStatus) {

		AdminBean adminBean = adminService.selectByAd_email(ad_email);

		if (adminService.updateAd_password(adminBean, ad_password_random, ad_password_new) != null) {

			// 清除 @SessionAttributes
			sessionStatus.setComplete();

			// 重設成功
			return REDIRECT + ADMIN_SIGN_IN_PAGE;
		} else {

			// 驗證碼輸入錯誤，重設失敗
			return ADMIN_RESET_PASSWORD_PAGE;
		}
	}

	/**
	 * 登出
	 * 
	 * @param admin
	 *            AdminBean --> Session
	 * @param request
	 *            HttpServletRequest
	 * @param sessionStatus
	 *            SessionStatus
	 * @return /WEB-INF/views/index.jsp
	 */
	@RequestMapping(value = "/secure/sign-out", method = RequestMethod.GET)
	public String signOutProcess(@ModelAttribute(ADMIN) AdminBean admin, HttpServletRequest request,
			SessionStatus sessionStatus) {

		// 寫入日誌
		AdminLogBean adminLogBean = new AdminLogBean();
		adminLogBean.setAl_AdminBean(admin);
		adminLogBean.setAl_operation("登出");
		adminLogBean.setAl_ip(request.getRemoteAddr());
		adminLogService.insert(adminLogBean);

		// 清除 @SessionAttributes
		sessionStatus.setComplete();

		return INDEX_PAGE;
	}

	/**
	 * 管理員一覽 - 初期處理
	 * 
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/admin/list.jsp
	 */
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	public String listView(Model model) {

		// 取得所有管理員 List，放入 table
		model.addAttribute(ADMIN_LIST, adminService.selectAll());

		return ADMIN_LIST_PAGE;
	}

	/**
	 * 帳戶開關 (AJAX)
	 * 
	 * @param ad_id
	 *            String --> 管理員流水號
	 * @return AdminBean JSON
	 */
	@RequestMapping(value = "/admin/switch.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String switchAjaxProcess(String ad_id) {

		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
		Gson gson = builder.create();

		AdminBean result = adminService.updateAd_status(Integer.valueOf(ad_id));

		String json = gson.toJson(result);

		logger.info("JSON = " + json);

		return json;
	}

}
