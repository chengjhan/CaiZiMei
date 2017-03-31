/*
 * CaiZiMei
 * File: CityController.java
 * Author: 詹晟
 * Date: 2017/3/31
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.caizimei.model.entity.CityBean;
import com.caizimei.model.service.CityService;
import com.caizimei.model.service.CountryService;
import com.google.gson.Gson;

/**
 * city controller
 * 
 * @author 詹晟
 */
@Controller
@RequestMapping("/admin/city")
@SessionAttributes("cityList")
public class CityController {

	/**
	 * 注入 CityService
	 */
	@Autowired
	private CityService cityService;

	/**
	 * 注入 CountryService
	 */
	@Autowired
	private CountryService countryService;

	/**
	 * 搜尋全部城市
	 * 
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/city/list.jsp
	 */
	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public ModelAndView selectProcess(Model model) {

		model.addAttribute("cityList", cityService.select());

		return new ModelAndView("admin/city/list");
	}

	/**
	 * 新增城市
	 * 
	 * @param ci_co_id-->國家流水號
	 * @param cityBean-->CityBean
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/city/list.jsp
	 */
	@RequestMapping(path = "/insert.do", method = RequestMethod.POST)
	public ModelAndView insertProcess(@RequestParam(name = "ci_co_id") Integer ci_co_id, CityBean cityBean,
			Model model) {

		cityBean.setCi_CountryBean(countryService.selectByCo_id(ci_co_id));
		cityService.insert(cityBean);
		model.addAttribute("cityList", cityService.select());

		return new ModelAndView("redirect:/admin/city/list");
	}

	/**
	 * 修改城市資訊
	 * 
	 * @param ci_co_id-->國家流水號
	 * @param cityBean-->CityBean
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/city/list.jsp
	 */
	@RequestMapping(path = "/update.do", method = RequestMethod.POST)
	public ModelAndView updateProcess(@RequestParam(name = "ci_co_id") Integer ci_co_id, CityBean cityBean,
			Model model) {

		cityBean.setCi_CountryBean(countryService.selectByCo_id(ci_co_id));
		cityService.update(cityBean);
		model.addAttribute("cityList", cityService.select());

		return new ModelAndView("redirect:/admin/city/list");
	}

	/**
	 * 刪除城市
	 * 
	 * @param cityBean-->CityBean
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/city/list.jsp
	 */
	@RequestMapping(path = "/delete.do", method = RequestMethod.GET)
	public ModelAndView deleteProcess(CityBean cityBean, Model model) {

		cityService.delete(cityBean.getCi_id());
		model.addAttribute("cityList", cityService.select());

		return new ModelAndView("redirect:/admin/city/list");
	}

	/**
	 * 搜尋國家中的所有城市 (ajax)
	 * 
	 * @param ci_co_id-->國家流水號
	 * @return 城市json
	 */
	@RequestMapping(path = "/select-by-country.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String selectByCountryAjaxProcess(Integer ci_co_id) {

		List<CityBean> result = cityService.selectByCi_co_id(ci_co_id);

		List<CityBean> jsonList = new ArrayList<CityBean>();
		for (CityBean bean : result) {
			CityBean jsonBean = new CityBean();
			jsonBean.setCi_id(bean.getCi_id());
			jsonBean.setCi_name(bean.getCi_name());
			jsonList.add(jsonBean);
		}
		String json = new Gson().toJson(jsonList);
		System.out.println("JSON = " + json);

		return json;
	}

}
