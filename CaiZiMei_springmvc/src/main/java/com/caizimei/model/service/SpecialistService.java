/*
 * CaiZiMei
 * File: SpecialistService.java
 * Author: 詹晟
 * Date: 2017/4/24
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.service;

import com.caizimei.model.entity.SpecialistBean;

/**
 * specialist service interface
 * 
 * @author 詹晟
 */
public interface SpecialistService {

	SpecialistBean signUp(SpecialistBean specialistBean);

	Boolean signIn(String s_username, String s_password);

	SpecialistBean selectByS_id(Integer s_id);

	SpecialistBean selectByS_username(String s_username);

	SpecialistBean update(SpecialistBean specialistBean);

	SpecialistBean updateS_password(Integer s_id, String s_password_new, String s_salt);

	SpecialistBean updateS_signin_ip(Integer s_id, String s_signin_ip);

	SpecialistBean updateS_signin_time(Integer s_id);

	String getHashedPassword(String s_password, String s_salt);

	String getMD5(String str);

	String getSalt();

	void sendEmail(String to, String from, String subject, String text);

}
