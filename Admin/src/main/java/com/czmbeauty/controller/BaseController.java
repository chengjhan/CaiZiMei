/*
 * CaiZiMei
 * File: BaseController.java
 * Author: 詹晟
 * Date: 2017/11/2
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
import com.czmbeauty.common.editor.CityBeanPropertyEditor;
import com.czmbeauty.common.editor.CountryBeanPropertyEditor;
import com.czmbeauty.common.editor.PrimitiveNumberEditor;
import com.czmbeauty.common.editor.StateBeanPropertyEditor;
import com.czmbeauty.common.exception.PageNotFoundException;
import com.czmbeauty.common.util.Pagination;
import com.czmbeauty.model.entity.BaseBean;
import com.czmbeauty.model.entity.CategoryBean;
import com.czmbeauty.model.entity.CityBean;
import com.czmbeauty.model.entity.CountryBean;
import com.czmbeauty.model.entity.StateBean;
import com.czmbeauty.model.service.BaseService;
import com.czmbeauty.model.service.CategoryService;
import com.czmbeauty.model.service.CityService;
import com.czmbeauty.model.service.CountryService;
import com.czmbeauty.model.service.StateService;

/**
 * base controller
 * 
 * @author 詹晟
 */
@Controller
public class BaseController implements ControllerConstants {

	private static final Logger logger = Logger.getLogger(BaseController.class);

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
	 * 注入 CountryService
	 */
	@Autowired
	private CountryService countryService;

	/**
	 * 注入 StateService
	 */
	@Autowired
	private StateService stateService;

	/**
	 * 注入 CityService
	 */
	@Autowired
	private CityService cityService;

	/**
	 * 注入 CategoryService
	 */
	@Autowired
	private CategoryService categoryService;

	/**
	 * 注入 BaseService
	 */
	@Autowired
	private BaseService baseService;

	/**
	 * 提供 form backing object 資料轉換
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Integer.class, new PrimitiveNumberEditor(Integer.class, true));
		webDataBinder.registerCustomEditor(CountryBean.class, new CountryBeanPropertyEditor());
		webDataBinder.registerCustomEditor(StateBean.class, new StateBeanPropertyEditor());
		webDataBinder.registerCustomEditor(CityBean.class, new CityBeanPropertyEditor());
	}

	/**
	 * 據點一覽 - 初期處理
	 * 
	 * @param page
	 *            Integer --> 當前頁碼
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/ca_directory/list.jsp
	 */
	@RequestMapping(value = "/base*/list", method = RequestMethod.GET)
	public String listView(@RequestParam Integer page, Model model) {

		String requestView = (String) request.getAttribute(REQUEST_VIEW);
		CategoryBean categoryBean = categoryService.selectByCa_directory(requestView);
		String ca_directory = categoryBean.getCa_directory();

		int pageRowCount = BASE_PAGE_ROW_COUNT;

		// 取得類別資料夾名稱
		model.addAttribute(CATEGORY_DIRECTORY, ca_directory);

		// 取得當前頁碼
		model.addAttribute(CURRENT_PAGE, page);

		// 取得每頁最大筆數
		model.addAttribute(PAGE_ROW_COUNT, pageRowCount);

		// 取得當前頁碼的據點 List，放入 table
		model.addAttribute(BASE_LIST, baseService.selectPagination(categoryBean.getCa_id(), page, pageRowCount));

		// 取得總頁數
		model.addAttribute(PAGE_COUNT,
				Pagination.getPageCount(baseService.selectCountByBa_Ca(categoryBean), pageRowCount));

		return ca_directory + LIST_PAGE;
	}

	/**
	 * 新增據點 - 初期處理
	 * 
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/ca_directory/add.jsp
	 */
	@RequestMapping(value = "/base*/add", method = RequestMethod.GET)
	public String addView(Model model) {

		String requestView = (String) request.getAttribute(REQUEST_VIEW);
		CategoryBean categoryBean = categoryService.selectByCa_directory(requestView);

		// 取得所有國家 List，放入 select
		model.addAttribute(COUNTRY_LIST, countryService.selectAll());

		// 新增 form backing object
		model.addAttribute(BASE_BEAN, new BaseBean());

		return categoryBean.getCa_directory() + ADD_PAGE;
	}

