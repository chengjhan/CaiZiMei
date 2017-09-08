/*
 * CaiZiMei
 * File: VideoController.java
 * Author: 詹晟
 * Date: 2017/9/9
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import static com.czmbeauty.common.constants.CommonConstants.AND;
import static com.czmbeauty.common.constants.CommonConstants.EQUAL;
import static com.czmbeauty.common.constants.CommonConstants.QUESTION;
import static com.czmbeauty.common.constants.ModelAttributeConstants.VIDEO_BEAN;
import static com.czmbeauty.common.constants.ModelAttributeConstants.VIDEO_LIST;
import static com.czmbeauty.common.constants.PageNameConstants.ADD_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.EDIT_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.LIST_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.REDIRECT;
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

import com.czmbeauty.common.editor.PrimitiveNumberEditor;
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
public class VideoController {

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
	 * 取得總頁數
	 * 
	 * @param vi_CategoryBean
	 *            CategoryBean
	 * @return int
	 */
	private int getPageCount(CategoryBean vi_CategoryBean) {
		int totalRowCount = videoService.selectCountByVi_Ca(vi_CategoryBean);
		int pageCount = 0;
		if (totalRowCount % VIDEO_PAGE_ROW_COUNT == 0) {
			pageCount = totalRowCount / VIDEO_PAGE_ROW_COUNT;
		} else {
			pageCount = totalRowCount / VIDEO_PAGE_ROW_COUNT + 1;
		}
		return pageCount;
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

		String ca_directory = request.getServletPath().split("/")[1];

		CategoryBean categoryBean = categoryService.selectByCa_directory(ca_directory);
		String ca_name = categoryBean.getCa_name();

		if (bindingResult.hasErrors()) {

			logger.error(ca_name + "新增失敗: 資料未填");

			return REDIRECT + ca_directory + ADD_PAGE;

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

		String ca_directory = request.getServletPath().split("/")[1];

		CategoryBean categoryBean = categoryService.selectByCa_directory(ca_directory);
		String ca_name = categoryBean.getCa_name();

		if (bindingResult.hasErrors()) {

			logger.error(ca_name + "編輯失敗: 資料未填");

			return REDIRECT + ca_directory + EDIT_PAGE + QUESTION + VIDEO_ID + EQUAL + videoBean.getVi_id() + AND + PAGE
					+ EQUAL + currentPage;

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
	 * @return /WEB-INF/views/ca_directory/list.jsp
	 */
	@RequestMapping(value = "/video*/list", method = RequestMethod.GET)
	public String listView(@RequestParam Integer page, Model model) {

		String ca_directory = request.getServletPath().split("/")[1];
		CategoryBean categoryBean = categoryService.selectByCa_directory(ca_directory);

		String hql = "from VideoBean where vi_ca_id=" + categoryBean.getCa_id() + " order by vi_rank asc, vi_id asc";

		// 取得當前頁碼
		model.addAttribute(CURRENT_PAGE, page);

		// 取得每頁最大筆數
		model.addAttribute(PAGE_ROW_COUNT, VIDEO_PAGE_ROW_COUNT);

		// 取得當頁起始筆數
		int first = (page - 1) * VIDEO_PAGE_ROW_COUNT;

		// 取得當前頁碼的影片 List，放入 table
		model.addAttribute(VIDEO_LIST, videoService.selectPagination(hql, first, VIDEO_PAGE_ROW_COUNT));

		// 取得總頁數
		model.addAttribute(PAGE_COUNT, getPageCount(categoryBean));

		return ca_directory + LIST_PAGE;
	}

	/**
	 * 新增影片 - 初期處理
	 * 
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/ca_directory/add.jsp
	 */
	@RequestMapping(value = "/video*/add", method = RequestMethod.GET)
	public String addView(Model model) {

		// 新增 form backing object
		model.addAttribute(VIDEO_BEAN, new VideoBean());

		return request.getServletPath().split("/")[1] + ADD_PAGE;
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
	 * @return /WEB-INF/views/ca_directory/edit.jsp
	 */
	@RequestMapping(value = "/video*/edit", method = RequestMethod.GET)
	public String editView(VideoBean videoBean_vi_id, @RequestParam String page, Model model) {

		currentPage = page;

		// 取得選定影片 id 的 VideoBean，使表單回填 VideoBean 內所有資料
		model.addAttribute(VIDEO_BEAN, videoService.selectByVi_id(videoBean_vi_id.getVi_id()));

		return request.getServletPath().split("/")[1] + EDIT_PAGE;
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
