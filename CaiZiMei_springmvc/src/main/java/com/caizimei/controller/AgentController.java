/*
 * CaiZiMei
 * File: AgentController.java
 * Author: 詹晟
 * Date: 2017/4/8
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

import com.caizimei.model.entity.AgentBean;
import com.caizimei.model.service.AgentService;

/**
 * agent controller
 * 
 * @author 詹晟
 */
@Controller
@RequestMapping("/admin/agent")
@SessionAttributes("agentList")
public class AgentController {

	/**
	 * 注入 AgentService
	 */
	@Autowired
	private AgentService agentService;

	/**
	 * 搜尋全部代理商
	 * 
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/agent/list.jsp
	 */
	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public String selectProcess(Model model) {

		model.addAttribute("agentList", agentService.select());

		return "admin/agent/list";
	}

	/**
	 * 新增代理商
	 * 
	 * @param agentBean-->AgentBean
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/agent/list.jsp
	 */
	@RequestMapping(path = "/insert.do", method = RequestMethod.POST)
	public String insertProcess(AgentBean agentBean, Model model) {

		agentBean.setA_insert_time(new java.util.Date());
		agentService.insert(agentBean);
		model.addAttribute("agentList", agentService.select());

		return "redirect:/admin/agent/list";
	}

}
