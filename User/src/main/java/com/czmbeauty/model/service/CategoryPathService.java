/*
 * CaiZiMei/User
 * File: CategoryPathService.java
 * Author: 詹晟
 * Date: 2017/11/30
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service;

import com.czmbeauty.model.entity.CategoryPathBean;

/**
 * category_path service interface
 * 
 * @author 詹晟
 */
public interface CategoryPathService {

	/**
	 * @see com.czmbeauty.model.service.impl.CategoryPathServiceImpl#selectByCp_extension(String)
	 */
	CategoryPathBean selectByCp_extension(String cp_extension);

}
