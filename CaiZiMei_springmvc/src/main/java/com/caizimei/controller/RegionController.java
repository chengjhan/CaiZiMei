/*
 * CaiZiMei
 * File: RegionController.java
 * Author: 詹晟
 * Date: 2017/4/15
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

import com.caizimei.model.entity.RegionBean;
import com.caizimei.model.service.CityService;
import com.caizimei.model.service.RegionService;
import com.google.gson.Gson;

/**
 * region controller
 * 
 * @author 詹晟
 */
@Controller
@RequestMapping("/admin/region")
@SessionAttributes("regionList")
public class RegionController {

	/**
	 * 注入 RegionService
	 */
	@Autowired
	private RegionService regionService;

	/**
	 * 注入 CityService
	 */
	@Autowired
	private CityService cityService;

	/**
	 * 條件搜尋
	 * 
	 * @param r_ci_id-->城市流水號
	 * @param regionBean-->RegionBean
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/region/search.jsp
	 */
	@RequestMapping(path = "/search.do", method = RequestMethod.GET)
	public String selectByRegionConditionsProcess(@RequestParam(name = "r_ci_id") Integer r_ci_id,
			RegionBean regionBean, Model model) {

		model.addAttribute("selectByRegionConditions",
				regionService.selectByRegionConditions(r_ci_id, regionBean.getR_name(), regionBean.getR_zipcode()));

		return "admin/region/search";
	}

	/**
	 * 新增區域
	 * 
	 * @param r_ci_id-->城市流水號
	 * @param regionBean-->RegionBean
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/region/search.jsp
	 */
	@RequestMapping(path = "/insert.do", method = RequestMethod.POST)
	public String insertProcess(@RequestParam(name = "r_ci_id") Integer r_ci_id, RegionBean regionBean, Model model) {

		regionBean.setR_CityBean(cityService.selectByCi_id(r_ci_id));
		regionService.insert(regionBean);
		model.addAttribute("regionList", regionService.selectByR_ci_id(r_ci_id));

		return "redirect:/admin/region/search";
	}

	/**
	 * 修改資料
	 * 
	 * @param r_ci_id-->城市流水號
	 * @param regionBean-->RegionBean
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/region/search.jsp
	 */
	@RequestMapping(path = "/update.do", method = RequestMethod.POST)
	public String updateProcess(@RequestParam(name = "r_ci_id") Integer r_ci_id, RegionBean regionBean, Model model) {

		regionBean.setR_CityBean(cityService.selectByCi_id(r_ci_id));
		regionService.update(regionBean);
		model.addAttribute("regionList", regionService.selectByR_ci_id(r_ci_id));

		return "redirect:/admin/region/search";
	}

	/**
	 * 刪除城市
	 * 
	 * @param regionBean-->RegionBean
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/region/search.jsp
	 */
	@RequestMapping(path = "/delete.do", method = RequestMethod.GET)
	public String deleteProcess(RegionBean regionBean, Model model) {

		regionService.delete(regionBean.getR_id());

		return "redirect:/admin/region/search";
	}

	/**
	 * 搜尋城市中的所有區域 (ajax)
	 * 
	 * @param r_ci_id-->城市流水號
	 * @return 區域json
	 */
	@RequestMapping(path = "/select-by-city.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String selectByCityAjaxProcess(Integer r_ci_id) {

		List<RegionBean> result = regionService.selectByR_ci_id(r_ci_id);

		List<RegionBean> jsonList = new ArrayList<RegionBean>();
		for (RegionBean bean : result) {
			RegionBean jsonBean = new RegionBean();
			jsonBean.setR_id(bean.getR_id());
			jsonBean.setR_name(bean.getR_name());
			jsonList.add(jsonBean);
		}
		String json = new Gson().toJson(jsonList);
		System.out.println("JSON = " + json);

		return json;
	}

	/**
	 * 區域流水號搜尋 (ajax)
	 * 
	 * @param r_id-->區域流水號
	 * @return m_zipcode
	 */
	@RequestMapping(path = "/select-by-id.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String selectByIdAjaxProcess(Integer r_id) {

		return regionService.selectByR_id(r_id).getR_zipcode();
	}

}
