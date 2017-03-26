/*
 * CaiZiMei
 * File: CityController.java
 * Author: 詹晟
 * Date: 2017/3/26
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
	 * admin/city/list 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/city/list.jsp
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		return new ModelAndView("admin/city/list");
	}

	/**
	 * admin/city/update 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/city/update.jsp
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update() {

		return new ModelAndView("admin/city/update");
	}

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
	 * @param co_name-->國家名
	 * @param ci_name-->城市名
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/city/list.jsp
	 */
	@RequestMapping(path = "/insert.do", method = RequestMethod.POST)
	public ModelAndView insertProcess(@RequestParam(name = "co_name") String co_name,
			@RequestParam(name = "ci_name") String ci_name, Model model) {

		CityBean cityBean = new CityBean();
		cityBean.setCi_name(ci_name);
		cityBean.setCi_CountryBean(countryService.selectByCo_name(co_name));
		cityService.insert(cityBean);
		model.addAttribute("cityList", cityService.select());

		return new ModelAndView("redirect:/admin/city/list");
	}

	/**
	 * 修改城市資訊
	 * 
	 * @param ci_id-->城市流水號
	 * @param co_name-->國家名
	 * @param ci_name-->城市名
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/city/list.jsp
	 */
	@RequestMapping(path = "/update.do", method = RequestMethod.POST)
	public ModelAndView updateProcess(@RequestParam(name = "ci_id") String ci_id,
			@RequestParam(name = "co_name") String co_name, @RequestParam(name = "ci_name") String ci_name,
			Model model) {

		CityBean cityBean = new CityBean();
		cityBean.setCi_id(Integer.parseInt(ci_id));
		cityBean.setCi_CountryBean(countryService.selectByCo_name(co_name));
		cityBean.setCi_name(ci_name);
		cityService.update(cityBean);
		model.addAttribute("cityList", cityService.select());

		return new ModelAndView("redirect:/admin/city/list");
	}

	/**
	 * 刪除國家
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
	 * @param co_name-->國家名
	 * @return 國家中的所有城市json
	 */
	@RequestMapping(path = "/select.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String selectByCountryAjaxProcess(String co_name) {

		List<CityBean> result = cityService.selectByCo_name(co_name);

		List<CityBean> jsonList = new ArrayList<CityBean>();
		for (CityBean bean : result) {
			CityBean jsonBean = new CityBean();
			jsonBean.setCi_name(bean.getCi_name());
			jsonList.add(jsonBean);
		}
		String json = new Gson().toJson(jsonList);
		System.out.println("JSON = " + json);

		return json;
	}

}
