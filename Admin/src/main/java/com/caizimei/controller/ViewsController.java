/*
 * CaiZiMei
 * File: ViewsController.java
 * Author: 詹晟
 * Date: 2017/7/10
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * views controller (視圖解析)
 * 
 * @author 詹晟
 */
@Controller
public class ViewsController {

	/**
	 * 首頁 - 采姿美管理系統
	 * 
	 * @return /WEB-INF/views/index.jsp
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {

		return "index";
	}

	/**
	 * 變更密碼 - 采姿美管理系統
	 * 
	 * @return /WEB-INF/views/admin/change-password.jsp
	 */
	@RequestMapping(value = "/admin/change-password", method = RequestMethod.GET)
	public String admin_changePassword() {

		return "admin/change-password";
	}

	/**
	 * 編輯個人資訊 - 采姿美管理系統
	 * 
	 * @return /WEB-INF/views/admin/edit.jsp
	 */
	@RequestMapping(value = "/admin/edit", method = RequestMethod.GET)
	public String admin_edit() {

		return "admin/edit";
	}

	/**
	 * 個人資訊 - 采姿美管理系統
	 * 
	 * @return /WEB-INF/views/admin/profile.jsp
	 */
	@RequestMapping(value = "/admin/profile", method = RequestMethod.GET)
	public String admin_profile() {

		return "admin/profile";
	}

	/**
	 * 註冊 - 采姿美管理系統
	 * 
	 * @return /WEB-INF/views/admin/sign-up.jsp
	 */
	@RequestMapping(value = "/admin/sign-up", method = RequestMethod.GET)
	public String admin_signUp() {

		return "admin/sign-up";
	}

	/**
	 * 忘記密碼 - 采姿美管理系統
	 * 
	 * @return /WEB-INF/views/secure/forget-password.jsp
	 */
	@RequestMapping(value = "/secure/forget-password", method = RequestMethod.GET)
	public String secure_forgetPassword() {

		return "secure/forget-password";
	}

	/**
	 * 重設密碼 - 采姿美管理系統
	 * 
	 * @return /WEB-INF/views/secure/reset-password.jsp
	 */
	@RequestMapping(value = "/secure/reset-password", method = RequestMethod.GET)
	public String secure_resetPassword() {

		return "secure/reset-password";
	}

	/**
	 * 登入 - 采姿美管理系統
	 * 
	 * @return /WEB-INF/views/secure/sign-in.jsp
	 */
	@RequestMapping(value = "/secure/sign-in", method = RequestMethod.GET)
	public String secure_signIn() {

		return "secure/sign-in";
	}

}
