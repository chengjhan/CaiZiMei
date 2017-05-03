/*
 * CaiZiMei
 * File: ClinicUserDAO.java
 * Author: 詹晟
 * Date: 2017/5/3
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.dao;

import java.util.List;

import com.caizimei.model.entity.ClinicUserBean;

/**
 * clinic_user DAO interface
 *
 * @author 詹晟
 */
public interface ClinicUserDAO {

	List<ClinicUserBean> select();

	ClinicUserBean selectByCu_id(Integer cu_id);

	ClinicUserBean selectByCu_username(String cu_username);
	
	ClinicUserBean selectByCu_email(String cu_email);

	ClinicUserBean insert(ClinicUserBean clinicUserBean);

	ClinicUserBean update(ClinicUserBean clinicUserBean);

	ClinicUserBean updateCu_password(Integer cu_id, String cu_password_new_hashed);

	ClinicUserBean updateCu_signin_number(Integer cu_id);

	ClinicUserBean updateCu_signin_ip(Integer cu_id, String cu_signin_ip);

	ClinicUserBean updateCu_signin_time(Integer cu_id);

}
