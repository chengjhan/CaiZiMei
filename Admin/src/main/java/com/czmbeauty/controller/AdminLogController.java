/*
 * CaiZiMei
 * File: AdminLogController.java
 * Author: 詹晟
 * Date: 2017/12/17
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.czmbeauty.common.constants.ControllerConstants;
import com.czmbeauty.common.editor.AdminBeanPropertyEditor;
import com.czmbeauty.common.editor.AdminPathBeanPropertyEditor;
import com.czmbeauty.common.util.PaginationUtil;
import com.czmbeauty.model.entity.AdminBean;
import com.czmbeauty.model.entity.AdminLogBean;
import com.czmbeauty.model.entity.AdminPathBean;
import com.czmbeauty.model.service.AdminLogService;
import com.czmbeauty.model.service.AdminPathService;
import com.czmbeauty.model.service.AdminService;
import com.czmbeauty.model.service.CategoryService;

/**
 * admin_log controller
 * 
 * @author 詹晟
 */
@Controller
public class AdminLogController implements ControllerConstants {

	/**
	 * 注入 HttpServletRequest
	 */
	@Autowired
	private HttpServletRequest request;

	/**
	 * 注入 AdminPathService
	 */
	@Autowired
	private AdminPathService adminPathService;

	/**
	 * 注入 CategoryService
	 */
	@Autowired
	private CategoryService categoryService;

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
	 * 提供 form backing object 資料轉換
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(AdminBean.class, new AdminBeanPropertyEditor());
		webDataBinder.registerCustomEditor(AdminPathBean.class, new AdminPathBeanPropertyEditor());
	}

	/**
	 * 條件搜尋 - 初期處理
	 * 
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/admin-log/list.jsp
	 */
	@RequestMapping(value = "/admin-log/list", method = RequestMethod.GET)
	public String listView(Model model) {

		// 取得所有管理員 List，放入 select
		model.addAttribute(ADMIN_LIST, adminService.selectAll());

		// 取得管理系統所有動作 path List，放入 select
		model.addAttribute(ADMIN_PATH_LIST, adminPathService.selectByAp_cp_id(2));

		// 新增 form backing object
		model.addAttribute(ADMIN_LOG_BEAN, new AdminLogBean());

		return ADMIN_LOG_LIST_PAGE;
	}

	/**
	 * 條件搜尋 - submit
	 * 
	 * @param begin
	 *            String --> 開始日期
	 * @param end
	 *            String --> 結束日期
	 * @param adminLogBean
	 *            AdminLogBean --> form backing object
	 * @param page
	 *            Integer --> 當前頁碼
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/admin-log/list.jsp
	 */
	@RequestMapping(value = "/admin-log/list.do", method = RequestMethod.GET)
	public String listAction(@RequestParam String begin, @RequestParam String end, AdminLogBean adminLogBean,
			@RequestParam Integer page, Model model) {

		// 取得表單資料
		AdminBean al_AdminBean = adminLogBean.getAl_AdminBean();
		AdminPathBean al_AdminPathBean = adminLogBean.getAl_AdminPathBean();
		Date beginDate = null;
		Date endDate = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			// 使表單回填 AdminLogBean 內所有資料
			if (al_AdminBean != null) {
				adminLogBean.setAl_AdminBean(adminService.selectByAd_id(al_AdminBean.getAd_id()));
			}
			if (al_AdminPathBean != null) {
				adminLogBean.setAl_AdminPathBean(adminPathService.selectByAp_id(al_AdminPathBean.getAp_id()));
			}
			if (!BLANK.equals(begin)) {
				beginDate = dateFormat.parse(begin);
			}
			if (!BLANK.equals(end)) {
				endDate = dateFormat.parse(end);
			}
		} catch (ParseException e) {

			// 取得參數 begin，並回填表單
			model.addAttribute(ADMIN_LOG_BEGIN, begin);

			// 取得參數 end，並回填表單
			model.addAttribute(ADMIN_LOG_END, end);

			// 取得所有管理員 List，放入 select
			model.addAttribute(ADMIN_LIST, adminService.selectAll());

			// 取得管理系統所有動作 path List，放入 select
			model.addAttribute(ADMIN_PATH_LIST, adminPathService.selectByAp_cp_id(2));

			return ADMIN_LOG_LIST_PAGE;
		}

		String requestPath = (String) request.getAttribute(REQUEST_PATH);

		int pageRowCount = ADMIN_LOG_PAGE_ROW_COUNT_NUMBER;

		Map<String, Object> map = adminLogService.selectByConditions(beginDate, endDate, al_AdminBean, al_AdminPathBean,
				page, pageRowCount);

		int pageCount = PaginationUtil.getPageCount((int) map.get("count"), pageRowCount);
		int groupRowCount = GROUP_ROW_COUNT_NUMBER;

		// 取得參數 al_AdminBean，並生成 URL
		model.addAttribute(ADMIN_LOG_ADMIN_BEAN, (al_AdminBean != null) ? al_AdminBean.getAd_id() : 0);

		// 取得參數 al_AdminPathBean，並生成 URL
		model.addAttribute(ADMIN_LOG_ADMIN_PATH_BEAN, (al_AdminPathBean != null) ? al_AdminPathBean.getAp_id() : 0);

		// 取得 beginDate，並回填表單，及生成 URL
		model.addAttribute(ADMIN_LOG_BEGIN_DATE, beginDate);

		// 取得 endDate，並回填表單，及生成 URL
		model.addAttribute(ADMIN_LOG_END_DATE, endDate);

		// 取得所有管理員 List，放入 select
		model.addAttribute(ADMIN_LIST, adminService.selectAll());

		// 取得管理系統所有動作 path List，放入 select
		model.addAttribute(ADMIN_PATH_LIST, adminPathService.selectByAp_cp_id(2));

		// 取得當前頁碼的管理員日誌 List，放入 table
		model.addAttribute(ADMIN_LOG_LIST, map.get("list"));

		// 取得類別資料夾名稱
		model.addAttribute(CATEGORY_DIRECTORY, categoryService.selectByCa_directory(requestPath).getCa_directory());

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

}
