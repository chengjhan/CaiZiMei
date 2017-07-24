/*
 * CaiZiMei
 * File: ClinicController.java
 * Author: 詹晟
 * Date: 2017/7/24
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import static com.czmbeauty.common.constants.ModelAttributeConstants.CITY_LIST;
import static com.czmbeauty.common.constants.ModelAttributeConstants.CLINIC_BEAN;
import static com.czmbeauty.common.constants.ModelAttributeConstants.CLINIC_LIST;
import static com.czmbeauty.common.constants.ModelAttributeConstants.COUNTRY_LIST;
import static com.czmbeauty.common.constants.ModelAttributeConstants.STATE_LIST;
import static com.czmbeauty.common.constants.PageNameConstants.CLINIC_ADD_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.CLINIC_EDIT_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.CLINIC_LIST_PAGE;
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
import com.czmbeauty.model.entity.ClinicBean;
import com.czmbeauty.model.entity.CountryBean;
import com.czmbeauty.model.entity.StateBean;
import com.czmbeauty.model.service.CityService;
import com.czmbeauty.model.service.ClinicService;
import com.czmbeauty.model.service.CountryService;
import com.czmbeauty.model.service.StateService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * clinic controller
 * 
 * @author 詹晟
 */
@Controller
public class ClinicController {

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
	 * 注入 ClinicService
	 */
	@Autowired
	private ClinicService clinicService;

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
	 * 診所一覽 - 初期處理
	 * 
	 * @param model-->Model
	 * @return /WEB-INF/views/clinic/list.jsp
	 */
	@RequestMapping(value = "/clinic/list", method = RequestMethod.GET)
	public String listView(Model model) {

		// 取得所有診所 List，放入 table
		model.addAttribute(CLINIC_LIST, clinicService.selectAll());

		return CLINIC_LIST_PAGE;
	}

	/**
	 * 新增診所 - 初期處理
	 * 
	 * @param model-->Model
	 * @return /WEB-INF/views/clinic/add.jsp
	 */
	@RequestMapping(value = "/clinic/add", method = RequestMethod.GET)
	public String addView(Model model) {

		// 取得所有國家 List，放入 select
		model.addAttribute(COUNTRY_LIST, countryService.selectAll());

		// 新增 form backing object
		model.addAttribute(CLINIC_BEAN, new ClinicBean());

		return CLINIC_ADD_PAGE;
	}

	/**
	 * 新增診所 - submit
	 * 
	 * @param clinicBean-->form-backing-object
	 * @return /WEB-INF/views/clinic/list.jsp
	 */
	@RequestMapping(value = "/clinic/add.do", method = RequestMethod.POST)
	public String addProcess(ClinicBean clinicBean) {

		clinicService.insert(clinicBean);

		return REDIRECT + CLINIC_LIST_PAGE;
	}

	/**
	 * 編輯診所資訊 - 初期處理
	 * 
	 * @param clinicBean_cl_id-->form-backing-object-->GET-->cl_id
	 * @param model-->Model
	 * @return /WEB-INF/views/clinic/edit.jsp
	 */
	@RequestMapping(value = "/clinic/edit", method = RequestMethod.GET)
	public String editView(ClinicBean clinicBean_cl_id, Model model) {

		// 取得選定診所 id 的 ClinicBean
		ClinicBean clinicBean = clinicService.selectByCl_id(clinicBean_cl_id.getCl_id());

		// 取得所有國家 List，放入 select
		model.addAttribute(COUNTRY_LIST, countryService.selectAll());

		// 取得診所所在國家中的所有區域 List，放入 select
		model.addAttribute(STATE_LIST, stateService.selectBySt_co_id(clinicBean.getCl_CountryBean().getCo_id()));

		// 取得診所所在區域中的所有城市 List，放入 select
		model.addAttribute(CITY_LIST, cityService.selectByCi_st_id(clinicBean.getCl_StateBean().getSt_id()));

		// 使表單回填 ClinicBean 內所有資料
		model.addAttribute(CLINIC_BEAN, clinicBean);

		return CLINIC_EDIT_PAGE;
	}

	/**
	 * 編輯診所資訊 - submit
	 * 
	 * @param clinicBean-->form-backing-object
	 * @return /WEB-INF/views/clinic/list.jsp
	 */
	@RequestMapping(value = "/clinic/edit.do", method = RequestMethod.POST)
	public String editProcess(ClinicBean clinicBean) {

		clinicService.update(clinicBean);

		return REDIRECT + CLINIC_LIST_PAGE;
	}

	/**
	 * 診所開關 - submit
	 * 
	 * @param clinicBean_cl_id-->form-backing-object-->GET-->cl_id
	 * @param model-->Model
	 * @return /WEB-INF/views/clinic/list.jsp
	 */
	@RequestMapping(value = "/clinic/switch", method = RequestMethod.GET)
	public String switchProcess(ClinicBean clinicBean_cl_id, Model model) {

		clinicService.updateCl_status(clinicBean_cl_id);

		return REDIRECT + CLINIC_LIST_PAGE;
	}

	/**
	 * 所有診所 JSON (AJAX)
	 * 
	 * @return clinic JSON
	 */
	@RequestMapping(value = "/clinic/all-clinic-list.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String allClinicListAjaxProcess() {

		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
		Gson gson = builder.create();

		List<ClinicBean> result = clinicService.selectAll();

		String json = gson.toJson(result);
		System.out.println("JSON = " + json);

		return json;
	}

	/**
	 * 開啟的診所 JSON (AJAX)
	 * 
	 * @return clinic JSON
	 */
	@RequestMapping(value = "/clinic/open-clinic-list.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String openClinicListAjaxProcess() {

		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
		Gson gson = builder.create();

		List<ClinicBean> result = clinicService.selectByCl_status();

		String json = gson.toJson(result);
		System.out.println("JSON = " + json);

		return json;
	}

}
