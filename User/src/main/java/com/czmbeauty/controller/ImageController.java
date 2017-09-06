/*
 * CaiZiMei/User
 * File: ImageController.java
 * Author: 詹晟
 * Date: 2017/9/6
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import static com.czmbeauty.common.constants.HqlConstants.HQL_SELECT_OPEN_SLIDER_FRANCHISEE;
import static com.czmbeauty.common.constants.HqlConstants.HQL_SELECT_OPEN_SLIDER_KNOWLEDGE;
import static com.czmbeauty.common.constants.HqlConstants.HQL_SELECT_OPEN_SLIDER_MAIN;
import static com.czmbeauty.common.constants.HqlConstants.HQL_SELECT_OPEN_SLIDER_RECENT;
import static com.czmbeauty.common.constants.HqlConstants.HQL_SELECT_OPEN_SLIDER_SALE;
import static com.czmbeauty.common.constants.HqlConstants.HQL_SELECT_OPEN_SLIDER_TEAM;

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
	 * 開啟的主輪播圖片 (AJAX)
	 * 
	 * @return image JSON
	 */
	@RequestMapping(value = "/image/open-slider-main-list.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String openSliderMainListAjaxProcess() {

		List<ImageBean> list = imageService.selectOpenImage(HQL_SELECT_OPEN_SLIDER_MAIN);

		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();

		String json = gson.toJson(list);

		logger.info("JSON = " + json);

		return json;
	}

	/**
	 * 開啟的加盟店資訊輪播圖片 (AJAX)
	 * 
	 * @return image JSON
	 */
	@RequestMapping(value = "/image/open-slider-franchisee-list.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String openSliderFranchiseeListAjaxProcess() {

		List<ImageBean> list = imageService.selectOpenImage(HQL_SELECT_OPEN_SLIDER_FRANCHISEE);

		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();

		String json = gson.toJson(list);

		logger.info("JSON = " + json);

		return json;
	}

	/**
	 * 開啟的近期活動輪播圖片 (AJAX)
	 * 
	 * @return image JSON
	 */
	@RequestMapping(value = "/image/open-slider-recent-list.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String openSliderRecentListAjaxProcess() {

		List<ImageBean> list = imageService.selectOpenImage(HQL_SELECT_OPEN_SLIDER_RECENT);

		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();

		String json = gson.toJson(list);

		logger.info("JSON = " + json);

		return json;
	}

	/**
	 * 開啟的優惠活動輪播圖片 (AJAX)
	 * 
	 * @return image JSON
	 */
	@RequestMapping(value = "/image/open-slider-sale-list.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String openSliderSaleListAjaxProcess() {

		List<ImageBean> list = imageService.selectOpenImage(HQL_SELECT_OPEN_SLIDER_SALE);

		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();

		String json = gson.toJson(list);

		logger.info("JSON = " + json);

		return json;
	}

	/**
	 * 開啟的醫療新知輪播圖片 (AJAX)
	 * 
	 * @return image JSON
	 */
	@RequestMapping(value = "/image/open-slider-knowledge-list.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String openSliderKnowledgeListAjaxProcess() {

		List<ImageBean> list = imageService.selectOpenImage(HQL_SELECT_OPEN_SLIDER_KNOWLEDGE);

		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();

		String json = gson.toJson(list);

		logger.info("JSON = " + json);

		return json;
	}

	/**
	 * 開啟的醫療團隊輪播圖片 (AJAX)
	 * 
	 * @return image JSON
	 */
	@RequestMapping(value = "/image/open-slider-team-list.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String openSliderTeamListAjaxProcess() {

		List<ImageBean> list = imageService.selectOpenImage(HQL_SELECT_OPEN_SLIDER_TEAM);

		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();

		String json = gson.toJson(list);

		logger.info("JSON = " + json);

		return json;
	}

}
