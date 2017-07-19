/*
 * CaiZiMei
 * File: AdminService.java
 * Author: 詹晟
 * Date: 2017/7/19
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

	Boolean signIn(String ad_username, String ad_password);

	List<AdminBean> selectAll();

	AdminBean selectByAd_id(Integer ad_id);

	AdminBean selectByAd_username(String ad_username);

	AdminBean selectByAd_email(String ad_email);

	AdminBean update(AdminBean adminBean);

	AdminBean updateAd_password(Integer ad_id, String ad_password_new, String ad_salt);

	AdminBean updateAd_signin_ip(Integer ad_id, String ad_signin_ip);

	AdminBean updateAd_signin_time(Integer ad_id);

	AdminBean updateAd_status(Integer ad_id);

	void sendEmail(String to, String from, String subject, String text);

}
