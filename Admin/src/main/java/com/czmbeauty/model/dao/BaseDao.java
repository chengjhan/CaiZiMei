/*
 * CaiZiMei
 * File: BaseDao.java
 * Author: 詹晟
 * Date: 2017/9/20
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import java.util.List;

import com.czmbeauty.common.constants.HqlConstants;
import com.czmbeauty.model.entity.BaseBean;
import com.czmbeauty.model.entity.CategoryBean;

/**
 * base DAO interface
 * 
 * @author 詹晟
 */
public interface BaseDao extends HqlConstants {

	/**
	 * @see com.czmbeauty.model.dao.impl.BaseDaoImpl#selectPagination(String,
	 *      int, int)
	 */
	List<BaseBean> selectPagination(String hql, int first, int max);

	/**
	 * @see com.czmbeauty.model.dao.impl.BaseDaoImpl#selectCountByBa_Ca(CategoryBean)
	 */
	int selectCountByBa_Ca(CategoryBean ba_CategoryBean);

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
