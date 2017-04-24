/*
 * CaiZiMei
 * File: SpecialistDAO.java
 * Author: 詹晟
 * Date: 2017/4/24
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.dao;

import java.util.List;

import com.caizimei.model.entity.SpecialistBean;

/**
 * specialist DAO interface
 *
 * @author 詹晟
 */
public interface SpecialistDAO {

	List<SpecialistBean> select();

	SpecialistBean selectByS_id(Integer s_id);

	SpecialistBean selectByS_username(String s_username);

	SpecialistBean insert(SpecialistBean specialistBean);

	SpecialistBean update(SpecialistBean specialistBean);

	SpecialistBean updateS_password(Integer s_id, String s_password_new_hashed);

	SpecialistBean updateS_signin_number(Integer s_id);

	SpecialistBean updateS_signin_ip(Integer s_id, String s_signin_ip);

	SpecialistBean updateS_signin_time(Integer s_id);

}
