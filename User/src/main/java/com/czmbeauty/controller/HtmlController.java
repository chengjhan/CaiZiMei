/*
 * CaiZiMei/User
 * File: HtmlController.java
 * Author: 詹晟
 * Date: 2017/10/24
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.czmbeauty.common.constants.ControllerConstants;
import com.czmbeauty.model.entity.HtmlBean;
import com.czmbeauty.model.service.HtmlService;

/**
 * html controller
 * 
 * @author 詹晟
 */
@Controller
public class HtmlController implements ControllerConstants {

	/**
	 * 注入 HttpServletRequest
	 */
	@Autowired
	private HttpServletRequest request;

	/**
	 * 注入 HtmlService
	 */
	@Autowired
	private HtmlService htmlService;

	/**
	 * 取得 html
	 * 
	 * @return HtmlBean
	 */
	private HtmlBean getHtml() {

		String servletPath = request.getServletPath();
		String ca_directory = servletPath.split("/")[1] + "-" + servletPath.split("/")[2].split("\\.")[0];

		return ((List<HtmlBean>) htmlService.selectOpenHtml(ca_directory)).get(0);
	}

	/**
	 * 醫療新知 - 初期處理
	 * 
	 * @return /WEB-INF/views/info/knowleage.jsp
	 */
	@RequestMapping(value = "/info/knowleage", method = RequestMethod.GET)
	public String knowleageView(Model model) {

		model.addAttribute(HTML_BEAN, getHtml());

		return INFO_KNOWLEAGE_PAGE;
	}

}
