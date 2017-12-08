/*
 * CaiZiMei
 * File: BaseDao.java
 * Author: 詹晟
 * Date: 2017/12/8
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import java.util.Map;

import com.czmbeauty.common.constants.HqlConstants;
import com.czmbeauty.model.entity.BaseBean;

/**
 * base DAO interface
 * 
 * @author 詹晟
 */
public interface BaseDao extends HqlConstants {

	/**
	 * @see com.czmbeauty.model.dao.impl.BaseDaoImpl#selectPagination(Integer, int,
	 *      int)
	 */
	Map<String, Object> selectPagination(Integer ba_ca_id, int first, int max);

	/**
	 * @see com.czmbeauty.model.dao.impl.BaseDaoImpl#selectByBa_id(Integer)
	 */
	BaseBean selectByBa_id(Integer ba_id);

	/**
	 * @see com.czmbeauty.model.dao.impl.BaseDaoImpl#insert(BaseBean)
	 */
	BaseBean insert(BaseBean baseBean);

	/**
	 * @see com.czmbeauty.model.dao.impl.BaseDaoImpl#update(BaseBean)
	 */
	BaseBean update(BaseBean baseBean);

}
