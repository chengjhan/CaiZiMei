/*
 * CaiZiMei
 * File: BaseDao.java
 * Author: 詹晟
 * Date: 2017/8/8
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import java.util.List;

import com.czmbeauty.model.entity.BaseBean;

/**
 * base DAO interface
 * 
 * @author 詹晟
 */
public interface BaseDao {

	/**
	 * @see com.czmbeauty.model.dao.impl.BaseDaoImpl#selectAllOffice()
	 */
	List<BaseBean> selectAllOffice();

	/**
	 * @see com.czmbeauty.model.dao.impl.BaseDaoImpl#selectAllFranchisee()
	 */
	List<BaseBean> selectAllFranchisee();

	/**
	 * @see com.czmbeauty.model.dao.impl.BaseDaoImpl#selectAllClinic()
	 */
	List<BaseBean> selectAllClinic();

	/**
	 * @see com.czmbeauty.model.dao.impl.BaseDaoImpl#selectAllBasePagination(String,
	 *      int, int)
	 */
	List<BaseBean> selectAllBasePagination(String hql, int first, int max);

	/**
	 * @see com.czmbeauty.model.dao.impl.BaseDaoImpl#count()
	 */
	Integer count();

	/**
	 * @see com.czmbeauty.model.dao.impl.BaseDaoImpl#selectOpenClinic()
	 */
	List<BaseBean> selectOpenClinic();

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
