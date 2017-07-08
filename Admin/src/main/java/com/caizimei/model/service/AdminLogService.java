/*
 * CaiZiMei
 * File: AdminLogService.java
 * Author: 詹晟
 * Date: 2017/7/8
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.service;

import java.util.List;

import com.caizimei.model.entity.AdminLogBean;

/**
 * admin_log service interface
 * 
 * @author 詹晟
 */
public interface AdminLogService {

	List<AdminLogBean> selectAll();

	List<AdminLogBean> selectByAl_a_id(Integer al_a_id);

	AdminLogBean insert(AdminLogBean adminLogBean);

}
