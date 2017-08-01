/*
 * CaiZiMei
 * File: AdminLogService.java
 * Author: 詹晟
 * Date: 2017/8/2
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service;

import java.util.List;

import com.czmbeauty.model.entity.AdminLogBean;

/**
 * admin_log service interface
 * 
 * @author 詹晟
 */
public interface AdminLogService {

	/**
	 * @see com.czmbeauty.model.service.impl.AdminLogServiceImpl#selectAll()
	 */
	List<AdminLogBean> selectAll();

	/**
	 * @see com.czmbeauty.model.service.impl.AdminLogServiceImpl#selectByAl_ad_id(Integer)
	 */
	List<AdminLogBean> selectByAl_ad_id(Integer al_ad_id);

	/**
	 * @see com.czmbeauty.model.service.impl.AdminLogServiceImpl#insert(AdminLogBean)
	 */
	AdminLogBean insert(AdminLogBean adminLogBean);

}
