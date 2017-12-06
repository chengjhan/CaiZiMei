/*
 * CaiZiMei
 * File: AdminLogService.java
 * Author: 詹晟
 * Date: 2017/12/6
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service;

import java.util.Date;
import java.util.List;

import com.czmbeauty.model.entity.AdminLogBean;

/**
 * admin_log service interface
 * 
 * @author 詹晟
 */
public interface AdminLogService {

	/**
	 * @see com.czmbeauty.model.service.impl.AdminLogServiceImpl#selectByConditions(Date,
	 *      Date, Integer, Integer)
	 */
	List<AdminLogBean> selectByConditions(Date startDate, Date endDate, Integer al_ad_id, Integer al_ap_id);

	/**
	 * @see com.czmbeauty.model.service.impl.AdminLogServiceImpl#insert(AdminLogBean)
	 */
	AdminLogBean insert(AdminLogBean adminLogBean);

}
