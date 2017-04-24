/*
 * CaiZiMei
 * File: EmployeeService.java
 * Author: 詹晟
 * Date: 2017/4/24
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.service;

import com.caizimei.model.entity.EmployeeBean;

/**
 * employee service interface
 * 
 * @author 詹晟
 */
public interface EmployeeService {
	
	EmployeeBean signUp(EmployeeBean employeeBean);

	Boolean signIn(String e_username, String e_password);

	EmployeeBean selectByE_id(Integer e_id);

	EmployeeBean selectByE_username(String e_username);

	EmployeeBean update(EmployeeBean employeeBean);

	EmployeeBean updateE_password(Integer e_id, String e_password_new, String e_salt);

	EmployeeBean updateE_signin_ip(Integer e_id, String e_signin_ip);

	EmployeeBean updateE_signin_time(Integer e_id);

	String getHashedPassword(String e_password, String e_salt);

	String getMD5(String str);

	String getSalt();

	void sendEmail(String to, String from, String subject, String text);

}
