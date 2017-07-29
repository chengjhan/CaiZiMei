/*
 * CaiZiMei
 * File: SliderMainController.java
 * Author: 詹晟
 * Date: 2017/7/30
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import static com.czmbeauty.common.constants.CommonConstants.DOT;
import static com.czmbeauty.common.constants.DirectoryConstants.IMAGES;
import static com.czmbeauty.common.constants.DirectoryConstants.SLIDER_MAIN;
import static com.czmbeauty.common.constants.ModelAttributeConstants.FILE;
import static com.czmbeauty.common.constants.ModelAttributeConstants.SLIDER_MAIN_BEAN;
import static com.czmbeauty.common.constants.ModelAttributeConstants.SLIDER_MAIN_LIST;
import static com.czmbeauty.common.constants.PageNameConstants.REDIRECT;
import static com.czmbeauty.common.constants.PageNameConstants.SLIDER_MAIN_ADD_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.SLIDER_MAIN_EDIT_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.SLIDER_MAIN_LIST_PAGE;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.czmbeauty.model.entity.SliderMainBean;
import com.czmbeauty.model.service.SliderMainService;

/**
 * slider_main controller
 * 
 * @author 詹晟
 */
@Controller
public class SliderMainController {

	private static final Logger logger = Logger.getLogger(SliderMainController.class);

	/**
	 * 注入 SliderMainService
	 */
	@Autowired
	private SliderMainService sliderMainService;

	/**
	 * 注入 ServletContext
	 */
	@Autowired
	private ServletContext context;

	/**
	 * 圖片一覽 - 初期處理
	 * 
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/slider-main/list.jsp
	 */
	@RequestMapping(value = "/slider-main/list", method = RequestMethod.GET)
	public String listView(Model model) {

		// 取得所有圖片 List，放入 table
		model.addAttribute(SLIDER_MAIN_LIST, sliderMainService.selectAll());

		return SLIDER_MAIN_LIST_PAGE;
	}

	/**
	 * 新增圖片 - 初期處理
	 * 
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/slider-main/add.jsp
	 */
	@RequestMapping(value = "/slider-main/add", method = RequestMethod.GET)
	public String addView(Model model) {

		// 新增 form backing object
		model.addAttribute(SLIDER_MAIN_BEAN, new SliderMainBean());

		return SLIDER_MAIN_ADD_PAGE;
	}

	/**
	 * 新增圖片 - submit
	 * 
	 * @param file
	 *            MultipartFile
	 * @param sliderMainBean
	 *            SliderMainBean --> form backing object
	 * @return /WEB-INF/views/slider-main/list.jsp
	 */
	@RequestMapping(value = "/slider-main/add.do", method = RequestMethod.POST)
	public String addProcess(@RequestParam(FILE) MultipartFile file, SliderMainBean sliderMainBean) {

		if (!file.isEmpty()) {

			String root = context.getRealPath("");
			String sm_path = root + IMAGES + File.separator + SLIDER_MAIN + File.separator;

			String time = String.valueOf(new java.util.Date().getTime());
			String extension = FilenameUtils.getExtension(file.getOriginalFilename());
			String sm_filename = time + DOT + extension;

			try {
				file.transferTo(new File(sm_path + sm_filename));
			} catch (Exception e) {
				e.printStackTrace();
			}

			sliderMainBean.setSm_path(sm_path);
			sliderMainBean.setSm_filename(sm_filename);
			sliderMainBean.setSm_status(1);
			sliderMainBean.setSm_update_time(new java.util.Date());

			sliderMainService.insert(sliderMainBean);

			logger.info("圖片上傳成功，位置: " + sm_path + sm_filename);

			// 新增成功
			return REDIRECT + SLIDER_MAIN_LIST_PAGE;
		}

		logger.info("圖片上傳失敗");

		// 新增失敗
		return REDIRECT + SLIDER_MAIN_LIST_PAGE;
	}

	/**
	 * 編輯圖片資訊 - 初期處理
	 * 
	 * @param sliderMainBean_sm_id
	 *            SliderMainBean --> form backing object --> GET --> sm_id
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/slider-main/edit.jsp
	 */
	@RequestMapping(value = "/slider-main/edit", method = RequestMethod.GET)
	public String editView(SliderMainBean sliderMainBean_sm_id, Model model) {

		// 取得選定圖片 id 的 SliderMainBean，使表單回填 SliderMainBean 內所有資料
		model.addAttribute(SLIDER_MAIN_BEAN, sliderMainService.selectBySm_id(sliderMainBean_sm_id.getSm_id()));

		return SLIDER_MAIN_EDIT_PAGE;
	}

	/**
	 * 編輯圖片資訊 - submit
	 * 
	 * @param file
	 *            MultipartFile
	 * @param sliderMainBean
	 *            SliderMainBean --> form backing object
	 * @return /WEB-INF/views/slider-main/list.jsp
	 */
	@RequestMapping(value = "/slider-main/edit.do", method = RequestMethod.POST)
	public String editProcess(@RequestParam(FILE) MultipartFile file, SliderMainBean sliderMainBean) {

		SliderMainBean oldSliderMainBean = sliderMainService.selectBySm_id(sliderMainBean.getSm_id());

		String sm_path;
		String sm_filename;

		if (!file.isEmpty()) {

			String root = context.getRealPath("");
			sm_path = root + IMAGES + File.separator + SLIDER_MAIN + File.separator;

			String time = String.valueOf(new java.util.Date().getTime());
			String extension = FilenameUtils.getExtension(file.getOriginalFilename());
			sm_filename = time + DOT + extension;

			try {
				file.transferTo(new File(sm_path + sm_filename));
			} catch (Exception e) {
				e.printStackTrace();
			}

			logger.info("圖片上傳成功，位置: " + sm_path + sm_filename);
		} else {

			sm_path = oldSliderMainBean.getSm_path();
			sm_filename = oldSliderMainBean.getSm_filename();
		}

		sliderMainBean.setSm_path(sm_path);
		sliderMainBean.setSm_filename(sm_filename);
		sliderMainBean.setSm_status(oldSliderMainBean.getSm_status());
		sliderMainBean.setSm_update_time(new java.util.Date());

		sliderMainService.update(sliderMainBean);

		logger.info("圖片編輯成功");

		return REDIRECT + SLIDER_MAIN_LIST_PAGE;
	}

	/**
	 * 圖片開關 (AJAX)
	 * 
	 * @param sm_id
	 *            String --> 圖片流水號
	 * @return sm_name
	 */
	@RequestMapping(value = "/slider-main/switch.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String switchAjaxProcess(String sm_id) {

		return sliderMainService.updateSm_status(Integer.valueOf(sm_id)).getSm_name();
	}

}
