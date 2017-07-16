/*
 * CaiZiMei
 * File: CityController.java
 * Author: 詹晟
 * Date: 2017/7/17
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.czmbeauty.model.entity.CityBean;
import com.czmbeauty.model.entity.CountryBean;
import com.czmbeauty.model.entity.StateBean;
import com.czmbeauty.model.service.CityService;
import com.czmbeauty.model.service.CountryService;
import com.czmbeauty.model.service.StateService;
import com.google.gson.Gson;

import misc.CountryBeanPropertyEditor;
import misc.PrimitiveNumberEditor;
import misc.StateBeanPropertyEditor;

/**
 * city controller
 * 
 * @author 詹晟
 */
@Controller
@SessionAttributes("cityList")
public class CityController {

	/**
	 * 注入 CountryService
	 */
	@Autowired
	private CountryService countryService;

	/**
	 * 注入 StateService
	 */
	@Autowired
	private StateService stateService;

	/**
	 * 注入 CityService
	 */
	@Autowired
	private CityService cityService;

	/**
	 * 提供 form backing object 資料轉換
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Integer.class, new PrimitiveNumberEditor(Integer.class, true));
		webDataBinder.registerCustomEditor(CountryBean.class, new CountryBeanPropertyEditor());
		webDataBinder.registerCustomEditor(StateBean.class, new StateBeanPropertyEditor());
	}

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
	 * 新增城市 - 采姿美管理系統
	 * 
	 * @param model-->Model
	 * @return /WEB-INF/views/city/add.jsp
	 */
	@RequestMapping(value = "/city/add", method = RequestMethod.GET)
	public String addView(Model model) {

		// 取得所有國家 List
		model.addAttribute("countryList", countryService.selectAll());

		// 新增 form backing object
		CityBean cityBean = new CityBean();
		model.addAttribute("cityBean", cityBean);

		return "city/add";
	}

	/**
	 * 新增城市 - submit
	 * 
	 * @param cityBean-->form-backing-object
	 * @return /WEB-INF/views/city/list.jsp
	 */
	@RequestMapping(value = "/city/add.do", method = RequestMethod.POST)
	public String addProcess(CityBean cityBean) {

		cityService.insert(cityBean);

		return "redirect:/city/list";
	}

	/**
	 * 編輯城市資訊 - 采姿美管理系統
	 * 
	 * @param StateBean-->form-backing-object
	 * @param model-->Model
	 * @return /WEB-INF/views/city/edit.jsp
	 */
	@RequestMapping(value = "/city/edit", method = RequestMethod.GET)
	public String editView(CityBean cityBean, Model model) {

		// 取得所有國家 List
		model.addAttribute("countryList", countryService.selectAll());

		// 取得選定國家中的所有區域 List
		model.addAttribute("stateList", stateService
				.selectByS_co_id(cityService.selectByCi_id(cityBean.getCi_id()).getCi_CountryBean().getCo_id()));

		// 取得選定城市 id 的 cityBean
		model.addAttribute("cityBean", cityService.selectByCi_id(cityBean.getCi_id()));

		return "city/edit";
	}

	/**
	 * 編輯城市資訊 - submit
	 * 
	 * @param cityBean-->form-backing-object
	 * @return /WEB-INF/views/city/list.jsp
	 */
	@RequestMapping(value = "/city/edit.do", method = RequestMethod.POST)
	public String editProcess(CityBean cityBean) {

		cityService.update(cityBean);

		return "redirect:/city/list";
	}

	/**
	 * 刪除城市 - submit
	 * 
	 * @param cityBean-->form-backing-object
	 * @return /WEB-INF/views/city/list.jsp
	 */
	@RequestMapping(value = "/city/delete", method = RequestMethod.GET)
	public String deleteProcess(CityBean cityBean) {

		cityService.delete(cityBean.getCi_id());

		return "redirect:/city/list";
	}

	/**
	 * 搜尋選定區域中的所有城市 (ajax)
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
			jsonBean.setCi_name(bean.getCi_name());
			jsonBean.setCi_rank(bean.getCi_rank());
			jsonList.add(jsonBean);
		}
		String json = new Gson().toJson(jsonList);
		System.out.println("JSON = " + json);

		return json;
	}

}
