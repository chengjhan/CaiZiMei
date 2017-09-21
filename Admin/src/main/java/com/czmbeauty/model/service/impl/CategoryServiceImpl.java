/*
 * CaiZiMei
 * File: CategoryServiceImpl.java
 * Author: 詹晟
 * Date: 2017/9/21
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.czmbeauty.common.exception.PageNotFoundException;
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
	 * @param pageName
	 *            String --> 頁面名
	 * @param ca_directory
	 *            String --> 類別資料夾名稱
	 * @throws PageNotFoundException
	 * @return CategoryBean
	 */
	@Override
	public CategoryBean selectByCa_directory(String pageName) throws PageNotFoundException {

		CategoryBean categoryBean = categoryDao.selectByCa_directory(pageName.split("/")[0]);

		if (categoryBean == null) {

			throw new PageNotFoundException(pageName);
		}
		return categoryBean;
	}

}
