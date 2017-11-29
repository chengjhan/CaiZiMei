/*
 * CaiZiMei/User
 * File: UserUrlDao.java
 * Author: 詹晟
 * Date: 2017/11/29
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import com.czmbeauty.common.constants.HqlConstants;
import com.czmbeauty.model.entity.CategoryUrlBean;
import com.czmbeauty.model.entity.UserUrlBean;

/**
 * user_url DAO interface
 *
 * @author 詹晟
 */
public interface UserUrlDao extends HqlConstants {

	/**
	 * @see com.czmbeauty.model.dao.impl.UserUrlDaoImpl#selectByUu_url(CategoryUrlBean,
	 *      String)
	 */
	UserUrlBean selectByUu_url(CategoryUrlBean uu_CategoryUrlBean, String uu_url);

}
