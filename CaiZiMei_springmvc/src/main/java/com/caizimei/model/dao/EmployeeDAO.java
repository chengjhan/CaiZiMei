/*
 * CaiZiMei
 * File: EmployeeDAO.java
 * Author: 詹晟
 * Date: 2017/4/24
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.dao;

import java.util.List;

import com.caizimei.model.entity.EmployeeBean;

/**
 * employee DAO interface
 *
 * @author 詹晟
 */
public interface EmployeeDAO {

	List<EmployeeBean> select();

	EmployeeBean selectByE_id(Integer e_id);

	EmployeeBean selectByE_username(String e_username);

	EmployeeBean insert(EmployeeBean employeeBean);

	EmployeeBean update(EmployeeBean employeeBean);

	EmployeeBean updateE_password(Integer e_id, String e_password_new_hashed);

	EmployeeBean updateE_signin_number(Integer e_id);

	EmployeeBean updateE_signin_ip(Integer e_id, String e_signin_ip);

	EmployeeBean updateE_signin_time(Integer e_id);

}
