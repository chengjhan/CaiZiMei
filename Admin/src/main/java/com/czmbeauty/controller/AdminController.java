/*
 * CaiZiMei
 * File: AdminController.java
 * Author: 詹晟
 * Date: 2017/9/26
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import static com.czmbeauty.common.constants.ModelAttributeConstants.ADMIN;
import static com.czmbeauty.common.constants.PaginationConstants.ADMIN_PAGE_ROW_COUNT;
import static com.czmbeauty.common.constants.PaginationConstants.CURRENT_PAGE;
import static com.czmbeauty.common.constants.PaginationConstants.PAGE_COUNT;
import static com.czmbeauty.common.constants.PaginationConstants.PAGE_ROW_COUNT;

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

import com.czmbeauty.common.constants.ModelAttributeConstants;
import com.czmbeauty.common.constants.PageNameConstants;
import com.czmbeauty.common.util.CryptographicHashFunction;
import com.czmbeauty.common.util.Pagination;
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
@SessionAttributes(ADMIN)
public class AdminController implements ModelAttributeConstants, PageNameConstants {

	private static final Logger logger = Logger.getLogger(AdminController.class);

	private String className = this.getClass().getSimpleName();

	/**
	 * 注入 HttpServletRequest
	 */
	@Autowired
	private HttpServletRequest request;

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
	 * @param ad_password_again
	 *            String --> 重複密碼(原碼)
	 * @param adminBean
	 *            AdminBean --> form backing object
	 * @param bindingResult
	 *            BindingResult
	 * @return /WEB-INF/views/admin/sign-up.jsp
	 * @return /WEB-INF/views/index.jsp
	 */
	// 得到 <form:form modelAttribute="adminBean"> 表單新增的資料
	@RequestMapping(value = "/admin/sign-up.do", method = RequestMethod.POST)
	public String signUpProcess(@RequestParam String ad_password_again, @Valid AdminBean adminBean,
			BindingResult bindingResult) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		if (bindingResult.hasErrors()) {

			logger.error("(" + className + "." + methodName + ") 註冊失敗: 格式錯誤");

			return ADMIN_SIGN_UP_PAGE;

		} else if (!adminBean.getAd_password().equals(ad_password_again)) {

			logger.error("(" + className + "." + methodName + ") 註冊失敗: 密碼重複錯誤");

			return ADMIN_SIGN_UP_PAGE;

		} else if (adminService.selectByAd_username(adminBean.getAd_username()) != null) {

			logger.error("(" + className + "." + methodName + ") 註冊失敗: 帳號重複");

			return ADMIN_SIGN_UP_PAGE;

		} else {

			adminService.signUp(adminBean);

			logger.info("(" + className + "." + methodName + ") 註冊成功");

			return REDIRECT + INDEX_PAGE;
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
	 * @param bindingResult
	 *            BindingResult
	 * @return /WEB-INF/views/admin/profile.jsp
	 */
	// 得到 <form:form modelAttribute="admin"> 表單更新的資料
	@RequestMapping(value = "/admin/edit.do", method = RequestMethod.POST)
	public String editProcess(@Valid @ModelAttribute(ADMIN) AdminBean admin, BindingResult bindingResult) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		if (bindingResult.hasErrors()) {

			logger.error("(" + className + "." + methodName + ") 個人資訊編輯失敗: 格式錯誤");

			return ADMIN_EDIT_PAGE;

		} else {

			adminService.update(admin);

			logger.info("(" + className + "." + methodName + ") 個人資訊編輯成功");

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
	 * @return /WEB-INF/views/admin/profile.jsp
	 */
	@RequestMapping(value = "/admin/change-password.do", method = RequestMethod.POST)
	public String changePasswordProcess(@ModelAttribute(ADMIN) AdminBean admin, @RequestParam String ad_password_old,
			@RequestParam String ad_password_new, @RequestParam String ad_password_new_again, Model model) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		if (ad_password_old == null || ad_password_old.isEmpty() || ad_password_new == null || ad_password_new.isEmpty()
				|| ad_password_new_again == null || ad_password_new_again.isEmpty()) {

			model.addAttribute(ERROR, "密碼未填");

			logger.error("(" + className + "." + methodName + ") 密碼變更失敗: 密碼未填");

			return ADMIN_CHANGE_PASSWORD_PAGE;

		} else if (!ad_password_new.matches("^[\\S]{8,32}$")) {

			model.addAttribute(ERROR, "密碼格式錯誤");

			logger.error("(" + className + "." + methodName + ") 密碼變更失敗: 密碼格式錯誤");

			return ADMIN_CHANGE_PASSWORD_PAGE;

		} else if (!ad_password_new.equals(ad_password_new_again)) {

			model.addAttribute(ERROR, "新密碼重複錯誤");

			logger.error("(" + className + "." + methodName + ") 密碼變更失敗: 新密碼重複錯誤");

			return ADMIN_CHANGE_PASSWORD_PAGE;

		} else if (!admin.getAd_password()
				.equals(CryptographicHashFunction.getHashedPassword(ad_password_old, admin.getAd_salt()))) {

			model.addAttribute(ADMIN_PASSWORD_OLD, ad_password_old);
			model.addAttribute(ADMIN_PASSWORD_NEW, ad_password_new);
			model.addAttribute(ADMIN_PASSWORD_NEW_AGAIN, ad_password_new_again);
			model.addAttribute(ADMIN_PASSWORD_OLD_ERROR, "密碼錯誤");

			logger.error("(" + className + "." + methodName + ") 密碼變更失敗: 密碼錯誤");

			return ADMIN_CHANGE_PASSWORD_PAGE;

		} else if (admin.getAd_password()
				.equals(CryptographicHashFunction.getHashedPassword(ad_password_new, admin.getAd_salt()))) {

			model.addAttribute(ADMIN_PASSWORD_OLD, ad_password_old);
			model.addAttribute(ADMIN_PASSWORD_NEW, ad_password_new);
			model.addAttribute(ADMIN_PASSWORD_NEW_AGAIN, ad_password_new_again);
			model.addAttribute(ADMIN_PASSWORD_NEW_ERROR, "密碼未變更");

			logger.error("(" + className + "." + methodName + ") 密碼變更失敗: 密碼未變更");

			return ADMIN_CHANGE_PASSWORD_PAGE;

		} else {

			adminService.updateAd_password(admin, ad_password_new);

			logger.info("(" + className + "." + methodName + ") 密碼變更成功");

			return REDIRECT + ADMIN_PROFILE_PAGE;
		}
	}

	/**
	 * 登入 - 初期處理
	 * 
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/secure/sign-in.jsp
	 */
	@RequestMapping(value = "/secure/sign-in", method = RequestMethod.GET)
	public String signInView(Model model) {

		HttpSession session = request.getSession();
		String next = (String) session.getAttribute(NEXT_PAGE);

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		if (next != null) {

			// 經過 SigninInterceptor
			logger.info("(" + className + "." + methodName + ") 原請求畫面: " + next);

		} else {

			// 未經過 SigninInterceptor
			logger.info("(" + className + "." + methodName + ") 原請求畫面: " + INDEX_PAGE);
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
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/secure/sign-in.jsp
	 * @return /WEB-INF/views/next
	 * @return /WEB-INF/views/index.jsp
	 */
	@RequestMapping(value = "/secure/sign-in.do", method = RequestMethod.POST)
	public String signInProcess(@RequestParam String ad_username, @RequestParam String ad_password, Model model) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		if (ad_username == null || ad_username.isEmpty()) {

			model.addAttribute(ADMIN_USERNAME, ad_username);
			model.addAttribute(ADMIN_PASSWORD, ad_password);
			model.addAttribute(ERROR, "請填入帳號。");

			logger.error("(" + className + "." + methodName + ") 登入失敗: 帳號未填");

			return ADMIN_SIGN_IN_PAGE;

		} else if (ad_password == null || ad_password.isEmpty()) {

			model.addAttribute(ADMIN_USERNAME, ad_username);
			model.addAttribute(ADMIN_PASSWORD, ad_password);
			model.addAttribute(ERROR, "請填入密碼。");

			logger.error("(" + className + "." + methodName + ") 登入失敗: 密碼未填");

			return ADMIN_SIGN_IN_PAGE;

		} else {

			AdminBean adminBean = adminService.signIn(ad_username, ad_password);

			if (adminService.signIn(ad_username, ad_password) == null) {

				model.addAttribute(ADMIN_USERNAME, ad_username);
				model.addAttribute(ADMIN_PASSWORD, ad_password);
				model.addAttribute(ERROR, "帳號或密碼錯誤。");

				logger.error("(" + className + "." + methodName + ") 登入失敗: 帳號或密碼錯誤");

				return ADMIN_SIGN_IN_PAGE;

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

					session.removeAttribute(NEXT_PAGE);

					// 經過 SigninInterceptor
					logger.info("(" + className + "." + methodName + ") 登入成功，導向原請求畫面: " + next);

					return REDIRECT.concat(next);

				} else {

					// 未經過 SigninInterceptor
					logger.info("(" + className + "." + methodName + ") 登入成功，導向首頁: " + INDEX_PAGE);

					return REDIRECT + INDEX_PAGE;
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
	 * @return /WEB-INF/views/secure/reset-password.jsp
	 */
	@RequestMapping(value = "/secure/forget-password.do", method = RequestMethod.POST)
	public String forgetPasswordProcess(@RequestParam String ad_email, Model model) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		if (ad_email == null || ad_email.isEmpty()) {

			model.addAttribute(ADMIN_EMAIL, ad_email);
			model.addAttribute(ERROR, "請填入信箱。");

			logger.error("(" + className + "." + methodName + ") 發送失敗: 信箱未填");

			return ADMIN_FORGET_PASSWORD_PAGE;

		} else {

			AdminBean adminBean = adminService.selectByAd_email(ad_email);

			if (adminBean == null) {

				model.addAttribute(ADMIN_EMAIL, ad_email);
				model.addAttribute(ERROR, "信箱錯誤。");

				logger.error("(" + className + "." + methodName + ") 發送失敗: 信箱錯誤");

				return ADMIN_FORGET_PASSWORD_PAGE;

			} else {

				adminService.updateAd_password(adminBean);

				// 將管理員 email 放入 Session
				request.getSession().setAttribute(ADMIN_EMAIL_SESSION, ad_email);

				logger.info("(" + className + "." + methodName + ") 發送成功，傳送至: " + ad_email);

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

		return ADMIN_RESET_PASSWORD_PAGE;
	}

	/**
	 * 重設密碼 - submit
	 * 
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
	 * @return /WEB-INF/views/secure/sign-in.jsp
	 */
	@RequestMapping(value = "/secure/reset-password.do", method = RequestMethod.POST)
	public String resetPasswordProcess(@RequestParam String ad_password_random, @RequestParam String ad_password_new,
			@RequestParam String ad_password_new_again, SessionStatus sessionStatus, Model model) {

		HttpSession session = request.getSession();
		String ad_email = (String) session.getAttribute(ADMIN_EMAIL_SESSION);
		AdminBean adminBean = adminService.selectByAd_email(ad_email);

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		if (ad_password_random == null || ad_password_random.isEmpty() || ad_password_new == null
				|| ad_password_new.isEmpty() || ad_password_new_again == null || ad_password_new_again.isEmpty()) {

			model.addAttribute(ERROR, "密碼未填");

			logger.error("(" + className + "." + methodName + ") 密碼重設失敗: 密碼未填");

			return ADMIN_RESET_PASSWORD_PAGE;

		} else if (!ad_password_new.matches("^[\\S]{8,32}$")) {

			model.addAttribute(ERROR, "密碼格式錯誤");

			logger.error("(" + className + "." + methodName + ") 密碼重設失敗: 密碼格式錯誤");

			return ADMIN_RESET_PASSWORD_PAGE;

		} else if (!ad_password_new.equals(ad_password_new_again)) {

			model.addAttribute(ERROR, "新密碼重複錯誤");

			logger.error("(" + className + "." + methodName + ") 密碼重設失敗: 新密碼重複錯誤");

			return ADMIN_RESET_PASSWORD_PAGE;

		} else if (!adminBean.getAd_password()
				.equals(CryptographicHashFunction.getHashedPassword(ad_password_random, adminBean.getAd_salt()))) {

			model.addAttribute(ADMIN_PASSWORD_RANDOM, ad_password_random);
			model.addAttribute(ADMIN_PASSWORD_NEW, ad_password_new);
			model.addAttribute(ADMIN_PASSWORD_NEW_AGAIN, ad_password_new_again);
			model.addAttribute(ERROR, "驗證碼錯誤。");

			logger.error("(" + className + "." + methodName + ") 密碼重設失敗: 驗證碼錯誤");

			return ADMIN_RESET_PASSWORD_PAGE;

		} else {

			adminService.updateAd_password(adminBean, ad_password_new);

			// 清除 @SessionAttributes
			sessionStatus.setComplete();

			// 清除所有 HttpSession
			session.invalidate();

			logger.info("(" + className + "." + methodName + ") 密碼重設成功");

			return REDIRECT + ADMIN_SIGN_IN_PAGE;
		}
	}

	/**
	 * 登出
	 * 
	 * @param admin
	 *            AdminBean --> Session
	 * @param sessionStatus
	 *            SessionStatus
	 * @return /WEB-INF/views/index.jsp
	 */
	@RequestMapping(value = "/secure/sign-out", method = RequestMethod.GET)
	public String signOutProcess(@ModelAttribute(ADMIN) AdminBean admin, SessionStatus sessionStatus) {

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

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		logger.info("(" + className + "." + methodName + ") 登出成功");

		return REDIRECT + INDEX_PAGE;
	}

	/**
	 * 管理員一覽 - 初期處理
	 * 
	 * @param page
	 *            Integer --> 當前頁碼
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/admin/list.jsp
	 */
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	public String listView(@RequestParam Integer page, Model model) {

		int pageRowCount = ADMIN_PAGE_ROW_COUNT;

		// 取得當前頁碼
		model.addAttribute(CURRENT_PAGE, page);

		// 取得每頁最大筆數
		model.addAttribute(PAGE_ROW_COUNT, pageRowCount);

		// 取得當前頁碼的管理員 List，放入 table
		model.addAttribute(ADMIN_LIST, adminService.selectPagination(page, pageRowCount));

		// 取得總頁數
		model.addAttribute(PAGE_COUNT, Pagination.getPageCount(adminService.selectCount(), pageRowCount));

		return ADMIN_LIST_PAGE;
	}

	/**
	 * 帳號重複驗證 (AJAX)
	 * 
	 * @param ad_username
	 *            String --> 管理員帳號
	 * @return "這個帳號已經被使用了"
	 * @return "true"
	 */
	@RequestMapping(value = "/admin/username-repeat.ajax", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String usernameRepeatAjaxProcess(String ad_username) {

		AdminBean bean = adminService.selectByAd_username(ad_username);

		return (bean != null) ? "這個帳號已經被使用了" : "true";
	}

	/**
	 * 信箱重複驗證 (AJAX)
	 * 
	 * @param ad_email
	 *            String --> 管理員信箱
	 * @return "這個信箱已經被使用了"
	 * @return "true"
	 */
	@RequestMapping(value = "/admin/email-repeat.ajax", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String emailRepeatAjaxProcess(String ad_email) {

		AdminBean bean = adminService.selectByAd_email(ad_email);

		return (bean != null) ? "這個信箱已經被使用了" : "true";
	}

	/**
	 * 信箱重複驗證 (edit) (AJAX)
	 * 
	 * @param admin
	 *            AdminBean --> Session
	 * @param ad_email
	 *            String --> 管理員信箱
	 * @return "這個信箱已經被使用了"
	 * @return "true"
	 */
	@RequestMapping(value = "/admin/edit-email-repeat.ajax", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String editEmailRepeatAjaxProcess(@ModelAttribute(ADMIN) AdminBean admin, String ad_email) {

		AdminBean bean = adminService.selectByAd_email(admin.getAd_id(), ad_email);

		return (bean != null) ? "這個信箱已經被使用了" : "true";
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

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		logger.info("(" + className + "." + methodName + ") JSON = " + json);

		return json;
	}

}
