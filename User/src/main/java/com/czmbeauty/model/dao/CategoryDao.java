/*
 * CaiZiMei/User
 * File: CategoryDao.java
 * Author: 詹晟
 * Date: 2017/9/8
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import java.util.List;

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

	/**
	 * @see com.czmbeauty.model.dao.impl.CategoryDaoImpl#selectByCa_directory(String)
	 */
	List<CategoryBean> selectByCa_directory(String ca_directory);

}
