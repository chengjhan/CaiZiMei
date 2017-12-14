/*
 * CaiZiMei
 * File: AdminLogService.java
 * Author: 詹晟
 * Date: 2017/12/14
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service;

import java.util.Date;
import java.util.Map;

import com.czmbeauty.model.entity.AdminBean;
import com.czmbeauty.model.entity.AdminLogBean;
import com.czmbeauty.model.entity.AdminPathBean;

/**
 * admin_log service interface
 * 
 * @author 詹晟
 */
public interface AdminLogService {

	/**
	 * @see com.czmbeauty.model.service.impl.AdminLogServiceImpl#selectByConditions(Date,
	 *      Date, AdminBean, AdminPathBean, Integer, int)
	 */
	Map<String, Object> selectByConditions(Date beginDate, Date endDate, AdminBean al_AdminBean,
			AdminPathBean al_AdminPathBean, Integer page, int max);

	/**
	 * @see com.czmbeauty.model.service.impl.AdminLogServiceImpl#insert(AdminLogBean)
	 */
	AdminLogBean insert(AdminLogBean adminLogBean);

}
