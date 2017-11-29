/*
 * CaiZiMei/User
 * File: CategoryUrlDao.java
 * Author: 詹晟
 * Date: 2017/11/29
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import com.czmbeauty.common.constants.HqlConstants;
import com.czmbeauty.model.entity.CategoryUrlBean;

/**
 * category_url DAO interface
 * 
 * @author 詹晟
 */
public interface CategoryUrlDao extends HqlConstants {

	/**
	 * @see com.czmbeauty.model.dao.impl.CategoryUrlDaoImpl#selectByCu_code(String)
	 */
	CategoryUrlBean selectByCu_code(String cu_code);

}
