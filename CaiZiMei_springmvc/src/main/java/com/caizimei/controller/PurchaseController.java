/*
 * CaiZiMei
 * File: PurchaseController.java
 * Author: 詹晟
 * Date: 2017/3/26
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caizimei.model.service.PurchaseService;

/**
 * purchase controller
 * 
 * @author 詹晟
 */
@Controller
//@RequestMapping("/purchase")
public class PurchaseController {

	/**
	 * 注入 PurchaseService
	 */
	@Autowired
	private PurchaseService purchaseService;

}
