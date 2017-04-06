/*
 * CaiZiMei
 * File: CountryController.java
 * Author: 詹晟
 * Date: 2017/4/6
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.caizimei.model.entity.CountryBean;
import com.caizimei.model.service.CountryService;
import com.google.gson.Gson;

/**
 * country controller
 * 
 * @author 詹晟
 */
@Controller
@RequestMapping("/admin/country")
@SessionAttributes("countryList")
public class CountryController {

	/**
	 * 注入 CountryService
	 */
	@Autowired
	private CountryService countryService;

	/**
	 * 搜尋全部國家
	 * 
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/country/list.jsp
	 */
	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public String selectProcess(Model model) {

		model.addAttribute("countryList", countryService.select());

		return "admin/country/list";
	}

	/**
	 * 新增國家
	 * 
	 * @param countryBean-->CountryBean
	 * @param redirectAttributes-->RedirectAttributes
	 * @return /WEB-INF/views/admin/country/list.jsp
	 */
	@RequestMapping(path = "/insert.do", method = RequestMethod.POST)
	public String insertProcess(CountryBean countryBean, RedirectAttributes redirectAttributes) {

		countryService.insert(countryBean);
		redirectAttributes.addFlashAttribute("countryList", countryService.select());

		return "redirect:/admin/country/list";
	}

	/**
	 * 修改國家資訊
	 * 
	 * @param countryBean-->CountryBean
	 * @param redirectAttributes-->RedirectAttributes
	 * @return /WEB-INF/views/admin/country/list.jsp
	 */
	@RequestMapping(path = "/update.do", method = RequestMethod.POST)
	public String updateProcess(CountryBean countryBean, RedirectAttributes redirectAttributes) {

		countryService.update(countryBean);
		redirectAttributes.addFlashAttribute("countryList", countryService.select());

		return "redirect:/admin/country/list";
	}

	/**
	 * 刪除國家
	 * 
	 * @param countryBean-->CountryBean
	 * @param redirectAttributes-->RedirectAttributes
	 * @return /WEB-INF/views/admin/country/list.jsp
	 */
	@RequestMapping(path = "/delete.do", method = RequestMethod.GET)
	public String deleteProcess(CountryBean countryBean, RedirectAttributes redirectAttributes) {

		countryService.delete(countryBean.getCo_id());
		redirectAttributes.addFlashAttribute("countryList", countryService.select());

		return "redirect:/admin/country/list";
	}

	/**
	 * 搜尋全部國家 (ajax)
	 * 
	 * @return 國家json
	 */
	@RequestMapping(path = "/select.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String selectAjaxProcess() {

		List<CountryBean> result = countryService.select();

		List<CountryBean> jsonList = new ArrayList<CountryBean>();
		for (CountryBean bean : result) {
			CountryBean jsonBean = new CountryBean();
			jsonBean.setCo_id(bean.getCo_id());
			jsonBean.setCo_name(bean.getCo_name());
			jsonList.add(jsonBean);
		}
		String json = new Gson().toJson(jsonList);
		System.out.println("JSON = " + json);

		return json;
	}

}
