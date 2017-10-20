/*
 * CaiZiMei/User
 * File: VideoController.java
 * Author: 詹晟
 * Date: 2017/10/20
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czmbeauty.common.constants.ControllerConstants;
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
	 * 開啟的影片 JSON
	 * 
	 * @return video JSON
	 */
	private String getJSON() {

		String ca_directory = request.getServletPath().split("/")[2].split("\\.")[0];

		VideoBean bean = videoService.selectOpenVideo(ca_directory).get(0);

		VideoBean jsonBean = new VideoBean();
		jsonBean.setVi_id(bean.getVi_id());
		jsonBean.setVi_name(bean.getVi_name());
		jsonBean.setVi_tag(bean.getVi_tag().split(" ")[3].split("\"")[1]);

		String json = new Gson().toJson(jsonBean);

		logger.info("JSON = " + json);

		return json;
	}

	/**
	 * 開啟的影片 JSON
	 * 
	 * @return video JSON
	 */
	private String getJSON(int number) {

		String ca_directory = request.getServletPath().split("/")[2].split("\\.")[0];

		List<VideoBean> list = videoService.selectOpenVideo(ca_directory);

		List<VideoBean> jsonList = new ArrayList<VideoBean>();
		if (list.size() > number) {
			for (int i = 0; i < number; i++) {
				VideoBean jsonBean = new VideoBean();
				jsonBean.setVi_id(list.get(i).getVi_id());
				jsonBean.setVi_name(list.get(i).getVi_name());
				jsonBean.setVi_tag(list.get(i).getVi_tag().split(" ")[3].split("\"")[1]);
				jsonList.add(jsonBean);
			}
		} else {
			jsonList = list;
		}

		String json = new Gson().toJson(jsonList);

		logger.info("JSON = " + json);

		return json;
	}

	/**
	 * 開啟的影片 (AJAX)
	 * 
	 * @return video JSON
	 */
	@RequestMapping(value = "/video/video*.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String openVideoAjax() {

		String ca_directory = request.getServletPath().split("/")[2].split("\\.")[0];

		if (VIDEO_RELATED.equals(ca_directory)) {

			return getJSON(6);
		}
		return getJSON();
	}

}
