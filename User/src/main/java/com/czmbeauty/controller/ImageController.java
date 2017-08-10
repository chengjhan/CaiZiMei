/*
 * CaiZiMei/User
 * File: ImageController.java
 * Author: 詹晟
 * Date: 2017/8/10
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
public class ImageController {

	private static final Logger logger = Logger.getLogger(ImageController.class);

	/**
	 * 注入 ImageService
	 */
	@Autowired
	private ImageService imageService;

	/**
	 * 開啟的圖片 JSON (AJAX)
	 * 
	 * @return image JSON
	 */
	@RequestMapping(value = "/slider-main/open-slide-list.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String openSlideListAjaxProcess() {

		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();

		List<ImageBean> result = imageService.selectByIm_status();

		String json = gson.toJson(result);

		logger.info("JSON = " + json);

		return json;
	}

}
