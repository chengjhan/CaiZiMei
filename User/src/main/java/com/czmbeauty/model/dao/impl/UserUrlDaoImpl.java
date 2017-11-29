/*
 * CaiZiMei/User
 * File: UserUrlDaoImpl.java
 * Author: 詹晟
 * Date: 2017/11/29
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

import com.czmbeauty.model.dao.UserUrlDao;
import com.czmbeauty.model.entity.CategoryUrlBean;
import com.czmbeauty.model.entity.UserUrlBean;

/**
 * user_url DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "userUrlDao")
public class UserUrlDaoImpl implements UserUrlDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * URL 類別及 URL 搜尋
	 * 
	 * @param uu_CategoryUrlBean
	 *            CategoryUrlBean --> URL 類別
	 * @param uu_url
	 *            String --> URL
	 * @return UserUrlBean
	 * @return null
	 */
	@Override
	@SuppressWarnings("unchecked")
	public UserUrlBean selectByUu_url(CategoryUrlBean uu_CategoryUrlBean, String uu_url) {

		DetachedCriteria criteria = DetachedCriteria.forClass(UserUrlBean.class);

		criteria.add(Restrictions.eq("uu_CategoryUrlBean", uu_CategoryUrlBean));
		criteria.add(Restrictions.eq("uu_url", uu_url));

		List<UserUrlBean> list = (List<UserUrlBean>) hibernateTemplate.findByCriteria(criteria);

		return !list.isEmpty() ? list.get(0) : null;
	}

}
