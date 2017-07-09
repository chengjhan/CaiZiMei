/*
 * CaiZiMei
 * File: AdminLogDaoImpl.java
 * Author: 詹晟
 * Date: 2017/7/10
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.caizimei.model.dao.AdminLogDao;
import com.caizimei.model.entity.AdminLogBean;

/**
 * admin_log Dao implement
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
	 * 搜尋全部管理員日誌
	 * 
	 * @return List<AdminLogBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<AdminLogBean> selectAll() {

		return (List<AdminLogBean>) hibernateTemplate.find("from AdminLogBean order by al_insert_time asc");
	}

	/**
	 * 管理員流水號搜尋
	 * 
	 * @param al_a_id-->管理員流水號
	 * @return List<AdminLogBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<AdminLogBean> selectByAl_a_id(Integer al_a_id) {

		return (List<AdminLogBean>) hibernateTemplate.findByNamedParam("from AdminLogBean where al_a_id=:al_a_id",
				"al_a_id", al_a_id);
	}

	/**
	 * 新增管理員日誌
	 * 
	 * @param adminLogBean-->AdminLogBean
	 * @return adminLogBean-->AdminLogBean
	 */
	@Override
	public AdminLogBean insert(AdminLogBean adminLogBean) {

		hibernateTemplate.save(adminLogBean);

		return adminLogBean;
	}

}
