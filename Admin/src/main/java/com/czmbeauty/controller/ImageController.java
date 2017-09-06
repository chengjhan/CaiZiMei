/*
 * CaiZiMei
 * File: ImageController.java
 * Author: 詹晟
 * Date: 2017/9/6
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import static com.czmbeauty.common.constants.CodeConstants.SLIDER_FRANCHISEE;
import static com.czmbeauty.common.constants.CodeConstants.SLIDER_FRANCHISEE_CODE;
import static com.czmbeauty.common.constants.CodeConstants.SLIDER_KNOWLEDGE;
import static com.czmbeauty.common.constants.CodeConstants.SLIDER_KNOWLEDGE_CODE;
import static com.czmbeauty.common.constants.CodeConstants.SLIDER_MAIN;
import static com.czmbeauty.common.constants.CodeConstants.SLIDER_MAIN_CODE;
import static com.czmbeauty.common.constants.CodeConstants.SLIDER_RECENT;
import static com.czmbeauty.common.constants.CodeConstants.SLIDER_RECENT_CODE;
import static com.czmbeauty.common.constants.CodeConstants.SLIDER_SALE;
import static com.czmbeauty.common.constants.CodeConstants.SLIDER_SALE_CODE;
import static com.czmbeauty.common.constants.CodeConstants.SLIDER_TEAM;
import static com.czmbeauty.common.constants.CodeConstants.SLIDER_TEAM_CODE;
import static com.czmbeauty.common.constants.CommonConstants.AND;
import static com.czmbeauty.common.constants.CommonConstants.DOT;
import static com.czmbeauty.common.constants.CommonConstants.EQUAL;
import static com.czmbeauty.common.constants.CommonConstants.QUESTION;
import static com.czmbeauty.common.constants.DirectoryConstants.IMAGES;
import static com.czmbeauty.common.constants.HqlConstants.HQL_SELECT_ALL_SLIDER_FRANCHISEE;
import static com.czmbeauty.common.constants.HqlConstants.HQL_SELECT_ALL_SLIDER_KNOWLEDGE;
import static com.czmbeauty.common.constants.HqlConstants.HQL_SELECT_ALL_SLIDER_MAIN;
import static com.czmbeauty.common.constants.HqlConstants.HQL_SELECT_ALL_SLIDER_RECENT;
import static com.czmbeauty.common.constants.HqlConstants.HQL_SELECT_ALL_SLIDER_SALE;
import static com.czmbeauty.common.constants.HqlConstants.HQL_SELECT_ALL_SLIDER_TEAM;
import static com.czmbeauty.common.constants.ModelAttributeConstants.IMAGE_BEAN;
import static com.czmbeauty.common.constants.ModelAttributeConstants.IMAGE_LIST;
import static com.czmbeauty.common.constants.PageNameConstants.REDIRECT;
import static com.czmbeauty.common.constants.PageNameConstants.SLIDER_FRANCHISEE_ADD_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.SLIDER_FRANCHISEE_EDIT_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.SLIDER_FRANCHISEE_LIST_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.SLIDER_KNOWLEDGE_ADD_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.SLIDER_KNOWLEDGE_EDIT_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.SLIDER_KNOWLEDGE_LIST_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.SLIDER_MAIN_ADD_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.SLIDER_MAIN_EDIT_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.SLIDER_MAIN_LIST_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.SLIDER_RECENT_ADD_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.SLIDER_RECENT_EDIT_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.SLIDER_RECENT_LIST_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.SLIDER_SALE_ADD_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.SLIDER_SALE_EDIT_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.SLIDER_SALE_LIST_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.SLIDER_TEAM_ADD_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.SLIDER_TEAM_EDIT_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.SLIDER_TEAM_LIST_PAGE;
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
	public int getPageCount(CategoryBean im_CategoryBean) {
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
	 * @param ca_name
	 *            String --> 類別資料夾名
	 * @param file
	 *            MultipartFile
	 * @return String[]
	 */
	public String[] getPathAndFilename(String ca_name, MultipartFile file) {
		String root = context.getRealPath("");
		String im_path = root + IMAGES + File.separator + ca_name + File.separator;
		String time = String.valueOf(new java.util.Date().getTime());
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		String im_filename = time + DOT + extension;
		return new String[] { im_path, im_filename };
	}

	/**
	 * 輪播圖片一覽 - 初期處理
	 * 
	 * @param page
	 *            Integer --> 當前頁碼
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/slider-main/list.jsp
	 * @return /WEB-INF/views/slider-franchisee/list.jsp
	 * @return /WEB-INF/views/slider-recent/list.jsp
	 * @return /WEB-INF/views/slider-sale/list.jsp
	 * @return /WEB-INF/views/slider-knowledge/list.jsp
	 * @return /WEB-INF/views/slider-team/list.jsp
	 */
	@RequestMapping(value = "/slider*/list", method = RequestMethod.GET)
	public String listView(@RequestParam Integer page, Model model) {

		// 取得當前頁碼
		model.addAttribute(CURRENT_PAGE, page);

		// 取得當頁起始筆數
		int first = (page - 1) * IMAGE_PAGE_ROW_COUNT;

		// 取得每頁最大筆數
		model.addAttribute(PAGE_ROW_COUNT, IMAGE_PAGE_ROW_COUNT);

		CategoryBean im_CategoryBean = new CategoryBean();

		String slider = request.getServletPath().split("/")[1];

		if (SLIDER_MAIN.equals(slider)) {

			// 取得當前頁碼的圖片 List，放入 table
			model.addAttribute(IMAGE_LIST,
					imageService.selectPagination(HQL_SELECT_ALL_SLIDER_MAIN, first, IMAGE_PAGE_ROW_COUNT));

			im_CategoryBean.setCa_id(SLIDER_MAIN_CODE);

			// 取得總頁數
			model.addAttribute(PAGE_COUNT, getPageCount(im_CategoryBean));

			return SLIDER_MAIN_LIST_PAGE;
		}
		if (SLIDER_FRANCHISEE.equals(slider)) {

			// 取得當前頁碼的圖片 List，放入 table
			model.addAttribute(IMAGE_LIST,
					imageService.selectPagination(HQL_SELECT_ALL_SLIDER_FRANCHISEE, first, IMAGE_PAGE_ROW_COUNT));

			im_CategoryBean.setCa_id(SLIDER_FRANCHISEE_CODE);

			// 取得總頁數
			model.addAttribute(PAGE_COUNT, getPageCount(im_CategoryBean));

			return SLIDER_FRANCHISEE_LIST_PAGE;
		}
		if (SLIDER_RECENT.equals(slider)) {

			// 取得當前頁碼的圖片 List，放入 table
			model.addAttribute(IMAGE_LIST,
					imageService.selectPagination(HQL_SELECT_ALL_SLIDER_RECENT, first, IMAGE_PAGE_ROW_COUNT));

			im_CategoryBean.setCa_id(SLIDER_RECENT_CODE);

			// 取得總頁數
			model.addAttribute(PAGE_COUNT, getPageCount(im_CategoryBean));

			return SLIDER_RECENT_LIST_PAGE;
		}
		if (SLIDER_SALE.equals(slider)) {

			// 取得當前頁碼的圖片 List，放入 table
			model.addAttribute(IMAGE_LIST,
					imageService.selectPagination(HQL_SELECT_ALL_SLIDER_SALE, first, IMAGE_PAGE_ROW_COUNT));

			im_CategoryBean.setCa_id(SLIDER_SALE_CODE);

			// 取得總頁數
			model.addAttribute(PAGE_COUNT, getPageCount(im_CategoryBean));

			return SLIDER_SALE_LIST_PAGE;
		}
		if (SLIDER_KNOWLEDGE.equals(slider)) {

			// 取得當前頁碼的圖片 List，放入 table
			model.addAttribute(IMAGE_LIST,
					imageService.selectPagination(HQL_SELECT_ALL_SLIDER_KNOWLEDGE, first, IMAGE_PAGE_ROW_COUNT));

			im_CategoryBean.setCa_id(SLIDER_KNOWLEDGE_CODE);

			// 取得總頁數
			model.addAttribute(PAGE_COUNT, getPageCount(im_CategoryBean));

			return SLIDER_KNOWLEDGE_LIST_PAGE;
		}
		if (SLIDER_TEAM.equals(slider)) {

			// 取得當前頁碼的圖片 List，放入 table
			model.addAttribute(IMAGE_LIST,
					imageService.selectPagination(HQL_SELECT_ALL_SLIDER_TEAM, first, IMAGE_PAGE_ROW_COUNT));

			im_CategoryBean.setCa_id(SLIDER_TEAM_CODE);

			// 取得總頁數
			model.addAttribute(PAGE_COUNT, getPageCount(im_CategoryBean));

			return SLIDER_TEAM_LIST_PAGE;
		}
		return null;
	}

	/**
	 * 新增輪播圖片 - 初期處理
	 * 
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/slider-main/add.jsp
	 * @return /WEB-INF/views/slider-franchisee/add.jsp
	 * @return /WEB-INF/views/slider-recent/add.jsp
	 * @return /WEB-INF/views/slider-sale/add.jsp
	 * @return /WEB-INF/views/slider-knowledge/add.jsp
	 * @return /WEB-INF/views/slider-team/add.jsp
	 */
	@RequestMapping(value = "/slider*/add", method = RequestMethod.GET)
	public String addView(Model model) {

		// 新增 form backing object
		model.addAttribute(IMAGE_BEAN, new ImageBean());

		String slider = request.getServletPath().split("/")[1];

		if (SLIDER_MAIN.equals(slider)) {

			return SLIDER_MAIN_ADD_PAGE;
		}
		if (SLIDER_FRANCHISEE.equals(slider)) {

			return SLIDER_FRANCHISEE_ADD_PAGE;
		}
		if (SLIDER_RECENT.equals(slider)) {

			return SLIDER_RECENT_ADD_PAGE;
		}
		if (SLIDER_SALE.equals(slider)) {

			return SLIDER_SALE_ADD_PAGE;
		}
		if (SLIDER_KNOWLEDGE.equals(slider)) {

			return SLIDER_KNOWLEDGE_ADD_PAGE;
		}
		if (SLIDER_TEAM.equals(slider)) {

			return SLIDER_TEAM_ADD_PAGE;
		}
		return null;
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
	 * @return /WEB-INF/views/slider-main/list.jsp
	 * @return /WEB-INF/views/slider-franchisee/list.jsp
	 * @return /WEB-INF/views/slider-recent/list.jsp
	 * @return /WEB-INF/views/slider-sale/list.jsp
	 * @return /WEB-INF/views/slider-knowledge/list.jsp
	 * @return /WEB-INF/views/slider-team/list.jsp
	 */
	@RequestMapping(value = "/slider*/add.do", method = RequestMethod.POST)
	public String addProcess(@RequestParam MultipartFile file, @Valid ImageBean imageBean,
			BindingResult bindingResult) {

		String slider = request.getServletPath().split("/")[1];

		if (SLIDER_MAIN.equals(slider)) {

			if (file.isEmpty()) {

				logger.error("主輪播圖片新增失敗: 未上傳圖片");

				return REDIRECT + SLIDER_MAIN_ADD_PAGE;

			} else if (bindingResult.hasErrors()) {

				logger.error("主輪播圖片新增失敗: 資料未填");

				return REDIRECT + SLIDER_MAIN_ADD_PAGE;

			} else {

				String[] pathAndFilename = getPathAndFilename(SLIDER_MAIN, file);
				try {
					file.transferTo(new File(pathAndFilename[0] + pathAndFilename[1]));
				} catch (Exception e) {
					e.printStackTrace();
				}

				logger.info("主輪播圖片上傳成功，位置: " + pathAndFilename[0] + pathAndFilename[1]);

				imageBean.setIm_CategoryBean(categoryService.selectByCa_id(SLIDER_MAIN_CODE));
				imageBean.setIm_path(pathAndFilename[0]);
				imageBean.setIm_filename(pathAndFilename[1]);
				imageBean.setIm_status(1);
				imageBean.setIm_update_time(new java.util.Date());

				imageService.insert(imageBean);

				logger.info("主輪播圖片新增成功");

				return REDIRECT + SLIDER_MAIN_LIST_PAGE + QUESTION + PAGE + EQUAL + "1";
			}
		}
		if (SLIDER_FRANCHISEE.equals(slider)) {

			if (file.isEmpty()) {

				logger.error("加盟店資訊輪播圖片新增失敗: 未上傳圖片");

				return REDIRECT + SLIDER_FRANCHISEE_ADD_PAGE;

			} else if (bindingResult.hasErrors()) {

				logger.error("加盟店資訊輪播圖片新增失敗: 資料未填");

				return REDIRECT + SLIDER_FRANCHISEE_ADD_PAGE;

			} else {

				String[] pathAndFilename = getPathAndFilename(SLIDER_FRANCHISEE, file);
				try {
					file.transferTo(new File(pathAndFilename[0] + pathAndFilename[1]));
				} catch (Exception e) {
					e.printStackTrace();
				}

				logger.info("加盟店資訊輪播圖片上傳成功，位置: " + pathAndFilename[0] + pathAndFilename[1]);

				imageBean.setIm_CategoryBean(categoryService.selectByCa_id(SLIDER_FRANCHISEE_CODE));
				imageBean.setIm_path(pathAndFilename[0]);
				imageBean.setIm_filename(pathAndFilename[1]);
				imageBean.setIm_status(1);
				imageBean.setIm_update_time(new java.util.Date());

				imageService.insert(imageBean);

				logger.info("加盟店資訊輪播圖片新增成功");

				return REDIRECT + SLIDER_FRANCHISEE_LIST_PAGE + QUESTION + PAGE + EQUAL + "1";
			}
		}
		if (SLIDER_RECENT.equals(slider)) {

			if (file.isEmpty()) {

				logger.error("近期活動輪播圖片新增失敗: 未上傳圖片");

				return REDIRECT + SLIDER_RECENT_ADD_PAGE;

			} else if (bindingResult.hasErrors()) {

				logger.error("近期活動輪播圖片新增失敗: 資料未填");

				return REDIRECT + SLIDER_RECENT_ADD_PAGE;

			} else {

				String[] pathAndFilename = getPathAndFilename(SLIDER_RECENT, file);
				try {
					file.transferTo(new File(pathAndFilename[0] + pathAndFilename[1]));
				} catch (Exception e) {
					e.printStackTrace();
				}

				logger.info("近期活動輪播圖片上傳成功，位置: " + pathAndFilename[0] + pathAndFilename[1]);

				imageBean.setIm_CategoryBean(categoryService.selectByCa_id(SLIDER_RECENT_CODE));
				imageBean.setIm_path(pathAndFilename[0]);
				imageBean.setIm_filename(pathAndFilename[1]);
				imageBean.setIm_status(1);
				imageBean.setIm_update_time(new java.util.Date());

				imageService.insert(imageBean);

				logger.info("近期活動輪播圖片新增成功");

				return REDIRECT + SLIDER_RECENT_LIST_PAGE + QUESTION + PAGE + EQUAL + "1";
			}
		}
		if (SLIDER_SALE.equals(slider)) {

			if (file.isEmpty()) {

				logger.error("優惠活動輪播圖片新增失敗: 未上傳圖片");

				return REDIRECT + SLIDER_SALE_ADD_PAGE;

			} else if (bindingResult.hasErrors()) {

				logger.error("優惠活動輪播圖片新增失敗: 資料未填");

				return REDIRECT + SLIDER_SALE_ADD_PAGE;

			} else {

				String[] pathAndFilename = getPathAndFilename(SLIDER_SALE, file);
				try {
					file.transferTo(new File(pathAndFilename[0] + pathAndFilename[1]));
				} catch (Exception e) {
					e.printStackTrace();
				}

				logger.info("優惠活動輪播圖片上傳成功，位置: " + pathAndFilename[0] + pathAndFilename[1]);

				imageBean.setIm_CategoryBean(categoryService.selectByCa_id(SLIDER_SALE_CODE));
				imageBean.setIm_path(pathAndFilename[0]);
				imageBean.setIm_filename(pathAndFilename[1]);
				imageBean.setIm_status(1);
				imageBean.setIm_update_time(new java.util.Date());

				imageService.insert(imageBean);

				logger.info("優惠活動輪播圖片新增成功");

				return REDIRECT + SLIDER_SALE_LIST_PAGE + QUESTION + PAGE + EQUAL + "1";
			}
		}
		if (SLIDER_KNOWLEDGE.equals(slider)) {

			if (file.isEmpty()) {

				logger.error("醫療新知輪播圖片新增失敗: 未上傳圖片");

				return REDIRECT + SLIDER_KNOWLEDGE_ADD_PAGE;

			} else if (bindingResult.hasErrors()) {

				logger.error("醫療新知輪播圖片新增失敗: 資料未填");

				return REDIRECT + SLIDER_KNOWLEDGE_ADD_PAGE;

			} else {

				String[] pathAndFilename = getPathAndFilename(SLIDER_KNOWLEDGE, file);
				try {
					file.transferTo(new File(pathAndFilename[0] + pathAndFilename[1]));
				} catch (Exception e) {
					e.printStackTrace();
				}

				logger.info("醫療新知輪播圖片上傳成功，位置: " + pathAndFilename[0] + pathAndFilename[1]);

				imageBean.setIm_CategoryBean(categoryService.selectByCa_id(SLIDER_KNOWLEDGE_CODE));
				imageBean.setIm_path(pathAndFilename[0]);
				imageBean.setIm_filename(pathAndFilename[1]);
				imageBean.setIm_status(1);
				imageBean.setIm_update_time(new java.util.Date());

				imageService.insert(imageBean);

				logger.info("醫療新知輪播圖片新增成功");

				return REDIRECT + SLIDER_KNOWLEDGE_LIST_PAGE + QUESTION + PAGE + EQUAL + "1";
			}
		}
		if (SLIDER_TEAM.equals(slider)) {

			if (file.isEmpty()) {

				logger.error("醫療團隊輪播圖片新增失敗: 未上傳圖片");

				return REDIRECT + SLIDER_TEAM_ADD_PAGE;

			} else if (bindingResult.hasErrors()) {

				logger.error("醫療團隊輪播圖片新增失敗: 資料未填");

				return REDIRECT + SLIDER_TEAM_ADD_PAGE;

			} else {

				String[] pathAndFilename = getPathAndFilename(SLIDER_TEAM, file);
				try {
					file.transferTo(new File(pathAndFilename[0] + pathAndFilename[1]));
				} catch (Exception e) {
					e.printStackTrace();
				}

				logger.info("醫療團隊輪播圖片上傳成功，位置: " + pathAndFilename[0] + pathAndFilename[1]);

				imageBean.setIm_CategoryBean(categoryService.selectByCa_id(SLIDER_TEAM_CODE));
				imageBean.setIm_path(pathAndFilename[0]);
				imageBean.setIm_filename(pathAndFilename[1]);
				imageBean.setIm_status(1);
				imageBean.setIm_update_time(new java.util.Date());

				imageService.insert(imageBean);

				logger.info("醫療團隊輪播圖片新增成功");

				return REDIRECT + SLIDER_TEAM_LIST_PAGE + QUESTION + PAGE + EQUAL + "1";
			}
		}
		return null;
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
	 * @return /WEB-INF/views/slider-main/edit.jsp
	 * @return /WEB-INF/views/slider-franchisee/edit.jsp
	 * @return /WEB-INF/views/slider-recent/edit.jsp
	 * @return /WEB-INF/views/slider-sale/edit.jsp
	 * @return /WEB-INF/views/slider-knowledge/edit.jsp
	 * @return /WEB-INF/views/slider-team/edit.jsp
	 */
	@RequestMapping(value = "/slider*/edit", method = RequestMethod.GET)
	public String editView(ImageBean imageBean_im_id, @RequestParam String page, Model model) {

		currentPage = page;

		// 取得選定圖片 id 的 ImageBean，使表單回填 ImageBean 內所有資料
		model.addAttribute(IMAGE_BEAN, imageService.selectByIm_id(imageBean_im_id.getIm_id()));

		String slider = request.getServletPath().split("/")[1];

		if (SLIDER_MAIN.equals(slider)) {

			return SLIDER_MAIN_EDIT_PAGE;
		}
		if (SLIDER_FRANCHISEE.equals(slider)) {

			return SLIDER_FRANCHISEE_EDIT_PAGE;
		}
		if (SLIDER_RECENT.equals(slider)) {

			return SLIDER_RECENT_EDIT_PAGE;
		}
		if (SLIDER_SALE.equals(slider)) {

			return SLIDER_SALE_EDIT_PAGE;
		}
		if (SLIDER_KNOWLEDGE.equals(slider)) {

			return SLIDER_KNOWLEDGE_EDIT_PAGE;
		}
		if (SLIDER_TEAM.equals(slider)) {

			return SLIDER_TEAM_EDIT_PAGE;
		}
		return null;
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
	 * @return /WEB-INF/views/slider-main/list.jsp
	 * @return /WEB-INF/views/slider-franchisee/list.jsp
	 * @return /WEB-INF/views/slider-recent/list.jsp
	 * @return /WEB-INF/views/slider-sale/list.jsp
	 */
	@RequestMapping(value = "/slider*/edit.do", method = RequestMethod.POST)
	public String editProcess(@RequestParam MultipartFile file, @Valid ImageBean imageBean,
			BindingResult bindingResult) {

		ImageBean oldImageBean = imageService.selectByIm_id(imageBean.getIm_id());

		String im_path;
		String im_filename;

		String slider = request.getServletPath().split("/")[1];

		if (SLIDER_MAIN.equals(slider)) {

			if (bindingResult.hasErrors()) {

				logger.error("主輪播圖片編輯失敗: 資料未填");

				return REDIRECT + SLIDER_MAIN_EDIT_PAGE + QUESTION + IMAGE_ID + EQUAL + imageBean.getIm_id() + AND
						+ PAGE + EQUAL + currentPage;

			} else if (file.isEmpty()) {

				im_path = oldImageBean.getIm_path();
				im_filename = oldImageBean.getIm_filename();

			} else {

				String[] pathAndFilename = getPathAndFilename(SLIDER_MAIN, file);
				im_path = pathAndFilename[0];
				im_filename = pathAndFilename[1];
				try {
					file.transferTo(new File(im_path + im_filename));
				} catch (Exception e) {
					e.printStackTrace();
				}

				logger.info("主輪播圖片上傳成功，位置: " + im_path + im_filename);
			}
			imageBean.setIm_CategoryBean(categoryService.selectByCa_id(SLIDER_MAIN_CODE));
			imageBean.setIm_path(im_path);
			imageBean.setIm_filename(im_filename);
			imageBean.setIm_status(oldImageBean.getIm_status());
			imageBean.setIm_update_time(new java.util.Date());

			imageService.update(imageBean);

			logger.info("主輪播圖片編輯成功");

			return REDIRECT + SLIDER_MAIN_LIST_PAGE + QUESTION + PAGE + EQUAL + currentPage;
		}
		if (SLIDER_FRANCHISEE.equals(slider)) {

			if (bindingResult.hasErrors()) {

				logger.error("加盟店資訊輪播圖片編輯失敗: 資料未填");

				return REDIRECT + SLIDER_FRANCHISEE_EDIT_PAGE + QUESTION + IMAGE_ID + EQUAL + imageBean.getIm_id() + AND
						+ PAGE + EQUAL + currentPage;

			} else if (file.isEmpty()) {

				im_path = oldImageBean.getIm_path();
				im_filename = oldImageBean.getIm_filename();

			} else {

				String[] pathAndFilename = getPathAndFilename(SLIDER_FRANCHISEE, file);
				im_path = pathAndFilename[0];
				im_filename = pathAndFilename[1];
				try {
					file.transferTo(new File(im_path + im_filename));
				} catch (Exception e) {
					e.printStackTrace();
				}

				logger.info("加盟店資訊輪播圖片上傳成功，位置: " + im_path + im_filename);
			}
			imageBean.setIm_CategoryBean(categoryService.selectByCa_id(SLIDER_FRANCHISEE_CODE));
			imageBean.setIm_path(im_path);
			imageBean.setIm_filename(im_filename);
			imageBean.setIm_status(oldImageBean.getIm_status());
			imageBean.setIm_update_time(new java.util.Date());

			imageService.update(imageBean);

			logger.info("加盟店資訊輪播圖片編輯成功");

			return REDIRECT + SLIDER_FRANCHISEE_LIST_PAGE + QUESTION + PAGE + EQUAL + currentPage;
		}
		if (SLIDER_RECENT.equals(slider)) {

			if (bindingResult.hasErrors()) {

				logger.error("近期活動輪播圖片編輯失敗: 資料未填");

				return REDIRECT + SLIDER_RECENT_EDIT_PAGE + QUESTION + IMAGE_ID + EQUAL + imageBean.getIm_id() + AND
						+ PAGE + EQUAL + currentPage;

			} else if (file.isEmpty()) {

				im_path = oldImageBean.getIm_path();
				im_filename = oldImageBean.getIm_filename();

			} else {

				String[] pathAndFilename = getPathAndFilename(SLIDER_RECENT, file);
				im_path = pathAndFilename[0];
				im_filename = pathAndFilename[1];
				try {
					file.transferTo(new File(im_path + im_filename));
				} catch (Exception e) {
					e.printStackTrace();
				}

				logger.info("近期活動輪播圖片上傳成功，位置: " + im_path + im_filename);
			}
			imageBean.setIm_CategoryBean(categoryService.selectByCa_id(SLIDER_RECENT_CODE));
			imageBean.setIm_path(im_path);
			imageBean.setIm_filename(im_filename);
			imageBean.setIm_status(oldImageBean.getIm_status());
			imageBean.setIm_update_time(new java.util.Date());

			imageService.update(imageBean);

			logger.info("近期活動輪播圖片編輯成功");

			return REDIRECT + SLIDER_RECENT_LIST_PAGE + QUESTION + PAGE + EQUAL + currentPage;
		}
		if (SLIDER_SALE.equals(slider)) {

			if (bindingResult.hasErrors()) {

				logger.error("優惠活動輪播圖片編輯失敗: 資料未填");

				return REDIRECT + SLIDER_SALE_EDIT_PAGE + QUESTION + IMAGE_ID + EQUAL + imageBean.getIm_id() + AND
						+ PAGE + EQUAL + currentPage;

			} else if (file.isEmpty()) {

				im_path = oldImageBean.getIm_path();
				im_filename = oldImageBean.getIm_filename();

			} else {

				String[] pathAndFilename = getPathAndFilename(SLIDER_SALE, file);
				im_path = pathAndFilename[0];
				im_filename = pathAndFilename[1];
				try {
					file.transferTo(new File(im_path + im_filename));
				} catch (Exception e) {
					e.printStackTrace();
				}

				logger.info("優惠活動輪播圖片上傳成功，位置: " + im_path + im_filename);
			}
			imageBean.setIm_CategoryBean(categoryService.selectByCa_id(SLIDER_SALE_CODE));
			imageBean.setIm_path(im_path);
			imageBean.setIm_filename(im_filename);
			imageBean.setIm_status(oldImageBean.getIm_status());
			imageBean.setIm_update_time(new java.util.Date());

			imageService.update(imageBean);

			logger.info("優惠活動輪播圖片編輯成功");

			return REDIRECT + SLIDER_SALE_LIST_PAGE + QUESTION + PAGE + EQUAL + currentPage;
		}
		if (SLIDER_KNOWLEDGE.equals(slider)) {

			if (bindingResult.hasErrors()) {

				logger.error("醫療新知輪播圖片編輯失敗: 資料未填");

				return REDIRECT + SLIDER_KNOWLEDGE_EDIT_PAGE + QUESTION + IMAGE_ID + EQUAL + imageBean.getIm_id() + AND
						+ PAGE + EQUAL + currentPage;

			} else if (file.isEmpty()) {

				im_path = oldImageBean.getIm_path();
				im_filename = oldImageBean.getIm_filename();

			} else {

				String[] pathAndFilename = getPathAndFilename(SLIDER_KNOWLEDGE, file);
				im_path = pathAndFilename[0];
				im_filename = pathAndFilename[1];
				try {
					file.transferTo(new File(im_path + im_filename));
				} catch (Exception e) {
					e.printStackTrace();
				}

				logger.info("醫療新知輪播圖片上傳成功，位置: " + im_path + im_filename);
			}
			imageBean.setIm_CategoryBean(categoryService.selectByCa_id(SLIDER_KNOWLEDGE_CODE));
			imageBean.setIm_path(im_path);
			imageBean.setIm_filename(im_filename);
			imageBean.setIm_status(oldImageBean.getIm_status());
			imageBean.setIm_update_time(new java.util.Date());

			imageService.update(imageBean);

			logger.info("醫療新知輪播圖片編輯成功");

			return REDIRECT + SLIDER_KNOWLEDGE_LIST_PAGE + QUESTION + PAGE + EQUAL + currentPage;
		}
		if (SLIDER_TEAM.equals(slider)) {

			if (bindingResult.hasErrors()) {

				logger.error("醫療團隊輪播圖片編輯失敗: 資料未填");

				return REDIRECT + SLIDER_TEAM_EDIT_PAGE + QUESTION + IMAGE_ID + EQUAL + imageBean.getIm_id() + AND
						+ PAGE + EQUAL + currentPage;

			} else if (file.isEmpty()) {

				im_path = oldImageBean.getIm_path();
				im_filename = oldImageBean.getIm_filename();

			} else {

				String[] pathAndFilename = getPathAndFilename(SLIDER_TEAM, file);
				im_path = pathAndFilename[0];
				im_filename = pathAndFilename[1];
				try {
					file.transferTo(new File(im_path + im_filename));
				} catch (Exception e) {
					e.printStackTrace();
				}

				logger.info("醫療團隊輪播圖片上傳成功，位置: " + im_path + im_filename);
			}
			imageBean.setIm_CategoryBean(categoryService.selectByCa_id(SLIDER_TEAM_CODE));
			imageBean.setIm_path(im_path);
			imageBean.setIm_filename(im_filename);
			imageBean.setIm_status(oldImageBean.getIm_status());
			imageBean.setIm_update_time(new java.util.Date());

			imageService.update(imageBean);

			logger.info("醫療團隊輪播圖片編輯成功");

			return REDIRECT + SLIDER_TEAM_LIST_PAGE + QUESTION + PAGE + EQUAL + currentPage;
		}
		return null;
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
