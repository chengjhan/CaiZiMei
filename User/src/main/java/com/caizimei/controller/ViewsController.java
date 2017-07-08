/*
 * CaiZiMei
 * File: ViewsController.java
 * Author: 詹晟
 * Date: 2017/7/9
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * views controller (視圖解析)
 * 
 * @author 詹晟
 */
@Controller
public class ViewsController {

	/**
	 * index 視圖解析
	 * 
	 * @return /WEB-INF/views/index.jsp
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {

		return "index";
	}

	/**
	 * menu/idea 視圖解析
	 * 
	 * @return /WEB-INF/views/menu/idea.jsp
	 */
	@RequestMapping(value = "/menu/idea", method = RequestMethod.GET)
	public String menu_idea() {

		return "menu/idea";
	}

	/**
	 * menu/vision 視圖解析
	 * 
	 * @return /WEB-INF/views/menu/vision.jsp
	 */
	@RequestMapping(value = "/menu/vision", method = RequestMethod.GET)
	public String menu_vision() {

		return "menu/vision";
	}

	/**
	 * menu/mission 視圖解析
	 * 
	 * @return /WEB-INF/views/menu/mission.jsp
	 */
	@RequestMapping(value = "/menu/mission", method = RequestMethod.GET)
	public String menu_mission() {

		return "menu/mission";
	}

	/**
	 * menu/territory 視圖解析
	 * 
	 * @return /WEB-INF/views/menu/territory.jsp
	 */
	@RequestMapping(value = "/menu/territory", method = RequestMethod.GET)
	public String menu_territory() {

		return "menu/territory";
	}

}
