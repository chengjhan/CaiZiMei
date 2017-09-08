/*
 * CaiZiMei/User
 * File: ImageController.java
 * Author: 詹晟
 * Date: 2017/9/8
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

import com.czmbeauty.model.entity.ImageBean;
import com.czmbeauty.model.service.CategoryService;
import com.czmbeauty.model.service.ImageService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * image controller
 * 
 * @author 詹晟
 */
@Controller
public class ImageController {

	private static final Logger logger = Logger.getLogger(ImageController.class);

	/**
	 * 注入 HttpServletRequest
	 */
	@Autowired
	private HttpServletRequest request;

	/**
	 * 注入 CategoryService
	 */
	@Autowired
	private CategoryService categoryService;

	/**
	 * 注入 ImageService
	 */
	@Autowired
	private ImageService imageService;

	/**
	 * 開啟的圖片
	 * 
	 * @return JSON
	 */
	private String getJSON() {

		String ca_directory = request.getServletPath().split("/")[2].split("-")[1] + "-"
				+ request.getServletPath().split("/")[2].split("-")[2];

		Integer ca_id = categoryService.selectByCa_directory(ca_directory).getCa_id();

		List<ImageBean> list = imageService.selectOpenImage(String.valueOf(ca_id));

		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();

		String json = gson.toJson(list);

		logger.info("JSON = " + json);

		return json;
	}

	/**
	 * 開啟的輪播圖片 (AJAX)
	 * 
	 * @return image JSON
	 */
	@RequestMapping(value = "/image/open-slider-*-list.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String openSliderListAjaxProcess() {

		return getJSON();
	}

}
