/*
 * CaiZiMei
 * File: AdministratorDAO.java
 * Author: 詹晟
 * Date: 2017/4/10
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.dao;

import java.util.List;

import com.caizimei.model.entity.AdministratorBean;

/**
 * administrator DAO interface
 *
 * @author 詹晟
 */
public interface AdministratorDAO {

	List<AdministratorBean> select();

	AdministratorBean selectByA_id(Integer a_id);

	AdministratorBean selectByA_username(String a_username);

	AdministratorBean insert(AdministratorBean administratorBean);

	AdministratorBean update(AdministratorBean administratorBean);

	AdministratorBean updateA_password(Integer a_id, String a_password_new_hashed);

	AdministratorBean updateA_signin_number(Integer a_id);

	AdministratorBean updateA_signin_ip(Integer a_id, String a_signin_ip);

	AdministratorBean updateA_signin_time(Integer a_id);

}
