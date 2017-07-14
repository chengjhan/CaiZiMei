/*
 * CaiZiMei
 * File: ViewsController.java
 * Author: 詹晟
 * Date: 2017/7/14
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.czmbeauty.model.entity.CountryBean;
import com.czmbeauty.model.entity.StateBean;
import com.czmbeauty.model.service.CountryService;

/**
 * views controller (視圖解析)
 * 
 * @author 詹晟
 */
@Controller
public class ViewsController {

	/**
	 * 注入 CountryService
	 */
	@Autowired
	private CountryService countryService;

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
	 * 管理員一覽 - 采姿美管理系統
	 * 
	 * @return /WEB-INF/views/admin/list.jsp
	 */
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	public String admin_list() {

		return "admin/list";
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

	/**
	 * 新增區域 - 采姿美管理系統
	 * 
	 * @param model-->Model
	 * @return /WEB-INF/views/state/add.jsp
	 */
	@RequestMapping(value = "/state/add", method = RequestMethod.GET)
	public String state_add(Model model) {

		// 取得所有國家 List 並製作 Map
		Map<Integer, String> countryMap = new LinkedHashMap<Integer, String>();
		for (CountryBean bean : countryService.selectAll()) {
			countryMap.put(bean.getCo_id(), bean.getCo_name());
		}
		model.addAttribute("countryMap", countryMap);

		// 新增 form backing object
		StateBean stateBean = new StateBean();
		model.addAttribute("stateBean", stateBean);

		return "state/add";
	}

	/**
	 * 區域一覽 - 采姿美管理系統
	 * 
	 * @return /WEB-INF/views/state/list.jsp
	 */
	@RequestMapping(value = "/state/list", method = RequestMethod.GET)
	public String state_list() {

		return "state/list";
	}

}
