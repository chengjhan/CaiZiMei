/*
 * CaiZiMei
 * File: PurchaseController.java
 * Author: 詹晟
 * Date: 2017/4/6
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

import com.caizimei.model.entity.MemberBean;
import com.caizimei.model.entity.PurchaseBean;
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
	 * 訂購
	 * 
	 * @param user-->Session
	 * @param p_c_id-->診所流水號
	 * @return /WEB-INF/views/index.jsp
	 */
	@RequestMapping(path = "/order.do", method = RequestMethod.POST)
	public String orderProcess(@ModelAttribute("user") MemberBean user, @RequestParam(name = "p_c_id") String p_c_id) {

		PurchaseBean purchaseBean = new PurchaseBean();
		purchaseBean.setP_m_id(user.getM_id());
		purchaseBean.setP_c_id(Integer.valueOf(p_c_id));
		purchaseService.order(purchaseBean);

		return "redirect:/index";
	}

}
