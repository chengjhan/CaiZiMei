/*
 * CaiZiMei/User
 * File: UserViewService.java
 * Author: 詹晟
 * Date: 2017/10/3
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service;

import com.czmbeauty.model.entity.UserViewBean;

/**
 * user_view service interface
 * 
 * @author 詹晟
 */
public interface UserViewService {

	/**
	 * @see com.czmbeauty.model.service.impl.UserViewServiceImpl#selectByUv_view_name(String)
	 */
	UserViewBean selectByUv_view_name(String uv_view_name);

}
