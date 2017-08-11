/*
 * CaiZiMei
 * File: CityDao.java
 * Author: 詹晟
 * Date: 2017/8/12
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import java.util.List;

import com.czmbeauty.model.entity.CityBean;

/**
 * city DAO interface
 * 
 * @author 詹晟
 */
public interface CityDao {

	/**
	 * @see com.czmbeauty.model.dao.impl.CityDaoImpl#selectByCi_id(Integer)
	 */
	CityBean selectByCi_id(Integer ci_id);

	/**
	 * @see com.czmbeauty.model.dao.impl.CityDaoImpl#selectByCi_st_id(Integer)
	 */
	List<CityBean> selectByCi_st_id(Integer ci_st_id);

	/**
	 * @see com.czmbeauty.model.dao.impl.CityDaoImpl#insert(CityBean)
	 */
	CityBean insert(CityBean cityBean);

	/**
	 * @see com.czmbeauty.model.dao.impl.CityDaoImpl#update(CityBean)
	 */
	CityBean update(CityBean cityBean);

}
