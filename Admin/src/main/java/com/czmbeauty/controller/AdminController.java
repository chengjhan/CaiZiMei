/*
 * CaiZiMei
 * File: AdminController.java
 * Author: 詹晟
 * Date: 2017/9/4
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
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
@SessionAttributes(value = { ADMIN, ADMIN_EMAIL })
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

		logger.info("進入註冊頁面: " + ADMIN_SIGN_UP_PAGE);

		// 新增 form backing object
		model.addAttribute(ADMIN_BEAN, new AdminBean());

		return ADMIN_SIGN_UP_PAGE;
	}

	/**
	 * 註冊 - submit
	 * 
	 * @param ad_password_again
	 *            String --> 重複密碼(原碼)
	 * @param adminBean
	 *            AdminBean --> form backing object
	 * @param bindingResult
	 *            BindingResult
	 * @return /WEB-INF/views/admin/sign-up.jsp
	 * @return /WEB-INF/views/admin/sign-up.jsp
	 * @return /WEB-INF/views/admin/sign-up.jsp
	 * @return /WEB-INF/views/index.jsp
	 */
	// 得到 <form:form modelAttribute="adminBean"> 表單新增的資料
	@RequestMapping(value = "/admin/sign-up.do", method = RequestMethod.POST)
	public String signUpProcess(@RequestParam String ad_password_again, @Valid AdminBean adminBean,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			logger.error("註冊失敗: 格式錯誤");

			return ADMIN_SIGN_UP_PAGE;

		} else if (!adminBean.getAd_password().equals(ad_password_again)) {

			logger.error("註冊失敗: 密碼重複錯誤");

			return ADMIN_SIGN_UP_PAGE;

		} else if (adminService.selectByAd_username(adminBean.getAd_username()) != null) {

			logger.error("註冊失敗: 帳號重複");

			return ADMIN_SIGN_UP_PAGE;

		} else {

			adminService.signUp(adminBean);

			logger.info("註冊成功");

			return INDEX_PAGE;
		}
	}

	/**
	 * 個人資訊 - 初期處理
	 * 
	 * @return /WEB-INF/views/admin/profile.jsp
	 */
	@RequestMapping(value = "/admin/profile", method = RequestMethod.GET)
	public String profileView() {

		logger.info("進入個人資訊頁面: " + ADMIN_PROFILE_PAGE);

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

		logger.info("進入編輯個人資訊頁面: " + ADMIN_EDIT_PAGE);

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
	public String editProcess(@Valid @ModelAttribute(ADMIN) AdminBean admin, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			logger.error("編輯失敗: 格式錯誤");

			return ADMIN_EDIT_PAGE;

		} else {

			adminService.update(admin);

			logger.info("編輯成功");

			return REDIRECT + ADMIN_PROFILE_PAGE;
		}
	}

	/**
	 * 變更密碼 - 初期處理
	 * 
	 * @return /WEB-INF/views/admin/change-password.jsp
	 */
	@RequestMapping(value = "/admin/change-password", method = RequestMethod.GET)
	public String changePasswordView() {

		logger.info("進入變更密碼頁面: " + ADMIN_CHANGE_PASSWORD_PAGE);

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
	 * @return /WEB-INF/views/admin/change-password.jsp
	 * @return /WEB-INF/views/admin/change-password.jsp
	 * @return /WEB-INF/views/admin/change-password.jsp
	 * @return /WEB-INF/views/admin/change-password.jsp
	 * @return /WEB-INF/views/admin/change-password.jsp
	 * @return /WEB-INF/views/admin/profile.jsp
	 */
	@RequestMapping(value = "/admin/change-password.do", method = RequestMethod.POST)
	public String changePasswordProcess(@ModelAttribute(ADMIN) AdminBean admin, @RequestParam String ad_password_old,
			@RequestParam String ad_password_new, @RequestParam String ad_password_new_again, Model model) {

		if (ad_password_old == null || ad_password_old.isEmpty() || ad_password_new == null || ad_password_new.isEmpty()
				|| ad_password_new_again == null || ad_password_new_again.isEmpty()) {

			model.addAttribute(ERROR, "密碼未填");

			logger.error("密碼變更失敗: 密碼未填");

			return ADMIN_CHANGE_PASSWORD_PAGE;

		} else if (!ad_password_new.matches("^[\\S]{8,32}$")) {

			model.addAttribute(ERROR, "密碼格式錯誤");

			logger.error("密碼變更失敗: 密碼格式錯誤");

			return ADMIN_CHANGE_PASSWORD_PAGE;

		} else if (ad_password_old.equals(ad_password_new)) {

			model.addAttribute(ERROR, "密碼未變更");

			logger.error("密碼變更失敗: 密碼未變更");

			return ADMIN_CHANGE_PASSWORD_PAGE;

		} else if (!ad_password_new.equals(ad_password_new_again)) {

			model.addAttribute(ERROR, "新密碼重複錯誤");

			logger.error("密碼變更失敗: 新密碼重複錯誤");

			return ADMIN_CHANGE_PASSWORD_PAGE;

		} else if (adminService.updateAd_password(admin, ad_password_old, ad_password_new) == null) {

			model.addAttribute(ERROR, "密碼錯誤");

			logger.error("密碼變更失敗: 密碼錯誤");

			return ADMIN_CHANGE_PASSWORD_PAGE;

		} else {

			logger.info("密碼變更成功");

			return REDIRECT + ADMIN_PROFILE_PAGE;
		}
	}

	/**
	 * 登入 - 初期處理
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/secure/sign-in.jsp
	 */
	@RequestMapping(value = "/secure/sign-in", method = RequestMethod.GET)
	public String signInView(HttpServletRequest request, Model model) {

		logger.info("進入登入頁面: " + ADMIN_SIGN_IN_PAGE);

		HttpSession session = request.getSession();
		String next = (String) session.getAttribute(NEXT_PAGE);

		if (next != null) {

			// 若經過 SigninInterceptor
			logger.info("原請求畫面: " + next);

		} else {

			logger.info("原請求畫面: index");
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
	 * @return /WEB-INF/views/secure/sign-in.jsp
	 * @return /WEB-INF/views/secure/sign-in.jsp
	 * @return /WEB-INF/views/secure/sign-in.jsp
	 * @return /WEB-INF/views/next
	 * @return /WEB-INF/views/index.jsp
	 */
	@RequestMapping(value = "/secure/sign-in.do", method = RequestMethod.POST)
	public String signInProcess(@RequestParam String ad_username, @RequestParam String ad_password,
			HttpServletRequest request, Model model) {

		if (ad_username == null || ad_username.isEmpty()) {

			model.addAttribute(ERROR, "帳號未填");

			logger.error("登入失敗: 帳號未填");

			return REDIRECT + ADMIN_SIGN_IN_PAGE;

		} else if (ad_password == null || ad_password.isEmpty()) {

			model.addAttribute(ERROR, "密碼未填");

			logger.error("登入失敗: 密碼未填");

			return REDIRECT + ADMIN_SIGN_IN_PAGE;

		} else {

			AdminBean adminBean = adminService.signIn(ad_username, ad_password);

			if (adminService.signIn(ad_username, ad_password) == null) {

				model.addAttribute(ERROR, "帳號或密碼錯誤");

				logger.error("登入失敗: 帳號或密碼錯誤");

				return REDIRECT + ADMIN_SIGN_IN_PAGE;

			} else {

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

				if (next != null) {

					// 若經過 SigninInterceptor
					logger.info("登入成功，導向原請求畫面: " + next);

					return REDIRECT.concat(next);

				} else {

					logger.info("登入成功，導向首頁: index");

					return INDEX_PAGE;
				}
			}
		}
	}

	/**
	 * 忘記密碼 - 初期處理
	 * 
	 * @return /WEB-INF/views/secure/forget-password.jsp
	 */
	@RequestMapping(value = "/secure/forget-password", method = RequestMethod.GET)
	public String forgetPasswordView() {

		logger.info("進入忘記密碼頁面: " + ADMIN_FORGET_PASSWORD_PAGE);

		return ADMIN_FORGET_PASSWORD_PAGE;
	}

	/**
	 * 忘記密碼 - submit
	 * 
	 * @param ad_email
	 *            String --> 管理員信箱
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/secure/forget-password.jsp
	 * @return /WEB-INF/views/secure/forget-password.jsp
	 * @return /WEB-INF/views/secure/reset-password.jsp
	 */
	@RequestMapping(value = "/secure/forget-password.do", method = RequestMethod.POST)
	public String forgetPasswordProcess(@RequestParam String ad_email, Model model) {

		if (ad_email == null || ad_email.isEmpty()) {

			model.addAttribute(ERROR, "信箱未填");

			logger.error("發送失敗: 信箱未填");

			return ADMIN_FORGET_PASSWORD_PAGE;

		} else {

			AdminBean adminBean = adminService.selectByAd_email(ad_email);

			if (adminBean == null) {

				model.addAttribute(ERROR, "信箱錯誤");

				logger.error("發送失敗: 信箱錯誤");

				return ADMIN_FORGET_PASSWORD_PAGE;

			} else {

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

				logger.info("發送成功，傳送至: " + to);

				return REDIRECT + ADMIN_RESET_PASSWORD_PAGE;
			}
		}
	}

	/**
	 * 重設密碼 - 初期處理
	 * 
	 * @return /WEB-INF/views/secure/reset-password.jsp
	 */
	@RequestMapping(value = "/secure/reset-password", method = RequestMethod.GET)
	public String resetPasswordView() {

		logger.info("進入重設密碼頁面: " + ADMIN_RESET_PASSWORD_PAGE);

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
	 * @return /WEB-INF/views/secure/reset-password.jsp
	 * @return /WEB-INF/views/secure/reset-password.jsp
	 * @return /WEB-INF/views/secure/reset-password.jsp
	 * @return /WEB-INF/views/secure/reset-password.jsp
	 * @return /WEB-INF/views/secure/sign-in.jsp
	 */
	@RequestMapping(value = "/secure/reset-password.do", method = RequestMethod.POST)
	public String resetPasswordProcess(@ModelAttribute(ADMIN_EMAIL) String ad_email,
			@RequestParam String ad_password_random, @RequestParam String ad_password_new,
			@RequestParam String ad_password_new_again, SessionStatus sessionStatus, Model model) {

		if (ad_password_random == null || ad_password_random.isEmpty() || ad_password_new == null
				|| ad_password_new.isEmpty() || ad_password_new_again == null || ad_password_new_again.isEmpty()) {

			model.addAttribute(ERROR, "密碼未填");

			logger.error("密碼重設失敗: 密碼未填");

			return ADMIN_RESET_PASSWORD_PAGE;

		} else if (!ad_password_new.matches("^[\\S]{8,32}$")) {

			model.addAttribute(ERROR, "密碼格式錯誤");

			logger.error("密碼重設失敗: 密碼格式錯誤");

			return ADMIN_RESET_PASSWORD_PAGE;

		} else if (!ad_password_new.equals(ad_password_new_again)) {

			model.addAttribute(ERROR, "新密碼重複錯誤");

			logger.error("密碼重設失敗: 新密碼重複錯誤");

			return ADMIN_RESET_PASSWORD_PAGE;

		} else if (adminService.updateAd_password(adminService.selectByAd_email(ad_email), ad_password_random,
				ad_password_new) == null) {

			model.addAttribute(ERROR, "驗證碼錯誤");

			logger.error("密碼重設失敗: 驗證碼錯誤");

			return ADMIN_RESET_PASSWORD_PAGE;

		} else {

			// 清除 @SessionAttributes
			sessionStatus.setComplete();

			logger.info("密碼重設成功");

			return REDIRECT + ADMIN_SIGN_IN_PAGE;
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

		// 清除所有 HttpSession
		request.getSession().invalidate();

		logger.info("登出成功");

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

		logger.info("進入管理員一覽頁面: " + ADMIN_LIST_PAGE);

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

		AdminBean bean = adminService.selectByAd_username(ad_username);

		return (bean != null) ? "1" : "0";
	}

	/**
	 * 信箱重複驗證 (AJAX)
	 * 
	 * @param ad_email
	 *            String --> 管理員信箱
	 * @return 1
	 * @return 0
	 */
	@RequestMapping(value = "/admin/email-repeat.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String emailRepeatAjaxProcess(String ad_email) {

		AdminBean bean = adminService.selectByAd_email(ad_email);

		return (bean != null) ? "1" : "0";
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

		AdminBean bean = adminService.updateAd_status(Integer.valueOf(ad_id));

		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
		Gson gson = builder.create();

		String json = gson.toJson(bean);

		logger.info("JSON = " + json);

		return json;
	}

}
