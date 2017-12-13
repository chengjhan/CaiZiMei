/*
 * CaiZiMei
 * File: AdminLogService.java
 * Author: 詹晟
 * Date: 2017/12/13
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service;

import java.util.Date;
import java.util.Map;

import com.czmbeauty.model.entity.AdminLogBean;

/**
 * admin_log service interface
 * 
 * @author 詹晟
 */
public interface AdminLogService {

	/**
	 * @see com.czmbeauty.model.service.impl.AdminLogServiceImpl#selectByConditions(Date,
	 *      Date, AdminLogBean, Integer, int)
	 */
	Map<String, Object> selectByConditions(Date startDate, Date endDate, AdminLogBean adminLogBean, Integer page,
			int max);

	/**
	 * @see com.czmbeauty.model.service.impl.AdminLogServiceImpl#insert(AdminLogBean)
	 */
	AdminLogBean insert(AdminLogBean adminLogBean);

}
