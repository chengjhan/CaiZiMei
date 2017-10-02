/*
 * CaiZiMei
 * File: AdminActionDaoImpl.java
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

import com.czmbeauty.model.dao.AdminActionDao;
import com.czmbeauty.model.entity.AdminActionBean;

/**
 * admin_action DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "adminActionDao")
public class AdminActionDaoImpl implements AdminActionDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 動作名搜尋
	 * 
	 * @param aa_action_name
	 *            String --> 動作名
	 * @return AdminActionBean
	 * @return null
	 */
	@Override
	@SuppressWarnings("unchecked")
	public AdminActionBean selectByAa_action_name(String aa_action_name) {

		List<AdminActionBean> list = (List<AdminActionBean>) hibernateTemplate
				.findByNamedParam(HQL_SELECT_ADMIN_ACTION_BY_ACTION_NAME, "aa_action_name", aa_action_name);

		return !list.isEmpty() ? list.get(0) : null;
	}

}
