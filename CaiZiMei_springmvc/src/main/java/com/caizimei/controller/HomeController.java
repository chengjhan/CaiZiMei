/*
 * CaiZiMei
 * File: MemberController.java
 * Author: 詹晟
 * Date: 2017/3/22
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * home controller (視圖解析)
 * 
 * @author 詹晟
 */
@Controller
public class HomeController {

	/**
	 * index 視圖解析
	 * 
	 * @return /WEB-INF/views/index.jsp
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		return new ModelAndView("index");
	}

	/**
	 * back 視圖解析
	 * 
	 * @return /WEB-INF/views/back.jsp
	 */
	@RequestMapping(value = "/back", method = RequestMethod.GET)
	public ModelAndView back() {
		return new ModelAndView("back");
	}

}
