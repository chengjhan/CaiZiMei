/*
 * CaiZiMei/User
 * File: BaseController.java
 * Author: 詹晟
 * Date: 2017/7/31
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

import com.czmbeauty.model.entity.BaseBean;
import com.czmbeauty.model.service.BaseService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * base controller
 * 
 * @author 詹晟
 */
@Controller
public class BaseController {

	private static final Logger logger = Logger.getLogger(BaseController.class);

	/**
	 * 注入 BaseService
	 */
	@Autowired
	private BaseService baseService;

	/**
	 * 開啟的據點 JSON (AJAX)
	 * 
	 * @return base JSON
	 */
	@RequestMapping(value = "/base/open-list.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String openListAjaxProcess() {

		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();

		List<BaseBean> result = baseService.selectOpen();

		String json = gson.toJson(result);

		logger.info("JSON = " + json);

		return json;
	}

}
