/*
 * CaiZiMei
 * File: VideoController.java
 * Author: 詹晟
 * Date: 2017/9/25
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import static com.czmbeauty.common.constants.CommonConstants.AND;
import static com.czmbeauty.common.constants.CommonConstants.EQUAL;
import static com.czmbeauty.common.constants.CommonConstants.QUESTION;
import static com.czmbeauty.common.constants.PaginationConstants.CURRENT_PAGE;
import static com.czmbeauty.common.constants.PaginationConstants.PAGE_COUNT;
import static com.czmbeauty.common.constants.PaginationConstants.PAGE_ROW_COUNT;
import static com.czmbeauty.common.constants.PaginationConstants.VIDEO_PAGE_ROW_COUNT;
import static com.czmbeauty.common.constants.ParameterConstants.PAGE;
import static com.czmbeauty.common.constants.ParameterConstants.VIDEO_ID;

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

import com.czmbeauty.common.constants.ModelAttributeConstants;
import com.czmbeauty.common.constants.PageNameConstants;
import com.czmbeauty.common.editor.PrimitiveNumberEditor;
import com.czmbeauty.common.exception.PageNotFoundException;
import com.czmbeauty.common.util.Pagination;
import com.czmbeauty.model.entity.CategoryBean;
import com.czmbeauty.model.entity.VideoBean;
import com.czmbeauty.model.service.CategoryService;
import com.czmbeauty.model.service.VideoService;

/**
 * video controller
 * 
 * @author 詹晟
 */
@Controller
public class VideoController implements ModelAttributeConstants, PageNameConstants {

	private static final Logger logger = Logger.getLogger(VideoController.class);

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
	 * 注入 VideoService
	 */
	@Autowired
	private VideoService videoService;

	/**
	 * 提供 form backing object 資料轉換
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Integer.class, new PrimitiveNumberEditor(Integer.class, true));
	}

	/**
	 * 新增影片
	 * 
	 * @param videoBean
	 *            VideoBean --> form backing object
	 * @param bindingResult
	 *            BindingResult
	 * @return /WEB-INF/views/ca_directory/add.jsp
	 * @return /WEB-INF/views/ca_directory/list.jsp
	 */
	private String add(VideoBean videoBean, BindingResult bindingResult) {

		String requestAction = (String) request.getAttribute(REQUEST_ACTION);
		CategoryBean categoryBean = categoryService.selectByCa_directory(requestAction);
		String ca_name = categoryBean.getCa_name();
		String ca_directory = categoryBean.getCa_directory();

		if (bindingResult.hasErrors()) {

			logger.error(ca_name + "新增失敗: 資料未填");

			return ca_directory + ADD_PAGE;

		} else {

			videoBean.setVi_CategoryBean(categoryBean);
			videoBean.setVi_status(0);
			videoBean.setVi_update_time(new java.util.Date());

			videoService.insert(videoBean);

			logger.info(ca_name + "新增成功");

			return REDIRECT + ca_directory + LIST_PAGE + QUESTION + PAGE + EQUAL + "1";
		}
	}

	/**
	 * 編輯影片資訊
	 * 
	 * @param videoBean
	 *            VideoBean --> form backing object
	 * @param bindingResult
	 *            BindingResult
	 * @return /WEB-INF/views/ca_directory/edit.jsp
	 * @return /WEB-INF/views/ca_directory/list.jsp
	 */
	private String edit(VideoBean videoBean, BindingResult bindingResult) {

		String requestAction = (String) request.getAttribute(REQUEST_ACTION);
		CategoryBean categoryBean = categoryService.selectByCa_directory(requestAction);
		String ca_name = categoryBean.getCa_name();
		String ca_directory = categoryBean.getCa_directory();

		if (bindingResult.hasErrors()) {

			logger.error(ca_name + "編輯失敗: 資料未填");

			return ca_directory + EDIT_PAGE + QUESTION + VIDEO_ID + EQUAL + videoBean.getVi_id() + AND + PAGE + EQUAL
					+ currentPage;

		}
		videoBean.setVi_CategoryBean(categoryBean);
		videoBean.setVi_status(videoService.selectByVi_id(videoBean.getVi_id()).getVi_status());
		videoBean.setVi_update_time(new java.util.Date());

		videoService.update(videoBean);

		logger.info(ca_name + "編輯成功");

		return REDIRECT + ca_directory + LIST_PAGE + QUESTION + PAGE + EQUAL + currentPage;
	}

