/*
 * CaiZiMei
 * File: AdminUserLogDAO.java
 * Author: 詹晟
 * Date: 2017/5/3
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.dao;

import java.util.List;

import com.caizimei.model.entity.AdminUserLogBean;

/**
 * admin_user_log DAO interface
 *
 * @author 詹晟
 */
public interface AdminUserLogDAO {

	List<AdminUserLogBean> select();

	List<AdminUserLogBean> selectByAdul_adu_id(Integer adul_adu_id);

	AdminUserLogBean insert(AdminUserLogBean adminUserLogBean);

}
