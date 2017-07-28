/*
 * CaiZiMei
 * File: ClinicDao.java
 * Author: 詹晟
 * Date: 2017/7/28
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import java.util.List;

import com.czmbeauty.model.entity.ClinicBean;

/**
 * clinic DAO interface
 * 
 * @author 詹晟
 */
public interface ClinicDao {

	/**
	 * @see com.czmbeauty.model.dao.impl.ClinicDaoImpl#selectAll()
	 */
	List<ClinicBean> selectAll();

	/**
	 * @see com.czmbeauty.model.dao.impl.ClinicDaoImpl#selectByCl_id(Integer)
	 */
	ClinicBean selectByCl_id(Integer cl_id);

	/**
	 * @see com.czmbeauty.model.dao.impl.ClinicDaoImpl#selectByCl_status()
	 */
	List<ClinicBean> selectByCl_status();

	/**
	 * @see com.czmbeauty.model.dao.impl.ClinicDaoImpl#insert(ClinicBean)
	 */
	ClinicBean insert(ClinicBean clinicBean);

	/**
	 * @see com.czmbeauty.model.dao.impl.ClinicDaoImpl#update(ClinicBean)
	 */
	ClinicBean update(ClinicBean clinicBean);

}
