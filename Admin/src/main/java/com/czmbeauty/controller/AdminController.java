/*
 * CaiZiMei
 * File: AdminController.java
 * Author: 詹晟
 * Date: 2017/8/2
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
	 * @param ad_password_again
	 *            String --> 重複密碼(原碼)
	 * @return /WEB-INF/views/index.jsp
	 * @return /WEB-INF/views/admin/sign-up.jsp
	 */
	// 得到 <form:form modelAttribute="adminBean"> 表單新增的資料
	@RequestMapping(value = "/admin/sign-up.do", method = RequestMethod.POST)
	public String signUpProcess(AdminBean adminBean, @RequestParam String ad_password_again) {

		if (adminService.selectByAd_username(adminBean.getAd_username()) == null) {

			if (adminBean.getAd_password().equals(ad_password_again)) {

				adminService.signUp(adminBean);

				logger.info("註冊成功。");

				// 註冊成功
				return INDEX_PAGE;
			} else {

				logger.error("密碼重複錯誤，註冊失敗。");

				// 密碼重複錯誤，註冊失敗
				return REDIRECT + ADMIN_SIGN_UP_PAGE;
			}
		} else {

			logger.error("帳號重複，註冊失敗。");

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
	 * @param ad_password_new_again
	 *            String --> 重複新密碼(原碼)
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/index.jsp
	 * @return /WEB-INF/views/admin/change-password.jsp
	 * @return /WEB-INF/views/admin/change-password.jsp
	 */
	@RequestMapping(value = "/admin/change-password.do", method = RequestMethod.POST)
	public String changePasswordProcess(@ModelAttribute(ADMIN) AdminBean admin, @RequestParam String ad_password_old,
			@RequestParam String ad_password_new, @RequestParam String ad_password_new_again, Model model) {

		if (ad_password_new.equals(ad_password_new_again)) {

			if (adminService.updateAd_password(admin, ad_password_old, ad_password_new) != null) {

				logger.info("密碼變更成功。");

				// 密碼變更成功
				return INDEX_PAGE;
			} else {

				model.addAttribute(ERROR, "密碼錯誤");

				logger.error("密碼錯誤，變更失敗。");

				// 密碼錯誤，變更失敗
				return ADMIN_CHANGE_PASSWORD_PAGE;
			}
		} else {

			model.addAttribute(ERROR, "新密碼重複錯誤");

			logger.error("新密碼重複錯誤，變更失敗。");

			// 新密碼重複錯誤，變更失敗
			return ADMIN_CHANGE_PASSWORD_PAGE;
		}
	}

	/**
	 * 登入 - 初期處理
	 * 
	 * @param next
	 *            String --> SigninInterceptor --> GET --> 原請求畫面
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/secure/sign-in.jsp
	 */
	@RequestMapping(value = "/secure/sign-in", method = RequestMethod.GET)
	public String signInView(String next, Model model) {

		logger.info("進入登入畫面: " + ADMIN_SIGN_IN_PAGE);

		// 若經過 SigninInterceptor
		if (next != null) {

			// 放入 Session
			model.addAttribute(NEXT_PAGE, next);

			logger.info("原請求畫面: " + next);
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
	public String signInProcess(@RequestParam String ad_username, @RequestParam String ad_password,
			HttpServletRequest request, Model model) {

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

				logger.info("登入成功，導向首頁: index");

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
	public String forgetPasswordProcess(@RequestParam String ad_email, Model model) {

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

			model.addAttribute(ERROR, "信箱錯誤");

			// 信箱錯誤，發送失敗
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
	 * @param ad_password_new_again
	 *            String --> 重複新密碼(原碼)
	 * @param sessionStatus
	 *            SessionStatus
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/secure/sign-in.jsp
	 * @return /WEB-INF/views/secure/reset-password.jsp
	 */
	@RequestMapping(value = "/secure/reset-password.do", method = RequestMethod.POST)
	public String resetPasswordProcess(@ModelAttribute(ADMIN_EMAIL) String ad_email,
			@RequestParam String ad_password_random, @RequestParam String ad_password_new,
			@RequestParam String ad_password_new_again, SessionStatus sessionStatus, Model model) {

		if (ad_password_new.equals(ad_password_new_again)) {

			AdminBean adminBean = adminService.selectByAd_email(ad_email);

			if (adminService.updateAd_password(adminBean, ad_password_random, ad_password_new) != null) {

				// 清除 @SessionAttributes
				sessionStatus.setComplete();

				logger.info("密碼重設成功。");

				// 密碼重設成功
				return REDIRECT + ADMIN_SIGN_IN_PAGE;
			} else {

				model.addAttribute(ERROR, "驗證碼錯誤");

				logger.error("驗證碼錯誤，重設失敗。");

				// 驗證碼錯誤，重設失敗
				return ADMIN_RESET_PASSWORD_PAGE;
			}
		} else {

			model.addAttribute(ERROR, "新密碼重複錯誤");

			logger.error("新密碼重複錯誤，重設失敗。");

			// 新密碼重複錯誤，重設失敗
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

		logger.info("登出成功。");

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
	 * 帳號重複驗證 (AJAX)
	 * 
	 * @param ad_username
	 *            String --> 管理員帳號
	 * @return 1
	 * @return 0
	 */
	@RequestMapping(value = "/admin/username-repeat.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String usernameRepeatAjaxProcess(String ad_username) {

		AdminBean adminBean = adminService.selectByAd_username(ad_username);

		if (adminBean != null) {

			// 已使用
			return "1";
		} else {

			// 未使用
			return "0";
		}
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
