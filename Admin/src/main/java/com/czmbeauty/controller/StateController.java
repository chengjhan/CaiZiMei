/*
 * CaiZiMei
 * File: StateController.java
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

import com.czmbeauty.model.entity.CountryBean;
import com.czmbeauty.model.entity.StateBean;
import com.czmbeauty.model.service.CountryService;
import com.czmbeauty.model.service.StateService;
import com.google.gson.Gson;

import misc.CountryBeanPropertyEditor;
import misc.PrimitiveNumberEditor;

/**
 * state controller
 * 
 * @author 詹晟
 */
@Controller
@SessionAttributes("stateList")
public class StateController {

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
	 * 提供 form backing object 資料轉換
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Integer.class, new PrimitiveNumberEditor(Integer.class, true));
		webDataBinder.registerCustomEditor(CountryBean.class, new CountryBeanPropertyEditor());
	}

	/**
	 * 區域一覽 - 采姿美管理系統
	 * 
	 * @param model-->Model
	 * @return /WEB-INF/views/state/list.jsp
	 */
	@RequestMapping(value = "/state/list", method = RequestMethod.GET)
	public String listView(Model model) {

		model.addAttribute("stateList", stateService.selectAll());

		return "state/list";
	}

	/**
	 * 新增區域 - 采姿美管理系統
	 * 
	 * @param model-->Model
	 * @return /WEB-INF/views/state/add.jsp
	 */
	@RequestMapping(value = "/state/add", method = RequestMethod.GET)
	public String addView(Model model) {

		// 取得所有國家 List
		model.addAttribute("countryList", countryService.selectAll());

		// 新增 form backing object
		StateBean stateBean = new StateBean();
		model.addAttribute("stateBean", stateBean);

		return "state/add";
	}

	/**
	 * 新增區域 - submit
	 * 
	 * @param stateBean-->form-backing-object
	 * @return /WEB-INF/views/state/list.jsp
	 */
	@RequestMapping(value = "/state/add.do", method = RequestMethod.POST)
	public String addProcess(StateBean stateBean) {

		stateService.insert(stateBean);

		return "redirect:/state/list";
	}

	/**
	 * 編輯區域資訊 - 采姿美管理系統
	 * 
	 * @param StateBean-->form-backing-object
	 * @param model-->Model
	 * @return /WEB-INF/views/state/edit.jsp
	 */
	@RequestMapping(value = "/state/edit", method = RequestMethod.GET)
	public String editView(StateBean stateBean, Model model) {

		// 取得所有國家 List
		model.addAttribute("countryList", countryService.selectAll());

		// 取得選定 id 的 StateBean
		model.addAttribute("stateBean", stateService.selectByS_id(stateBean.getS_id()));

		return "state/edit";
	}

	/**
	 * 編輯區域資訊 - submit
	 * 
	 * @param stateBean-->form-backing-object
	 * @return /WEB-INF/views/state/list.jsp
	 */
	@RequestMapping(value = "/state/edit.do", method = RequestMethod.POST)
	public String editProcess(StateBean stateBean) {

		stateService.update(stateBean);

		return "redirect:/state/list";
	}

	/**
	 * 刪除區域 - submit
	 * 
	 * @param stateBean-->form-backing-object
	 * @return /WEB-INF/views/state/list.jsp
	 */
	@RequestMapping(value = "/state/delete", method = RequestMethod.GET)
	public String deleteProcess(StateBean stateBean) {

		stateService.delete(stateBean.getS_id());

		return "redirect:/state/list";
	}

	/**
	 * 搜尋選定國家中的所有區域 (ajax)
	 * 
	 * @param s_co_id-->國家流水號
	 * @return state json
	 */
	@RequestMapping(value = "/state/select-by-country.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String selectByCountryAjaxProcess(Integer s_co_id) {

		List<StateBean> result = stateService.selectByS_co_id(s_co_id);

		List<StateBean> jsonList = new ArrayList<StateBean>();
		for (StateBean bean : result) {
			StateBean jsonBean = new StateBean();
			jsonBean.setS_id(bean.getS_id());
			jsonBean.setS_name(bean.getS_name());
			jsonBean.setS_rank(bean.getS_rank());
			jsonList.add(jsonBean);
		}
		String json = new Gson().toJson(jsonList);
		System.out.println("JSON = " + json);

		return json;
	}

}