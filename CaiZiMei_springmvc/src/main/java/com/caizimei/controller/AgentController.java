/*
 * CaiZiMei
 * File: AgentController.java
 * Author: 詹晟
 * Date: 2017/5/3
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

import com.caizimei.model.entity.AgentBean;
import com.caizimei.model.service.AgentService;
import com.google.gson.Gson;

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
		agentBean.setA_update_time(new java.util.Date());
		agentBean.setA_status(1);
		agentBean.setA_status_time(new java.util.Date());
		agentService.insert(agentBean);
		model.addAttribute("agentList", agentService.select());

		return "redirect:/admin/agent/list";
	}

	/**
	 * 修改資料
	 * 
	 * @param agentBean-->AgentBean
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/agent/list.jsp
	 */
	@RequestMapping(path = "/update.do", method = RequestMethod.POST)
	public String updateProcess(AgentBean newAgentBean, Model model) {

		agentService.update(newAgentBean);
		model.addAttribute("agentList", agentService.select());

		return "redirect:/admin/agent/list";
	}

	/**
	 * 切換顯示狀態
	 * 
	 * @param a_id-->代理商流水號
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/agent/list.jsp
	 */
	@RequestMapping(path = "/update-status.do", method = RequestMethod.GET)
	public String deleteProcess(@RequestParam(name = "a_id") String a_id, Model model) {

		agentService.updateA_status(Integer.valueOf(a_id));
		model.addAttribute("agentList", agentService.select());

		return "redirect:/admin/agent/list";
	}

	/**
	 * 搜尋可顯示的代理商 (ajax)
	 * 
	 * @return 代理商json
	 */
	@RequestMapping(path = "/select-by-status.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String selectAjaxProcess() {

		List<AgentBean> result = agentService.selectByA_status();

		List<AgentBean> jsonList = new ArrayList<AgentBean>();
		for (AgentBean bean : result) {
			AgentBean jsonBean = new AgentBean();
			jsonBean.setA_id(bean.getA_id());
			jsonBean.setA_name(bean.getA_name());
			jsonList.add(jsonBean);
		}
		String json = new Gson().toJson(jsonList);
		System.out.println("JSON = " + json);

		return json;
	}

}
