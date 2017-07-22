/*
 * CaiZiMei
 * File: CountryController.java
 * Author: 詹晟
 * Date: 2017/7/22
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import static com.czmbeauty.common.constants.ModelAttributeConstants.COUNTRY_BEAN;
import static com.czmbeauty.common.constants.ModelAttributeConstants.COUNTRY_LIST;
import static com.czmbeauty.common.constants.PageNameConstants.COUNTRY_ADD_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.COUNTRY_EDIT_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.COUNTRY_LIST_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.REDIRECT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.czmbeauty.model.entity.CountryBean;
import com.czmbeauty.model.service.CountryService;

/**
 * country controller
 * 
 * @author 詹晟
 */
@Controller
public class CountryController {

	/**
	 * 注入 CountryService
	 */
	@Autowired
	private CountryService countryService;

	/**
	 * 國家一覽 - 初期處理
	 * 
	 * @param model-->Model
	 * @return /WEB-INF/views/country/list.jsp
	 */
	@RequestMapping(value = "/country/list", method = RequestMethod.GET)
	public String listView(Model model) {

		// 取得所有國家 List，放入 table
		model.addAttribute(COUNTRY_LIST, countryService.selectAll());

		return COUNTRY_LIST_PAGE;
	}

	/**
	 * 新增國家 - 初期處理
	 * 
	 * @param model-->Model
	 * @return /WEB-INF/views/country/add.jsp
	 */
	@RequestMapping(value = "/country/add", method = RequestMethod.GET)
	public String addView(Model model) {

		// 新增 form backing object
		model.addAttribute(COUNTRY_BEAN, new CountryBean());

		return COUNTRY_ADD_PAGE;
	}

	/**
	 * 新增國家 - submit
	 * 
	 * @param countryBean-->form-backing-object
	 * @return /WEB-INF/views/country/list.jsp
	 */
	@RequestMapping(value = "/country/add.do", method = RequestMethod.POST)
	public String addProcess(CountryBean countryBean) {

		countryService.insert(countryBean);

		return REDIRECT + COUNTRY_LIST_PAGE;
	}

	/**
	 * 編輯國家資訊 - 初期處理
	 * 
	 * @param countryBean_co_id-->form-backing-object-->GET-->co_id
	 * @param model-->Model
	 * @return /WEB-INF/views/country/edit.jsp
	 */
	@RequestMapping(value = "/country/edit", method = RequestMethod.GET)
	public String editView(CountryBean countryBean_co_id, Model model) {

		// 取得選定國家 id 的 CountryBean，使表單回填 CountryBean 內所有資料
		model.addAttribute(COUNTRY_BEAN, countryService.selectByCo_id(countryBean_co_id.getCo_id()));

		return COUNTRY_EDIT_PAGE;
	}

	/**
	 * 編輯國家資訊 - submit
	 * 
	 * @param countryBean-->form-backing-object
	 * @return /WEB-INF/views/country/list.jsp
	 */
	@RequestMapping(value = "/country/edit.do", method = RequestMethod.POST)
	public String editProcess(CountryBean countryBean) {

		countryService.update(countryBean);

		return REDIRECT + COUNTRY_LIST_PAGE;
	}

	/**
	 * 刪除國家 - submit
	 * 
	 * @param countryBean-->form-backing-object
	 * @return /WEB-INF/views/country/list.jsp
	 */
	@RequestMapping(value = "/country/delete", method = RequestMethod.GET)
	public String deleteProcess(CountryBean countryBean) {

		countryService.delete(countryBean.getCo_id());

		return REDIRECT + COUNTRY_LIST_PAGE;
	}

}
