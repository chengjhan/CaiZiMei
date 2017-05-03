/*
 * CaiZiMei
 * File: AdminService.java
 * Author: 詹晟
 * Date: 2017/5/3
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.service;

import java.util.List;

import com.caizimei.model.entity.AdminBean;

/**
 * admin service interface
 *
 * @author 詹晟
 */
public interface AdminService {

	List<AdminBean> select();

	AdminBean insert(AdminBean adminBean);

}
