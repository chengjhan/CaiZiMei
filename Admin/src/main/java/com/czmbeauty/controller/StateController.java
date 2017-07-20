/*
 * CaiZiMei
 * File: StateController.java
 * Author: 詹晟
 * Date: 2017/7/20
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import static com.czmbeauty.common.constants.ModelAttributeConstants.COUNTRY_LIST;
import static com.czmbeauty.common.constants.ModelAttributeConstants.STATE_BEAN;
import static com.czmbeauty.common.constants.PageNameConstants.REDIRECT;
import static com.czmbeauty.common.constants.PageNameConstants.STATE_ADD_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.STATE_EDIT_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.STATE_LIST_PAGE;

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

import com.czmbeauty.common.editor.CountryBeanPropertyEditor;
import com.czmbeauty.common.editor.PrimitiveNumberEditor;
import com.czmbeauty.model.entity.CountryBean;
import com.czmbeauty.model.entity.StateBean;
import com.czmbeauty.model.service.CountryService;
import com.czmbeauty.model.service.StateService;
import com.google.gson.Gson;

/**
 * state controller
 * 
 * @author 詹晟
 */
@Controller
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

		return STATE_LIST_PAGE;
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
		model.addAttribute(COUNTRY_LIST, countryService.selectAll());

		// 新增 form backing object
		model.addAttribute(STATE_BEAN, new StateBean());

		return STATE_ADD_PAGE;
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

		return REDIRECT + STATE_LIST_PAGE;
	}

	/**
	 * 編輯區域資訊 - 采姿美管理系統
	 * 
	 * @param stateBean_st_id-->form-backing-object-->GET-->st_id
	 * @param model-->Model
	 * @return /WEB-INF/views/state/edit.jsp
	 */
	@RequestMapping(value = "/state/edit", method = RequestMethod.GET)
	public String editView(StateBean stateBean_st_id, Model model) {

		// 取得所有國家 List
		model.addAttribute(COUNTRY_LIST, countryService.selectAll());

		// 取得選定區域 id 的 StateBean，並回傳 StateBean 內所有資料
		model.addAttribute(STATE_BEAN, stateService.selectBySt_id(stateBean_st_id.getSt_id()));

		return STATE_EDIT_PAGE;
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

		return REDIRECT + STATE_LIST_PAGE;
	}

	/**
	 * 刪除區域 - submit
	 * 
	 * @param stateBean-->form-backing-object
	 * @return /WEB-INF/views/state/list.jsp
	 */
	@RequestMapping(value = "/state/delete", method = RequestMethod.GET)
	public String deleteProcess(StateBean stateBean) {

		stateService.delete(stateBean.getSt_id());

		return REDIRECT + STATE_LIST_PAGE;
	}

	/**
	 * 選定國家中的所有區域列表 (AJAX)
	 * 
	 * @param st_co_id-->國家流水號
	 * @return state JSON
	 */
	@RequestMapping(value = "/state/choice-country-state-list.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String choiceCountryStateListAjaxProcess(Integer st_co_id) {

		List<StateBean> result = stateService.selectBySt_co_id(st_co_id);

		List<StateBean> jsonList = new ArrayList<StateBean>();

		for (StateBean bean : result) {
			StateBean jsonBean = new StateBean();
			jsonBean.setSt_id(bean.getSt_id());
			jsonBean.setSt_name(bean.getSt_name());
			jsonBean.setSt_rank(bean.getSt_rank());
			jsonList.add(jsonBean);
		}

		String json = new Gson().toJson(jsonList);
		System.out.println("JSON = " + json);

		return json;
	}

}
