/*
 * CaiZiMei/User
 * File: CategoryUrlServiceImpl.java
 * Author: 詹晟
 * Date: 2017/11/29
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czmbeauty.model.dao.CategoryUrlDao;
import com.czmbeauty.model.entity.CategoryUrlBean;
import com.czmbeauty.model.service.CategoryUrlService;

/**
 * category_url service implement
 * 
 * @author 詹晟
 */
@Service(value = "categoryUrlService")
public class CategoryUrlServiceImpl implements CategoryUrlService {

	/**
	 * 注入 CategoryUrlDao
	 */
	@Autowired
	private CategoryUrlDao categoryUrlDao;

	/**
	 * URL 類別 code 搜尋
	 * 
	 * @param cu_code
	 *            String --> URL 類別 code
	 * @return CategoryUrlBean
	 */
	@Override
	@Transactional(readOnly = true)
	public CategoryUrlBean selectByCu_code(String cu_code) {

		return categoryUrlDao.selectByCu_code(cu_code);
	}

}
