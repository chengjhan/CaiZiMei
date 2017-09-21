/*
 * CaiZiMei
 * File: CategoryService.java
 * Author: 詹晟
 * Date: 2017/9/21
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service;

import com.czmbeauty.common.exception.PageNotFoundException;
import com.czmbeauty.model.entity.CategoryBean;

/**
 * category service interface
 * 
 * @author 詹晟
 */
public interface CategoryService {

	/**
	 * @see com.czmbeauty.model.service.impl.CategoryServiceImpl#selectByCa_directory(String)
	 */
	CategoryBean selectByCa_directory(String pageName) throws PageNotFoundException;

}
