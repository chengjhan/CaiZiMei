/*
 * CaiZiMei
 * File: AdminController.java
 * Author: 詹晟
 * Date: 2017/5/3
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.caizimei.model.entity.AdminBean;
import com.caizimei.model.service.AdminService;

/**
 * admin controller
 * 
 * @author 詹晟
 */
@Controller
@RequestMapping("/admin/admin")
@SessionAttributes("adminList")
public class AdminController {

	/**
	 * 注入 AdminService
	 */
	@Autowired
	private AdminService adminService;

	/**
	 * 搜尋全部總公司
	 * 
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/admin/list.jsp
	 */
	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public String selectProcess(Model model) {

		model.addAttribute("adminList", adminService.select());

		return "admin/admin/list";
	}

	/**
	 * 新增總公司
	 * 
	 * @param adminBean-->AdminBean
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/admin/list.jsp
	 */
	@RequestMapping(path = "/insert.do", method = RequestMethod.POST)
	public String insertProcess(AdminBean adminBean, Model model) {

		adminBean.setAd_insert_time(new java.util.Date());
		adminBean.setAd_update_time(new java.util.Date());
		adminBean.setAd_status(1);
		adminBean.setAd_status_time(new java.util.Date());
		adminService.insert(adminBean);
		model.addAttribute("adminList", adminService.select());

		return "redirect:/admin/admin/list";
	}

}
