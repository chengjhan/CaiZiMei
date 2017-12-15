/*
 * CaiZiMei
 * File: HtmlService.java
 * Author: 詹晟
 * Date: 2017/12/15
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service;

import java.util.Map;

import com.czmbeauty.model.entity.HtmlBean;

/**
 * html service interface
 * 
 * @author 詹晟
 */
public interface HtmlService {

	/**
	 * @see com.czmbeauty.model.service.impl.HtmlServiceImpl#selectPagination(Integer,
	 *      Integer, int)
	 */
	Map<String, Object> selectPagination(Integer ht_ca_id, Integer page, int max);

	/**
	 * @see com.czmbeauty.model.service.impl.HtmlServiceImpl#selectByHt_id(Integer)
	 */
	HtmlBean selectByHt_id(Integer ht_id) throws IllegalArgumentException;

	/**
	 * @see com.czmbeauty.model.service.impl.HtmlServiceImpl#insert(HtmlBean)
	 */
	HtmlBean insert(HtmlBean htmlBean);

	/**
	 * @see com.czmbeauty.model.service.impl.HtmlServiceImpl#update(HtmlBean)
	 */
	HtmlBean update(HtmlBean htmlBean);

	/**
	 * @see com.czmbeauty.model.service.impl.HtmlServiceImpl#updateHt_status(Integer)
	 */
	HtmlBean updateHt_status(Integer ht_id);

}
