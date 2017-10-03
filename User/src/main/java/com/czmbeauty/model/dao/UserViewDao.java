/*
 * CaiZiMei/User
 * File: UserViewDao.java
 * Author: 詹晟
 * Date: 2017/10/3
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import com.czmbeauty.common.constants.HqlConstants;
import com.czmbeauty.model.entity.UserViewBean;

/**
 * user_view DAO interface
 *
 * @author 詹晟
 */
public interface UserViewDao extends HqlConstants {

	/**
	 * @see com.czmbeauty.model.dao.impl.UserViewDaoImpl#selectByUv_view_name(String)
	 */
	UserViewBean selectByUv_view_name(String uv_view_name);

}
