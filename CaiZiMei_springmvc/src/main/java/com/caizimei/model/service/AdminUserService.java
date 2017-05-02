/*
 * CaiZiMei
 * File: AdminUserService.java
 * Author: 詹晟
 * Date: 2017/5/3
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.service;

import com.caizimei.model.entity.AdminUserBean;

/**
 * admin_user service interface
 * 
 * @author 詹晟
 */
public interface AdminUserService {

	AdminUserBean signUp(AdminUserBean adminUserBean);

	Boolean signIn(String adu_username, String adu_password);

	AdminUserBean selectByAdu_id(Integer adu_id);

	AdminUserBean selectByAdu_username(String adu_username);

	AdminUserBean update(AdminUserBean adminUserBean);

	AdminUserBean updateAdu_password(Integer adu_id, String adu_password_new, String adu_salt);

	AdminUserBean updateAdu_signin_ip(Integer adu_id, String adu_signin_ip);

	AdminUserBean updateAdu_signin_time(Integer adu_id);

	String getHashedPassword(String adu_password, String adu_salt);

	String getMD5(String str);

	String getSalt();

	void sendEmail(String to, String from, String subject, String text);

}
