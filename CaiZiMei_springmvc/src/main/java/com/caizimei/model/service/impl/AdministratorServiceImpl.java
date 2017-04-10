/*
 * CaiZiMei
 * File: AdministratorServiceImpl.java
 * Author: 詹晟
 * Date: 2017/4/10
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.service.impl;

import com.caizimei.model.entity.AdministratorBean;
import com.caizimei.model.service.AdministratorService;

/**
 * administrator service implement
 * 
 * @author 詹晟
 */
public class AdministratorServiceImpl implements AdministratorService {

	@Override
	public AdministratorBean signUp(AdministratorBean administratorBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean signIn(String a_username, String a_password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdministratorBean selectByM_id(Integer a_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdministratorBean selectByM_username(String a_username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdministratorBean update(AdministratorBean administratorBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdministratorBean updateM_password(Integer a_id, String a_password_new, String a_salt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdministratorBean updateM_signin_ip(Integer a_id, String a_signin_ip) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdministratorBean updateM_signin_time(Integer a_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHashedPassword(String a_password, String a_salt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMD5(String str) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSalt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendEmail(String to, String from, String subject, String text) {
		// TODO Auto-generated method stub

	}

}
