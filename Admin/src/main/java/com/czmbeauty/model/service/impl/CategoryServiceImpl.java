/*
 * CaiZiMei
 * File: CategoryServiceImpl.java
 * Author: 詹晟
 * Date: 2017/8/10
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	 * 類別流水號搜尋
	 * 
	 * @param ca_id
	 *            Integer --> 類別流水號搜尋
	 * @return CategoryBean
	 */
	@Override
	@Transactional(readOnly = true)
	public CategoryBean selectByCa_id(Integer ca_id) {

		CategoryBean result = null;

		if (ca_id != 0) {

			result = categoryDao.selectByCa_id(ca_id);
		}
		return result;
	}

}
