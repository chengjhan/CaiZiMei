/*
 * CaiZiMei
 * File: AdminService.java
 * Author: 詹晟
 * Date: 2017/7/10
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.service;

import com.caizimei.model.entity.AdminBean;

/**
 * admin service interface
 * 
 * @author 詹晟
 */
public interface AdminService {

	AdminBean signUp(AdminBean adminBean);

	Boolean signIn(String a_username, String a_password);

	AdminBean selectByA_id(Integer a_id);

	AdminBean selectByA_username(String a_username);

	AdminBean update(AdminBean adminBean);

	AdminBean updateA_password(Integer a_id, String a_password_new, String a_salt);

	AdminBean updateA_signin_ip(Integer a_id, String a_signin_ip);

	AdminBean updateA_signin_time(Integer a_id);

	AdminBean updateA_status(Integer a_id);

	String getHashedPassword(String a_password, String a_salt);

	String getMD5(String str);

	String getSalt();

	void sendEmail(String to, String from, String subject, String text);

}
