/*
 * CaiZiMei
 * File: AdminViewDaoImpl.java
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

import com.czmbeauty.model.dao.AdminViewDao;
import com.czmbeauty.model.entity.AdminViewBean;

/**
 * admin_view DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "adminViewDao")
public class AdminViewDaoImpl implements AdminViewDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 視圖名搜尋
	 * 
	 * @param av_view_name
	 *            String --> 視圖名
	 * @return AdminViewBean
	 * @return null
	 */
	@Override
	@SuppressWarnings("unchecked")
	public AdminViewBean selectByAv_view_name(String av_view_name) {

		List<AdminViewBean> list = (List<AdminViewBean>) hibernateTemplate
				.findByNamedParam(HQL_SELECT_ADMIN_VIEW_BY_VIEW_NAME, "av_view_name", av_view_name);

		return !list.isEmpty() ? list.get(0) : null;
	}

}
