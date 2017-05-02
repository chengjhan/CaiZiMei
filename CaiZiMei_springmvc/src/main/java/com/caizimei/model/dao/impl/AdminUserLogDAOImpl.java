/*
 * CaiZiMei
 * File: AdminUserLogDAOImpl.java
 * Author: 詹晟
 * Date: 2017/5/3
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.caizimei.model.dao.AdminUserLogDAO;
import com.caizimei.model.entity.AdminUserLogBean;

/**
 * admin_user_log DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "adminUserLogDAO")
public class AdminUserLogDAOImpl implements AdminUserLogDAO {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 搜尋全部管理員日誌
	 * 
	 * @return List<AdminUserLogBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<AdminUserLogBean> select() {

		return (List<AdminUserLogBean>) hibernateTemplate.find("from AdminUserLogBean order by adul_insert_time asc");
	}

	/**
	 * 管理員流水號搜尋
	 * 
	 * @param adul_adu_id-->管理員流水號
	 * @return List<AdminUserLogBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<AdminUserLogBean> selectByAdul_adu_id(Integer adul_adu_id) {

		return (List<AdminUserLogBean>) hibernateTemplate
				.findByNamedParam("from AdminUserLogBean where adul_adu_id=:adul_adu_id", "adul_adu_id", adul_adu_id);
	}

	/**
	 * 新增管理員日誌
	 * 
	 * @param adminUserLogBean-->AdminUserLogBean
	 * @return adminUserLogBean-->AdminUserLogBean
	 */
	@Override
	public AdminUserLogBean insert(AdminUserLogBean adminUserLogBean) {

		hibernateTemplate.save(adminUserLogBean);

		return adminUserLogBean;
	}

}
