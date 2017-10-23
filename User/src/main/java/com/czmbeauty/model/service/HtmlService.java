/*
 * CaiZiMei/User
 * File: HtmlService.java
 * Author: 詹晟
 * Date: 2017/10/24
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service;

import java.util.List;

import com.czmbeauty.model.entity.HtmlBean;

/**
 * html service interface
 * 
 * @author 詹晟
 */
public interface HtmlService {

	/**
	 * @see com.czmbeauty.model.service.impl.HtmlServiceImpl#selectOpenHtml(String)
	 */
	List<HtmlBean> selectOpenHtml(String ca_directory);

}
