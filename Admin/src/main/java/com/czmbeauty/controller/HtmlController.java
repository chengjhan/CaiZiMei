/*
 * CaiZiMei
 * File: HtmlController.java
 * Author: 詹晟
 * Date: 2017/11/2
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czmbeauty.common.constants.ControllerConstants;
import com.czmbeauty.common.editor.PrimitiveNumberEditor;
import com.czmbeauty.common.exception.PageNotFoundException;
import com.czmbeauty.common.util.Pagination;
import com.czmbeauty.model.entity.CategoryBean;
import com.czmbeauty.model.entity.HtmlBean;
import com.czmbeauty.model.service.CategoryService;
import com.czmbeauty.model.service.HtmlService;

/**
 * html controller
 * 
 * @author 詹晟
 */
@Controller
public class HtmlController implements ControllerConstants {

	private static final Logger logger = Logger.getLogger(HtmlController.class);

	/**
	 * 當前頁碼
	 */
	private String currentPage;

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
	 * 注入 HtmlService
	 */
	@Autowired
	private HtmlService htmlService;

	/**
	 * 提供 form backing object 資料轉換
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Integer.class, new PrimitiveNumberEditor(Integer.class, true));
	}

	/**
	 * html 一覽 - 初期處理
	 * 
	 * @param page
	 *            Integer --> 當前頁碼
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/ca_directory/list.jsp
	 */
	@RequestMapping(value = { "/about*/list", "/team*/list", "/news*/list", "/info*/list" }, method = RequestMethod.GET)
	public String listView(@RequestParam Integer page, Model model) {

		String requestView = (String) request.getAttribute(REQUEST_VIEW);
		CategoryBean categoryBean = categoryService.selectByCa_directory(requestView);
		String ca_directory = categoryBean.getCa_directory();

		int pageRowCount = HTML_PAGE_ROW_COUNT;

		// 取得類別資料夾名稱
		model.addAttribute(CATEGORY_DIRECTORY, ca_directory);

		// 取得當前頁碼
		model.addAttribute(CURRENT_PAGE, page);

		// 取得每頁最大筆數
		model.addAttribute(PAGE_ROW_COUNT, pageRowCount);

		// 取得當前頁碼的 html List，放入 table
		model.addAttribute(HTML_LIST, htmlService.selectPagination(categoryBean.getCa_id(), page, pageRowCount));

		// 取得總頁數
		model.addAttribute(PAGE_COUNT,
				Pagination.getPageCount(htmlService.selectCountByHt_Ca(categoryBean), pageRowCount));

		return ca_directory + LIST_PAGE;
	}

	/**
	 * 新增 html - 初期處理
	 * 
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/ca_directory/add.jsp
	 */
	@RequestMapping(value = { "/about*/add", "/team*/add", "/news*/add", "/info*/add" }, method = RequestMethod.GET)
	public String addView(Model model) {

		String requestView = (String) request.getAttribute(REQUEST_VIEW);
		CategoryBean categoryBean = categoryService.selectByCa_directory(requestView);

		// 新增 form backing object
		model.addAttribute(HTML_BEAN, new HtmlBean());

		return categoryBean.getCa_directory() + ADD_PAGE;
	}

	/**
	 * 新增 html - submit
	 * 
	 * @param htmlBean
	 *            HtmlBean --> form backing object
	 * @param bindingResult
	 *            BindingResult
	 * @return /WEB-INF/views/ca_directory/list.jsp
	 */
	@RequestMapping(value = { "/about*/add.do", "/team*/add.do", "/news*/add.do",
			"/info*/add.do" }, method = RequestMethod.POST)
	public String addAction(@Valid HtmlBean htmlBean, BindingResult bindingResult) {

		String requestAction = (String) request.getAttribute(REQUEST_ACTION);
		CategoryBean categoryBean = categoryService.selectByCa_directory(requestAction);
		String ca_name = categoryBean.getCa_name();
		String ca_directory = categoryBean.getCa_directory();

		if (bindingResult.hasErrors()) {

			logger.error(ca_name + "新增失敗: 資料未填");

			return ca_directory + ADD_PAGE;

		} else {

			htmlBean.setHt_CategoryBean(categoryBean);
			htmlBean.setHt_status(0);
			htmlBean.setHt_update_time(new java.util.Date());

			htmlService.insert(htmlBean);

			logger.info(ca_name + "新增成功");

			return REDIRECT + ca_directory + LIST_PAGE + QUESTION + PAGE + EQUAL + "1";
		}
	}

