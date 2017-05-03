/*
 * CaiZiMei
 * File: ClinicUserService.java
 * Author: 詹晟
 * Date: 2017/5/3
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.service;

import com.caizimei.model.entity.ClinicUserBean;

/**
 * clinic_user service interface
 * 
 * @author 詹晟
 */
public interface ClinicUserService {

	ClinicUserBean signUp(ClinicUserBean clinicUserBean);

	Boolean signIn(String cu_username, String cu_password);

	ClinicUserBean selectByCu_id(Integer cu_id);

	ClinicUserBean selectByCu_username(String cu_username);

	ClinicUserBean selectByCu_email(String cu_email);

	ClinicUserBean update(ClinicUserBean clinicUserBean);

	ClinicUserBean updateCu_password(Integer cu_id, String cu_password_new, String cu_salt);

	ClinicUserBean updateCu_signin_ip(Integer cu_id, String cu_signin_ip);

	ClinicUserBean updateCu_signin_time(Integer cu_id);

	String getHashedPassword(String cu_password, String cu_salt);

	String getMD5(String str);

	String getSalt();

	void sendEmail(String to, String from, String subject, String text);

}
