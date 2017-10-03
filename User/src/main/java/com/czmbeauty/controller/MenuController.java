/*
 * CaiZiMei/User
 * File: MenuController.java
 * Author: 詹晟
 * Date: 2017/10/3
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
 * menu controller
 * 
 * @author 詹晟
 */
@Controller
public class MenuController implements ModelAttributeConstants, PageNameConstants {

	/**
	 * 采姿美 - 經營理念
	 * 
	 * @return /WEB-INF/views/menu/idea.jsp
	 */
	@RequestMapping(value = "/menu/idea", method = RequestMethod.GET)
	public String ideaView() {

		return MENU_IDEA_PAGE;
	}

	/**
	 * 采姿美 - 公司願景
	 * 
	 * @return /WEB-INF/views/menu/vision.jsp
	 */
	@RequestMapping(value = "/menu/vision", method = RequestMethod.GET)
	public String visionView() {

		return MENU_IDEA_VISION;
	}

	/**
	 * 采姿美 - 公司使命
	 * 
	 * @return /WEB-INF/views/menu/mission.jsp
	 */
	@RequestMapping(value = "/menu/mission", method = RequestMethod.GET)
	public String missionView() {

		return MENU_IDEA_MISSION;
	}

	/**
	 * 采姿美 - 事業版圖
	 * 
	 * @return /WEB-INF/views/menu/territory.jsp
	 */
	@RequestMapping(value = "/menu/territory", method = RequestMethod.GET)
	public String territoryView() {

		return MENU_IDEA_TERRITORY;
	}

}
