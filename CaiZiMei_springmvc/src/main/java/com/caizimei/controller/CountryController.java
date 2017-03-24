/*
 * CaiZiMei
 * File: CountryController.java
 * Author: 詹晟
 * Date: 2017/3/24
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.caizimei.model.entity.CountryBean;
import com.caizimei.model.service.CountryService;

/**
 * country controller
 * 
 * @author 詹晟
 */
@Controller
@RequestMapping("/country")
@SessionAttributes("countryList")
public class CountryController {

	/**
	 * 注入 CountryService
	 */
	@Autowired
	private CountryService countryService;

	/**
	 * country/list 視圖解析
	 * 
	 * @return /WEB-INF/views/country/list.jsp
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		return new ModelAndView("country/list");
	}

	/**
	 * country/update 視圖解析
	 * 
	 * @return /WEB-INF/views/country/update.jsp
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update() {
		return new ModelAndView("country/update");
	}

	/**
	 * 搜尋全部國家
	 * 
	 * @param model-->Model
	 * @return /WEB-INF/views/country/list.jsp
	 */
	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public ModelAndView selectProcess(Model model) {
		
		model.addAttribute("countryList", countryService.select());
		
		return new ModelAndView("country/list");
	}

	/**
	 * 新增國家
	 * 
	 * @param countryBean-->CountryBean
	 * @param redirectAttributes-->RedirectAttributes
	 * @return /WEB-INF/views/country/list.jsp
	 */
	@RequestMapping(path = "/insert.do", method = RequestMethod.POST)
	public ModelAndView insertProcess(CountryBean countryBean, RedirectAttributes redirectAttributes) {
		
		countryService.insert(countryBean);
		redirectAttributes.addFlashAttribute("countryList", countryService.select());
		
		return new ModelAndView("redirect:/country/list");
	}

	/**
	 * 修改國家資訊
	 * 
	 * @param countryBean-->CountryBean
	 * @param redirectAttributes-->RedirectAttributes
	 * @return /WEB-INF/views/country/list.jsp
	 */
	@RequestMapping(path = "/update.do", method = RequestMethod.POST)
	public ModelAndView updateProcess(CountryBean countryBean, RedirectAttributes redirectAttributes) {
		
		countryService.update(countryBean);
		redirectAttributes.addFlashAttribute("countryList", countryService.select());
		
		return new ModelAndView("redirect:/country/list");
	}

	/**
	 * 刪除國家
	 * 
	 * @param countryBean-->CountryBean
	 * @param redirectAttributes-->RedirectAttributes
	 * @return /WEB-INF/views/country/list.jsp
	 */
	@RequestMapping(path = "/delete.do", method = RequestMethod.GET)
	public ModelAndView deleteProcess(CountryBean countryBean, RedirectAttributes redirectAttributes) {
		
		countryService.delete(countryBean.getCo_id());
		redirectAttributes.addFlashAttribute("countryList", countryService.select());
		
		return new ModelAndView("redirect:/country/list");
	}

}
