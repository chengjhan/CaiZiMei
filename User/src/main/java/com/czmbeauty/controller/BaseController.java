/*
 * CaiZiMei/User
 * File: BaseController.java
 * Author: 詹晟
 * Date: 2017/11/28
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

import com.czmbeauty.common.constants.ControllerConstants;
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
public class BaseController implements ControllerConstants {

	private static final Logger logger = Logger.getLogger(BaseController.class);

	private String className = this.getClass().getSimpleName();

	/**
	 * 注入 BaseService
	 */
	@Autowired
	private BaseService baseService;

	/**
	 * 開啟的據點 (AJAX)
	 * 
	 * @return base JSON
	 */
	@RequestMapping(value = "/base/open-base-list.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String openBaseListAjax() {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		List<BaseBean> list = baseService.selectOpenBase();

		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();

		String json = gson.toJson(list);

		logger.info("(" + className + "." + methodName + ") JSON = " + json);

		return json;
	}

}
