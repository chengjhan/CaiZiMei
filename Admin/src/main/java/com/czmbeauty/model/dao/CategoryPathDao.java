/*
 * CaiZiMei
 * File: CategoryPathDao.java
 * Author: 詹晟
 * Date: 2017/11/30
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import com.czmbeauty.common.constants.HqlConstants;
import com.czmbeauty.model.entity.CategoryPathBean;

/**
 * category_path DAO interface
 * 
 * @author 詹晟
 */
public interface CategoryPathDao extends HqlConstants {

	/**
	 * @see com.czmbeauty.model.dao.impl.CategoryPathDaoImpl#selectByCp_extension(String)
	 */
	CategoryPathBean selectByCp_extension(String cp_extension);

}
