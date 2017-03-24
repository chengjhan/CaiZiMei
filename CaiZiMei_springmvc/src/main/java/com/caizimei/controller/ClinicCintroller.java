/*
 * CaiZiMei
 * File: ClinicCintroller.java
 * Author: 詹晟
 * Date: 2017/3/24
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.caizimei.model.entity.ClinicBean;
import com.caizimei.model.service.CityService;
import com.caizimei.model.service.ClinicService;

/**
 * clinic controller
 * 
 * @author 詹晟
 */
@Controller
@RequestMapping("/clinic")
@SessionAttributes("clinicList")
public class ClinicCintroller {

	/**
	 * 注入 ClinicService
	 */
	@Autowired
	private ClinicService clinicService;

	/**
	 * 注入 CityService
	 */
	@Autowired
	private CityService cityService;

	/**
	 * clinic/list 視圖解析
	 * 
	 * @return /WEB-INF/views/clinic/list.jsp
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		return new ModelAndView("clinic/list");
	}

	/**
	 * clinic/update 視圖解析
	 * 
	 * @return /WEB-INF/views/clinic/update.jsp
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update() {
		return new ModelAndView("clinic/update");
	}

	/**
	 * clinic/search 視圖解析
	 * 
	 * @return /WEB-INF/views/clinic/search.jsp
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search() {
		return new ModelAndView("clinic/search");
	}

	/**
	 * 搜尋全部診所
	 * 
	 * @param model-->Model
	 * @return /WEB-INF/views/clinic/list.jsp
	 */
	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public ModelAndView selectProcess(Model model) {

		model.addAttribute("clinicList", clinicService.select());

		return new ModelAndView("clinic/list");
	}

	/**
	 * 新增診所
	 * 
	 * @param clinicBean-->ClinicBean
	 * @param c_telephone_front-->診所電話(前碼)
	 * @param c_telephone_back-->診所電話(後碼)
	 * @param ci_name-->城市名
	 * @param model-->Model
	 * @return /WEB-INF/views/clinic/list.jsp
	 */
	@RequestMapping(path = "/insert.do", method = RequestMethod.POST)
	public ModelAndView insertProcess(ClinicBean clinicBean,
			@RequestParam(name = "c_telephone_front") String c_telephone_front,
			@RequestParam(name = "c_telephone_back") String c_telephone_back,
			@RequestParam(name = "ci_name") String ci_name, Model model) {

		clinicBean.setC_telephone(c_telephone_front + "-" + c_telephone_back);
		clinicBean.setC_CityBean(cityService.selectByCi_name(ci_name));
		Double[] LatLng = new Double[2];
		try {
			LatLng = clinicService.addressToLatLng(clinicBean.getC_address());
		} catch (Exception e) {
			e.printStackTrace();
		}
		clinicBean.setC_latitude(LatLng[0]);
		clinicBean.setC_longitude(LatLng[1]);
		clinicService.insert(clinicBean);
		model.addAttribute("clinicList", clinicService.select());

		return new ModelAndView("redirect:/clinic/list");
	}

	/**
	 * 修改診所資訊
	 * 
	 * @param c_id-->診所流水號
	 * @param c_name-->診所名
	 * @param c_eng_name-->診所英文名
	 * @param c_telephone-->診所電話
	 * @param ci_name-->城市名
	 * @param c_address-->診所地址
	 * @param c_url-->診所網址
	 * @param model-->Model
	 * @return /WEB-INF/views/clinic/insert.jsp
	 */
	@RequestMapping(path = "/update.do", method = RequestMethod.POST)
	public ModelAndView updateProcess(@RequestParam(name = "c_id") String c_id,
			@RequestParam(name = "c_name") String c_name, @RequestParam(name = "c_eng_name") String c_eng_name,
			@RequestParam(name = "c_telephone") String c_telephone, @RequestParam(name = "ci_name") String ci_name,
			@RequestParam(name = "c_address") String c_address, @RequestParam(name = "c_url") String c_url,
			Model model) {

		ClinicBean clinicBean = new ClinicBean();
		clinicBean.setC_id(Integer.parseInt(c_id));
		clinicBean.setC_name(c_name);
		clinicBean.setC_eng_name(c_eng_name);
		clinicBean.setC_telephone(c_telephone);
		clinicBean.setC_CityBean(cityService.selectByCi_name(ci_name));
		clinicBean.setC_address(c_address);
		Double[] LatLng = new Double[2];
		try {
			LatLng = clinicService.addressToLatLng(c_address);
		} catch (Exception e) {
			e.printStackTrace();
		}
		clinicBean.setC_latitude(LatLng[0]);
		clinicBean.setC_longitude(LatLng[1]);
		clinicBean.setC_url(c_url);
		clinicService.update(clinicBean);
		model.addAttribute("clinicList", clinicService.select());

		return new ModelAndView("redirect:/clinic/list");
	}

	/**
	 * 刪除診所
	 * 
	 * @param c_id-->診所流水號
	 * @param model-->Model
	 * @return /WEB-INF/views/clinic/list.jsp
	 */
	@RequestMapping(path = "/delete.do", method = RequestMethod.GET)
	public ModelAndView deleteProcess(@RequestParam(name = "c_id") String c_id, Model model) {

		clinicService.delete(Integer.parseInt(c_id));
		model.addAttribute("clinicList", clinicService.select());

		return new ModelAndView("redirect:/clinic/list");
	}

	/**
	 * 條件查詢
	 * 
	 * @param clinicBean-->ClinicBean
	 * @return /WEB-INF/views/clinic/search.jsp
	 */
	@RequestMapping(path = "/search.do", method = RequestMethod.GET)
	public ModelAndView selectByConditionsProcess(ClinicBean clinicBean, Model model) {

		model.addAttribute("selectByConditions",
				clinicService.selectByConditions(clinicBean.getC_name().trim(), clinicBean.getC_telephone().trim()));

		return new ModelAndView("clinic/search");
	}

}
