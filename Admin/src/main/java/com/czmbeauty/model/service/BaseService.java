/*
 * CaiZiMei
 * File: BaseService.java
 * Author: 詹晟
 * Date: 2017/11/23
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service;

import java.util.List;

import com.czmbeauty.model.entity.BaseBean;
import com.czmbeauty.model.entity.CategoryBean;

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
	List<BaseBean> selectPagination(Integer ba_ca_id, Integer page, int max);

	/**
	 * @see com.czmbeauty.model.service.impl.BaseServiceImpl#selectCountByBa_Ca(CategoryBean)
	 */
	int selectCountByBa_Ca(CategoryBean ba_CategoryBean);

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
