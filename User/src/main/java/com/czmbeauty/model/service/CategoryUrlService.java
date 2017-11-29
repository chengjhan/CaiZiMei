/*
 * CaiZiMei/User
 * File: CategoryUrlService.java
 * Author: 詹晟
 * Date: 2017/11/29
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service;

import com.czmbeauty.model.entity.CategoryUrlBean;

/**
 * category_url service interface
 * 
 * @author 詹晟
 */
public interface CategoryUrlService {

	/**
	 * @see com.czmbeauty.model.service.impl.CategoryUrlServiceImpl#selectByCu_code(String)
	 */
	CategoryUrlBean selectByCu_code(String cu_code);

}
