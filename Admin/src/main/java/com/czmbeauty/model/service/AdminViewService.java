/*
 * CaiZiMei
 * File: AdminViewService.java
 * Author: 詹晟
 * Date: 2017/10/2
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service;

import com.czmbeauty.model.entity.AdminViewBean;

/**
 * admin_view service interface
 * 
 * @author 詹晟
 */
public interface AdminViewService {

	/**
	 * @see com.czmbeauty.model.service.impl.AdminViewServiceImpl#selectByAv_view_name(String)
	 */
	AdminViewBean selectByAv_view_name(String av_view_name);

}
