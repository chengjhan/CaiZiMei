/*
 * CaiZiMei
 * File: AdminLogDao.java
 * Author: 詹晟
 * Date: 2017/12/8
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import java.util.Date;
import java.util.List;
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
	Map<String, Object> selectByConditions(Date startDate, Date endDate, AdminBean adminBean,
			AdminPathBean adminPathBean, int first, int max);

	/**
	 * @see com.czmbeauty.model.dao.impl.AdminLogDaoImpl#selectCount(Date, Date,
	 *      AdminBean, AdminPathBean)
	 */
	int selectCount(Date startDate, Date endDate, AdminBean adminBean, AdminPathBean adminPathBean);

	/**
	 * @see com.czmbeauty.model.dao.impl.AdminLogDaoImpl#selectByConditions(Date,
	 *      Date, AdminBean, AdminPathBean)
	 */
	List<AdminLogBean> selectByConditions(Date startDate, Date endDate, AdminBean adminBean,
			AdminPathBean adminPathBean);

	/**
	 * @see com.czmbeauty.model.dao.impl.AdminLogDaoImpl#insert(AdminLogBean)
	 */
	AdminLogBean insert(AdminLogBean adminLogBean);

}
