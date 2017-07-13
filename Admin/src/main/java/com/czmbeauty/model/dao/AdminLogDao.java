/*
 * CaiZiMei
 * File: AdminLogDao.java
 * Author: 詹晟
 * Date: 2017/7/10
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import java.util.List;

import com.czmbeauty.model.entity.AdminLogBean;

/**
 * admin_log dao interface
 *
 * @author 詹晟
 */
public interface AdminLogDao {

	List<AdminLogBean> selectAll();

	List<AdminLogBean> selectByAl_a_id(Integer al_a_id);

	AdminLogBean insert(AdminLogBean adminLogBean);

}