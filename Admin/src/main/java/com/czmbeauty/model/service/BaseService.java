/*
 * CaiZiMei
 * File: BaseService.java
 * Author: 詹晟
 * Date: 2017/12/8
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service;

import java.util.Map;

import com.czmbeauty.model.entity.BaseBean;

/**
 * base service interface
 * 
 * @author 詹晟
 */
public interface BaseService {

	/**
	 * @see com.czmbeauty.model.service.impl.BaseServiceImpl#selectPagination(Integer,
	 *      Integer, int)
	 */
	Map<String, Object> selectPagination(Integer ba_ca_id, Integer page, int max);

	/**
	 * @see com.czmbeauty.model.service.impl.BaseServiceImpl#selectByBa_id(Integer)
	 */
	BaseBean selectByBa_id(Integer ba_id) throws IllegalArgumentException;

	/**
	 * @see com.czmbeauty.model.service.impl.BaseServiceImpl#insert(BaseBean)
	 */
	BaseBean insert(BaseBean baseBean) throws Exception;

	/**
	 * @see com.czmbeauty.model.service.impl.BaseServiceImpl#update(BaseBean)
	 */
	BaseBean update(BaseBean baseBean) throws Exception;

	/**
	 * @see com.czmbeauty.model.service.impl.BaseServiceImpl#updateBa_status(Integer)
	 */
	BaseBean updateBa_status(Integer ba_id);

}
