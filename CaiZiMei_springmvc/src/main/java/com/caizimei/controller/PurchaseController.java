/*
 * CaiZiMei
 * File: PurchaseController.java
 * Author: 詹晟
 * Date: 2017/3/28
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.caizimei.model.entity.MemberBean;
import com.caizimei.model.entity.PurchaseBean;
import com.caizimei.model.service.ClinicService;
import com.caizimei.model.service.PurchaseService;

/**
 * purchase controller
 * 
 * @author 詹晟
 */
@Controller
@RequestMapping("/purchase")
@SessionAttributes("user")
public class PurchaseController {

	/**
	 * 注入 PurchaseService
	 */
	@Autowired
	private PurchaseService purchaseService;

	/**
	 * 注入 ClinicService
	 */
	@Autowired
	private ClinicService clinicService;

	/**
	 * 訂購
	 * 
	 * @param user-->Session
	 * @param c_name-->診所名
	 * @return /WEB-INF/views/index.jsp
	 */
	@RequestMapping(path = "/order.do", method = RequestMethod.POST)
	public ModelAndView orderProcess(@ModelAttribute("user") MemberBean user,
			@RequestParam(name = "c_name") String c_name) {

		PurchaseBean purchaseBean = new PurchaseBean();
		purchaseBean.setP_m_id(user.getM_id());
		purchaseBean.setP_c_id(clinicService.selectByC_name(c_name).getC_id());
		purchaseService.order(purchaseBean);

		return new ModelAndView("redirect:/index");
	}

}
