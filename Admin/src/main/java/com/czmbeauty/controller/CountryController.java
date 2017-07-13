/*
 * CaiZiMei
 * File: CountryController.java
 * Author: 詹晟
 * Date: 2017/7/13
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
	 * 國家一覽
	 * 
	 * @param model-->Model
	 * @return /WEB-INF/views/country/list.jsp
	 */
	@RequestMapping(value = "/country/list.do", method = RequestMethod.GET)
	public String listProcess(Model model) {

		model.addAttribute("countryList", countryService.selectAll());

		return "country/list";
	}

	/**
	 * 新增國家
	 * 
	 * @param countryBean-->form-backing-object
	 * @param model-->Model
	 * @return /WEB-INF/views/country/list.jsp
	 */
	@RequestMapping(path = "/country/add.do", method = RequestMethod.POST)
	public String addProcess(CountryBean countryBean, Model model) {

		countryService.insert(countryBean);
		model.addAttribute("countryList", countryService.selectAll());

		return "redirect:/index";
	}

	/**
	 * 編輯資料
	 * 
	 * @param countryBean-->CountryBean
	 * @param model-->Model
	 * @return /WEB-INF/views/country/list.jsp
	 */
	@RequestMapping(path = "/country/edit.do", method = RequestMethod.POST)
	public String editProcess(CountryBean countryBean, Model model) {

		countryService.update(countryBean);
		model.addAttribute("countryList", countryService.selectAll());

		return "redirect:/country/list";
	}

	/**
	 * 刪除國家
	 * 
	 * @param countryBean-->CountryBean
	 * @param model-->Model
	 * @return /WEB-INF/views/country/list.jsp
	 */
	@RequestMapping(path = "/country/delete.do", method = RequestMethod.GET)
	public String deleteProcess(CountryBean countryBean, Model model) {

		countryService.delete(countryBean.getCo_id());
		model.addAttribute("countryList", countryService.selectAll());

		return "redirect:/country/list";
	}

	/**
	 * 搜尋全部國家 (ajax)
	 * 
	 * @return country json
	 */
	@RequestMapping(path = "/select.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String selectAjaxProcess() {

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
