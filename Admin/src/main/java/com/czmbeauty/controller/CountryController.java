/*
 * CaiZiMei
 * File: CountryController.java
 * Author: 詹晟
 * Date: 2017/10/23
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.czmbeauty.common.constants.ControllerConstants;
import com.czmbeauty.common.editor.PrimitiveNumberEditor;
import com.czmbeauty.common.exception.PageNotFoundException;
import com.czmbeauty.model.entity.CountryBean;
import com.czmbeauty.model.service.CountryService;

/**
 * country controller
 * 
 * @author 詹晟
 */
@Controller
public class CountryController implements ControllerConstants {

	private static final Logger logger = Logger.getLogger(CountryController.class);

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
	 * 提供 form backing object 資料轉換
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Integer.class, new PrimitiveNumberEditor(Integer.class, true));
	}

	/**
	 * 國家一覽 - 初期處理
	 * 
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/country/list.jsp
	 */
	@RequestMapping(value = "/country/list", method = RequestMethod.GET)
	public String listView(Model model) {

		// 取得所有國家 List，放入 table
		model.addAttribute(COUNTRY_LIST, countryService.selectAll());

		return COUNTRY_LIST_PAGE;
	}

	/**
	 * 新增國家 - 初期處理
	 * 
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/country/add.jsp
	 */
	@RequestMapping(value = "/country/add", method = RequestMethod.GET)
	public String addView(Model model) {

		// 新增 form backing object
		model.addAttribute(COUNTRY_BEAN, new CountryBean());

		return COUNTRY_ADD_PAGE;
	}

	/**
	 * 新增國家 - submit
	 * 
	 * @param countryBean
	 *            CountryBean --> form backing object
	 * @param bindingResult
	 *            BindingResult
	 * @return /WEB-INF/views/country/list.jsp
	 */
	@RequestMapping(value = "/country/add.do", method = RequestMethod.POST)
	public String addAction(@Valid CountryBean countryBean, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			logger.error("國家新增失敗: 格式錯誤");

			return COUNTRY_ADD_PAGE;

		} else {

			countryService.insert(countryBean);

			logger.info("國家新增成功");

			return REDIRECT + COUNTRY_LIST_PAGE;
		}
	}

	/**
	 * 編輯國家 - 初期處理
	 * 
	 * @param countryBean_co_id
	 *            CountryBean --> form backing object --> GET --> co_id
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/error/page-not-found.jsp
	 * @return /WEB-INF/views/country/edit.jsp
	 */
	@RequestMapping(value = "/country/edit", method = RequestMethod.GET)
	public String editView(CountryBean countryBean_co_id, Model model) {

		String requestView = (String) request.getAttribute(REQUEST_VIEW);

		CountryBean countryBean;
		try {
			countryBean = countryService.selectByCo_id(countryBean_co_id.getCo_id());

			if (countryBean == null) {

				throw new PageNotFoundException(requestView);
			}
		} catch (PageNotFoundException e) {

			return ERROR_PAGE_NOT_FOUND_PAGE;

		} catch (IllegalArgumentException e) {

			logger.error("找不到這個頁面: " + requestView);

			return ERROR_PAGE_NOT_FOUND_PAGE;
		}

		// 取得選定國家 id 的 CountryBean，使表單回填 CountryBean 內所有資料
		model.addAttribute(COUNTRY_BEAN, countryBean);

		return COUNTRY_EDIT_PAGE;
	}

	/**
	 * 編輯國家 - submit
	 * 
	 * @param countryBean
	 *            CountryBean --> form backing object
	 * @param bindingResult
	 *            BindingResult
	 * @return /WEB-INF/views/country/list.jsp
	 */
	@RequestMapping(value = "/country/edit.do", method = RequestMethod.POST)
	public String editAction(@Valid CountryBean countryBean, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			logger.error("國家編輯失敗: 格式錯誤");

			return COUNTRY_EDIT_PAGE;

		} else {

			countryService.update(countryBean);

			logger.info("國家編輯成功");

			return REDIRECT + COUNTRY_LIST_PAGE;
		}
	}

	/**
	 * 國家開關 (AJAX)
	 * 
	 * @param co_id
	 *            String --> 國家流水號
	 * @return co_name
	 */
	@RequestMapping(value = "/country/switch.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String switchAjax(String co_id) {

		return countryService.updateCo_status(Integer.valueOf(co_id)).getCo_name();
	}

}
