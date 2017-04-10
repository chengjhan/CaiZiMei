/*
 * CaiZiMei
 * File: AdministratorService.java
 * Author: 詹晟
 * Date: 2017/4/10
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.service;

import com.caizimei.model.entity.AdministratorBean;

/**
 * administrator service interface
 * 
 * @author 詹晟
 */
public interface AdministratorService {

	AdministratorBean signUp(AdministratorBean administratorBean);

	Boolean signIn(String a_username, String a_password);

	AdministratorBean selectByA_id(Integer a_id);

	AdministratorBean selectByA_username(String a_username);

	AdministratorBean update(AdministratorBean administratorBean);

	AdministratorBean updateA_password(Integer a_id, String a_password_new, String a_salt);

	AdministratorBean updateA_signin_ip(Integer a_id, String a_signin_ip);

	AdministratorBean updateA_signin_time(Integer a_id);

	String getHashedPassword(String a_password, String a_salt);

	String getMD5(String str);

	String getSalt();

	void sendEmail(String to, String from, String subject, String text);

}
