/*
 * CaiZiMei/User
 * File: CategoryUrlDaoImpl.java
 * Author: 詹晟
 * Date: 2017/11/29
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.czmbeauty.model.dao.CategoryUrlDao;
import com.czmbeauty.model.entity.CategoryUrlBean;

/**
 * category_url DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "categoryUrlDao")
public class CategoryUrlDaoImpl implements CategoryUrlDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * URL 類別 code 搜尋
	 * 
	 * @param cu_code
	 *            String --> URL 類別 code
	 * @return CategoryUrlBean
	 * @return null
	 */
	@Override
	@SuppressWarnings("unchecked")
	public CategoryUrlBean selectByCu_code(String cu_code) {

		List<CategoryUrlBean> list = (List<CategoryUrlBean>) hibernateTemplate
				.findByNamedParam(HQL_SELECT_CATEGORY_URL_BY_CODE, "cu_code", cu_code);

		return !list.isEmpty() ? list.get(0) : null;
	}

}
