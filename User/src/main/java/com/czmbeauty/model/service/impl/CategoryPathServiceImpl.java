/*
 * CaiZiMei/User
 * File: CategoryPathServiceImpl.java
 * Author: 詹晟
 * Date: 2017/11/30
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czmbeauty.model.dao.CategoryPathDao;
import com.czmbeauty.model.entity.CategoryPathBean;
import com.czmbeauty.model.service.CategoryPathService;

/**
 * category_path service implement
 * 
 * @author 詹晟
 */
@Service(value = "categoryPathService")
public class CategoryPathServiceImpl implements CategoryPathService {

	/**
	 * 注入 CategoryPathDao
	 */
	@Autowired
	private CategoryPathDao categoryPathDao;

	/**
	 * extension 搜尋
	 * 
	 * @param cp_extension
	 *            String --> extension
	 * @return CategoryPathBean
	 */
	@Override
	@Transactional(readOnly = true)
	public CategoryPathBean selectByCp_extension(String cp_extension) {

		return categoryPathDao.selectByCp_extension(cp_extension);
	}

}
