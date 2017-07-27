/*
 * CaiZiMei/User
 * File: MenuController.java
 * Author: 詹晟
 * Date: 2017/7/27
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * menu controller
 * 
 * @author 詹晟
 */
@Controller
public class MenuController {

	/**
	 * 采姿美 - 經營理念
	 * 
	 * @return /WEB-INF/views/menu/idea.jsp
	 */
	@RequestMapping(value = "/menu/idea", method = RequestMethod.GET)
	public String ideaView() {

		return "menu/idea";
	}

	/**
	 * 采姿美 - 公司願景
	 * 
	 * @return /WEB-INF/views/menu/vision.jsp
	 */
	@RequestMapping(value = "/menu/vision", method = RequestMethod.GET)
	public String visionView() {

		return "menu/vision";
	}

	/**
	 * 采姿美 - 公司使命
	 * 
	 * @return /WEB-INF/views/menu/mission.jsp
	 */
	@RequestMapping(value = "/menu/mission", method = RequestMethod.GET)
	public String missionView() {

		return "menu/mission";
	}

	/**
	 * 采姿美 - 事業版圖
	 * 
	 * @return /WEB-INF/views/menu/territory.jsp
	 */
	@RequestMapping(value = "/menu/territory", method = RequestMethod.GET)
	public String territoryView() {

		return "menu/territory";
	}

}
