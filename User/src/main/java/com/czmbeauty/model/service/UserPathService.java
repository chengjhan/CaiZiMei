/*
 * CaiZiMei/User
 * File: UserPathService.java
 * Author: 詹晟
 * Date: 2017/11/30
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service;

import com.czmbeauty.model.entity.UserPathBean;

/**
 * user_path service interface
 * 
 * @author 詹晟
 */
public interface UserPathService {

	/**
	 * @see com.czmbeauty.model.service.impl.UserPathServiceImpl#selectByUp_path(String,
	 *      String)
	 */
	UserPathBean selectByUp_path(String cp_extension, String up_path);

}
