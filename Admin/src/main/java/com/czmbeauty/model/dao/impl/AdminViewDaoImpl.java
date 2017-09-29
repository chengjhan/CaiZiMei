/*
 * CaiZiMei
 * File: AdminViewDaoImpl.java
 * Author: 詹晟
 * Date: 2017/9/29
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
	 * 頁面名搜尋
	 * 
	 * @param av_page_name
	 *            String --> 頁面名
	 * @return AdminViewBean
	 * @return null
	 */
	@Override
	@SuppressWarnings("unchecked")
	public AdminViewBean selectByAv_page_name(String av_page_name) {

		List<AdminViewBean> list = (List<AdminViewBean>) hibernateTemplate
				.findByNamedParam(HQL_SELECT_ADMIN_VIEW_BY_PAGE_NAME, "av_page_name", av_page_name);

		return (!list.isEmpty()) ? list.get(0) : null;
	}

}
