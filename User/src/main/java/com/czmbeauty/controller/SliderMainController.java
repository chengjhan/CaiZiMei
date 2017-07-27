/*
 * CaiZiMei/User
 * File: SliderMainController.java
 * Author: 詹晟
 * Date: 2017/7/27
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czmbeauty.model.entity.SliderMainBean;
import com.czmbeauty.model.service.SliderMainService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * slider_main controller
 * 
 * @author 詹晟
 */
@Controller
public class SliderMainController {

	/**
	 * 注入 SliderMainService
	 */
	@Autowired
	private SliderMainService sliderMainService;

	/**
	 * 開啟的圖片 JSON (AJAX)
	 * 
	 * @return slide JSON
	 */
	@RequestMapping(value = "/slider-main/open-slide-list.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String openClinicListAjaxProcess() {

		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();

		List<SliderMainBean> result = sliderMainService.selectBySm_status();

		String json = gson.toJson(result);
		System.out.println("JSON = " + json);

		return json;
	}

}
