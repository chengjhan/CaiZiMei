/*
 * CaiZiMei
 * File: AdminLogDaoImpl.java
 * Author: 詹晟
 * Date: 2017/8/13
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao.impl;

import static com.czmbeauty.common.constants.HqlConstants.HQL_SELECT_ALL_ADMIN_LOG;
import static com.czmbeauty.common.constants.HqlConstants.HQL_SELECT_ADMIN_LOG_BY_ADMIN;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.czmbeauty.model.dao.AdminLogDao;
import com.czmbeauty.model.entity.AdminLogBean;

/**
 * admin_log DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "adminLogDao")
public class AdminLogDaoImpl implements AdminLogDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 搜尋所有管理員日誌
	 * 
	 * @return List<AdminLogBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<AdminLogBean> selectAll() {

		return (List<AdminLogBean>) hibernateTemplate.find(HQL_SELECT_ALL_ADMIN_LOG);
	}

	/**
	 * 管理員流水號搜尋
	 * 
	 * @param al_ad_id
	 *            Integer --> 管理員流水號
	 * @return List<AdminLogBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<AdminLogBean> selectByAl_ad_id(Integer al_ad_id) {

		return (List<AdminLogBean>) hibernateTemplate.findByNamedParam(HQL_SELECT_ADMIN_LOG_BY_ADMIN, "al_ad_id",
				al_ad_id);
	}

	/**
	 * 新增管理員日誌
	 * 
	 * @param adminLogBean
	 *            AdminLogBean
	 * @return AdminLogBean
	 */
	@Override
	public AdminLogBean insert(AdminLogBean adminLogBean) {

		hibernateTemplate.save(adminLogBean);

		return adminLogBean;
	}

}
