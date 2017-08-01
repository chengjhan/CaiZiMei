/*
 * CaiZiMei
 * File: AdminLogDao.java
 * Author: 詹晟
 * Date: 2017/8/2
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import java.util.List;

import com.czmbeauty.model.entity.AdminLogBean;

/**
 * admin_log DAO interface
 *
 * @author 詹晟
 */
public interface AdminLogDao {

	/**
	 * @see com.czmbeauty.model.dao.impl.AdminLogDaoImpl#selectAll()
	 */
	List<AdminLogBean> selectAll();

	/**
	 * @see com.czmbeauty.model.dao.impl.AdminLogDaoImpl#selectByAl_ad_id(Integer)
	 */
	List<AdminLogBean> selectByAl_ad_id(Integer al_ad_id);

	/**
	 * @see com.czmbeauty.model.dao.impl.AdminLogDaoImpl#insert(AdminLogBean)
	 */
	AdminLogBean insert(AdminLogBean adminLogBean);

}
