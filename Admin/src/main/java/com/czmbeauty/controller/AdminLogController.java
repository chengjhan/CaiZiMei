/*
 * CaiZiMei
 * File: AdminLogController.java
 * Author: 詹晟
 * Date: 2017/12/11
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czmbeauty.common.constants.ControllerConstants;
import com.czmbeauty.common.util.PaginationUtil;
import com.czmbeauty.model.entity.AdminLogBean;
import com.czmbeauty.model.service.AdminLogService;
import com.czmbeauty.model.service.AdminPathService;
import com.czmbeauty.model.service.AdminService;
import com.czmbeauty.model.service.CategoryService;
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
	 * 注入 HttpServletRequest
	 */
	@Autowired
	private HttpServletRequest request;

	/**
	 * 注入 CategoryService
	 */
	@Autowired
	private CategoryService categoryService;
	
	/**
	 * 注入 AdminPathService
	 */
	@Autowired
	private AdminPathService adminPathService;
	
	/**
	 * 注入 AdminService
	 */
	@Autowired
	private AdminService adminService;

	/**
	 * 注入 AdminLogService
	 */
	@Autowired
	private AdminLogService adminLogService;

	/**
	 * 條件搜尋 - 初期處理
	 * 
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/admin-log/list.jsp
	 */
	@RequestMapping(value = "/admin-log/list", method = RequestMethod.GET)
	public String listView(AdminLogBean adminLogBean, Model model) {
		
		// 取得所有管理員 List，放入 select
		model.addAttribute("adminList", adminService.selectAll());

		// 取得所有管理系統 path List，放入 select
		model.addAttribute("adminPathList", adminPathService.selectByAp_cp_id(2));

		return ADMIN_LOG_LIST_PAGE;
	}

	/**
	 * 條件搜尋 - submit
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
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/admin-log/list.jsp
	 */
	@RequestMapping(value = "/admin-log/list.do", method = RequestMethod.GET)
	public String listAction(String start, String end, String ad_id, String ap_id, @RequestParam Integer page,
			Model model) {

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

		String requestPath = (String) request.getAttribute(REQUEST_PATH);

		int pageRowCount = ADMIN_LOG_PAGE_ROW_COUNT_NUMBER;

		Map<String, Object> map = adminLogService.selectByConditions(startDate, endDate, al_ad_id, al_ap_id, page,
				pageRowCount);

		int pageCount = PaginationUtil.getPageCount((int) map.get("count"), pageRowCount);
		int groupRowCount = GROUP_ROW_COUNT_NUMBER;

		// 取得類別資料夾名稱
		model.addAttribute(CATEGORY_DIRECTORY, categoryService.selectByCa_directory(requestPath).getCa_directory());

		// 取得當前頁碼的管理員日誌 List，放入 table
		model.addAttribute(ADMIN_LOG_LIST, map.get("list"));

		// 取得每頁最大筆數
		model.addAttribute(PAGE_ROW_COUNT, pageRowCount);

		// 取得總頁數
		model.addAttribute(PAGE_COUNT, pageCount);

		// 取得當前頁碼
		model.addAttribute(CURRENT_PAGE, page);

		// 取得每群最大頁數
		model.addAttribute(GROUP_ROW_COUNT, groupRowCount);

		// 取得總群數
		model.addAttribute(GROUP_COUNT, PaginationUtil.getGroupCount(pageCount, groupRowCount));

		// 取得當前群序
		model.addAttribute(CURRENT_GROUP, PaginationUtil.getCurrentGroup(page, groupRowCount));

		// 取得當前群序起始頁碼
		model.addAttribute(CURRENT_GROUP_BEGIN, PaginationUtil.getCurrentGroupBegin(page, groupRowCount));

		// 取得當前群序結束頁碼
		model.addAttribute(CURRENT_GROUP_END, PaginationUtil.getCurrentGroupEnd(pageCount, page, groupRowCount));

		return ADMIN_LOG_LIST_PAGE;
	}

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
