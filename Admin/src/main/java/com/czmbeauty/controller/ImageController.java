/*
 * CaiZiMei
 * File: ImageController.java
 * Author: 詹晟
 * Date: 2017/8/10
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import static com.czmbeauty.common.constants.CommonConstants.DOT;
import static com.czmbeauty.common.constants.DirectoryConstants.IMAGES;
import static com.czmbeauty.common.constants.DirectoryConstants.SLIDER_MAIN;
import static com.czmbeauty.common.constants.HqlConstants.HQL_SELECT_ALL_SLIDER_MAIN;
import static com.czmbeauty.common.constants.ModelAttributeConstants.FILE;
import static com.czmbeauty.common.constants.ModelAttributeConstants.IMAGE_BEAN;
import static com.czmbeauty.common.constants.ModelAttributeConstants.IMAGE_LIST;
import static com.czmbeauty.common.constants.PageNameConstants.REDIRECT;
import static com.czmbeauty.common.constants.PageNameConstants.SLIDER_MAIN_ADD_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.SLIDER_MAIN_EDIT_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.SLIDER_MAIN_LIST_PAGE;
import static com.czmbeauty.common.constants.PaginationConstants.CURRENT_PAGE;
import static com.czmbeauty.common.constants.PaginationConstants.MAX_ROW;
import static com.czmbeauty.common.constants.PaginationConstants.PAGE_COUNT;
import static com.czmbeauty.common.constants.PaginationConstants.PAGE_ROW;

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

import com.czmbeauty.model.entity.CategoryBean;
import com.czmbeauty.model.entity.ImageBean;
import com.czmbeauty.model.service.ImageService;

/**
 * image controller
 * 
 * @author 詹晟
 */
@Controller
public class ImageController {

	private static final Logger logger = Logger.getLogger(ImageController.class);

	/**
	 * 注入 ImageService
	 */
	@Autowired
	private ImageService imageService;

	/**
	 * 注入 ServletContext
	 */
	@Autowired
	private ServletContext context;

	/**
	 * 主輪播圖片一覽 - 初期處理
	 * 
	 * @param page
	 *            Integer --> 當前頁碼
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/slider-main/list.jsp
	 */
	@RequestMapping(value = "/slider-main/list", method = RequestMethod.GET)
	public String SliderMainlistView(@RequestParam Integer page, Model model) {

		// 取得當前頁碼
		model.addAttribute(CURRENT_PAGE, page);

		// 取得每頁最大筆數
		model.addAttribute(PAGE_ROW, MAX_ROW);

		// 取得當前頁碼的圖片 List，放入 table
		int first = (page - 1) * MAX_ROW;
		model.addAttribute(IMAGE_LIST,
				imageService.selectAllImagePagination(HQL_SELECT_ALL_SLIDER_MAIN, first, MAX_ROW));

		// 取得總頁數
		CategoryBean im_CategoryBean = new CategoryBean();
		im_CategoryBean.setCa_id(4);
		int totalRowCount = imageService.selectAllImageCount(im_CategoryBean);
		int pageCount = 0;
		if (totalRowCount % MAX_ROW == 0) {
			pageCount = totalRowCount / MAX_ROW;
		} else {
			pageCount = totalRowCount / MAX_ROW + 1;
		}
		model.addAttribute(PAGE_COUNT, pageCount);

		return SLIDER_MAIN_LIST_PAGE;
	}

	/**
	 * 新增主輪播圖片 - 初期處理
	 * 
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/slider-main/add.jsp
	 */
	@RequestMapping(value = "/slider-main/add", method = RequestMethod.GET)
	public String addView(Model model) {

		// 新增 form backing object
		model.addAttribute(IMAGE_BEAN, new ImageBean());

		return SLIDER_MAIN_ADD_PAGE;
	}

