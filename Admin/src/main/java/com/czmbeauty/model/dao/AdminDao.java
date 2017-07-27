/*
 * CaiZiMei
 * File: AdminDao.java
 * Author: 詹晟
 * Date: 2017/7/27
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import java.util.List;

import com.czmbeauty.model.entity.AdminBean;

/**
 * admin DAO interface
 *
 * @author 詹晟
 */
public interface AdminDao {

	/**
	 * @see com.czmbeauty.model.dao.impl.AdminDaoImpl#selectAll()
	 */
	List<AdminBean> selectAll();

	/**
	 * @see com.czmbeauty.model.dao.impl.AdminDaoImpl#selectByAd_id(Integer)
	 */
	AdminBean selectByAd_id(Integer ad_id);

	/**
	 * @see com.czmbeauty.model.dao.impl.AdminDaoImpl#selectByAd_username(String)
	 */
	AdminBean selectByAd_username(String ad_username);

	/**
	 * @see com.czmbeauty.model.dao.impl.AdminDaoImpl#selectByAd_email(String)
	 */
	AdminBean selectByAd_email(String ad_email);

	/**
	 * @see com.czmbeauty.model.dao.impl.AdminDaoImpl#insert(AdminBean)
	 */
	AdminBean insert(AdminBean adminBean);

	/**
	 * @see com.czmbeauty.model.dao.impl.AdminDaoImpl#update(AdminBean)
	 */
	AdminBean update(AdminBean adminBean);

}
