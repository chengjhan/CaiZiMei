/*
 * CaiZiMei
 * File: IndexController.java
 * Author: 詹晟
 * Date: 2017/9/14
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import static com.czmbeauty.common.constants.PageNameConstants.INDEX_PAGE;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * index controller
 * 
 * @author 詹晟
 */
@Controller
public class IndexController {

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
