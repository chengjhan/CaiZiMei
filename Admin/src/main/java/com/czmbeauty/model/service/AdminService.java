/*
 * CaiZiMei
 * File: AdminService.java
 * Author: 詹晟
 * Date: 2017/7/21
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

	AdminBean signUp(AdminBean adminBean);

	AdminBean signIn(String ad_username, String ad_password);

	List<AdminBean> selectAll();

	AdminBean selectByAd_id(Integer ad_id);

	AdminBean selectByAd_username(String ad_username);

	AdminBean selectByAd_email(String ad_email);

	AdminBean update(AdminBean adminBean);

	AdminBean updateAd_password(AdminBean adminBean, String ad_password, String ad_password_new);

	AdminBean updateAd_password(AdminBean adminBean, String ad_password_new);

	AdminBean updateAd_status(AdminBean adminBean);

}
