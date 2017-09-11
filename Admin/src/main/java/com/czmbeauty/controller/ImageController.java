/*
 * CaiZiMei
 * File: ImageController.java
 * Author: 詹晟
 * Date: 2017/9/11
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import static com.czmbeauty.common.constants.CommonConstants.AND;
import static com.czmbeauty.common.constants.CommonConstants.DOT;
import static com.czmbeauty.common.constants.CommonConstants.EQUAL;
import static com.czmbeauty.common.constants.CommonConstants.QUESTION;
import static com.czmbeauty.common.constants.DirectoryConstants.IMAGES;
import static com.czmbeauty.common.constants.ModelAttributeConstants.IMAGE_BEAN;
import static com.czmbeauty.common.constants.ModelAttributeConstants.IMAGE_LIST;
import static com.czmbeauty.common.constants.PageNameConstants.ADD_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.EDIT_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.LIST_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.REDIRECT;
import static com.czmbeauty.common.constants.PaginationConstants.CURRENT_PAGE;
import static com.czmbeauty.common.constants.PaginationConstants.IMAGE_PAGE_ROW_COUNT;
import static com.czmbeauty.common.constants.PaginationConstants.PAGE_COUNT;
import static com.czmbeauty.common.constants.PaginationConstants.PAGE_ROW_COUNT;
import static com.czmbeauty.common.constants.ParameterConstants.IMAGE_ID;
import static com.czmbeauty.common.constants.ParameterConstants.PAGE;

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

import com.czmbeauty.common.editor.PrimitiveNumberEditor;
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
public class ImageController {

	private static final Logger logger = Logger.getLogger(ImageController.class);

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
	 * 取得總頁數
	 * 
	 * @param im_CategoryBean
	 *            CategoryBean
	 * @return int
	 */
	private int getPageCount(CategoryBean im_CategoryBean) {
		int totalRowCount = imageService.selectCountByIm_Ca(im_CategoryBean);
		int pageCount = 0;
		if (totalRowCount % IMAGE_PAGE_ROW_COUNT == 0) {
			pageCount = totalRowCount / IMAGE_PAGE_ROW_COUNT;
		} else {
			pageCount = totalRowCount / IMAGE_PAGE_ROW_COUNT + 1;
		}
		return pageCount;
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
	 * 新增圖片
	 * 
	 * @param file
	 *            MultipartFile
	 * @param imageBean
	 *            ImageBean --> form backing object
	 * @param bindingResult
	 *            BindingResult
	 * @return /WEB-INF/views/ca_directory/add.jsp
	 * @return /WEB-INF/views/ca_directory/add.jsp
	 * @return /WEB-INF/views/ca_directory/list.jsp
	 */
	private String add(MultipartFile file, ImageBean imageBean, BindingResult bindingResult) {

		String ca_directory = request.getServletPath().split("/")[1];
		CategoryBean categoryBean = categoryService.selectByCa_directory(ca_directory);
		String ca_name = categoryBean.getCa_name();

		if (file.isEmpty()) {

			logger.error(ca_name + "新增失敗: 未上傳圖片");

			return REDIRECT + ca_directory + ADD_PAGE;

		} else if (bindingResult.hasErrors()) {

			logger.error(ca_name + "新增失敗: 資料未填");

			return REDIRECT + ca_directory + ADD_PAGE;

		} else {

			String[] pathAndFilename = getPathAndFilename(ca_directory, file);
			try {
				file.transferTo(new File(pathAndFilename[0] + pathAndFilename[1]));
			} catch (Exception e) {
				e.printStackTrace();
			}

			logger.info(ca_name + "上傳成功，位置: " + pathAndFilename[0] + pathAndFilename[1]);

			imageBean.setIm_CategoryBean(categoryBean);
			imageBean.setIm_path(pathAndFilename[0]);
			imageBean.setIm_filename(pathAndFilename[1]);
			imageBean.setIm_status(1);
			imageBean.setIm_update_time(new java.util.Date());

			imageService.insert(imageBean);

			logger.info(ca_name + "新增成功");

			return REDIRECT + ca_directory + LIST_PAGE + QUESTION + PAGE + EQUAL + "1";
		}
	}

	/**
	 * 編輯圖片資訊
	 * 
	 * @param file
	 *            MultipartFile
	 * @param imageBean
	 *            ImageBean --> form backing object
	 * @param bindingResult
	 *            BindingResult
	 * @return /WEB-INF/views/ca_directory/edit.jsp
	 * @return /WEB-INF/views/ca_directory/list.jsp
	 */
	private String edit(MultipartFile file, ImageBean imageBean, BindingResult bindingResult) {

		String ca_directory = request.getServletPath().split("/")[1];
		CategoryBean categoryBean = categoryService.selectByCa_directory(ca_directory);
		String ca_name = categoryBean.getCa_name();

		ImageBean oldImageBean = imageService.selectByIm_id(imageBean.getIm_id());

		String im_path;
		String im_filename;

		if (bindingResult.hasErrors()) {

			logger.error(ca_name + "編輯失敗: 資料未填");

			return REDIRECT + ca_directory + EDIT_PAGE + QUESTION + IMAGE_ID + EQUAL + imageBean.getIm_id() + AND + PAGE
					+ EQUAL + currentPage;

		} else if (file.isEmpty()) {

			im_path = oldImageBean.getIm_path();
			im_filename = oldImageBean.getIm_filename();

		} else {

			String[] pathAndFilename = getPathAndFilename(ca_directory, file);
			im_path = pathAndFilename[0];
			im_filename = pathAndFilename[1];
			try {
				file.transferTo(new File(im_path + im_filename));
			} catch (Exception e) {
				e.printStackTrace();
			}

			logger.info(ca_name + "上傳成功，位置: " + im_path + im_filename);
		}
		imageBean.setIm_CategoryBean(categoryBean);
		imageBean.setIm_path(im_path);
		imageBean.setIm_filename(im_filename);
		imageBean.setIm_status(oldImageBean.getIm_status());
		imageBean.setIm_update_time(new java.util.Date());

		imageService.update(imageBean);

		logger.info(ca_name + "編輯成功");

		return REDIRECT + ca_directory + LIST_PAGE + QUESTION + PAGE + EQUAL + currentPage;
	}

	/**
	 * 輪播圖片一覽 - 初期處理
	 * 
	 * @param page
	 *            Integer --> 當前頁碼
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/ca_directory/list.jsp
	 */
	@RequestMapping(value = "/slider*/list", method = RequestMethod.GET)
	public String listView(@RequestParam Integer page, Model model) {

		String ca_directory = request.getServletPath().split("/")[1];
		CategoryBean categoryBean = categoryService.selectByCa_directory(ca_directory);

		String hql = "from ImageBean where im_ca_id=" + categoryBean.getCa_id()
				+ " order by im_status desc, im_rank asc, im_id asc";

		// 取得當前頁碼
		model.addAttribute(CURRENT_PAGE, page);

		// 取得每頁最大筆數
		model.addAttribute(PAGE_ROW_COUNT, IMAGE_PAGE_ROW_COUNT);

		// 取得當頁起始筆數
		int first = (page - 1) * IMAGE_PAGE_ROW_COUNT;

		// 取得當前頁碼的圖片 List，放入 table
		model.addAttribute(IMAGE_LIST, imageService.selectPagination(hql, first, IMAGE_PAGE_ROW_COUNT));

		// 取得總頁數
		model.addAttribute(PAGE_COUNT, getPageCount(categoryBean));

		logger.info("進入" + categoryBean.getCa_name() + "一覽頁面: " + ca_directory + LIST_PAGE);

		return ca_directory + LIST_PAGE;
	}

	/**
	 * 新增輪播圖片 - 初期處理
	 * 
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/ca_directory/add.jsp
	 */
	@RequestMapping(value = "/slider*/add", method = RequestMethod.GET)
	public String addView(Model model) {

		String ca_directory = request.getServletPath().split("/")[1];
		String ca_name = categoryService.selectByCa_directory(ca_directory).getCa_name();

		// 新增 form backing object
		model.addAttribute(IMAGE_BEAN, new ImageBean());

		logger.info("進入新增" + ca_name + "頁面: " + ca_directory + ADD_PAGE);

		return ca_directory + ADD_PAGE;
	}

	/**
	 * 新增輪播圖片 - submit
	 * 
	 * @param file
	 *            MultipartFile
	 * @param imageBean
	 *            ImageBean --> form backing object
	 * @param bindingResult
	 *            BindingResult
	 * @return /WEB-INF/views/ca_directory/list.jsp
	 */
	@RequestMapping(value = "/slider*/add.do", method = RequestMethod.POST)
	public String addProcess(@RequestParam MultipartFile file, @Valid ImageBean imageBean,
			BindingResult bindingResult) {

		return add(file, imageBean, bindingResult);
	}

	/**
	 * 編輯輪播圖片資訊 - 初期處理
	 * 
	 * @param imageBean_im_id
	 *            ImageBean --> form backing object --> GET --> im_id
	 * @param page
	 *            String --> 當前頁碼
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/ca_directory/edit.jsp
	 */
	@RequestMapping(value = "/slider*/edit", method = RequestMethod.GET)
	public String editView(ImageBean imageBean_im_id, @RequestParam String page, Model model) {

		currentPage = page;

		String ca_directory = request.getServletPath().split("/")[1];
		String ca_name = categoryService.selectByCa_directory(ca_directory).getCa_name();

		// 取得選定圖片 id 的 ImageBean，使表單回填 ImageBean 內所有資料
		model.addAttribute(IMAGE_BEAN, imageService.selectByIm_id(imageBean_im_id.getIm_id()));

		logger.info("進入編輯" + ca_name + "資訊頁面: " + ca_directory + EDIT_PAGE);

		return ca_directory + EDIT_PAGE;
	}

	/**
	 * 編輯輪播圖片資訊 - submit
	 * 
	 * @param file
	 *            MultipartFile
	 * @param imageBean
	 *            ImageBean --> form backing object
	 * @param bindingResult
	 *            BindingResult
	 * @return /WEB-INF/views/ca_directory/list.jsp
	 */
	@RequestMapping(value = "/slider*/edit.do", method = RequestMethod.POST)
	public String editProcess(@RequestParam MultipartFile file, @Valid ImageBean imageBean,
			BindingResult bindingResult) {

		return edit(file, imageBean, bindingResult);
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