	/**
	 * 影片一覽 - 初期處理
	 * 
	 * @param page
	 *            Integer --> 當前頁碼
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/error/page-not-found.jsp
	 * @return /WEB-INF/views/ca_directory/list.jsp
	 */
	@RequestMapping(value = "/video*/list", method = RequestMethod.GET)
	public String listView(@RequestParam Integer page, Model model) {

		String requestPage = (String) request.getAttribute(REQUEST_PAGE);

		CategoryBean categoryBean;
		try {
			categoryBean = categoryService.selectByCa_directory(requestPage);

		} catch (PageNotFoundException e) {

			return ERROR_PAGE_NOT_FOUND_PAGE;
		}

		int pageRowCount = VIDEO_PAGE_ROW_COUNT;

		// 取得當前頁碼
		model.addAttribute(CURRENT_PAGE, page);

		// 取得每頁最大筆數
		model.addAttribute(PAGE_ROW_COUNT, pageRowCount);

		// 取得當前頁碼的影片 List，放入 table
		model.addAttribute(VIDEO_LIST, videoService.selectPagination(categoryBean.getCa_id(), page, pageRowCount));

		// 取得總頁數
		model.addAttribute(PAGE_COUNT,
				Pagination.getPageCount(videoService.selectCountByVi_Ca(categoryBean), pageRowCount));

		return categoryBean.getCa_directory() + LIST_PAGE;
	}

	/**
	 * 新增影片 - 初期處理
	 * 
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/error/page-not-found.jsp
	 * @return /WEB-INF/views/ca_directory/add.jsp
	 */
	@RequestMapping(value = "/video*/add", method = RequestMethod.GET)
	public String addView(Model model) {

		String requestPage = (String) request.getAttribute(REQUEST_PAGE);

		CategoryBean categoryBean;
		try {
			categoryBean = categoryService.selectByCa_directory(requestPage);

		} catch (PageNotFoundException e) {

			return ERROR_PAGE_NOT_FOUND_PAGE;
		}

		// 新增 form backing object
		model.addAttribute(VIDEO_BEAN, new VideoBean());

		return categoryBean.getCa_directory() + ADD_PAGE;
	}

	/**
	 * 新增影片 - submit
	 * 
	 * @param videoBean
	 *            VideoBean --> form backing object
	 * @param bindingResult
	 *            BindingResult
	 * @return /WEB-INF/views/ca_directory/list.jsp
	 */
	@RequestMapping(value = "/video*/add.do", method = RequestMethod.POST)
	public String addProcess(@Valid VideoBean videoBean, BindingResult bindingResult) {

		return add(videoBean, bindingResult);
	}

	/**
	 * 編輯影片資訊 - 初期處理
	 * 
	 * @param videoBean_vi_id
	 *            VideoBean --> form backing object --> GET --> vi_id
	 * @param page
	 *            String --> 當前頁碼
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/error/page-not-found.jsp
	 * @return /WEB-INF/views/ca_directory/edit.jsp
	 */
	@RequestMapping(value = "/video*/edit", method = RequestMethod.GET)
	public String editView(VideoBean videoBean_vi_id, @RequestParam String page, Model model) {

		currentPage = page;

		String requestPage = (String) request.getAttribute(REQUEST_PAGE);

		CategoryBean categoryBean;
		VideoBean videoBean;
		try {
			categoryBean = categoryService.selectByCa_directory(requestPage);

			// 取得選定影片 id 的 VideoBean
			videoBean = videoService.selectByVi_id(videoBean_vi_id.getVi_id());

			if (videoBean == null) {

				throw new PageNotFoundException(requestPage);
			}
		} catch (PageNotFoundException e) {

			return ERROR_PAGE_NOT_FOUND_PAGE;

		} catch (IllegalArgumentException e) {

			logger.error("找不到這個頁面: " + requestPage);

			return ERROR_PAGE_NOT_FOUND_PAGE;
		}

		// 使表單回填 VideoBean 內所有資料
		model.addAttribute(VIDEO_BEAN, videoBean);

		return categoryBean.getCa_directory() + EDIT_PAGE;
	}

	/**
	 * 編輯影片資訊 - submit
	 * 
	 * @param videoBean
	 *            VideoBean --> form backing object
	 * @param bindingResult
	 *            BindingResult
	 * @return /WEB-INF/views/ca_directory/list.jsp
	 */
	@RequestMapping(value = "/video*/edit.do", method = RequestMethod.POST)
	public String editProcess(@Valid VideoBean videoBean, BindingResult bindingResult) {

		return edit(videoBean, bindingResult);
	}

	/**
	 * 影片開關 (AJAX)
	 * 
	 * @param vi_id
	 *            String --> 影片流水號
	 * @return vi_name
	 */
	@RequestMapping(value = "/video/switch.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String switchAjaxProcess(String vi_id) {

		return videoService.updateVi_status(Integer.valueOf(vi_id)).getVi_name();
	}

}
