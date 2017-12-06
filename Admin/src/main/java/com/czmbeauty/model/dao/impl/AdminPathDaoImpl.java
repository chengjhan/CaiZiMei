/*
 * CaiZiMei
 * File: AdminPathDaoImpl.java
 * Author: 詹晟
 * Date: 2017/12/6
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.czmbeauty.model.dao.AdminPathDao;
import com.czmbeauty.model.entity.AdminPathBean;
import com.czmbeauty.model.entity.CategoryPathBean;

/**
 * admin_path DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "adminPathDao")
public class AdminPathDaoImpl implements AdminPathDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * path 流水號搜尋
	 * 
	 * @param ap_id
	 *            Integer --> path 流水號
	 * @return AdminPathBean
	 */
	@Override
	public AdminPathBean selectByAp_id(Integer ap_id) {

		return hibernateTemplate.get(AdminPathBean.class, ap_id);
	}

	/**
	 * path 類別及 path 搜尋
	 * 
	 * @param ap_CategoryPathBean
	 *            CategoryPathBean --> path 類別
	 * @param ap_path
	 *            String --> path
	 * @return AdminPathBean
	 * @return null
	 */
	@Override
	@SuppressWarnings("unchecked")
	public AdminPathBean selectByAp_path(CategoryPathBean ap_CategoryPathBean, String ap_path) {

		DetachedCriteria criteria = DetachedCriteria.forClass(AdminPathBean.class);

		criteria.add(Restrictions.eq("ap_CategoryPathBean", ap_CategoryPathBean));
		criteria.add(Restrictions.eq("ap_path", ap_path));

		List<AdminPathBean> list = (List<AdminPathBean>) hibernateTemplate.findByCriteria(criteria);

		return !list.isEmpty() ? list.get(0) : null;
	}

}
