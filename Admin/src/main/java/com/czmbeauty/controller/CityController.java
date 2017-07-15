/*
 * CaiZiMei
 * File: CityController.java
 * Author: 詹晟
 * Date: 2017/7/15
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * city controller
 * 
 * @author 詹晟
 */
@Controller
@SessionAttributes("cityList")
public class CityController {

	/**
	 * 城市一覽 - 采姿美管理系統
	 * 
	 * @param model-->Model
	 * @return /WEB-INF/views/city/list.jsp
	 */
	@RequestMapping(value = "/city/list", method = RequestMethod.GET)
	public String listView(Model model) {

		return "city/list";
	}

}
