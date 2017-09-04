/*
 * CaiZiMei
 * File: AdminDaoImpl.java
 * Author: 詹晟
 * Date: 2017/9/4
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao.impl;

import static com.czmbeauty.common.constants.HqlConstants.HQL_SELECT_ALL_ADMIN;
import static com.czmbeauty.common.constants.HqlConstants.HQL_SELECT_OPEN_ADMIN_BY_EMAIL;
import static com.czmbeauty.common.constants.HqlConstants.HQL_SELECT_OPEN_ADMIN_BY_USERNAME;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.czmbeauty.model.dao.AdminDao;
import com.czmbeauty.model.entity.AdminBean;

/**
 * admin DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "adminDao")
public class AdminDaoImpl implements AdminDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 搜尋所有管理員
	 * 
	 * @return List<AdminBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<AdminBean> selectAll() {

		return (List<AdminBean>) hibernateTemplate.find(HQL_SELECT_ALL_ADMIN);
	}

	/**
	 * 管理員流水號搜尋
	 * 
	 * @param ad_id
	 *            Integer --> 管理員流水號
	 * @return AdminBean
	 */
	@Override
	public AdminBean selectByAd_id(Integer ad_id) {

		return hibernateTemplate.get(AdminBean.class, ad_id);
	}

	/**
	 * 管理員帳號搜尋
	 * 
	 * @param ad_username
	 *            String --> 管理員帳號
	 * @return AdminBean
	 * @return null
	 */
	@Override
	@SuppressWarnings("unchecked")
	public AdminBean selectByAd_username(String ad_username) {

		List<AdminBean> list = (List<AdminBean>) hibernateTemplate.findByNamedParam(HQL_SELECT_OPEN_ADMIN_BY_USERNAME,
				"ad_username", ad_username);

		return (!list.isEmpty()) ? list.get(0) : null;
	}

	/**
	 * 管理員信箱搜尋
	 * 
	 * @param ad_email
	 *            String --> 管理員信箱
	 * @return AdminBean
	 * @return null
	 */
	@Override
	@SuppressWarnings("unchecked")
	public AdminBean selectByAd_email(String ad_email) {

		List<AdminBean> list = (List<AdminBean>) hibernateTemplate.findByNamedParam(HQL_SELECT_OPEN_ADMIN_BY_EMAIL,
				"ad_email", ad_email);

		return (!list.isEmpty()) ? list.get(0) : null;
	}

	/**
	 * 新增管理員
	 * 
	 * @param adminBean
	 *            AdminBean
	 * @return AdminBean
	 */
	@Override
	public AdminBean insert(AdminBean adminBean) {

		hibernateTemplate.save(adminBean);

		return adminBean;
	}

	/**
	 * 修改資料
	 * 
	 * @param adminBean
	 *            AdminBean
	 * @return AdminBean
	 */
	@Override
	public AdminBean update(AdminBean adminBean) {

		hibernateTemplate.clear();
		hibernateTemplate.update(adminBean);

		return adminBean;
	}

}
