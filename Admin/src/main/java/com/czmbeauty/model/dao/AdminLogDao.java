/*
 * CaiZiMei
 * File: AdminLogDao.java
 * Author: 詹晟
 * Date: 2017/12/14
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import java.util.Date;
import java.util.Map;

import com.czmbeauty.common.constants.HqlConstants;
import com.czmbeauty.model.entity.AdminBean;
import com.czmbeauty.model.entity.AdminLogBean;
import com.czmbeauty.model.entity.AdminPathBean;

/**
 * admin_log DAO interface
 *
 * @author 詹晟
 */
public interface AdminLogDao extends HqlConstants {

	/**
	 * @see com.czmbeauty.model.dao.impl.AdminLogDaoImpl#selectByConditions(Date,
	 *      Date, AdminBean, AdminPathBean, int, int)
	 */
	Map<String, Object> selectByConditions(Date beginDate, Date endDate, AdminBean al_AdminBean,
			AdminPathBean al_AdminPathBean, int first, int max);

	/**
	 * @see com.czmbeauty.model.dao.impl.AdminLogDaoImpl#insert(AdminLogBean)
	 */
	AdminLogBean insert(AdminLogBean adminLogBean);

}
