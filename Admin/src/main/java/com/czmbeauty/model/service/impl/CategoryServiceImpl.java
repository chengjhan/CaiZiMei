/*
 * CaiZiMei
 * File: CategoryServiceImpl.java
 * Author: 詹晟
 * Date: 2017/9/9
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.czmbeauty.model.dao.CategoryDao;
import com.czmbeauty.model.entity.CategoryBean;
import com.czmbeauty.model.service.CategoryService;

/**
 * category service implement
 * 
 * @author 詹晟
 */
@Service(value = "categoryService")
public class CategoryServiceImpl implements CategoryService {

	/**
	 * 注入 categoryDao
	 */
	@Autowired
	private CategoryDao categoryDao;

	/**
	 * 類別資料夾名稱搜尋
	 * 
	 * @param ca_directory
	 *            String --> 類別資料夾名稱
	 * @return CategoryBean
	 */
	@Override
	public CategoryBean selectByCa_directory(String ca_directory) {

		return categoryDao.selectByCa_directory(ca_directory);
	}

}
