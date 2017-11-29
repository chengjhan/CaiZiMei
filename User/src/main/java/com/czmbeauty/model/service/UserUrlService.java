/*
 * CaiZiMei/User
 * File: UserUrlService.java
 * Author: 詹晟
 * Date: 2017/11/29
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service;

import com.czmbeauty.model.entity.CategoryUrlBean;
import com.czmbeauty.model.entity.UserUrlBean;

/**
 * user_url service interface
 * 
 * @author 詹晟
 */
public interface UserUrlService {

	/**
	 * @see com.czmbeauty.model.service.impl.UserUrlServiceImpl#selectByUu_url(CategoryUrlBean,
	 *      String)
	 */
	UserUrlBean selectByUu_url(CategoryUrlBean uu_CategoryUrlBean, String uu_url);

}
