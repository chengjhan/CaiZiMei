/*
 * CaiZiMei
 * File: AdminLogController.java
 * Author: 詹晟
 * Date: 2017/12/8
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czmbeauty.common.constants.ControllerConstants;
import com.czmbeauty.model.entity.AdminLogBean;
import com.czmbeauty.model.service.AdminLogService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * admin_log controller
 * 
 * @author 詹晟
 */
@Controller
public class AdminLogController implements ControllerConstants {

	private static final Logger logger = Logger.getLogger(AdminLogController.class);

	private String className = this.getClass().getSimpleName();

	/**
	 * 注入 AdminLogService
	 */
	@Autowired
	private AdminLogService adminLogService;

	/**
	 * 條件搜尋 (AJAX)
	 * 
	 * @param start
	 *            String --> 開始日期
	 * @param end
	 *            String --> 結束日期
	 * @param ad_id
	 *            String --> 管理員流水號
	 * @param ap_id
	 *            String --> path 流水號
	 * @param page
	 *            Integer --> 當前頁碼
	 * @return admin_log JSON
	 */
	@RequestMapping(value = "/admin-log/list.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public String listAjax(String start, String end, String ad_id, String ap_id, Integer page) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		Date startDate = null;
		Date endDate = null;
		Integer al_ad_id = null;
		Integer al_ap_id = null;
		try {
			startDate = dateFormat.parse(start);
			endDate = dateFormat.parse(end);
			al_ad_id = Integer.parseInt(ad_id);
			al_ap_id = Integer.parseInt(ap_id);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		int pageRowCount = ADMIN_LOG_PAGE_ROW_COUNT_NUMBER;

		List<AdminLogBean> list = (List<AdminLogBean>) adminLogService
				.selectByConditions(startDate, endDate, al_ad_id, al_ap_id, page, pageRowCount).get("list");

		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
		Gson gson = builder.create();

		String json = gson.toJson(list);

		logger.info("(" + className + "." + methodName + ") JSON = " + json);

		return json;
	}

}
