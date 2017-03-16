/*
 * CaiZiMei
 * File: CountryController.java
 * Author: 詹晟
 * Date: 2017/3/16
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caizimei.model.CountryBean;
import com.caizimei.model.service.CountryService;
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
	 * 新增國家
	 * 
	 * @param 表單的CountryBean
	 * @return /country/insert.jsp
	 */
	@RequestMapping(path = "/country/insert.controller", method = RequestMethod.POST)
	public String insertProcess(CountryBean countryBean) {
		countryService.insert(countryBean);
		return "country.insert";
	}

	/**
	 * 修改國家資訊
	 * 
	 * @param 表單的CountryBean
	 * @return /country/insert.jsp
	 */
	@RequestMapping(path = "/country/update.controller", method = RequestMethod.POST)
	public String updateProcess(CountryBean countryBean) {
		countryService.update(countryBean);
		return "country.insert";
	}

	/**
	 * 刪除國家
	 * 
	 * @param 表單的CountryBean
	 * @return /country/insert.jsp
	 */
	@RequestMapping(path = "/country/delete.controller", method = RequestMethod.GET)
	public String deleteProcess(CountryBean countryBean) {
		countryService.delete(countryBean.getCo_id());
		return "country.insert";
	}

	/**
	 * 查詢全部國家 ajax
	 * 
	 * @return CountryBean集合的json
	 */
	@RequestMapping(path = "/country/select.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String selectAjaxProcess() {
		List<CountryBean> result = countryService.select();
		List<CountryBean> jsonList = new ArrayList<CountryBean>();
		for (CountryBean countryBean : result) {
			CountryBean jsonBean = new CountryBean();
			jsonBean.setCo_id(countryBean.getCo_id());
			jsonBean.setCo_name(countryBean.getCo_name());
			jsonBean.setCo_countrycode(countryBean.getCo_countrycode());
			jsonList.add(jsonBean);
		}
		String json = new Gson().toJson(jsonList);
		System.out.println("JSON = " + json);
		return json;
	}

}
