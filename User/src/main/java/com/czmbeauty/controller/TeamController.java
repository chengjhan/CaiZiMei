/*
 * CaiZiMei/User
 * File: TeamController.java
 * Author: 詹晟
 * Date: 2017/10/18
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.czmbeauty.common.constants.ControllerConstants;

/**
 * team controller
 * 
 * @author 詹晟
 */
@Controller
public class TeamController implements ControllerConstants {

	/**
	 * 醫療團隊 - 初期處理
	 * 
	 * @return /WEB-INF/views/team/medical.jsp
	 */
	@RequestMapping(value = "/team/medical", method = RequestMethod.GET)
	public String medicalView() {

		return TEAM_MEDICAL_PAGE;
	}

}
