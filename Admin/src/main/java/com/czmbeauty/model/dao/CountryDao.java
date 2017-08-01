/*
 * CaiZiMei
 * File: CountryDao.java
 * Author: 詹晟
 * Date: 2017/8/1
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import java.util.List;

import com.czmbeauty.model.entity.CountryBean;

/**
 * country DAO interface
 * 
 * @author 詹晟
 */
public interface CountryDao {

	/**
	 * @see com.czmbeauty.model.dao.impl.CountryDaoImpl#selectAll()
	 */
	List<CountryBean> selectAll();

	/**
	 * @see com.czmbeauty.model.dao.impl.CountryDaoImpl#selectByCo_id(Integer)
	 */
	CountryBean selectByCo_id(Integer co_id);

	/**
	 * @see com.czmbeauty.model.dao.impl.CountryDaoImpl#insert(CountryBean)
	 */
	CountryBean insert(CountryBean countryBean);

	/**
	 * @see com.czmbeauty.model.dao.impl.CountryDaoImpl#update(CountryBean)
	 */
	CountryBean update(CountryBean countryBean);

	/**
	 * @see com.czmbeauty.model.dao.impl.CountryDaoImpl#delete(Integer)
	 */
	Boolean delete(Integer co_id);

}
