/*
 * CaiZiMei/User
 * File: UserViewDaoImpl.java
 * Author: 詹晟
 * Date: 2017/10/3
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.czmbeauty.model.dao.UserViewDao;
import com.czmbeauty.model.entity.UserViewBean;

/**
 * user_view DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "userViewDao")
public class UserViewDaoImpl implements UserViewDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 視圖名搜尋
	 * 
	 * @param uv_view_name
	 *            String --> 視圖名
	 * @return UserViewBean
	 * @return null
	 */
	@Override
	@SuppressWarnings("unchecked")
	public UserViewBean selectByUv_view_name(String uv_view_name) {

		List<UserViewBean> list = (List<UserViewBean>) hibernateTemplate
				.findByNamedParam(HQL_SELECT_USER_VIEW_BY_VIEW_NAME, "uv_view_name", uv_view_name);

		return !list.isEmpty() ? list.get(0) : null;
	}

}
