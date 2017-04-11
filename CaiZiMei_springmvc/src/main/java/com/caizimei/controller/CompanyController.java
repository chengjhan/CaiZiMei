/*
 * CaiZiMei
 * File: CompanyController.java
 * Author: 詹晟
 * Date: 2017/4/12
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

import com.caizimei.model.entity.CompanyBean;
import com.caizimei.model.service.CompanyService;
import com.google.gson.Gson;

/**
 * company controller
 * 
 * @author 詹晟
 */
@Controller
@RequestMapping("/admin/company")
@SessionAttributes("companyList")
public class CompanyController {

	/**
	 * 注入 CompanyService
	 */
	@Autowired
	private CompanyService companyService;

	/**
	 * 搜尋全部代理商
	 * 
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/company/list.jsp
	 */
	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public String selectProcess(Model model) {

		model.addAttribute("companyList", companyService.select());

		return "admin/company/list";
	}

	/**
	 * 新增代理商
	 * 
	 * @param companyBean-->CompanyBean
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/company/list.jsp
	 */
	@RequestMapping(path = "/insert.do", method = RequestMethod.POST)
	public String insertProcess(CompanyBean companyBean, Model model) {

		companyBean.setCom_insert_time(new java.util.Date());
		companyService.insert(companyBean);
		model.addAttribute("companyList", companyService.select());

		return "redirect:/admin/company/list";
	}

	/**
	 * 搜尋全部公司 (ajax)
	 * 
	 * @return 國家json
	 */
	@RequestMapping(path = "/select.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String selectAjaxProcess() {

		List<CompanyBean> result = companyService.select();

		List<CompanyBean> jsonList = new ArrayList<CompanyBean>();
		for (CompanyBean bean : result) {
			CompanyBean jsonBean = new CompanyBean();
			jsonBean.setCom_id(bean.getCom_id());
			jsonBean.setCom_name(bean.getCom_name());
			jsonList.add(jsonBean);
		}
		String json = new Gson().toJson(jsonList);
		System.out.println("JSON = " + json);

		return json;
	}

}
