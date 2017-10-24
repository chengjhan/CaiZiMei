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
	 * 采姿美介紹 - 初期處理
	 * 
	 * @return /WEB-INF/views/about/introduction.jsp
	 */
	@RequestMapping(value = "/about/introduction", method = RequestMethod.GET)
	public String aboutIntroductionView() {

		return ABOUT_INTRODUCTION_PAGE;
	}

	/**
	 * 經營理念 - 初期處理
	 * 
	 * @return /WEB-INF/views/about/idea.jsp
	 */
	@RequestMapping(value = "/about/idea", method = RequestMethod.GET)
	public String aboutIdeaView() {

		return ABOUT_IDEA_PAGE;
	}

	/**
	 * 公司願景 - 初期處理
	 * 
	 * @return /WEB-INF/views/about/vision.jsp
	 */
	@RequestMapping(value = "/about/vision", method = RequestMethod.GET)
	public String aboutVisionView() {

		return ABOUT_VISION_PAGE;
	}

	/**
	 * 公司使命 - 初期處理
	 * 
	 * @return /WEB-INF/views/about/mission.jsp
	 */
	@RequestMapping(value = "/about/mission", method = RequestMethod.GET)
	public String aboutMissionView() {

		return ABOUT_MISSION_PAGE;
	}

	/**
	 * 事業版圖 - 初期處理
	 * 
	 * @return /WEB-INF/views/about/territory.jsp
	 */
	@RequestMapping(value = "/about/territory", method = RequestMethod.GET)
	public String aboutTerritoryView() {

		return ABOUT_TERRITORY_PAGE;
	}

	/**
	 * 加盟店 - 初期處理
	 * 
	 * @return /WEB-INF/views/about/franchisee.jsp
	 */
	@RequestMapping(value = "/about/franchisee", method = RequestMethod.GET)
	public String aboutFranchiseeView() {

		return ABOUT_FRANCHISEE_PAGE;
	}

	/**
	 * 醫療團隊 - 初期處理
	 * 
	 * @return /WEB-INF/views/team/doctor.jsp
	 */
	@RequestMapping(value = "/team/doctor", method = RequestMethod.GET)
	public String teamDoctorView() {

		return TEAM_DOCTOR_PAGE;
	}

	/**
	 * 近期活動 - 初期處理
	 * 
	 * @return /WEB-INF/views/news/recent.jsp
	 */
	@RequestMapping(value = "/news/recent", method = RequestMethod.GET)
	public String newsRecentView() {

		return NEWS_RECENT_PAGE;
	}

	/**
	 * 優惠活動 - 初期處理
	 * 
	 * @return /WEB-INF/views/news/sale.jsp
	 */
	@RequestMapping(value = "/news/sale", method = RequestMethod.GET)
	public String newsSaleView() {

		return NEWS_SALE_PAGE;
	}

	/**
	 * 醫療新知 - 初期處理
	 * 
	 * @return /WEB-INF/views/info/knowleage.jsp
	 */
	@RequestMapping(value = "/info/knowleage", method = RequestMethod.GET)
	public String infoKnowleageView(Model model) {

		model.addAttribute(HTML_BEAN, getHtml());

		return INFO_KNOWLEAGE_PAGE;
	}

}
