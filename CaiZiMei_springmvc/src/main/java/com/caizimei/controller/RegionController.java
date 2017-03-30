/*
 * CaiZiMei
 * File: RegionController.java
 * Author: 詹晟
 * Date: 2017/3/30
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.caizimei.model.service.RegionService;

/**
 * region controller
 * 
 * @author 詹晟
 */
@Controller
@RequestMapping("/admin/region")
@SessionAttributes("regionList")
public class RegionController {
	
	/**
	 * 注入 RegionService
	 */
	@Autowired
	private RegionService regionService;

}
