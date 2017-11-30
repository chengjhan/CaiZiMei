/*
 * CaiZiMei/User
 * File: VideoController.java
 * Author: 詹晟
 * Date: 2017/11/30
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czmbeauty.common.constants.ControllerConstants;
import com.czmbeauty.common.util.StringUtil;
import com.czmbeauty.model.entity.VideoBean;
import com.czmbeauty.model.service.VideoService;
import com.google.gson.Gson;

/**
 * video controller
 * 
 * @author 詹晟
 */
@Controller
public class VideoController implements ControllerConstants {

	private static final Logger logger = Logger.getLogger(VideoController.class);

	private String className = this.getClass().getSimpleName();

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
	 * 相關影音 - 初期處理
	 * 
	 * @return /WEB-INF/views/info/video-related.jsp
	 */
	@RequestMapping(value = "/info/video-related", method = RequestMethod.GET)
	public String infoVideoRelatedView(Model model) {

		String servletPath = request.getServletPath();
		String directory = StringUtil.getDirectory(servletPath);

		List<VideoBean> list = videoService.selectOpenVideo(directory);

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

	/**
	 * 開啟的影片 (AJAX)
	 * 
	 * @return video JSON
	 */
	@RequestMapping(value = "/video/*.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String videoAjax() {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		String servletPath = request.getServletPath();
		String directory = StringUtil.getDirectory(servletPath);

		VideoBean bean = videoService.selectOpenVideo(directory).get(0);

		VideoBean jsonBean = new VideoBean();
		jsonBean.setVi_id(bean.getVi_id());
		jsonBean.setVi_name(bean.getVi_name());
		jsonBean.setVi_tag(bean.getVi_tag().split(" ")[3].split("\"")[1]);

		String json = new Gson().toJson(jsonBean);

		logger.info("(" + className + "." + methodName + ") JSON = " + json);

		return json;
	}

}
