/*
 * CaiZiMei/User
 * File: UserPathDao.java
 * Author: 詹晟
 * Date: 2017/11/30
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import com.czmbeauty.common.constants.HqlConstants;
import com.czmbeauty.model.entity.CategoryPathBean;
import com.czmbeauty.model.entity.UserPathBean;

/**
 * user_path DAO interface
 *
 * @author 詹晟
 */
public interface UserPathDao extends HqlConstants {

	/**
	 * @see com.czmbeauty.model.dao.impl.UserPathDaoImpl#selectByUp_path(CategoryPathBean,
	 *      String)
	 */
	UserPathBean selectByUp_path(CategoryPathBean up_CategoryPathBean, String up_path);

}
