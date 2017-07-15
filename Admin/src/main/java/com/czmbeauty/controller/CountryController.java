/*
 * CaiZiMei
 * File: CountryController.java
 * Author: 詹晟
 * Date: 2017/7/15
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.czmbeauty.model.entity.CountryBean;
import com.czmbeauty.model.service.CountryService;
import com.google.gson.Gson;

/**
 * country controller
 * 
 * @author 詹晟
 */
@Controller
@SessionAttributes("countryList")
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

		model.addAttribute("countryList", countryService.selectAll());

		return "country/list";
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
		CountryBean countryBean = new CountryBean();
		model.addAttribute("countryBean", countryBean);

		return "country/add";
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

		return "redirect:/country/list";
	}

	/**
	 * 編輯國家資訊 - 采姿美管理系統
	 * 
	 * @param countryBean-->form-backing-object
	 * @param model-->Model
	 * @return /WEB-INF/views/country/edit.jsp
	 */
	@RequestMapping(value = "/country/edit", method = RequestMethod.GET)
	public String editView(CountryBean countryBean, Model model) {

		// 取得選定 id 的 CountryBean
		model.addAttribute("countryBean", countryService.selectByCo_id(countryBean.getCo_id()));

		return "country/edit";
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

		return "redirect:/country/list";
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

		return "redirect:/country/list";
	}

	/**
	 * 搜尋所有國家 (ajax)
	 * 
	 * @return country json
	 */
	@RequestMapping(value = "/country/select-all.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String selectAllAjaxProcess() {

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
