/*
 * CaiZiMei/User
 * File: UserPathDaoImpl.java
 * Author: 詹晟
 * Date: 2017/11/30
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

import com.czmbeauty.model.dao.UserPathDao;
import com.czmbeauty.model.entity.CategoryPathBean;
import com.czmbeauty.model.entity.UserPathBean;

/**
 * user_path DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "userPathDao")
public class UserPathDaoImpl implements UserPathDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * path 類別及 path 搜尋
	 * 
	 * @param up_CategoryPathBean
	 *            CategoryPathBean --> path 類別
	 * @param up_path
	 *            String --> path
	 * @return UserPathBean
	 * @return null
	 */
	@Override
	@SuppressWarnings("unchecked")
	public UserPathBean selectByUp_path(CategoryPathBean up_CategoryPathBean, String up_path) {

		DetachedCriteria criteria = DetachedCriteria.forClass(UserPathBean.class);

		criteria.add(Restrictions.eq("up_CategoryPathBean", up_CategoryPathBean));
		criteria.add(Restrictions.eq("up_path", up_path));

		List<UserPathBean> list = (List<UserPathBean>) hibernateTemplate.findByCriteria(criteria);

		return !list.isEmpty() ? list.get(0) : null;
	}

}
