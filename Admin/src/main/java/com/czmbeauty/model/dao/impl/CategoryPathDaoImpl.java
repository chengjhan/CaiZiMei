/*
 * CaiZiMei
 * File: CategoryPathDaoImpl.java
 * Author: 詹晟
 * Date: 2017/11/30
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.czmbeauty.model.dao.CategoryPathDao;
import com.czmbeauty.model.entity.CategoryPathBean;

/**
 * category_path DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "categoryPathDao")
public class CategoryPathDaoImpl implements CategoryPathDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * extension 搜尋
	 * 
	 * @param cp_extension
	 *            String --> extension
	 * @return CategoryPathBean
	 * @return null
	 */
	@Override
	@SuppressWarnings("unchecked")
	public CategoryPathBean selectByCp_extension(String cp_extension) {

		List<CategoryPathBean> list = (List<CategoryPathBean>) hibernateTemplate
				.findByNamedParam(HQL_SELECT_CATEGORY_PATH_BY_EXTENSION, "cp_extension", cp_extension);

		return !list.isEmpty() ? list.get(0) : null;
	}

}
