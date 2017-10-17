/*
 * CaiZiMei/User
 * File: NewsController.java
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
 * news controller
 * 
 * @author 詹晟
 */
@Controller
public class NewsController implements ControllerConstants {

	/**
	 * 近期活動 - 初期處理
	 * 
	 * @return /WEB-INF/views/news/recent.jsp
	 */
	@RequestMapping(value = "/news/recent", method = RequestMethod.GET)
	public String recentView() {

		return NEWS_RECENT_PAGE;
	}

	/**
	 * 優惠活動 - 初期處理
	 * 
	 * @return /WEB-INF/views/news/sale.jsp
	 */
	@RequestMapping(value = "/news/sale", method = RequestMethod.GET)
	public String saleView() {

		return NEWS_SALE_PAGE;
	}

}
