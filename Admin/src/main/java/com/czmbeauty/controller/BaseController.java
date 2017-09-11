/*
 * CaiZiMei
 * File: BaseController.java
 * Author: 詹晟
 * Date: 2017/9/11
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import static com.czmbeauty.common.constants.CommonConstants.AND;
import static com.czmbeauty.common.constants.CommonConstants.EQUAL;
import static com.czmbeauty.common.constants.CommonConstants.QUESTION;
import static com.czmbeauty.common.constants.ModelAttributeConstants.BASE_BEAN;
import static com.czmbeauty.common.constants.ModelAttributeConstants.BASE_LIST;
import static com.czmbeauty.common.constants.ModelAttributeConstants.CITY_LIST;
import static com.czmbeauty.common.constants.ModelAttributeConstants.COUNTRY_LIST;
import static com.czmbeauty.common.constants.ModelAttributeConstants.STATE_LIST;
import static com.czmbeauty.common.constants.PageNameConstants.ADD_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.EDIT_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.LIST_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.REDIRECT;
import static com.czmbeauty.common.constants.PaginationConstants.BASE_PAGE_ROW_COUNT;
import static com.czmbeauty.common.constants.PaginationConstants.CURRENT_PAGE;
import static com.czmbeauty.common.constants.PaginationConstants.PAGE_COUNT;
import static com.czmbeauty.common.constants.PaginationConstants.PAGE_ROW_COUNT;
import static com.czmbeauty.common.constants.ParameterConstants.BASE_ID;
import static com.czmbeauty.common.constants.ParameterConstants.PAGE;

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

import com.czmbeauty.common.editor.CityBeanPropertyEditor;
import com.czmbeauty.common.editor.CountryBeanPropertyEditor;
import com.czmbeauty.common.editor.PrimitiveNumberEditor;
import com.czmbeauty.common.editor.StateBeanPropertyEditor;
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
public class BaseController {

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
	 * 取得總頁數
	 * 
	 * @param ba_CategoryBean
	 *            CategoryBean
	 * @return int
	 */
	private int getPageCount(CategoryBean ba_CategoryBean) {
		int totalRowCount = baseService.selectCountByBa_Ca(ba_CategoryBean);
		int pageCount = 0;
		if (totalRowCount % BASE_PAGE_ROW_COUNT == 0) {
			pageCount = totalRowCount / BASE_PAGE_ROW_COUNT;
		} else {
			pageCount = totalRowCount / BASE_PAGE_ROW_COUNT + 1;
		}
		return pageCount;
	}

	/**
	 * 新增據點
	 * 
	 * @param baseBean
	 *            BaseBean --> form backing object
	 * @param bindingResult
	 *            BindingResult
	 * @return /WEB-INF/views/ca_directory/add.jsp
	 * @return /WEB-INF/views/ca_directory/list.jsp
	 */
	private String add(BaseBean baseBean, BindingResult bindingResult) {

		String ca_directory = request.getServletPath().split("/")[1];
		CategoryBean categoryBean = categoryService.selectByCa_directory(ca_directory);
		String ca_name = categoryBean.getCa_name();

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
	 * 編輯據點資訊
	 * 
	 * @param baseBean
	 *            BaseBean --> form backing object
	 * @param bindingResult
	 *            BindingResult
	 * @return /WEB-INF/views/ca_directory/edit.jsp
	 * @return /WEB-INF/views/ca_directory/list.jsp
	 */
	private String edit(BaseBean baseBean, BindingResult bindingResult) {

		String ca_directory = request.getServletPath().split("/")[1];
		String ca_name = categoryService.selectByCa_directory(ca_directory).getCa_name();

		if (bindingResult.hasErrors()) {

			logger.error(ca_name + "編輯失敗: 資料未填");

			return REDIRECT + ca_directory + EDIT_PAGE + QUESTION + BASE_ID + EQUAL + baseBean.getBa_id() + AND + PAGE
					+ EQUAL + currentPage;

		} else {

			baseService.update(baseBean);

			logger.info(ca_name + "編輯成功");

			return REDIRECT + ca_directory + LIST_PAGE + QUESTION + PAGE + EQUAL + currentPage;
		}
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
	@RequestMapping(value = "/*/list", method = RequestMethod.GET)
	public String baseListView(@RequestParam Integer page, Model model) {

		String ca_directory = request.getServletPath().split("/")[1];
		CategoryBean categoryBean = categoryService.selectByCa_directory(ca_directory);

		String hql = "from BaseBean where ba_ca_id=" + categoryBean.getCa_id() + " order by ba_status desc, ba_id asc";

		// 取得當前頁碼
		model.addAttribute(CURRENT_PAGE, page);

		// 取得每頁最大筆數
		model.addAttribute(PAGE_ROW_COUNT, BASE_PAGE_ROW_COUNT);

		// 取得當頁起始筆數
		int first = (page - 1) * BASE_PAGE_ROW_COUNT;

		// 取得當前頁碼的據點 List，放入 table
		model.addAttribute(BASE_LIST, baseService.selectPagination(hql, first, BASE_PAGE_ROW_COUNT));

		// 取得總頁數
		model.addAttribute(PAGE_COUNT, getPageCount(categoryBean));

		logger.info("進入" + categoryBean.getCa_name() + "一覽頁面: " + ca_directory + LIST_PAGE);

		return ca_directory + LIST_PAGE;
	}

	/**
	 * 新增據點 - 初期處理
	 * 
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/ca_directory/add.jsp
	 */
	@RequestMapping(value = "/*/add", method = RequestMethod.GET)
	public String officeAddView(Model model) {

		String ca_directory = request.getServletPath().split("/")[1];
		String ca_name = categoryService.selectByCa_directory(ca_directory).getCa_name();

		// 取得所有國家 List，放入 select
		model.addAttribute(COUNTRY_LIST, countryService.selectAll());

		// 新增 form backing object
		model.addAttribute(BASE_BEAN, new BaseBean());

		logger.info("進入新增" + ca_name + "頁面: " + ca_directory + ADD_PAGE);

		return ca_directory + ADD_PAGE;
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
	@RequestMapping(value = "/*/add.do", method = RequestMethod.POST)
	public String officeAddProcess(@Valid BaseBean baseBean, BindingResult bindingResult) {

		return add(baseBean, bindingResult);
	}

	/**
	 * 編輯據點資訊 - 初期處理
	 * 
	 * @param baseBean_ba_id
	 *            BaseBean --> form backing object --> GET --> ba_id
	 * @param page
	 *            String --> 當前頁碼
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/ca_directory/edit.jsp
	 */
	@RequestMapping(value = "/*/edit", method = RequestMethod.GET)
	public String clinicEditView(BaseBean baseBean_ba_id, @RequestParam String page, Model model) {

		currentPage = page;

		String ca_directory = request.getServletPath().split("/")[1];
		String ca_name = categoryService.selectByCa_directory(ca_directory).getCa_name();

		// 取得選定診所 id 的 BaseBean
		BaseBean baseBean = baseService.selectByBa_id(baseBean_ba_id.getBa_id());

		// 取得所有國家 List，放入 select
		model.addAttribute(COUNTRY_LIST, countryService.selectAll());

		// 取得診所所在國家中的所有區域 List，放入 select
		model.addAttribute(STATE_LIST, stateService.selectBySt_co_id(baseBean.getBa_CountryBean().getCo_id()));

		// 取得診所所在區域中的所有城市 List，放入 select
		model.addAttribute(CITY_LIST, cityService.selectByCi_st_id(baseBean.getBa_StateBean().getSt_id()));

		// 使表單回填 BaseBean 內所有資料
		model.addAttribute(BASE_BEAN, baseBean);

		logger.info("進入編輯" + ca_name + "資訊頁面: " + ca_directory + EDIT_PAGE);

		return ca_directory + EDIT_PAGE;
	}

	/**
	 * 編輯據點資訊 - submit
	 * 
	 * @param baseBean
	 *            BaseBean --> form backing object
	 * @param bindingResult
	 *            BindingResult
	 * @return /WEB-INF/views/ca_directory/list.jsp
	 */
	@RequestMapping(value = "/*/edit.do", method = RequestMethod.POST)
	public String officeEditProcess(@Valid BaseBean baseBean, BindingResult bindingResult) {

		return edit(baseBean, bindingResult);
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
	public String switchAjaxProcess(String ba_id) {

		return baseService.updateBa_status(Integer.valueOf(ba_id)).getBa_name();
	}

}
