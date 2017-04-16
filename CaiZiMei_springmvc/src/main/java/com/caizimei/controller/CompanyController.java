/*
 * CaiZiMei
 * File: CompanyController.java
 * Author: 詹晟
 * Date: 2017/4/17
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
	 * 搜尋全部公司
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
	 * 新增公司
	 * 
	 * @param companyBean-->CompanyBean
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/company/list.jsp
	 */
	@RequestMapping(path = "/insert.do", method = RequestMethod.POST)
	public String insertProcess(CompanyBean companyBean, Model model) {

		companyBean.setCom_insert_time(new java.util.Date());
		companyBean.setCom_status(1);
		companyBean.setCom_status_time(new java.util.Date());
		companyService.insert(companyBean);
		model.addAttribute("companyList", companyService.select());

		return "redirect:/admin/company/list";
	}

	/**
	 * 修改公司資訊
	 * 
	 * @param companyBean-->CompanyBean
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/company/list.jsp
	 */
	@RequestMapping(path = "/update.do", method = RequestMethod.POST)
	public String updateProcess(CompanyBean newCompanyBean, Model model) {

		companyService.update(newCompanyBean);
		model.addAttribute("companyList", companyService.select());

		return "redirect:/admin/company/list";
	}

	/**
	 * 切換公司顯示狀態
	 * 
	 * @param com_id-->公司流水號
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/company/list.jsp
	 */
	@RequestMapping(path = "/update-status.do", method = RequestMethod.GET)
	public String deleteProcess(@RequestParam(name = "com_id") String com_id, Model model) {

		companyService.updateCom_status(Integer.valueOf(com_id));
		model.addAttribute("companyList", companyService.select());

		return "redirect:/admin/company/list";
	}

	/**
	 * 搜尋可顯示的公司 (ajax)
	 * 
	 * @return 公司json
	 */
	@RequestMapping(path = "/select-by-status.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String selectAjaxProcess() {

		List<CompanyBean> result = companyService.selectByCom_status();

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
