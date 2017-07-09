/*
 * CaiZiMei
 * File: AdminDao.java
 * Author: 詹晟
 * Date: 2017/7/10
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.dao;

import java.util.List;

import com.caizimei.model.entity.AdminBean;

/**
 * admin Dao interface
 *
 * @author 詹晟
 */
public interface AdminDao {

	List<AdminBean> selectAll();

	AdminBean selectByA_id(Integer a_id);

	AdminBean selectByA_username(String a_username);

	AdminBean insert(AdminBean adminBean);

	AdminBean update(AdminBean adminBean);

	AdminBean updateA_password(Integer a_id, String a_password_new_hashed);

	AdminBean updateA_signin_number(Integer a_id);

	AdminBean updateA_signin_ip(Integer a_id, String a_signin_ip);

	AdminBean updateA_signin_time(Integer a_id);

}
