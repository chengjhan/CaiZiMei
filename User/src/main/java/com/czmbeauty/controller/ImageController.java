/*
 * CaiZiMei/User
 * File: ImageController.java
 * Author: 詹晟
 * Date: 2017/11/29
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czmbeauty.common.constants.ControllerConstants;
import com.czmbeauty.model.entity.ImageBean;
import com.czmbeauty.model.service.ImageService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * image controller
 * 
 * @author 詹晟
 */
@Controller
public class ImageController implements ControllerConstants {

	private static final Logger logger = Logger.getLogger(ImageController.class);

	private String className = this.getClass().getSimpleName();

	/**
	 * 注入 HttpServletRequest
	 */
	@Autowired
	private HttpServletRequest request;

	/**
	 * 注入 ImageService
	 */
	@Autowired
	private ImageService imageService;

	/**
	 * 開啟的輪播圖片 (AJAX)
	 * 
	 * @return image JSON
	 */
	@RequestMapping(value = "/slider/*.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String openSliderAjax() {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		String servletPath = request.getServletPath();
		String ca_directory = servletPath.split("/")[1] + HYPHEN + servletPath.split("/")[2].split("\\.")[0];

		List<ImageBean> list = imageService.selectOpenImage(ca_directory);

		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();

		String json = gson.toJson(list);

		logger.info("(" + className + "." + methodName + ") JSON = " + json);

		return json;
	}

}
