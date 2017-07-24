/*
 * CaiZiMei
 * File: FranchiseeController.java
 * Author: 詹晟
 * Date: 2017/7/24
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import static com.czmbeauty.common.constants.ModelAttributeConstants.CITY_LIST;
import static com.czmbeauty.common.constants.ModelAttributeConstants.COUNTRY_LIST;
import static com.czmbeauty.common.constants.ModelAttributeConstants.FRANCHISEE_BEAN;
import static com.czmbeauty.common.constants.ModelAttributeConstants.FRANCHISEE_LIST;
import static com.czmbeauty.common.constants.ModelAttributeConstants.STATE_LIST;
import static com.czmbeauty.common.constants.PageNameConstants.FRANCHISEE_ADD_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.FRANCHISEE_EDIT_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.FRANCHISEE_LIST_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.REDIRECT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czmbeauty.common.editor.CityBeanPropertyEditor;
import com.czmbeauty.common.editor.CountryBeanPropertyEditor;
import com.czmbeauty.common.editor.PrimitiveNumberEditor;
import com.czmbeauty.common.editor.StateBeanPropertyEditor;
import com.czmbeauty.model.entity.CityBean;
import com.czmbeauty.model.entity.CountryBean;
import com.czmbeauty.model.entity.FranchiseeBean;
import com.czmbeauty.model.entity.StateBean;
import com.czmbeauty.model.service.CityService;
import com.czmbeauty.model.service.CountryService;
import com.czmbeauty.model.service.FranchiseeService;
import com.czmbeauty.model.service.StateService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * franchisee controller
 * 
 * @author 詹晟
 */
@Controller
public class FranchiseeController {

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
	 * 注入 FranchiseeService
	 */
	@Autowired
	private FranchiseeService franchiseeService;

	/**
	 * 提供 form backing object 資料轉換
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Integer.class, new PrimitiveNumberEditor(Integer.class, true));
		webDataBinder.registerCustomEditor(CountryBean.class, new CountryBeanPropertyEditor());
		webDataBinder.registerCustomEditor(StateBean.class, new StateBeanPropertyEditor());
		webDataBinder.registerCustomEditor(CityBean.class, new CityBeanPropertyEditor());
	}

	/**
	 * 加盟店一覽 - 初期處理
	 * 
	 * @param model-->Model
	 * @return /WEB-INF/views/franchisee/list.jsp
	 */
	@RequestMapping(value = "/franchisee/list", method = RequestMethod.GET)
	public String listView(Model model) {

		// 取得所有加盟店 List，放入 table
		model.addAttribute(FRANCHISEE_LIST, franchiseeService.selectAll());

		return FRANCHISEE_LIST_PAGE;
	}

	/**
	 * 新增加盟店 - 初期處理
	 * 
	 * @param model-->Model
	 * @return /WEB-INF/views/franchisee/add.jsp
	 */
	@RequestMapping(value = "/franchisee/add", method = RequestMethod.GET)
	public String addView(Model model) {

		// 取得所有國家 List，放入 select
		model.addAttribute(COUNTRY_LIST, countryService.selectAll());

		// 新增 form backing object
		model.addAttribute(FRANCHISEE_BEAN, new FranchiseeBean());

		return FRANCHISEE_ADD_PAGE;
	}

	/**
	 * 新增加盟店 - submit
	 * 
	 * @param franchiseeBean-->form-backing-object
	 * @return /WEB-INF/views/franchisee/list.jsp
	 */
	@RequestMapping(value = "/franchisee/add.do", method = RequestMethod.POST)
	public String addProcess(FranchiseeBean franchiseeBean) {

		franchiseeService.insert(franchiseeBean);

		return REDIRECT + FRANCHISEE_LIST_PAGE;
	}

	/**
	 * 編輯加盟店資訊 - 初期處理
	 * 
	 * @param franchiseeBean_fr_id-->form-backing-object-->GET-->fr_id
	 * @param model-->Model
	 * @return /WEB-INF/views/franchisee/edit.jsp
	 */
	@RequestMapping(value = "/franchisee/edit", method = RequestMethod.GET)
	public String editView(FranchiseeBean franchiseeBean_fr_id, Model model) {

		// 取得選定加盟店 id 的 FranchiseeBean
		FranchiseeBean franchiseeBean = franchiseeService.selectByFr_id(franchiseeBean_fr_id.getFr_id());

		// 取得所有國家 List，放入 select
		model.addAttribute(COUNTRY_LIST, countryService.selectAll());

		// 取得加盟店所在國家中的所有區域 List，放入 select
		model.addAttribute(STATE_LIST, stateService.selectBySt_co_id(franchiseeBean.getFr_CountryBean().getCo_id()));

		// 取得加盟店所在區域中的所有城市 List，放入 select
		model.addAttribute(CITY_LIST, cityService.selectByCi_st_id(franchiseeBean.getFr_StateBean().getSt_id()));

		// 使表單回填 FranchiseeBean 內所有資料
		model.addAttribute(FRANCHISEE_BEAN, franchiseeBean);

		return FRANCHISEE_EDIT_PAGE;
	}

	/**
	 * 編輯加盟店資訊 - submit
	 * 
	 * @param franchiseeBean-->form-backing-object
	 * @return /WEB-INF/views/franchisee/list.jsp
	 */
	@RequestMapping(value = "/franchisee/edit.do", method = RequestMethod.POST)
	public String editProcess(FranchiseeBean franchiseeBean) {

		franchiseeService.update(franchiseeBean);

		return REDIRECT + FRANCHISEE_LIST_PAGE;
	}

	/**
	 * 加盟店開關 - submit
	 * 
	 * @param franchiseeBean_fr_id-->form-backing-object-->GET-->fr_id
	 * @param model-->Model
	 * @return /WEB-INF/views/franchisee/list.jsp
	 */
	@RequestMapping(value = "/franchisee/switch", method = RequestMethod.GET)
	public String switchProcess(FranchiseeBean franchiseeBean_fr_id, Model model) {

		// 在同一個 Session 中利用 get() 取出資料為持久化狀態 (Persistent)，物件的內容更新將直接反應至資料庫
		franchiseeService.updateFr_status(franchiseeService.selectByFr_id(franchiseeBean_fr_id.getFr_id()));

		return REDIRECT + FRANCHISEE_LIST_PAGE;
	}

	/**
	 * 所有加盟店 JSON (AJAX)
	 * 
	 * @return franchisee JSON
	 */
	@RequestMapping(value = "/franchisee/all-franchisee-list.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String allFranchiseeListAjaxProcess() {

		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
		Gson gson = builder.create();

		List<FranchiseeBean> result = franchiseeService.selectAll();

		String json = gson.toJson(result);
		System.out.println("JSON = " + json);

		return json;
	}

	/**
	 * 開啟的加盟店 JSON (AJAX)
	 * 
	 * @return franchisee JSON
	 */
	@RequestMapping(value = "/franchisee/open-franchisee-list.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String openFranchiseeListAjaxProcess() {

		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
		Gson gson = builder.create();

		List<FranchiseeBean> result = franchiseeService.selectByFr_status();

		String json = gson.toJson(result);
		System.out.println("JSON = " + json);

		return json;
	}

}
