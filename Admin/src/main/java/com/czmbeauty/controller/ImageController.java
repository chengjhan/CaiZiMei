/*
 * CaiZiMei
 * File: ImageController.java
 * Author: 詹晟
 * Date: 2017/12/6
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
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
import org.springframework.web.multipart.MultipartFile;

import com.czmbeauty.common.constants.ControllerConstants;
import com.czmbeauty.common.editor.PrimitiveNumberEditor;
import com.czmbeauty.common.exception.PageNotFoundException;
import com.czmbeauty.common.util.ImageUtil;
import com.czmbeauty.common.util.PaginationUtil;
import com.czmbeauty.model.entity.CategoryBean;
import com.czmbeauty.model.entity.ImageBean;
import com.czmbeauty.model.service.CategoryService;
import com.czmbeauty.model.service.ImageService;

/**
 * image controller
 * 
 * @author 詹晟
 */
@Controller
public class ImageController implements ControllerConstants {

	private static final Logger logger = Logger.getLogger(ImageController.class);

	private String className = this.getClass().getSimpleName();

	/**
	 * 當前頁碼
	 */
	private String currentPage;

	/**
	 * 注入 ServletContext
	 */
	@Autowired
	private ServletContext context;

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
	 * 注入 ImageService
	 */
	@Autowired
	private ImageService imageService;

	/**
	 * 提供 form backing object 資料轉換
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Integer.class, new PrimitiveNumberEditor(Integer.class, true));
	}

	/**
	 * 取得圖片路徑及檔名
	 * 
	 * @param ca_directory
	 *            String --> 類別資料夾名稱
	 * @param file
	 *            MultipartFile
	 * @return String[]
	 */
	private String[] getPathAndFilename(String ca_directory, MultipartFile file) {
		String root = context.getRealPath("");
		String im_path = root + IMAGES + File.separator + ca_directory + File.separator;
		String time = String.valueOf(new java.util.Date().getTime());
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		String im_filename = time + DOT + extension;
		return new String[] { im_path, im_filename };
	}

	/**
	 * 圖片一覽 - 初期處理
	 * 
	 * @param page
	 *            Integer --> 當前頁碼
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/ca_directory/list.jsp
	 */
	@RequestMapping(value = { "/slider*/list", "/image*/list" }, method = RequestMethod.GET)
	public String listView(@RequestParam Integer page, Model model) {

		String requestPath = (String) request.getAttribute(REQUEST_PATH);

		CategoryBean categoryBean = categoryService.selectByCa_directory(requestPath);
		String ca_directory = categoryBean.getCa_directory();

		int pageRowCount = IMAGE_PAGE_ROW_COUNT_NUMBER;
		int pageCount = PaginationUtil.getPageCount(imageService.selectCountByIm_Ca(categoryBean), pageRowCount);
		int groupRowCount = GROUP_ROW_COUNT_NUMBER;

		// 取得類別資料夾名稱
		model.addAttribute(CATEGORY_DIRECTORY, ca_directory);

		// 取得當前頁碼的圖片 List，放入 table
		model.addAttribute(IMAGE_LIST, imageService.selectPagination(categoryBean.getCa_id(), page, pageRowCount));

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
	 * 新增圖片 - 初期處理
	 * 
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/ca_directory/add.jsp
	 */
	@RequestMapping(value = { "/slider*/add", "/image*/add" }, method = RequestMethod.GET)
	public String addView(Model model) {

		String requestPath = (String) request.getAttribute(REQUEST_PATH);

		CategoryBean categoryBean = categoryService.selectByCa_directory(requestPath);

		// 新增 form backing object
		model.addAttribute(IMAGE_BEAN, new ImageBean());

		return categoryBean.getCa_directory() + ADD_PAGE;
	}

	/**
	 * 新增圖片 - submit
	 * 
	 * @param file
	 *            MultipartFile
	 * @param imageBean
	 *            ImageBean --> form backing object
	 * @param bindingResult
	 *            BindingResult
	 * @return /WEB-INF/views/ca_directory/list.jsp
	 */
	@RequestMapping(value = { "/slider*/add.do", "/image*/add.do" }, method = RequestMethod.POST)
	public String addAction(@RequestParam MultipartFile file, @Valid ImageBean imageBean, BindingResult bindingResult) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		String requestPath = (String) request.getAttribute(REQUEST_PATH);

		CategoryBean categoryBean = categoryService.selectByCa_directory(requestPath);
		String ca_name = categoryBean.getCa_name();
		String ca_directory = categoryBean.getCa_directory();

		if (bindingResult.hasErrors()) {

			logger.error("(" + className + "." + methodName + ") " + ca_name + "新增失敗: 資料未填");

			return ca_directory + ADD_PAGE;

		} else if (file.isEmpty()) {

			logger.error("(" + className + "." + methodName + ") " + ca_name + "新增失敗: 未上傳圖片");

			return ca_directory + ADD_PAGE;

		} else {

			if (!ImageUtil.isImage(file)) {

				logger.error("(" + className + "." + methodName + ") " + ca_name + "新增失敗: 上傳格式錯誤");

				return ca_directory + ADD_PAGE;
			}

			String[] pathAndFilename = getPathAndFilename(ca_directory, file);

			try {
				file.transferTo(new File(pathAndFilename[0] + pathAndFilename[1]));

			} catch (Exception e) {

				logger.error("(" + className + "." + methodName + ") " + ca_name + "上傳失敗");

				return ca_directory + ADD_PAGE;
			}

			logger.info("(" + className + "." + methodName + ") " + ca_name + "上傳成功，位置: " + pathAndFilename[0]
					+ pathAndFilename[1]);

			imageBean.setIm_CategoryBean(categoryBean);
			imageBean.setIm_path(pathAndFilename[0]);
			imageBean.setIm_filename(pathAndFilename[1]);
			imageService.insert(imageBean);

			request.setAttribute(SUCCESS_KEY, OK);

			logger.info("(" + className + "." + methodName + ") " + ca_name + "新增成功");

			return REDIRECT + ca_directory + LIST_PAGE + QUESTION + PAGE + EQUAL + PAGE_ONE;
		}
	}

