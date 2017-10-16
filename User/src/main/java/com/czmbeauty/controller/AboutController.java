/*
 * CaiZiMei/User
 * File: AboutController.java
 * Author: 詹晟
 * Date: 2017/10/16
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.czmbeauty.common.constants.ControllerConstants;

/**
 * about controller
 * 
 * @author 詹晟
 */
@Controller
public class AboutController implements ControllerConstants {

	/**
	 * 采姿美介紹 - 初期處理
	 * 
	 * @return /WEB-INF/views/about/introduction.jsp
	 */
	@RequestMapping(value = "/about/introduction", method = RequestMethod.GET)
	public String introductionView() {

		return ABOUT_INTRODUCTION_PAGE;
	}

	/**
	 * 經營理念 - 初期處理
	 * 
	 * @return /WEB-INF/views/about/idea.jsp
	 */
	@RequestMapping(value = "/about/idea", method = RequestMethod.GET)
	public String ideaView() {

		return ABOUT_IDEA_PAGE;
	}

	/**
	 * 公司願景 - 初期處理
	 * 
	 * @return /WEB-INF/views/about/vision.jsp
	 */
	@RequestMapping(value = "/about/vision", method = RequestMethod.GET)
	public String visionView() {

		return ABOUT_IDEA_VISION;
	}

	/**
	 * 公司使命 - 初期處理
	 * 
	 * @return /WEB-INF/views/about/mission.jsp
	 */
	@RequestMapping(value = "/about/mission", method = RequestMethod.GET)
	public String missionView() {

		return ABOUT_IDEA_MISSION;
	}

	/**
	 * 事業版圖 - 初期處理
	 * 
	 * @return /WEB-INF/views/about/territory.jsp
	 */
	@RequestMapping(value = "/about/territory", method = RequestMethod.GET)
	public String territoryView() {

		return ABOUT_IDEA_TERRITORY;
	}

}
