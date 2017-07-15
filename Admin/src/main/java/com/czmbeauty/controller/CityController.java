/*
 * CaiZiMei
 * File: CityController.java
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

import com.czmbeauty.model.entity.CityBean;
import com.czmbeauty.model.service.CityService;
import com.google.gson.Gson;

/**
 * city controller
 * 
 * @author 詹晟
 */
@Controller
@SessionAttributes("cityList")
public class CityController {

	/**
	 * 注入 CityService
	 */
	@Autowired
	private CityService cityService;

	/**
	 * 城市一覽 - 采姿美管理系統
	 * 
	 * @param model-->Model
	 * @return /WEB-INF/views/city/list.jsp
	 */
	@RequestMapping(value = "/city/list", method = RequestMethod.GET)
	public String listView(Model model) {

		return "city/list";
	}

	/**
	 * 搜尋選定區域中所有城市 (ajax)
	 * 
	 * @param ci_s_id-->區域流水號
	 * @return city json
	 */
	@RequestMapping(value = "/city/select-by-state.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String selectByStateAjaxProcess(Integer ci_s_id) {

		List<CityBean> result = cityService.selectByCi_s_id(ci_s_id);

		List<CityBean> jsonList = new ArrayList<CityBean>();
		for (CityBean bean : result) {
			CityBean jsonBean = new CityBean();
			jsonBean.setCi_id(bean.getCi_id());
//			jsonBean.setCi_CountryBean(bean.getCi_CountryBean());
//			jsonBean.setCi_StateBean(bean.getCi_StateBean());
			jsonBean.setCi_name(bean.getCi_name());
			jsonBean.setCi_rank(bean.getCi_rank());
			jsonList.add(jsonBean);
		}
		String json = new Gson().toJson(jsonList);
		System.out.println("JSON = " + json);

		return json;
	}

}