	/**
	 * 編輯圖片 - 初期處理
	 * 
	 * @param imageBean_im_id
	 *            ImageBean --> form backing object --> GET --> im_id
	 * @param page
	 *            String --> 當前頁碼
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/error/page-not-found.jsp
	 * @return /WEB-INF/views/ca_directory/edit.jsp
	 */
	@RequestMapping(value = { "/slider*/edit", "/image*/edit" }, method = RequestMethod.GET)
	public String editView(ImageBean imageBean_im_id, @RequestParam String page, Model model) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		currentPage = page;

		String requestPath = (String) request.getAttribute(REQUEST_PATH);

		CategoryBean categoryBean = categoryService.selectByCa_directory(requestPath);

		ImageBean imageBean;
		try {
			// 取得選定圖片 id 的 ImageBean
			imageBean = imageService.selectByIm_id(imageBean_im_id.getIm_id());

			if (imageBean == null) {

				throw new PageNotFoundException(requestPath);
			}
		} catch (PageNotFoundException e) {

			return ERROR_PAGE_NOT_FOUND_PAGE;

		} catch (IllegalArgumentException e) {

			logger.error("(" + className + "." + methodName + ") 找不到這個頁面: " + requestPath);

			return ERROR_PAGE_NOT_FOUND_PAGE;
		}

		// 使表單回填 ImageBean 內所有資料
		model.addAttribute(IMAGE_BEAN, imageBean);

		// 取得當前頁碼
		model.addAttribute(CURRENT_PAGE, page);

		return categoryBean.getCa_directory() + EDIT_PAGE;
	}

	/**
	 * 編輯圖片 - submit
	 * 
	 * @param file
	 *            MultipartFile
	 * @param imageBean
	 *            ImageBean --> form backing object
	 * @param bindingResult
	 *            BindingResult
	 * @return /WEB-INF/views/ca_directory/list.jsp
	 */
	@RequestMapping(value = { "/slider*/edit.do", "/image*/edit.do" }, method = RequestMethod.POST)
	public String editAction(@RequestParam MultipartFile file, @Valid ImageBean imageBean,
			BindingResult bindingResult) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		String requestPath = (String) request.getAttribute(REQUEST_PATH);

		CategoryBean categoryBean = categoryService.selectByCa_directory(requestPath);
		String ca_name = categoryBean.getCa_name();
		String ca_directory = categoryBean.getCa_directory();

		ImageBean oldImageBean = imageService.selectByIm_id(imageBean.getIm_id());

		String im_path;
		String im_filename;

		if (bindingResult.hasErrors()) {

			logger.error("(" + className + "." + methodName + ") " + ca_name + "編輯失敗: 資料未填");

			return ca_directory + EDIT_PAGE;

		} else if (file.isEmpty()) {

			im_path = oldImageBean.getIm_path();
			im_filename = oldImageBean.getIm_filename();

		} else {

			if (!ImageUtil.isImage(file)) {

				logger.error("(" + className + "." + methodName + ") " + ca_name + "編輯失敗: 上傳格式錯誤");

				return ca_directory + EDIT_PAGE;
			}

			String[] pathAndFilename = getPathAndFilename(ca_directory, file);
			im_path = pathAndFilename[0];
			im_filename = pathAndFilename[1];

			try {
				file.transferTo(new File(im_path + im_filename));

			} catch (Exception e) {

				logger.error("(" + className + "." + methodName + ") " + ca_name + "上傳失敗");

				return ca_directory + EDIT_PAGE;
			}

			logger.info("(" + className + "." + methodName + ") " + ca_name + "上傳成功，位置: " + im_path + im_filename);
		}

		imageBean.setIm_CategoryBean(categoryBean);
		imageBean.setIm_path(im_path);
		imageBean.setIm_filename(im_filename);
		imageBean.setIm_status(oldImageBean.getIm_status());
		imageService.update(imageBean);

		request.setAttribute(SUCCESS_KEY, OK);

		logger.info("(" + className + "." + methodName + ") " + ca_name + "編輯成功");

		return REDIRECT + ca_directory + LIST_PAGE + QUESTION + PAGE + EQUAL + currentPage;
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
	public String switchAjax(String im_id) {

		return imageService.updateIm_status(Integer.valueOf(im_id)).getIm_name();
	}

}
