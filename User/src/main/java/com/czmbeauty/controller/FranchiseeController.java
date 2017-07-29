/*
 * CaiZiMei/User
 * File: FranchiseeController.java
 * Author: 詹晟
 * Date: 2017/7/30
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czmbeauty.model.entity.FranchiseeBean;
import com.czmbeauty.model.service.FranchiseeService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * franchisee controller
 * 
 * @author 詹晟
 */
@Controller
public class FranchiseeController {
	
	private static final Logger logger = Logger.getLogger(FranchiseeController.class);

	/**
	 * 注入 FranchiseeService
	 */
	@Autowired
	private FranchiseeService franchiseeService;

	/**
	 * 開啟的診所 JSON (AJAX)
	 * 
	 * @return franchisee JSON
	 */
	@RequestMapping(value = "/franchisee/open-franchisee-list.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String openFranchiseeListAjaxProcess() {

		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();

		List<FranchiseeBean> result = franchiseeService.selectByFr_status();

		String json = gson.toJson(result);
		
		logger.info("JSON = " + json);

		return json;
	}

}
