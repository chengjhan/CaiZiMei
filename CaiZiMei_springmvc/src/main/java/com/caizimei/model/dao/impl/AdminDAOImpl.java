/*
 * CaiZiMei
 * File: AdminDAOImpl.java
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

import com.caizimei.model.dao.AdminDAO;
import com.caizimei.model.entity.AdminBean;

/**
 * admin_user DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "adminDAO")
public class AdminDAOImpl implements AdminDAO {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 搜尋全部總公司
	 * 
	 * @return List<AdminBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<AdminBean> select() {

		return (List<AdminBean>) hibernateTemplate.find("from AdminBean order by ad_id asc");
	}

	/**
	 * 新增總公司
	 * 
	 * @param adminBean-->AdminBean
	 * @return adminBean-->AdminBean
	 */
	@Override
	public AdminBean insert(AdminBean adminBean) {

		hibernateTemplate.save(adminBean);

		return adminBean;
	}

}