	/**
	 * 編輯 html - 初期處理
	 * 
	 * @param htmlBean_ht_id
	 *            HtmlBean --> form backing object --> GET --> ht_id
	 * @param page
	 *            String --> 當前頁碼
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/error/page-not-found.jsp
	 * @return /WEB-INF/views/ca_directory/edit.jsp
	 */
	@RequestMapping(value = { "/about*/edit", "/team*/edit", "/news*/edit", "/info*/edit" }, method = RequestMethod.GET)
	public String editView(HtmlBean htmlBean_ht_id, @RequestParam String page, Model model) {

		currentPage = page;

		String requestView = (String) request.getAttribute(REQUEST_VIEW);
		CategoryBean categoryBean = categoryService.selectByCa_directory(requestView);

		HtmlBean htmlBean;
		try {
			// 取得選定 html id 的 HtmlBean
			htmlBean = htmlService.selectByHt_id(htmlBean_ht_id.getHt_id());

			if (htmlBean == null) {

				throw new PageNotFoundException(requestView);
			}
		} catch (PageNotFoundException e) {

			return ERROR_PAGE_NOT_FOUND_PAGE;

		} catch (IllegalArgumentException e) {

			logger.error("找不到這個頁面: " + requestView);

			return ERROR_PAGE_NOT_FOUND_PAGE;
		}

		// 使表單回填 htmlBean 內所有資料
		model.addAttribute(HTML_BEAN, htmlBean);

		// 取得當前頁碼
		model.addAttribute(CURRENT_PAGE, page);

		return categoryBean.getCa_directory() + EDIT_PAGE;
	}

	/**
	 * 編輯 html - submit
	 * 
	 * @param htmlBean
	 *            HtmlBean --> form backing object
	 * @param bindingResult
	 *            BindingResult
	 * @return /WEB-INF/views/ca_directory/list.jsp
	 */
	@RequestMapping(value = { "/about*/edit.do", "/team*/edit.do", "/news*/edit.do",
			"/info*/edit.do" }, method = RequestMethod.POST)
	public String editAction(@Valid HtmlBean htmlBean, BindingResult bindingResult) {

		String requestAction = (String) request.getAttribute(REQUEST_ACTION);
		CategoryBean categoryBean = categoryService.selectByCa_directory(requestAction);
		String ca_name = categoryBean.getCa_name();
		String ca_directory = categoryBean.getCa_directory();

		if (bindingResult.hasErrors()) {

			logger.error(ca_name + "編輯失敗: 資料未填");

			return ca_directory + EDIT_PAGE + QUESTION + HTML_ID + EQUAL + htmlBean.getHt_id() + AND + PAGE + EQUAL
					+ currentPage;
		}
		htmlBean.setHt_CategoryBean(categoryBean);
		htmlBean.setHt_status(htmlService.selectByHt_id(htmlBean.getHt_id()).getHt_status());
		htmlBean.setHt_update_time(new java.util.Date());

		htmlService.update(htmlBean);

		logger.info(ca_name + "編輯成功");

		return REDIRECT + ca_directory + LIST_PAGE + QUESTION + PAGE + EQUAL + currentPage;
	}

	/**
	 * html 開關 (AJAX)
	 * 
	 * @param ht_id
	 *            String --> html 流水號
	 * @return ht_name
	 */
	@RequestMapping(value = "/html/switch.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String switchAjax(String ht_id) {

		return htmlService.updateHt_status(Integer.valueOf(ht_id)).getHt_name();
	}

}