	/**
	 * 新增據點 - submit
	 * 
	 * @param baseBean
	 *            BaseBean --> form backing object
	 * @param bindingResult
	 *            BindingResult
	 * @return /WEB-INF/views/ca_directory/list.jsp
	 */
	@RequestMapping(value = "/base*/add.do", method = RequestMethod.POST)
	public String addAction(@Valid BaseBean baseBean, BindingResult bindingResult) {

		String requestAction = (String) request.getAttribute(REQUEST_ACTION);
		CategoryBean categoryBean = categoryService.selectByCa_directory(requestAction);
		String ca_name = categoryBean.getCa_name();
		String ca_directory = categoryBean.getCa_directory();

		if (bindingResult.hasErrors()) {

			logger.error(ca_name + "新增失敗: 資料未填");

			return ca_directory + ADD_PAGE;

		} else {

			baseBean.setBa_CategoryBean(categoryBean);

			baseService.insert(baseBean);

			logger.info(ca_name + "新增成功");

			return REDIRECT + ca_directory + LIST_PAGE + QUESTION + PAGE + EQUAL + "1";
		}
	}

	/**
	 * 編輯據點 - 初期處理
	 * 
	 * @param baseBean_ba_id
	 *            BaseBean --> form backing object --> GET --> ba_id
	 * @param page
	 *            String --> 當前頁碼
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/error/page-not-found.jsp
	 * @return /WEB-INF/views/ca_directory/edit.jsp
	 */
	@RequestMapping(value = "/base*/edit", method = RequestMethod.GET)
	public String editView(BaseBean baseBean_ba_id, @RequestParam String page, Model model) {

		currentPage = page;

		String requestView = (String) request.getAttribute(REQUEST_VIEW);
		CategoryBean categoryBean = categoryService.selectByCa_directory(requestView);

		BaseBean baseBean;
		try {
			// 取得選定據點 id 的 BaseBean
			baseBean = baseService.selectByBa_id(baseBean_ba_id.getBa_id());

			if (baseBean == null) {

				throw new PageNotFoundException(requestView);
			}
		} catch (PageNotFoundException e) {

			return ERROR_PAGE_NOT_FOUND_PAGE;

		} catch (IllegalArgumentException e) {

			logger.error("找不到這個頁面: " + requestView);

			return ERROR_PAGE_NOT_FOUND_PAGE;
		}

		// 取得所有國家 List，放入 select
		model.addAttribute(COUNTRY_LIST, countryService.selectAll());

		// 取得據點所在國家中的所有區域 List，放入 select
		model.addAttribute(STATE_LIST, stateService.selectBySt_co_id(baseBean.getBa_CountryBean().getCo_id()));

		// 取得據點所在區域中的所有城市 List，放入 select
		model.addAttribute(CITY_LIST, cityService.selectByCi_st_id(baseBean.getBa_StateBean().getSt_id()));

		// 使表單回填 BaseBean 內所有資料
		model.addAttribute(BASE_BEAN, baseBean);

		// 取得當前頁碼
		model.addAttribute(CURRENT_PAGE, page);

		return categoryBean.getCa_directory() + EDIT_PAGE;
	}

	/**
	 * 編輯據點 - submit
	 * 
	 * @param baseBean
	 *            BaseBean --> form backing object
	 * @param bindingResult
	 *            BindingResult
	 * @return /WEB-INF/views/ca_directory/list.jsp
	 */
	@RequestMapping(value = "/base*/edit.do", method = RequestMethod.POST)
	public String editAction(@Valid BaseBean baseBean, BindingResult bindingResult) {

		String requestAction = (String) request.getAttribute(REQUEST_ACTION);
		CategoryBean categoryBean = categoryService.selectByCa_directory(requestAction);
		String ca_name = categoryBean.getCa_name();
		String ca_directory = categoryBean.getCa_directory();

		if (bindingResult.hasErrors()) {

			logger.error(ca_name + "編輯失敗: 資料未填");

			return ca_directory + EDIT_PAGE + QUESTION + BASE_ID + EQUAL + baseBean.getBa_id() + AND + PAGE + EQUAL
					+ currentPage;

		} else {

			baseService.update(baseBean);

			logger.info(ca_name + "編輯成功");

			return REDIRECT + ca_directory + LIST_PAGE + QUESTION + PAGE + EQUAL + currentPage;
		}
	}

	/**
	 * 據點開關 (AJAX)
	 * 
	 * @param ba_id
	 *            String --> 據點流水號
	 * @return ba_name
	 */
	@RequestMapping(value = "/base/switch.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String switchAjax(String ba_id) {

		return baseService.updateBa_status(Integer.valueOf(ba_id)).getBa_name();
	}

}
