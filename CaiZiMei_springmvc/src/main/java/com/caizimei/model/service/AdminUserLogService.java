/*
 * CaiZiMei
 * File: AdminUserLogService.java
 * Author: 詹晟
 * Date: 2017/5/3
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.service;

import java.util.List;

import com.caizimei.model.entity.AdminUserLogBean;

/**
 * admin_user_log service interface
 * 
 * @author 詹晟
 */
public interface AdminUserLogService {

	List<AdminUserLogBean> select();

	List<AdminUserLogBean> selectByAdul_adu_id(Integer adul_adu_id);

	AdminUserLogBean insert(AdminUserLogBean adminUserLogBean);

}
