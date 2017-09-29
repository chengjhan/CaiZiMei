/*
 * CaiZiMei
 * File: AdminViewDao.java
 * Author: 詹晟
 * Date: 2017/9/29
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import com.czmbeauty.common.constants.HqlConstants;
import com.czmbeauty.model.entity.AdminViewBean;

/**
 * admin_view DAO interface
 *
 * @author 詹晟
 */
public interface AdminViewDao extends HqlConstants {

	/**
	 * @see com.czmbeauty.model.dao.impl.AdminViewDaoImpl#selectByAv_page_name(String)
	 */
	AdminViewBean selectByAv_page_name(String av_page_name);

}
