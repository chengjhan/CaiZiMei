/*
 * CaiZiMei
 * File: BaseController.java
 * Author: 詹晟
 * Date: 2017/7/30
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import static com.czmbeauty.common.constants.ModelAttributeConstants.BASE_BEAN;
import static com.czmbeauty.common.constants.ModelAttributeConstants.BASE_LIST;
import static com.czmbeauty.common.constants.ModelAttributeConstants.CITY_LIST;
import static com.czmbeauty.common.constants.ModelAttributeConstants.COUNTRY_LIST;
import static com.czmbeauty.common.constants.ModelAttributeConstants.STATE_LIST;
import static com.czmbeauty.common.constants.PageNameConstants.OFFICE_ADD_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.OFFICE_EDIT_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.OFFICE_LIST_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.REDIRECT;

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
import com.czmbeauty.model.entity.BaseBean;
import com.czmbeauty.model.entity.CityBean;
import com.czmbeauty.model.entity.CountryBean;
import com.czmbeauty.model.entity.StateBean;
import com.czmbeauty.model.service.BaseService;
import com.czmbeauty.model.service.CategoryService;
import com.czmbeauty.model.service.CityService;
import com.czmbeauty.model.service.CountryService;
import com.czmbeauty.model.service.StateService;

/**
 * base controller
 * 
 * @author 詹晟
 */
@Controller
public class BaseController {

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
	 * 注入 CategoryService
	 */
	@Autowired
	private CategoryService categoryService;

	/**
	 * 注入 BaseService
	 */
	@Autowired
	private BaseService baseService;

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
	 * 辦事處一覽 - 初期處理
	 * 
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/office/list.jsp
	 */
	@RequestMapping(value = "/office/list", method = RequestMethod.GET)
	public String officeListView(Model model) {

		// 取得所有辦事處 List，放入 table
		model.addAttribute(BASE_LIST, baseService.selectAllOffice());

		return OFFICE_LIST_PAGE;
	}

	/**
	 * 新增辦事處 - 初期處理
	 * 
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/office/add.jsp
	 */
	@RequestMapping(value = "/office/add", method = RequestMethod.GET)
	public String officeAddView(Model model) {

		// 取得所有國家 List，放入 select
		model.addAttribute(COUNTRY_LIST, countryService.selectAll());

		// 新增 form backing object
		model.addAttribute(BASE_BEAN, new BaseBean());

		return OFFICE_ADD_PAGE;
	}

	/**
	 * 新增辦事處 - submit
	 * 
	 * @param beasBean
	 *            BaseBean --> form backing object
	 * @return /WEB-INF/views/office/list.jsp
	 */
	@RequestMapping(value = "/office/add.do", method = RequestMethod.POST)
	public String officeAddProcess(BaseBean baseBean) {

		baseBean.setBa_CategoryBean(categoryService.selectByCa_id(1));

		baseService.insert(baseBean);

		return REDIRECT + OFFICE_LIST_PAGE;
	}

	/**
	 * 編輯辦事處資訊 - 初期處理
	 * 
	 * @param baseBean_ba_id
	 *            BaseBean --> form backing object --> GET --> ba_id
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/office/edit.jsp
	 */
	@RequestMapping(value = "/office/edit", method = RequestMethod.GET)
	public String officeEditView(BaseBean baseBean_ba_id, Model model) {

		// 取得選定辦事處 id 的 BaseBean
		BaseBean baseBean = baseService.selectByBa_id(baseBean_ba_id.getBa_id());

		// 取得所有國家 List，放入 select
		model.addAttribute(COUNTRY_LIST, countryService.selectAll());

		// 取得辦事處所在國家中的所有區域 List，放入 select
		model.addAttribute(STATE_LIST, stateService.selectBySt_co_id(baseBean.getBa_CountryBean().getCo_id()));

		// 取得辦事處所在區域中的所有城市 List，放入 select
		model.addAttribute(CITY_LIST, cityService.selectByCi_st_id(baseBean.getBa_StateBean().getSt_id()));

		// 使表單回填 BaseBean 內所有資料
		model.addAttribute(BASE_BEAN, baseBean);

		return OFFICE_EDIT_PAGE;
	}

	/**
	 * 編輯辦事處資訊 - submit
	 * 
	 * @param baseBean
	 *            BaseBean --> form backing object
	 * @return /WEB-INF/views/office/list.jsp
	 */
	@RequestMapping(value = "/office/edit.do", method = RequestMethod.POST)
	public String officeEditProcess(BaseBean baseBean) {

		baseService.update(baseBean);

		return REDIRECT + OFFICE_LIST_PAGE;
	}

	/**
	 * 據點開關 (AJAX)
	 * 
	 * @param ba_id
	 *            String --> 據點流水號
	 * @return ba_name
	 */
	@RequestMapping(value = "/base/switch.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String switchAjaxProcess(String ba_id) {

		return baseService.updateBa_status(Integer.valueOf(ba_id)).getBa_name();
	}

}
