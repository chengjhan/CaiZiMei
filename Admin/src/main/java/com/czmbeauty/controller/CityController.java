/*
 * CaiZiMei
 * File: CityController.java
 * Author: 詹晟
 * Date: 2017/9/19
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.controller;

import static com.czmbeauty.common.constants.ModelAttributeConstants.CITY_BEAN;
import static com.czmbeauty.common.constants.ModelAttributeConstants.CITY_LIST;
import static com.czmbeauty.common.constants.ModelAttributeConstants.COUNTRY_LIST;
import static com.czmbeauty.common.constants.ModelAttributeConstants.STATE_LIST;
import static com.czmbeauty.common.constants.PageNameConstants.CITY_ADD_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.CITY_EDIT_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.CITY_LIST_PAGE;
import static com.czmbeauty.common.constants.PageNameConstants.REDIRECT;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.czmbeauty.common.editor.CountryBeanPropertyEditor;
import com.czmbeauty.common.editor.PrimitiveNumberEditor;
import com.czmbeauty.common.editor.StateBeanPropertyEditor;
import com.czmbeauty.model.entity.CityBean;
import com.czmbeauty.model.entity.CountryBean;
import com.czmbeauty.model.entity.StateBean;
import com.czmbeauty.model.service.CityService;
import com.czmbeauty.model.service.CountryService;
import com.czmbeauty.model.service.StateService;
import com.google.gson.Gson;

/**
 * city controller
 * 
 * @author 詹晟
 */
@Controller
@SessionAttributes(value = CITY_BEAN)
public class CityController {

