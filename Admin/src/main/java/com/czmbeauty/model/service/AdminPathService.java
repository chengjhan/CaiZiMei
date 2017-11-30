/*
 * CaiZiMei
 * File: AdminPathService.java
 * Author: 詹晟
 * Date: 2017/11/30
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service;

import com.czmbeauty.model.entity.AdminPathBean;

/**
 * admin_path service interface
 * 
 * @author 詹晟
 */
public interface AdminPathService {

	/**
	 * @see com.czmbeauty.model.service.impl.AdminPathServiceImpl#selectByAp_path(String,
	 *      String)
	 */
	AdminPathBean selectByAp_path(String cp_extension, String ap_path);

}
