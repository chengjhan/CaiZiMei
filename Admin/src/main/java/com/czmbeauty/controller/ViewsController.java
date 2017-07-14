/*
 * CaiZiMei
 * File: ViewsController.java
 * Author: 詹晟
 * Date: 2017/7/15
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

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