	private static final Logger logger = Logger.getLogger(CityController.class);

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
	 * 提供 form backing object 資料轉換
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Integer.class, new PrimitiveNumberEditor(Integer.class, true));
		webDataBinder.registerCustomEditor(CountryBean.class, new CountryBeanPropertyEditor());
		webDataBinder.registerCustomEditor(StateBean.class, new StateBeanPropertyEditor());
	}

	/**
	 * 城市一覽 - 初期處理
	 * 
	 * @param cityBean
	 *            CityBean --> Session
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/city/list.jsp
	 */
	@RequestMapping(value = "/city/list", method = RequestMethod.GET)
	public String listView(CityBean cityBean, Model model) {

		// 取得所有國家 List，放入 select
		model.addAttribute(COUNTRY_LIST, countryService.selectAll());

		// 如果 Session 不為空
		if (cityBean.getCi_id() != null) {

			// 取得城市所在國家中的所有區域 List，放入 select
			model.addAttribute(STATE_LIST, stateService.selectBySt_co_id(cityBean.getCi_CountryBean().getCo_id()));

			CityBean persistentCityBean = cityService.selectByCi_id(cityBean.getCi_id());

			if (persistentCityBean != null) {

				// 若為編輯，取得編輯的 CityBean，放入 Session，使 select 回填國家及區域
				model.addAttribute(CITY_BEAN, persistentCityBean);

			} else {

				// 若為刪除，取得刪除的 CityBean 中的 CountryBean 及 StateBean，放入空 CityBean
				CountryBean deletedCi_CountryBean = new CountryBean();
				StateBean deletedCi_StateBean = new StateBean();
				CityBean deletedCityBean = new CityBean();
				deletedCi_CountryBean = countryService.selectByCo_id(cityBean.getCi_CountryBean().getCo_id());
				deletedCi_StateBean = stateService.selectBySt_id(cityBean.getCi_StateBean().getSt_id());
				deletedCityBean.setCi_CountryBean(deletedCi_CountryBean);
				deletedCityBean.setCi_StateBean(deletedCi_StateBean);

				// 放入 Session，使 select 回填國家及區域
				model.addAttribute(CITY_BEAN, deletedCityBean);
			}

			// 取得編輯或刪除的 CityBean 的區域，並取得此區域中的所有城市 List，放入 table
			model.addAttribute(CITY_LIST, cityService.selectByCi_st_id(cityBean.getCi_StateBean().getSt_id()));
		}

		logger.info("進入城市一覽頁面: " + CITY_LIST_PAGE);

		return CITY_LIST_PAGE;
	}

	/**
	 * 新增城市 - 初期處理
	 * 
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/city/add.jsp
	 */
	@RequestMapping(value = "/city/add", method = RequestMethod.GET)
	public String addView(Model model) {

		// 取得所有國家 List，放入 select
		model.addAttribute(COUNTRY_LIST, countryService.selectAll());

		// 新增 form backing object
		model.addAttribute(CITY_BEAN, new CityBean());

		logger.info("進入新增城市頁面: " + CITY_ADD_PAGE);

		return CITY_ADD_PAGE;
	}

	/**
	 * 新增城市 - submit
	 * 
	 * @param cityBean
	 *            CityBean --> form backing object
	 * @param bindingResult
	 *            BindingResult
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/city/list.jsp
	 */
	@RequestMapping(value = "/city/add.do", method = RequestMethod.POST)
	public String addProcess(@Valid CityBean cityBean, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {

			// 取得所有國家 List，放入 select
			model.addAttribute(COUNTRY_LIST, countryService.selectAll());

			logger.error("城市新增失敗: 格式錯誤");

			return CITY_ADD_PAGE;

		} else {

			cityService.insert(cityBean);

			// 將新增的 CityBean 放入 Session，使 select 回填國家及區域
			model.addAttribute(CITY_BEAN, cityBean);

			logger.info("城市新增成功");

			return REDIRECT + CITY_LIST_PAGE;
		}
	}

	/**
	 * 編輯城市資訊 - 初期處理
	 * 
	 * @param cityBean_ci_id
	 *            CityBean --> form backing object --> GET --> ci_id
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/city/edit.jsp
	 */
	@RequestMapping(value = "/city/edit", method = RequestMethod.GET)
	public String editView(CityBean cityBean_ci_id, Model model) {

		// 取得選定城市 id 的 CityBean
		CityBean cityBean = cityService.selectByCi_id(cityBean_ci_id.getCi_id());

		// 取得所有國家 List，放入 select
		model.addAttribute(COUNTRY_LIST, countryService.selectAll());

		// 取得城市所在國家中的所有區域 List，放入 select
		model.addAttribute(STATE_LIST, stateService.selectBySt_co_id(cityBean.getCi_CountryBean().getCo_id()));

		// 放入 Session，使表單回填 CityBean 內所有資料
		model.addAttribute(CITY_BEAN, cityBean);

		logger.info("進入編輯城市資訊頁面: " + CITY_EDIT_PAGE);

		return CITY_EDIT_PAGE;
	}

	/**
	 * 編輯城市資訊 - submit
	 * 
	 * @param cityBean
	 *            CityBean --> form backing object
	 * @param bindingResult
	 *            BindingResult
	 * @param model
	 *            Model
	 * @return /WEB-INF/views/city/list.jsp
	 */
	@RequestMapping(value = "/city/edit.do", method = RequestMethod.POST)
	public String editProcess(@Valid CityBean cityBean, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {

			// 取得所有國家 List，放入 select
			model.addAttribute(COUNTRY_LIST, countryService.selectAll());

			// 取得城市所在國家中的所有區域 List，放入 select
			model.addAttribute(STATE_LIST, stateService.selectBySt_co_id(cityBean.getCi_CountryBean().getCo_id()));

			// 取得選定城市 id 的 CityBean，放入 Session，使表單回填 CityBean 內所有資料
			model.addAttribute(CITY_BEAN, cityService.selectByCi_id(cityBean.getCi_id()));

			logger.error("城市資訊編輯失敗: 格式錯誤");

			return CITY_EDIT_PAGE;

		} else {

			cityService.update(cityBean);

			// 將編輯的 CityBean 放入 Session，使 select 回填國家及區域
			model.addAttribute(CITY_BEAN, cityBean);

			logger.info("城市資訊編輯成功");

			return REDIRECT + CITY_LIST_PAGE;
		}
	}

	/**
	 * 選定區域中的所有城市 (AJAX)
	 * 
	 * @param ci_st_id
	 *            Integer --> 區域流水號
	 * @return city JSON
	 */
	@RequestMapping(value = "/city/choice-state-city-list.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String choiceStateCityListAjaxProcess(Integer ci_st_id) {

		List<CityBean> list = cityService.selectByCi_st_id(ci_st_id);

		List<CityBean> jsonList = new ArrayList<CityBean>();
		for (CityBean bean : list) {
			CityBean jsonBean = new CityBean();
			jsonBean.setCi_id(bean.getCi_id());
			jsonBean.setCi_name(bean.getCi_name());
			jsonBean.setCi_rank(bean.getCi_rank());
			jsonBean.setCi_status(bean.getCi_status());
			jsonList.add(jsonBean);
		}

		String json = new Gson().toJson(jsonList);

		logger.info("JSON = " + json);

		return json;
	}

	/**
	 * 城市開關 (AJAX)
	 * 
	 * @param ci_id
	 *            String --> 城市流水號
	 * @return ci_name
	 */
	@RequestMapping(value = "/city/switch.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String switchAjaxProcess(String ci_id) {

		return cityService.updateCi_status(Integer.valueOf(ci_id)).getCi_name();
	}

}
