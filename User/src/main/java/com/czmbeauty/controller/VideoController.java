/*
 * CaiZiMei/User
 * File: VideoController.java
 * Author: 詹晟
 * Date: 2017/9/6
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import static com.czmbeauty.common.constants.HqlConstants.HQL_SELECT_OPEN_VIDEO_RELATED;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czmbeauty.model.entity.VideoBean;
import com.czmbeauty.model.service.VideoService;
import com.google.gson.Gson;

/**
 * video controller
 * 
 * @author 詹晟
 */
@Controller
public class VideoController {

	private static final Logger logger = Logger.getLogger(VideoController.class);

	/**
	 * 注入 VideoService
	 */
	@Autowired
	private VideoService videoService;

	/**
	 * 開啟的相關影音 (AJAX)
	 * 
	 * @return video JSON
	 */
	@RequestMapping(value = "/video/open-video-related-list.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String openVideoRelatedListAjaxProcess() {

		VideoBean bean = videoService.selectOpenVideo(HQL_SELECT_OPEN_VIDEO_RELATED).get(0);

		VideoBean jsonBean = new VideoBean();
		jsonBean.setVi_id(bean.getVi_id());
		jsonBean.setVi_name(bean.getVi_name());
		jsonBean.setVi_tag(bean.getVi_tag().split(" ")[3].split("\"")[1]);

		String json = new Gson().toJson(jsonBean);

		logger.info("JSON = " + json);

		return json;
	}

}