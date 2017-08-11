/*
 * CaiZiMei
 * File: CountryController.java
 * Author: 詹晟
 * Date: 2017/8/12
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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czmbeauty.common.editor.PrimitiveNumberEditor;
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
	 * 提供 form backing object 資料轉換
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Integer.class, new PrimitiveNumberEditor(Integer.class, true));
	}

	/**
	 * 國家一覽 - 初期處理
	 * 
	 * @param model
	 *            Model
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
	 * @param model
	 *            Model
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
	 * @param countryBean
	 *            CountryBean --> form backing object
	 * @param result
	 *            BindingResult
	 * @return /WEB-INF/views/country/list.jsp
	 */
	@RequestMapping(value = "/country/add.do", method = RequestMethod.POST)
	public String addProcess(@Valid CountryBean countryBean, BindingResult result) {

		if (result.hasErrors()) {

			return COUNTRY_ADD_PAGE;
		} else {

			countryService.insert(countryBean);

			return REDIRECT + COUNTRY_LIST_PAGE;
		}
	}

	/**
	 * 編輯國家資訊 - 初期處理
	 * 
	 * @param countryBean_co_id
	 *            CountryBean --> form backing object --> GET --> co_id
	 * @param model
	 *            Model
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
	 * @param countryBean
	 *            CountryBean --> form backing object
	 * @param result
	 *            BindingResult
	 * @return /WEB-INF/views/country/list.jsp
	 */
	@RequestMapping(value = "/country/edit.do", method = RequestMethod.POST)
	public String editProcess(@Valid CountryBean countryBean, BindingResult result) {

		if (result.hasErrors()) {

			return COUNTRY_EDIT_PAGE;
		} else {

			countryService.update(countryBean);

			return REDIRECT + COUNTRY_LIST_PAGE;
		}
	}

	/**
	 * 國家開關 (AJAX)
	 * 
	 * @param co_id
	 *            String --> 國家流水號
	 * @return co_name
	 */
	@RequestMapping(value = "/country/switch.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String switchAjaxProcess(String co_id) {

		return countryService.updateCo_status(Integer.valueOf(co_id)).getCo_name();
	}

}
