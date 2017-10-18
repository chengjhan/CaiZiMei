/*
 * CaiZiMei/User
 * File: InfoController.java
 * Author: 詹晟
 * Date: 2017/10/19
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.czmbeauty.common.constants.ControllerConstants;

/**
 * info controller
 * 
 * @author 詹晟
 */
@Controller
public class InfoController implements ControllerConstants {

	/**
	 * 醫療新知 - 初期處理
	 * 
	 * @return /WEB-INF/views/info/knowleage.jsp
	 */
	@RequestMapping(value = "/info/knowleage", method = RequestMethod.GET)
	public String knowleageView() {

		return INFO_KNOWLEAGE_PAGE;
	}

	/**
	 * 相關影音 - 初期處理
	 * 
	 * @return /WEB-INF/views/info/video.jsp
	 */
	@RequestMapping(value = "/info/video", method = RequestMethod.GET)
	public String videoView() {

		return INFO_VIDEO_PAGE;
	}

}
