/*
 * CaiZiMei
 * File: CountryController.java
 * Author: 詹晟
 * Date: 2017/3/17
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.caizimei.model.CountryBean;
import com.caizimei.model.service.CountryService;

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

}
