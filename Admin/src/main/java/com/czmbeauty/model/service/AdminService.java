/*
 * CaiZiMei
 * File: AdminService.java
 * Author: 詹晟
 * Date: 2017/12/1
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service;

import java.util.List;

import com.czmbeauty.model.entity.AdminBean;

/**
 * admin service interface
 * 
 * @author 詹晟
 */
public interface AdminService {

	/**
	 * @see com.czmbeauty.model.service.AdminService#signIn(String, String)
	 */
	AdminBean signIn(String ad_username, String ad_password);

	/**
	 * @see com.czmbeauty.model.service.AdminService#signUp(AdminBean)
	 */
	AdminBean signUp(AdminBean adminBean);

	/**
	 * @see com.czmbeauty.model.service.AdminService#selectPagination(Integer, int)
	 */
	List<AdminBean> selectPagination(Integer page, int max);

	/**
	 * @see com.czmbeauty.model.service.AdminService#selectCount()
	 */
	int selectCount();

	/**
	 * @see com.czmbeauty.model.service.AdminService#selectByAd_id(Integer)
	 */
	AdminBean selectByAd_id(Integer ad_id);

	/**
	 * @see com.czmbeauty.model.service.AdminService#selectByAd_username(String,
	 *      Integer)
	 */
	AdminBean selectByAd_username(String ad_username, Integer ad_status);

	/**
	 * @see com.czmbeauty.model.service.AdminService#selectByAd_email(String,
	 *      Integer)
	 */
	AdminBean selectByAd_email(String ad_email, Integer ad_status);

	/**
	 * @see com.czmbeauty.model.service.AdminService#selectByAd_email(Integer,
	 *      String)
	 */
	AdminBean selectByAd_email(Integer ad_id, String ad_email);

	/**
	 * @see com.czmbeauty.model.service.AdminService#update(AdminBean)
	 */
	AdminBean update(AdminBean adminBean);

	/**
	 * @see com.czmbeauty.model.service.AdminService#updateAd_password(AdminBean,
	 *      String)
	 */
	AdminBean updateAd_password(AdminBean adminBean, String ad_password_new);

	/**
	 * @see com.czmbeauty.model.service.AdminService#update(AdminBean)
	 */
	AdminBean updateAd_password(AdminBean adminBean);

	/**
	 * @see com.czmbeauty.model.service.AdminService#updateAd_status(Integer)
	 */
	AdminBean updateAd_status(Integer ad_id);

}
