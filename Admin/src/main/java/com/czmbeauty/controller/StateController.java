/*
 * CaiZiMei
 * File: StateController.java
 * Author: 詹晟
 * Date: 2017/7/22
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import static com.czmbeauty.common.constants.ModelAttributeConstants.COUNTRY_LIST;
import static com.czmbeauty.common.constants.ModelAttributeConstants.STATE_BEAN;
import static com.czmbeauty.common.constants.ModelAttributeConstants.STATE_LIST;
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
import org.springframework.web.bind.annotation.SessionAttributes;

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
@SessionAttributes(value = STATE_BEAN)
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
	 * @param stateBean-->Session
	 * @param model-->Model
	 * @return /WEB-INF/views/state/list.jsp
	 */
	@RequestMapping(value = "/state/list", method = RequestMethod.GET)
	public String listView(StateBean stateBean, Model model) {

		// 取得所有國家 List
		model.addAttribute(COUNTRY_LIST, countryService.selectAll());

		// 如果 Session 不為空
		if (stateBean.getSt_id() != null) {

			StateBean persistentStateBean = stateService.selectBySt_id(stateBean.getSt_id());

			if (persistentStateBean != null) {

				// 若為編輯，取得編輯的 StateBean，放入 Session
				model.addAttribute(STATE_BEAN, persistentStateBean);
			} else {

				// 若為刪除，取得刪除的 StateBean 中的 CountryBean，放入空 StateBean，放入 Session
				CountryBean deletedSt_CountryBean = new CountryBean();
				StateBean deletedStateBean = new StateBean();
				deletedSt_CountryBean = countryService.selectByCo_id(stateBean.getSt_CountryBean().getCo_id());
				deletedStateBean.setSt_CountryBean(deletedSt_CountryBean);
				model.addAttribute(STATE_BEAN, deletedStateBean);
			}

			// 取得編輯或刪除的 StateBean 的國家，及取得此國家中的所有區域 List
			model.addAttribute(STATE_LIST, stateService.selectBySt_co_id(stateBean.getSt_CountryBean().getCo_id()));
		}

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
	 * @param model-->Model
	 * @return /WEB-INF/views/state/list.jsp
	 */
	@RequestMapping(value = "/state/add.do", method = RequestMethod.POST)
	public String addProcess(StateBean stateBean, Model model) {

		stateService.insert(stateBean);

		// 將新增的 StateBean 放入 Session
		model.addAttribute(STATE_BEAN, stateBean);

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

		// 取得選定區域 id 的 StateBean，放入 Session，使表單回傳 StateBean 內所有資料
		model.addAttribute(STATE_BEAN, stateService.selectBySt_id(stateBean_st_id.getSt_id()));

		return STATE_EDIT_PAGE;
	}

	/**
	 * 編輯區域資訊 - submit
	 * 
	 * @param stateBean-->form-backing-object
	 * @param model-->Model
	 * @return /WEB-INF/views/state/list.jsp
	 */
	@RequestMapping(value = "/state/edit.do", method = RequestMethod.POST)
	public String editProcess(StateBean stateBean, Model model) {

		stateService.update(stateBean);

		// 將編輯的 StateBean 放入 Session
		model.addAttribute(STATE_BEAN, stateBean);

		return REDIRECT + STATE_LIST_PAGE;
	}

	/**
	 * 刪除區域 - submit
	 * 
	 * @param stateBean-->form-backing-object
	 * @param model-->Model
	 * @return /WEB-INF/views/state/list.jsp
	 */
	@RequestMapping(value = "/state/delete", method = RequestMethod.GET)
	public String deleteProcess(StateBean stateBean, Model model) {

		// 將刪除的 StateBean 放入 Session
		model.addAttribute(STATE_BEAN, stateService.selectBySt_id(stateBean.getSt_id()));

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
