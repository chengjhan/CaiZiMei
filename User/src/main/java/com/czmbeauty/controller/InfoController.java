/*
 * CaiZiMei/User
 * File: InfoController.java
 * Author: 詹晟
 * Date: 2017/10/23
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.czmbeauty.common.constants.ControllerConstants;
import com.czmbeauty.model.entity.VideoBean;
import com.czmbeauty.model.service.VideoService;

/**
 * info controller
 * 
 * @author 詹晟
 */
@Controller
public class InfoController implements ControllerConstants {

	/**
	 * 注入 HttpServletRequest
	 */
	@Autowired
	private HttpServletRequest request;

	/**
	 * 注入 VideoService
	 */
	@Autowired
	private VideoService videoService;

	/**
	 * 醫療新知 - 初期處理
	 * 
	 * @return /WEB-INF/views/info/knowleage.jsp
	 */
	@RequestMapping(value = "/info/knowleage", method = RequestMethod.GET)
	public String knowleageView() {

		return INFO_KNOWLEAGE_PAGE;
	}

	/**
	 * 相關影音 - 初期處理
	 * 
	 * @return /WEB-INF/views/info/video-related.jsp
	 */
	@RequestMapping(value = "/info/video-related", method = RequestMethod.GET)
	public String videoRelatedView(Model model) {

		String servletPath = request.getServletPath();
		String ca_directory = servletPath.split("/")[1] + "-" + servletPath.split("/")[2].split("\\.")[0];

		List<VideoBean> list = videoService.selectOpenVideo(ca_directory);

		List<VideoBean> videoList = new ArrayList<VideoBean>();
		if (list.size() > VIDEO_NUMBER) {
			for (int i = 0; i < VIDEO_NUMBER; i++) {
				VideoBean videoBean = new VideoBean();
				videoBean.setVi_id(list.get(i).getVi_id());
				videoBean.setVi_name(list.get(i).getVi_name());
				videoBean.setVi_tag(list.get(i).getVi_tag());
				videoList.add(videoBean);
			}
		} else {
			videoList = list;
		}

		model.addAttribute(VIDEO_LIST, videoList);

		return INFO_VIDEO_RELATED_PAGE;
	}

}
