/*
 * CaiZiMei
 * File: VideoController.java
 * Author: 詹晟
 * Date: 2017/11/23
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
import com.czmbeauty.common.util.PaginationUtil;
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
public class VideoController implements ControllerConstants {

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
	 * 影片一覽 - 初期處理
	 * 
	 * @param page
	 *            Integer --> 當前頁碼
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/ca_directory/list.jsp
	 */
	@RequestMapping(value = { "/video*/list", "/info-video-related/list" }, method = RequestMethod.GET)
	public String listView(@RequestParam Integer page, Model model) {

		String requestView = (String) request.getAttribute(REQUEST_VIEW);
		CategoryBean categoryBean = categoryService.selectByCa_directory(requestView);
		String ca_directory = categoryBean.getCa_directory();

		int pageRowCount = VIDEO_PAGE_ROW_COUNT_NUMBER;
		int groupRowCount = GROUP_ROW_COUNT_NUMBER;

		int pageCount = PaginationUtil.getPageCount(videoService.selectCountByVi_Ca(categoryBean), pageRowCount);

		// 取得類別資料夾名稱
		model.addAttribute(CATEGORY_DIRECTORY, ca_directory);

		// 取得當前頁碼的影片 List，放入 table
		model.addAttribute(VIDEO_LIST, videoService.selectPagination(categoryBean.getCa_id(), page, pageRowCount));

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

		return ca_directory + LIST_PAGE;
	}

	/**
	 * 新增影片 - 初期處理
	 * 
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/ca_directory/add.jsp
	 */
	@RequestMapping(value = { "/video*/add", "/info-video-related/add" }, method = RequestMethod.GET)
	public String addView(Model model) {

		String requestView = (String) request.getAttribute(REQUEST_VIEW);
		CategoryBean categoryBean = categoryService.selectByCa_directory(requestView);

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
	@RequestMapping(value = { "/video*/add.do", "/info-video-related/add.do" }, method = RequestMethod.POST)
	public String addAction(@Valid VideoBean videoBean, BindingResult bindingResult) {

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

			return REDIRECT + ca_directory + LIST_PAGE + QUESTION + PAGE + EQUAL + PAGE_ONE;
		}
	}

	/**
	 * 編輯影片 - 初期處理
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
	@RequestMapping(value = { "/video*/edit", "/info-video-related/edit" }, method = RequestMethod.GET)
	public String editView(VideoBean videoBean_vi_id, @RequestParam String page, Model model) {

		currentPage = page;

		String requestView = (String) request.getAttribute(REQUEST_VIEW);
		CategoryBean categoryBean = categoryService.selectByCa_directory(requestView);

		VideoBean videoBean;
		try {
			// 取得選定影片 id 的 VideoBean
			videoBean = videoService.selectByVi_id(videoBean_vi_id.getVi_id());

			if (videoBean == null) {

				throw new PageNotFoundException(requestView);
			}
		} catch (PageNotFoundException e) {

			return ERROR_PAGE_NOT_FOUND_PAGE;

		} catch (IllegalArgumentException e) {

			logger.error("找不到這個頁面: " + requestView);

			return ERROR_PAGE_NOT_FOUND_PAGE;
		}

		// 使表單回填 VideoBean 內所有資料
		model.addAttribute(VIDEO_BEAN, videoBean);

		// 取得當前頁碼
		model.addAttribute(CURRENT_PAGE, page);

		return categoryBean.getCa_directory() + EDIT_PAGE;
	}

	/**
	 * 編輯影片 - submit
	 * 
	 * @param videoBean
	 *            VideoBean --> form backing object
	 * @param bindingResult
	 *            BindingResult
	 * @return /WEB-INF/views/ca_directory/list.jsp
	 */
	@RequestMapping(value = { "/video*/edit.do", "/info-video-related/edit.do" }, method = RequestMethod.POST)
	public String editAction(@Valid VideoBean videoBean, BindingResult bindingResult) {

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
	 * 影片開關 (AJAX)
	 * 
	 * @param vi_id
	 *            String --> 影片流水號
	 * @return vi_name
	 */
	@RequestMapping(value = "/video/switch.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String switchAjax(String vi_id) {

		return videoService.updateVi_status(Integer.valueOf(vi_id)).getVi_name();
	}

}
