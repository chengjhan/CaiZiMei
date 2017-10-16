/*
 * CaiZiMei/User
 * File: IndexController.java
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
 * index controller
 * 
 * @author 詹晟
 */
@Controller
public class IndexController implements ControllerConstants {

	/**
	 * 首頁 - 初期處理
	 * 
	 * @return /WEB-INF/views/index.jsp
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String indexView() {

		return INDEX_PAGE;
	}

}
