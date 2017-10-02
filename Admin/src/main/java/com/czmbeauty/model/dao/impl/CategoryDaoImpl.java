/*
 * CaiZiMei
 * File: CategoryDaoImpl.java
 * Author: 詹晟
 * Date: 2017/10/2
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.czmbeauty.model.dao.CategoryDao;
import com.czmbeauty.model.entity.CategoryBean;

/**
 * category DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "categoryDao")
public class CategoryDaoImpl implements CategoryDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 類別資料夾名稱搜尋
	 * 
	 * @param ca_directory
	 *            String --> 類別資料夾名稱
	 * @return CategoryBean
	 * @return null
	 */
	@Override
	@SuppressWarnings("unchecked")
	public CategoryBean selectByCa_directory(String ca_directory) {

		List<CategoryBean> list = (List<CategoryBean>) hibernateTemplate
				.findByNamedParam(HQL_SELECT_CATEGORY_BY_DIRECTORY, "ca_directory", ca_directory);

		return !list.isEmpty() ? list.get(0) : null;
	}

}
