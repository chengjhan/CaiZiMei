/*
 * CaiZiMei/User
 * File: ClinicController.java
 * Author: 詹晟
 * Date: 2017/7/28
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

import com.czmbeauty.model.entity.ClinicBean;
import com.czmbeauty.model.service.ClinicService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * clinic controller
 * 
 * @author 詹晟
 */
@Controller
public class ClinicController {
	
	private static final Logger logger = Logger.getLogger(ClinicController.class);

	/**
	 * 注入 ClinicService
	 */
	@Autowired
	private ClinicService clinicService;

	/**
	 * 開啟的診所 JSON (AJAX)
	 * 
	 * @return clinic JSON
	 */
	@RequestMapping(value = "/clinic/open-clinic-list.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String openClinicListAjaxProcess() {

		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();

		List<ClinicBean> result = clinicService.selectByCl_status();

		String json = gson.toJson(result);
		
		logger.info("JSON = " + json);

		return json;
	}

}
