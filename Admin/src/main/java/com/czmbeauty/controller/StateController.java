/*
 * CaiZiMei
 * File: StateController.java
 * Author: 詹晟
 * Date: 2017/7/14
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.czmbeauty.model.entity.CountryBean;
import com.czmbeauty.model.entity.StateBean;
import com.czmbeauty.model.service.CountryService;
import com.czmbeauty.model.service.StateService;

import misc.CountryBeanPropertyEditor;
import misc.PrimitiveNumberEditor;

/**
 * state controller
 * 
 * @author 詹晟
 */
@Controller
@SessionAttributes("stateList")
public class StateController {

	/**
	 * 注入 StateService
	 */
	@Autowired
	private StateService stateService;

	/**
	 * 注入 CountryService
	 */
	@Autowired
	private CountryService countryService;

	/**
	 * 提供 form backing object 資料轉換
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Integer.class, new PrimitiveNumberEditor(Integer.class, true));
		webDataBinder.registerCustomEditor(CountryBean.class, new CountryBeanPropertyEditor());
	}

	/**
	 * 區域一覽 - 采姿美管理系統
	 * 
	 * @param model-->Model
	 * @return /WEB-INF/views/state/list.jsp
	 */
	@RequestMapping(value = "/state/list", method = RequestMethod.GET)
	public String listView(Model model) {

		model.addAttribute("stateList", stateService.selectAll());

		return "state/list";
	}

	/**
	 * 新增區域 - 采姿美管理系統
	 * 
	 * @param model-->Model
	 * @return /WEB-INF/views/state/add.jsp
	 */
	@RequestMapping(value = "/state/add", method = RequestMethod.GET)
	public String addView(Model model) {

		// 取得所有國家 List
		model.addAttribute("countryList", countryService.selectAll());

		// 新增 form backing object
		StateBean stateBean = new StateBean();
		model.addAttribute("stateBean", stateBean);

		return "state/add";
	}

	/**
	 * 新增區域 - submit
	 * 
	 * @param stateBean-->form-backing-object
	 * @return /WEB-INF/views/state/list.jsp
	 */
	@RequestMapping(value = "/state/add.do", method = RequestMethod.POST)
	public String addProcess(StateBean stateBean) {

		stateBean.setS_CountryBean(countryService.selectByCo_id(stateBean.getS_CountryBean().getCo_id()));
		stateService.insert(stateBean);

		return "redirect:/state/list";
	}

}
