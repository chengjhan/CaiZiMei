/*
 * CaiZiMei
 * File: AdminLogDAO.java
 * Author: 詹晟
 * Date: 2017/7/8
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.dao;

import java.util.List;

import com.caizimei.model.entity.AdminLogBean;

/**
 * admin_log DAO interface
 *
 * @author 詹晟
 */
public interface AdminLogDao {

	List<AdminLogBean> selectAll();

	List<AdminLogBean> selectByAl_a_id(Integer al_a_id);

	AdminLogBean insert(AdminLogBean adminLogBean);

}
