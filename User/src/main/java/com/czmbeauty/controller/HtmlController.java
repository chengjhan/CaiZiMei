/*
 * CaiZiMei/User
 * File: HtmlController.java
 * Author: 詹晟
 * Date: 2017/10/28
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
import com.czmbeauty.common.exception.PageNotFoundException;
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
	 * html - 初期處理
	 * 
	 * @return /WEB-INF/views/viewName
	 */
	@RequestMapping(value = { "/about/*", "/team/*", "/news/*", "/info/*" }, method = RequestMethod.GET)
	public String htmlView(Model model) {

		String servletPath = request.getServletPath();
		String viewNameFront = servletPath.split("/")[1];
		String viewNameBack = servletPath.split("/")[2].split("\\.")[0];

		String ca_directory = viewNameFront + "-" + viewNameBack;
		String viewName = viewNameFront + "/" + viewNameBack;

		List<HtmlBean> list;
		try {
			list = (List<HtmlBean>) htmlService.selectOpenHtml(ca_directory);

			if (list.size() == 0) {

				throw new PageNotFoundException(viewName);
			}
		} catch (PageNotFoundException e) {

			return ERROR_PAGE_NOT_FOUND_PAGE;
		}

		model.addAttribute(HTML_BEAN, list.get(0));

		return viewName;
	}

}