	/**
	 * 新增主輪播圖片 - submit
	 * 
	 * @param file
	 *            MultipartFile
	 * @param imageBean
	 *            ImageBean --> form backing object
	 * @return /WEB-INF/views/slider-main/list.jsp
	 */
	@RequestMapping(value = "/slider-main/add.do", method = RequestMethod.POST)
	public String addProcess(@RequestParam(FILE) MultipartFile file, ImageBean imageBean) {

		if (!file.isEmpty()) {

			String root = context.getRealPath("");
			String im_path = root + IMAGES + File.separator + SLIDER_MAIN + File.separator;

			String time = String.valueOf(new java.util.Date().getTime());
			String extension = FilenameUtils.getExtension(file.getOriginalFilename());
			String im_filename = time + DOT + extension;

			try {
				file.transferTo(new File(im_path + im_filename));
			} catch (Exception e) {
				e.printStackTrace();
			}

			imageBean.setIm_path(im_path);
			imageBean.setIm_filename(im_filename);
			imageBean.setIm_status(1);
			imageBean.setIm_update_time(new java.util.Date());

			imageService.insert(imageBean);

			logger.info("圖片上傳成功，位置: " + im_path + im_filename);

			// 新增成功
			return REDIRECT + SLIDER_MAIN_LIST_PAGE;
		}

		logger.info("圖片上傳失敗");

		// 新增失敗
		return REDIRECT + SLIDER_MAIN_LIST_PAGE;
	}

	/**
	 * 編輯主輪播圖片資訊 - 初期處理
	 * 
	 * @param imageBean_im_id
	 *            ImageBean --> form backing object --> GET --> im_id
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/slider-main/edit.jsp
	 */
	@RequestMapping(value = "/slider-main/edit", method = RequestMethod.GET)
	public String editView(ImageBean imageBean_im_id, Model model) {

		// 取得選定圖片 id 的 ImageBean，使表單回填 ImageBean 內所有資料
		model.addAttribute(IMAGE_BEAN, imageService.selectByIm_id(imageBean_im_id.getIm_id()));

		return SLIDER_MAIN_EDIT_PAGE;
	}

	/**
	 * 編輯主輪播圖片資訊 - submit
	 * 
	 * @param file
	 *            MultipartFile
	 * @param imageBean
	 *            ImageBean --> form backing object
	 * @return /WEB-INF/views/slider-main/list.jsp
	 */
	@RequestMapping(value = "/slider-main/edit.do", method = RequestMethod.POST)
	public String editProcess(@RequestParam(FILE) MultipartFile file, ImageBean imageBean) {

		ImageBean oldImageBean = imageService.selectByIm_id(imageBean.getIm_id());

		String im_path;
		String im_filename;

		if (!file.isEmpty()) {

			String root = context.getRealPath("");
			im_path = root + IMAGES + File.separator + SLIDER_MAIN + File.separator;

			String time = String.valueOf(new java.util.Date().getTime());
			String extension = FilenameUtils.getExtension(file.getOriginalFilename());
			im_filename = time + DOT + extension;

			try {
				file.transferTo(new File(im_path + im_filename));
			} catch (Exception e) {
				e.printStackTrace();
			}

			logger.info("圖片上傳成功，位置: " + im_path + im_filename);
		} else {

			im_path = oldImageBean.getIm_path();
			im_filename = oldImageBean.getIm_filename();
		}

		imageBean.setIm_path(im_path);
		imageBean.setIm_filename(im_filename);
		imageBean.setIm_status(oldImageBean.getIm_status());
		imageBean.setIm_update_time(new java.util.Date());

		imageService.update(imageBean);

		logger.info("圖片編輯成功");

		return REDIRECT + SLIDER_MAIN_LIST_PAGE;
	}

	/**
	 * 圖片開關 (AJAX)
	 * 
	 * @param im_id
	 *            String --> 圖片流水號
	 * @return im_name
	 */
	@RequestMapping(value = "/image/switch.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String switchAjaxProcess(String im_id) {

		return imageService.updateIm_status(Integer.valueOf(im_id)).getIm_name();
	}

}
