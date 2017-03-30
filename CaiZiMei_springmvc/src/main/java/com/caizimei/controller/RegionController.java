/*
 * CaiZiMei
 * File: RegionController.java
 * Author: 詹晟
 * Date: 2017/3/30
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
import org.springframework.web.servlet.ModelAndView;

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
	 * 新增區域
	 * 
	 * @param ci_id-->城市流水號
	 * @param regionBean-->RegionBean
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/region/insert.jsp
	 */
	@RequestMapping(path = "/insert.do", method = RequestMethod.POST)
	public ModelAndView insertProcess(@RequestParam(name = "ci_id") Integer ci_id, RegionBean regionBean, Model model) {

		regionBean.setR_CityBean(cityService.selectByCi_id(ci_id));
		regionService.insert(regionBean);
		model.addAttribute("regionList", regionService.selectByR_ci_id(ci_id));

		return new ModelAndView("redirect:/admin/region/insert");
	}

	/**
	 * 修改城市資訊
	 * 
	 * @param ci_id-->城市流水號
	 * @param regionBean-->RegionBean
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/region/insert.jsp
	 */
	@RequestMapping(path = "/update.do", method = RequestMethod.POST)
	public ModelAndView updateProcess(@RequestParam(name = "ci_id") Integer ci_id, RegionBean regionBean, Model model) {

		regionBean.setR_CityBean(cityService.selectByCi_id(ci_id));
		regionService.update(regionBean);
		model.addAttribute("regionList", regionService.selectByR_ci_id(ci_id));

		return new ModelAndView("redirect:/admin/region/insert");
	}

	/**
	 * 刪除城市
	 * 
	 * @param regionBean-->RegionBean
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/region/insert.jsp
	 */
	@RequestMapping(path = "/delete.do", method = RequestMethod.GET)
	public ModelAndView deleteProcess(RegionBean regionBean, Model model) {

		regionService.delete(regionBean.getR_id());
		model.addAttribute("regionList", regionService.selectByR_ci_id(regionBean.getR_CityBean().getCi_id()));

		return new ModelAndView("redirect:/admin/region/insert");
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

}
