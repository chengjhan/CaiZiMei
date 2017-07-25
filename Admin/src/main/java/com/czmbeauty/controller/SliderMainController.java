/*
 * CaiZiMei
 * File: SliderMainController.java
 * Author: 詹晟
 * Date: 2017/7/26
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import static com.czmbeauty.common.constants.ModelAttributeConstants.SLIDER_MAIN_BEAN;
import static com.czmbeauty.common.constants.ModelAttributeConstants.SLIDER_MAIN_LIST;
import static com.czmbeauty.common.constants.PageNameConstants.REDIRECT;
import static com.czmbeauty.common.constants.PageNameConstants.SLIDER_MAIN_ADD_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.SLIDER_MAIN_EDIT_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.SLIDER_MAIN_LIST_PAGE;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	/**
	 * 注入 SliderMainService
	 */
	@Autowired
	private SliderMainService sliderMainService;

	/**
	 * 圖片一覽 - 初期處理
	 * 
	 * @param model-->Model
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
	 * @param model-->Model
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
	 * @param file-->MultipartFile
	 * @param sliderMainBean-->form-backing-object
	 * @param request-->HttpServletRequest
	 * @return /WEB-INF/views/slider-main/list.jsp
	 */
	@RequestMapping(value = "/slider-main/add.do", method = RequestMethod.POST)
	public String addProcess(@RequestParam("file") MultipartFile file, SliderMainBean sliderMainBean,
			HttpServletRequest request) {

		if (!file.isEmpty()) {

			String sm_path = request.getServletContext().getRealPath("/images/slider-main/");
			String sm_name = sliderMainBean.getSm_name();
			String originalFilename = file.getOriginalFilename();
			String extension = FilenameUtils.getExtension(originalFilename);
			String sm_filename = sm_name + "." + extension;

			try {
				file.transferTo(new File(sm_path + sm_filename));
			} catch (Exception e) {
				e.printStackTrace();
			}

			sliderMainBean.setSm_path(sm_path);
			sliderMainBean.setSm_filename(sm_filename);

			sliderMainService.insert(sliderMainBean);

			// 新增成功
			return REDIRECT + SLIDER_MAIN_LIST_PAGE;
		}

		// 新增失敗
		return REDIRECT + SLIDER_MAIN_LIST_PAGE;
	}

	/**
	 * 編輯圖片資訊 - 初期處理
	 * 
	 * @param sliderMainBean_sm_id-->form-backing-object-->GET-->sm_id
	 * @param model-->Model
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
	 * @param sliderMainBean-->form-backing-object
	 * @return /WEB-INF/views/slider-main/list.jsp
	 */
	@RequestMapping(value = "/slider-main/edit.do", method = RequestMethod.POST)
	public String editProcess(SliderMainBean sliderMainBean) {

		sliderMainService.update(sliderMainBean);

		return REDIRECT + SLIDER_MAIN_LIST_PAGE;
	}

	/**
	 * 圖片開關 - submit
	 * 
	 * @param sliderMainBean_sm_id-->form-backing-object-->GET-->sm_id
	 * @return /WEB-INF/views/slider-main/list.jsp
	 */
	@RequestMapping(value = "/slider-main/switch", method = RequestMethod.GET)
	public String switchProcess(SliderMainBean sliderMainBean_sm_id) {

		sliderMainService.updateSm_status(sliderMainBean_sm_id);

		return REDIRECT + SLIDER_MAIN_LIST_PAGE;
	}

}
