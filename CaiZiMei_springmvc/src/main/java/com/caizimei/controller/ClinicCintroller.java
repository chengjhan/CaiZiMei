/*
 * CaiZiMei
 * File: ClinicCintroller.java
 * Author: 詹晟
 * Date: 2017/7/7
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

import com.caizimei.model.entity.ClinicBean;
import com.caizimei.model.service.ClinicService;
import com.caizimei.model.service.RegionService;
import com.google.gson.Gson;

/**
 * clinic controller
 * 
 * @author 詹晟
 */
@Controller
@RequestMapping("/admin/clinic")
@SessionAttributes("clinicList")
public class ClinicCintroller {

	/**
	 * 注入 ClinicService
	 */
	@Autowired
	private ClinicService clinicService;

	/**
	 * 注入 RegionService
	 */
	@Autowired
	private RegionService regionService;

	/**
	 * 搜尋全部診所
	 * 
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/clinic/list.jsp
	 */
	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public String selectProcess(Model model) {

		model.addAttribute("clinicList", clinicService.select());

		return "admin/clinic/list";
	}

	/**
	 * 條件搜尋
	 * 
	 * @param clinicBean-->ClinicBean
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/clinic/search.jsp
	 */
	@RequestMapping(path = "/search.do", method = RequestMethod.GET)
	public String selectByClinicConditionsProcess(ClinicBean clinicBean, Model model) {

		model.addAttribute("selectByClinicConditions", clinicService
				.selectByClinicConditions(clinicBean.getC_name().trim(), clinicBean.getC_localphone().trim()));

		return "admin/clinic/search";
	}

	/**
	 * 新增診所
	 * 
	 * @param clinicBean-->ClinicBean
	 * @param c_localphone_front-->診所電話(前碼)
	 * @param c_localphone_back-->診所電話(後碼)
	 * @param c_r_id-->區域流水號
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/clinic/list.jsp
	 */
	@RequestMapping(path = "/insert.do", method = RequestMethod.POST)
	public String insertProcess(ClinicBean clinicBean,
			@RequestParam(name = "c_localphone_front") String c_localphone_front,
			@RequestParam(name = "c_localphone_back") String c_localphone_back,
			@RequestParam(name = "c_r_id") String c_r_id, Model model) {

		clinicBean.setC_localphone(c_localphone_front + "-" + c_localphone_back);
		clinicBean.setC_RegionBean(regionService.selectByR_id(Integer.valueOf(c_r_id)));

		// 取得經緯度
		String ci_name = regionService.selectByR_id(Integer.valueOf(c_r_id)).getR_CityBean().getCi_name();
		String r_name = regionService.selectByR_id(Integer.valueOf(c_r_id)).getR_name();
		Double[] LatLng = new Double[2];
		try {
			LatLng = clinicService.addressToLatLng(ci_name + r_name + clinicBean.getC_address());
		} catch (Exception e) {
			e.printStackTrace();
		}

		clinicBean.setC_latitude(LatLng[0]);
		clinicBean.setC_longitude(LatLng[1]);
		clinicBean.setC_insert_time(new java.util.Date());
		clinicBean.setC_update_time(new java.util.Date());
		clinicBean.setC_status(1);
		clinicBean.setC_status_time(new java.util.Date());

		clinicService.insert(clinicBean);
		model.addAttribute("clinicList", clinicService.select());

		return "redirect:/admin/clinic/list";
	}

	/**
	 * 修改資料
	 * 
	 * @param clinicBean-->ClinicBean
	 * @param c_r_id-->區域流水號
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/clinic/insert.jsp
	 */
	@RequestMapping(path = "/update.do", method = RequestMethod.POST)
	public String updateProcess(ClinicBean clinicBean, @RequestParam(name = "c_r_id") String c_r_id, Model model) {

		clinicBean.setC_RegionBean(regionService.selectByR_id(Integer.valueOf(c_r_id)));

		// 取得經緯度
		String ci_name = regionService.selectByR_id(Integer.valueOf(c_r_id)).getR_CityBean().getCi_name();
		String r_name = regionService.selectByR_id(Integer.valueOf(c_r_id)).getR_name();
		Double[] LatLng = new Double[2];
		try {
			LatLng = clinicService.addressToLatLng(ci_name + r_name + clinicBean.getC_address());
		} catch (Exception e) {
			e.printStackTrace();
		}

		clinicBean.setC_latitude(LatLng[0]);
		clinicBean.setC_longitude(LatLng[1]);
		clinicBean.setC_update_time(new java.util.Date());

		clinicService.update(clinicBean);
		model.addAttribute("clinicList", clinicService.select());

		return "redirect:/admin/clinic/list";
	}

	/**
	 * 切換顯示狀態
	 * 
	 * @param c_id-->診所流水號
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/clinic/list.jsp
	 */
	@RequestMapping(path = "/update-status.do", method = RequestMethod.GET)
	public String deleteProcess(@RequestParam(name = "c_id") String c_id, Model model) {

		clinicService.updateC_status(Integer.valueOf(c_id));
		model.addAttribute("clinicList", clinicService.select());

		return "redirect:/admin/clinic/list";
	}

	/**
	 * 搜尋全部診所 (ajax)
	 * 
	 * @return 診所json
	 */
	@RequestMapping(path = "/select.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String selectByStatusAjaxProcess() {

		List<ClinicBean> result = clinicService.select();

		List<ClinicBean> jsonList = new ArrayList<ClinicBean>();
		for (ClinicBean bean : result) {
			ClinicBean jsonBean = new ClinicBean();
			jsonBean.setC_id(bean.getC_id());
			jsonBean.setC_name(bean.getC_name());
			jsonBean.setC_latitude(bean.getC_latitude());
			jsonBean.setC_longitude(bean.getC_longitude());
			jsonBean.setC_url(bean.getC_url());
			jsonList.add(jsonBean);
		}
		String json = new Gson().toJson(jsonList);
		System.out.println("JSON = " + json);

		return json;
	}

	/**
	 * 搜尋區域中可顯示的診所 (ajax)
	 * 
	 * @param c_r_id-->區域流水號
	 * @return 診所json
	 */
	@RequestMapping(path = "/select-by-region.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String selectByRegionAjaxProcess(String c_r_id) {

		List<ClinicBean> result = clinicService.selectByC_r_id(Integer.valueOf(c_r_id));

		List<ClinicBean> jsonList = new ArrayList<ClinicBean>();
		for (ClinicBean bean : result) {
			ClinicBean jsonBean = new ClinicBean();
			jsonBean.setC_id(bean.getC_id());
			jsonBean.setC_name(bean.getC_name());
			jsonList.add(jsonBean);
		}
		String json = new Gson().toJson(jsonList);
		System.out.println("JSON = " + json);

		return json;
	}

}
