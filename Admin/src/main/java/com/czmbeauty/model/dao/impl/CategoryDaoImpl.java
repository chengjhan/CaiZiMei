/*
 * CaiZiMei
 * File: CategoryDaoImpl.java
 * Author: 詹晟
 * Date: 2017/9/7
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao.impl;

import static com.czmbeauty.common.constants.HqlConstants.HQL_SELECT_CATEGORY_BY_DIRECTORY;

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
	 * 類別流水號搜尋
	 * 
	 * @param ca_id
	 *            Integer --> 類別流水號
	 * @return CategoryBean
	 */
	@Override
	public CategoryBean selectByCa_id(Integer ca_id) {

		return hibernateTemplate.get(CategoryBean.class, ca_id);
	}

	/**
	 * 類別資料夾名稱搜尋
	 * 
	 * @param ca_directory
	 *            String --> 類別資料夾名稱
	 * @return List<CategoryBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<CategoryBean> selectByCa_directory(String ca_directory) {

		return (List<CategoryBean>) hibernateTemplate.findByNamedParam(HQL_SELECT_CATEGORY_BY_DIRECTORY, "ca_directory",
				ca_directory);
	}

}
