/*
 * CaiZiMei
 * File: ClinicController.java
 * Author: 詹晟
 * Date: 2017/7/18
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.czmbeauty.model.entity.CityBean;
import com.czmbeauty.model.entity.ClinicBean;
import com.czmbeauty.model.entity.CountryBean;
import com.czmbeauty.model.entity.StateBean;
import com.czmbeauty.model.service.CityService;
import com.czmbeauty.model.service.ClinicService;
import com.czmbeauty.model.service.CountryService;

import misc.CityBeanPropertyEditor;
import misc.CountryBeanPropertyEditor;
import misc.PrimitiveNumberEditor;
import misc.StateBeanPropertyEditor;

/**
 * clinic controller
 * 
 * @author 詹晟
 */
@Controller
@SessionAttributes("clinicList")
public class ClinicController {

	/**
	 * 注入 CountryService
	 */
	@Autowired
	private CountryService countryService;

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
	 * 診所一覽 - 采姿美管理系統
	 * 
	 * @param model-->Model
	 * @return /WEB-INF/views/clinic/list.jsp
	 */
	@RequestMapping(value = "/clinic/list", method = RequestMethod.GET)
	public String listView(Model model) {

		model.addAttribute("clinicList", clinicService.selectAll());

		return "clinic/list";
	}

	/**
	 * 新增診所 - 采姿美管理系統
	 * 
	 * @param model-->Model
	 * @return /WEB-INF/views/clinic/add.jsp
	 */
	@RequestMapping(value = "/clinic/add", method = RequestMethod.GET)
	public String addView(Model model) {

		// 取得所有國家 List
		model.addAttribute("countryList", countryService.selectAll());

		// 新增 form backing object
		model.addAttribute("clinicBean", new ClinicBean());

		return "clinic/add";
	}

	/**
	 * 新增診所 - submit
	 * 
	 * @param clinicBean-->form-backing-object
	 * @return /WEB-INF/views/clinic/list.jsp
	 */
	@RequestMapping(value = "/clinic/add.do", method = RequestMethod.POST)
	public String addProcess(ClinicBean clinicBean) {

		// 取得經緯度
		String ci_name = cityService.selectByCi_id(clinicBean.getCl_CityBean().getCi_id()).getCi_name();
		String cl_address = clinicBean.getCl_address();
		System.out.println(cl_address);
		Double[] LatLng = new Double[2];
		try {
			LatLng = clinicService.addressToLatLng(ci_name + cl_address);
		} catch (Exception e) {
			e.printStackTrace();
		}

		clinicBean.setCl_latitude(LatLng[0]);
		clinicBean.setCl_longitude(LatLng[1]);
		clinicBean.setCl_insert_time(new java.util.Date());
		clinicBean.setCl_update_time(new java.util.Date());
		clinicBean.setCl_status(1);
		clinicBean.setCl_status_time(new java.util.Date());

		clinicService.insert(clinicBean);

		return "redirect:/clinic/list";
	}

	/**
	 * 編輯診所資訊 - 采姿美管理系統
	 * 
	 * @param clinicBean-->form-backing-object
	 * @param model-->Model
	 * @return /WEB-INF/views/clinic/edit.jsp
	 */
	@RequestMapping(value = "/clinic/edit", method = RequestMethod.GET)
	public String editView(ClinicBean clinicBean, Model model) {

		// 取得選定診所 id 的 ClinicBean
		model.addAttribute("clinicBean", clinicService.selectByCl_id(clinicBean.getCl_id()));

		return "clinic/edit";
	}

	/**
	 * 編輯診所資訊 - submit
	 * 
	 * @param clinicBean-->form-backing-object
	 * @return /WEB-INF/views/clinic/list.jsp
	 */
	@RequestMapping(value = "/clinic/edit.do", method = RequestMethod.POST)
	public String editProcess(ClinicBean clinicBean) {

		// 取得經緯度
		String ci_name = clinicBean.getCl_CityBean().getCi_name();
		String cl_address = clinicBean.getCl_address();
		Double[] LatLng = new Double[2];
		try {
			LatLng = clinicService.addressToLatLng(ci_name + cl_address);
		} catch (Exception e) {
			e.printStackTrace();
		}

		clinicBean.setCl_latitude(LatLng[0]);
		clinicBean.setCl_longitude(LatLng[1]);
		clinicBean.setCl_update_time(new java.util.Date());

		clinicService.update(clinicBean);

		return "redirect:/clinic/list";
	}

}
