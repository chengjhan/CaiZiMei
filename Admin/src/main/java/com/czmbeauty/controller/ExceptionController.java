/*
 * CaiZiMei
 * File: ExceptionController.java
 * Author: 詹晟
 * Date: 2017/9/22
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
 * exception controller
 * 
 * @author 詹晟
 */
@Controller
public class ExceptionController implements ModelAttributeConstants, PageNameConstants {

	/**
	 * 找不到網頁 - 初期處理
	 * 
	 * @return /WEB-INF/views/error/page-not-found.jsp
	 */
	@RequestMapping(value = "/error/page-not-found", method = RequestMethod.GET)
	public String pageNotFoundView() {

		return ERROR_PAGE_NOT_FOUND_PAGE;
	}

}
