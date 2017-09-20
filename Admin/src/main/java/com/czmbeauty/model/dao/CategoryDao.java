/*
 * CaiZiMei
 * File: CategoryDao.java
 * Author: 詹晟
 * Date: 2017/9/20
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import com.czmbeauty.common.constants.HqlConstants;
import com.czmbeauty.model.entity.CategoryBean;

/**
 * category DAO interface
 * 
 * @author 詹晟
 */
public interface CategoryDao extends HqlConstants {

	/**
	 * @see com.czmbeauty.model.dao.impl.CategoryDaoImpl#selectByCa_directory(String)
	 */
	CategoryBean selectByCa_directory(String ca_directory);

}
