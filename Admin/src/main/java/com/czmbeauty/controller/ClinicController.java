/*
 * CaiZiMei
 * File: ClinicController.java
 * Author: 詹晟
 * Date: 2017/7/17
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.czmbeauty.model.entity.ClinicBean;
import com.czmbeauty.model.service.ClinicService;

/**
 * clinic controller
 * 
 * @author 詹晟
 */
@Controller
@SessionAttributes("clinicList")
public class ClinicController {

	/**
	 * 注入 ClinicService
	 */
	@Autowired
	private ClinicService clinicService;

	/**
	 * 診所一覽 - 采姿美管理系統
	 * 
	 * @param model-->Model
	 * @return /WEB-INF/views/clinic/list.jsp
	 */
	@RequestMapping(value = "/clinic/list", method = RequestMethod.GET)
	public String listView(Model model) {

		model.addAttribute("clinicList", clinicService.selectAll());

		return "clinic/list";
	}

	/**
	 * 新增診所 - 采姿美管理系統
	 * 
	 * @param model-->Model
	 * @return /WEB-INF/views/clinic/add.jsp
	 */
	@RequestMapping(value = "/clinic/add", method = RequestMethod.GET)
	public String addView(Model model) {

		// 新增 form backing object
		ClinicBean clinicBean = new ClinicBean();
		model.addAttribute("clinicBean", clinicBean);

		return "clinic/add";
	}

	/**
	 * 新增診所 - submit
	 * 
	 * @param clinicBean-->form-backing-object
	 * @return /WEB-INF/views/clinic/list.jsp
	 */
	@RequestMapping(value = "/clinic/add.do", method = RequestMethod.POST)
	public String addProcess(ClinicBean clinicBean) {

		clinicService.insert(clinicBean);

		return "redirect:/clinic/list";
	}

}
