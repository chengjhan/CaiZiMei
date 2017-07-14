/*
 * CaiZiMei
 * File: StateController.java
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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.czmbeauty.model.entity.CountryBean;
import com.czmbeauty.model.entity.StateBean;
import com.czmbeauty.model.service.CountryService;

/**
 * state controller
 * 
 * @author 詹晟
 */
@Controller
@SessionAttributes("stateyList")
public class StateController {

	/**
	 * 注入 CountryService
	 */
	@Autowired
	private CountryService countryService;

	/**
	 * 區域一覽 - 采姿美管理系統
	 * 
	 * @param model-->Model
	 * @return /WEB-INF/views/state/list.jsp
	 */
	@RequestMapping(value = "/state/list", method = RequestMethod.GET)
	public String listView(Model model) {

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

}
