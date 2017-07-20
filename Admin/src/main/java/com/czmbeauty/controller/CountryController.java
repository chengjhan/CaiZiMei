/*
 * CaiZiMei
 * File: CountryController.java
 * Author: 詹晟
 * Date: 2017/7/20
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

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czmbeauty.model.entity.CountryBean;
import com.czmbeauty.model.service.CountryService;
import com.google.gson.Gson;

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
	 * 國家一覽 - 采姿美管理系統
	 * 
	 * @param model-->Model
	 * @return /WEB-INF/views/country/list.jsp
	 */
	@RequestMapping(value = "/country/list", method = RequestMethod.GET)
	public String listView(Model model) {

		model.addAttribute(COUNTRY_LIST, countryService.selectAll());

		return COUNTRY_LIST_PAGE;
	}

	/**
	 * 新增國家 - 采姿美管理系統
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
	 * 編輯國家資訊 - 采姿美管理系統
	 * 
	 * @param countryBean_co_id-->form-backing-object-->GET-->co_id
	 * @param model-->Model
	 * @return /WEB-INF/views/country/edit.jsp
	 */
	@RequestMapping(value = "/country/edit", method = RequestMethod.GET)
	public String editView(CountryBean countryBean_co_id, Model model) {

		// 取得選定國家 id 的 CountryBean，並回傳 CountryBean 內所有資料
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

	/**
	 * 所有國家列表 (AJAX)
	 * 
	 * @return country JSON
	 */
	@RequestMapping(value = "/country/all-country-list.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String allCountryListAjaxProcess() {

		List<CountryBean> result = countryService.selectAll();

		List<CountryBean> jsonList = new ArrayList<CountryBean>();

		for (CountryBean bean : result) {
			CountryBean jsonBean = new CountryBean();
			jsonBean.setCo_id(bean.getCo_id());
			jsonBean.setCo_name(bean.getCo_name());
			jsonList.add(jsonBean);
		}

		String json = new Gson().toJson(jsonList);
		System.out.println("JSON = " + json);

		return json;
	}

}
