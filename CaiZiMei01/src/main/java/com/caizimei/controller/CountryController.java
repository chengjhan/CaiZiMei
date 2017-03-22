/*
 * CaiZiMei
 * File: CountryController.java
 * Author: 詹晟
 * Date: 2017/3/22
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.caizimei.model.entity.CountryBean;
import com.caizimei.model.service.CountryService;

/**
 * country controller
 * 
 * @author 詹晟
 */
@Controller
@RequestMapping("/country")
public class CountryController {

	/**
	 * 注入 CountryService
	 */
	@Autowired
	private CountryService countryService;

	/**
	 * country/insert 視圖解析
	 * 
	 * @return /WEB-INF/views/country/insert.jsp
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public ModelAndView insert() {
		return new ModelAndView("country/insert");
	}
	
	/**
	 * country/update 視圖解析
	 * 
	 * @return /WEB-INF/views/country/update.jsp
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update() {
		return new ModelAndView("country/update");
	}
	
	/**
	 * 新增國家
	 * 
	 * @param countryBean-->CountryBean
	 * @return /WEB-INF/views/country/insert.jsp
	 */
	@RequestMapping(path = "/insert.do", method = RequestMethod.POST)
	public ModelAndView insertProcess(CountryBean countryBean) {
		countryService.insert(countryBean);
		return new ModelAndView("redirect:/country/insert");
	}

	/**
	 * 修改國家資訊
	 * 
	 * @param countryBean-->CountryBean
	 * @return /WEB-INF/views/country/update.jsp
	 */
	@RequestMapping(path = "/update.do", method = RequestMethod.POST)
	public ModelAndView updateProcess(CountryBean countryBean) {
		countryService.update(countryBean);
		return new ModelAndView("redirect:/country/insert");
	}

	/**
	 * 刪除國家
	 * 
	 * @param countryBean-->CountryBean
	 * @return /WEB-INF/views/country/insert.jsp
	 */
	@RequestMapping(path = "/delete.do", method = RequestMethod.GET)
	public ModelAndView deleteProcess(CountryBean countryBean) {
		countryService.delete(countryBean.getCo_id());
		return new ModelAndView("redirect:/country/insert");
	}

}
