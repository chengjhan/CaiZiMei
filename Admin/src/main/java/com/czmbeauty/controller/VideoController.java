/*
 * CaiZiMei
 * File: VideoController.java
 * Author: 詹晟
 * Date: 2017/9/3
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import static com.czmbeauty.common.constants.CodeConstants.VIDEO_MAIN;
import static com.czmbeauty.common.constants.CodeConstants.VIDEO_MAIN_CODE;
import static com.czmbeauty.common.constants.CommonConstants.AND;
import static com.czmbeauty.common.constants.CommonConstants.EQUAL;
import static com.czmbeauty.common.constants.CommonConstants.QUESTION;
import static com.czmbeauty.common.constants.HqlConstants.HQL_SELECT_ALL_VIDEO_MAIN;
import static com.czmbeauty.common.constants.ModelAttributeConstants.VIDEO_BEAN;
import static com.czmbeauty.common.constants.ModelAttributeConstants.VIDEO_LIST;
import static com.czmbeauty.common.constants.PageNameConstants.REDIRECT;
import static com.czmbeauty.common.constants.PageNameConstants.VIDEO_MAIN_ADD_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.VIDEO_MAIN_EDIT_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.VIDEO_MAIN_LIST_PAGE;
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
	public int getPageCount(CategoryBean vi_CategoryBean) {
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
	 * 影片一覽 - 初期處理
	 * 
	 * @param page
	 *            Integer --> 當前頁碼
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/video-main/list.jsp
	 */
	@RequestMapping(value = "/video*/list", method = RequestMethod.GET)
	public String listView(@RequestParam Integer page, Model model) {

		// 取得當前頁碼
		model.addAttribute(CURRENT_PAGE, page);

		// 取得當頁起始筆數
		int first = (page - 1) * VIDEO_PAGE_ROW_COUNT;

		// 取得每頁最大筆數
		model.addAttribute(PAGE_ROW_COUNT, VIDEO_PAGE_ROW_COUNT);

		CategoryBean vi_CategoryBean = new CategoryBean();

		if (VIDEO_MAIN.equals(request.getServletPath().split("/")[1])) {

			// 取得當前頁碼的影片 List，放入 table
			model.addAttribute(VIDEO_LIST,
					videoService.selectPagination(HQL_SELECT_ALL_VIDEO_MAIN, first, VIDEO_PAGE_ROW_COUNT));

			vi_CategoryBean.setCa_id(VIDEO_MAIN_CODE);

			// 取得總頁數
			model.addAttribute(PAGE_COUNT, getPageCount(vi_CategoryBean));

			return VIDEO_MAIN_LIST_PAGE;
		}
		return null;
	}

	/**
	 * 新增影片 - 初期處理
	 * 
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/video-main/add.jsp
	 */
	@RequestMapping(value = "/video*/add", method = RequestMethod.GET)
	public String addView(Model model) {

		// 新增 form backing object
		model.addAttribute(VIDEO_BEAN, new VideoBean());

		if (VIDEO_MAIN.equals(request.getServletPath().split("/")[1])) {

			return VIDEO_MAIN_ADD_PAGE;
		}
		return null;
	}

	/**
	 * 新增影片 - submit
	 * 
	 * @param videoBean
	 *            VideoBean --> form backing object
	 * @param bindingResult
	 *            BindingResult
	 * @return /WEB-INF/views/video-main/add.jsp
	 * @return /WEB-INF/views/video-main/list.jsp
	 */
	@RequestMapping(value = "/video*/add.do", method = RequestMethod.POST)
	public String addProcess(@Valid VideoBean videoBean, BindingResult bindingResult) {

		if (VIDEO_MAIN.equals(request.getServletPath().split("/")[1])) {

			if (bindingResult.hasErrors()) {

				logger.error("主影片新增失敗: 資料未填");

				return REDIRECT + VIDEO_MAIN_ADD_PAGE;

			} else {

				videoBean.setVi_CategoryBean(categoryService.selectByCa_id(VIDEO_MAIN_CODE));
				videoBean.setVi_status(0);
				videoBean.setVi_update_time(new java.util.Date());

				videoService.insert(videoBean);

				logger.info("主影片新增成功");

				return REDIRECT + VIDEO_MAIN_LIST_PAGE + QUESTION + PAGE + EQUAL + "1";
			}
		}
		return null;
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
	 * @return /WEB-INF/views/video-main/edit.jsp
	 */
	@RequestMapping(value = "/video*/edit", method = RequestMethod.GET)
	public String editView(VideoBean videoBean_vi_id, @RequestParam String page, Model model) {

		currentPage = page;

		// 取得選定影片 id 的 VideoBean，使表單回填 VideoBean 內所有資料
		model.addAttribute(VIDEO_BEAN, videoService.selectByVi_id(videoBean_vi_id.getVi_id()));

		if (VIDEO_MAIN.equals(request.getServletPath().split("/")[1])) {

			return VIDEO_MAIN_EDIT_PAGE;
		}
		return null;
	}

	/**
	 * 編輯影片資訊 - submit
	 * 
	 * @param videoBean
	 *            VideoBean --> form backing object
	 * @param bindingResult
	 *            BindingResult
	 * @return /WEB-INF/views/video-main/edit.jsp
	 * @return /WEB-INF/views/video-main/list.jsp
	 */
	@RequestMapping(value = "/video*/edit.do", method = RequestMethod.POST)
	public String editProcess(@Valid VideoBean videoBean, BindingResult bindingResult) {

		if (VIDEO_MAIN.equals(request.getServletPath().split("/")[1])) {

			if (bindingResult.hasErrors()) {

				logger.error("主影片編輯失敗: 資料未填");

				return REDIRECT + VIDEO_MAIN_EDIT_PAGE + QUESTION + VIDEO_ID + EQUAL + videoBean.getVi_id() + AND + PAGE
						+ EQUAL + currentPage;

			}

			videoBean.setVi_CategoryBean(categoryService.selectByCa_id(VIDEO_MAIN_CODE));
			videoBean.setVi_status(videoService.selectByVi_id(videoBean.getVi_id()).getVi_status());
			videoBean.setVi_update_time(new java.util.Date());

			videoService.update(videoBean);

			logger.info("主影片編輯成功");

			return REDIRECT + VIDEO_MAIN_LIST_PAGE + QUESTION + PAGE + EQUAL + currentPage;
		}
		return null;
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
