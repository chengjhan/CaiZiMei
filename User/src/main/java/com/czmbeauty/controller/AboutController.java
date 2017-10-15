/*
 * CaiZiMei/User
 * File: AboutController.java
 * Author: 詹晟
 * Date: 2017/10/15
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.czmbeauty.common.constants.ModelAttributeConstants;
import com.czmbeauty.common.constants.PageNameConstants;

/**
 * about controller
 * 
 * @author 詹晟
 */
@Controller
public class AboutController implements ModelAttributeConstants, PageNameConstants {

	/**
	 * 經營理念 - 初期處理
	 * 
	 * @return /WEB-INF/views/about/idea.jsp
	 */
	@RequestMapping(value = "/about/idea", method = RequestMethod.GET)
	public String ideaView() {

		return MENU_IDEA_PAGE;
	}

	/**
	 * 公司願景 - 初期處理
	 * 
	 * @return /WEB-INF/views/about/vision.jsp
	 */
	@RequestMapping(value = "/about/vision", method = RequestMethod.GET)
	public String visionView() {

		return MENU_IDEA_VISION;
	}

	/**
	 * 公司使命 - 初期處理
	 * 
	 * @return /WEB-INF/views/about/mission.jsp
	 */
	@RequestMapping(value = "/about/mission", method = RequestMethod.GET)
	public String missionView() {

		return MENU_IDEA_MISSION;
	}

	/**
	 * 事業版圖 - 初期處理
	 * 
	 * @return /WEB-INF/views/about/territory.jsp
	 */
	@RequestMapping(value = "/about/territory", method = RequestMethod.GET)
	public String territoryView() {

		return MENU_IDEA_TERRITORY;
	}

}
