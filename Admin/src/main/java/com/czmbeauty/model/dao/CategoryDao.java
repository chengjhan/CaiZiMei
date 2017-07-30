/*
 * CaiZiMei
 * File: CategoryDao.java
 * Author: 詹晟
 * Date: 2017/7/30
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import com.czmbeauty.model.entity.CategoryBean;

/**
 * category DAO interface
 * 
 * @author 詹晟
 */
public interface CategoryDao {

	/**
	 * @see com.czmbeauty.model.dao.impl.CategoryDaoImpl#selectByCa_id(Integer)
	 */
	CategoryBean selectByCa_id(Integer ca_id);

}
