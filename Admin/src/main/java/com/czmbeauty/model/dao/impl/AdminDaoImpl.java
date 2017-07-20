/*
 * CaiZiMei
 * File: AdminDaoImpl.java
 * Author: 詹晟
 * Date: 2017/7/21
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao.impl;

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

		return (List<AdminBean>) hibernateTemplate.find("from AdminBean order by ad_id asc");
	}

	/**
	 * 管理員流水號搜尋
	 * 
	 * @param ad_id-->管理員流水號
	 * @return AdminBean
	 */
	@Override
	public AdminBean selectByAd_id(Integer ad_id) {

		return hibernateTemplate.get(AdminBean.class, ad_id);
	}

	/**
	 * 管理員帳號搜尋
	 * 
	 * @param ad_username-->管理員帳號
	 * @return AdminBean
	 * @return null
	 */
	@Override
	@SuppressWarnings("unchecked")
	public AdminBean selectByAd_username(String ad_username) {

		List<AdminBean> list = (List<AdminBean>) hibernateTemplate
				.findByNamedParam("from AdminBean where ad_username=:ad_username", "ad_username", ad_username);

		if (!list.isEmpty()) {

			return list.get(0);
		}
		return null;
	}

	/**
	 * 管理員信箱搜尋
	 * 
	 * @param ad_email-->管理員信箱
	 * @return AdminBean
	 * @return null
	 */
	@Override
	@SuppressWarnings("unchecked")
	public AdminBean selectByAd_email(String ad_email) {

		List<AdminBean> list = (List<AdminBean>) hibernateTemplate
				.findByNamedParam("from AdminBean where ad_email=:ad_email", "ad_email", ad_email);

		if (!list.isEmpty()) {

			return list.get(0);
		}
		return null;
	}

	/**
	 * 新增管理員
	 * 
	 * @param adminBean-->AdminBean
	 * @return adminBean-->AdminBean
	 */
	@Override
	public AdminBean insert(AdminBean adminBean) {

		hibernateTemplate.save(adminBean);

		return adminBean;
	}

	/**
	 * 修改資料
	 * 
	 * @param adminBean-->AdminBean
	 * @return adminBean-->AdminBean
	 */
	@Override
	public AdminBean update(AdminBean adminBean) {

		hibernateTemplate.update(adminBean);

		return adminBean;
	}

	/**
	 * 修改密碼
	 * 
	 * @param ad_id-->管理員流水號
	 * @param ad_password_new_hashed-->新密碼(雜湊)
	 * @return adminBean-->AdminBean
	 */
	@Override
	public AdminBean updateAd_password(Integer ad_id, String ad_password_new_hashed) {

		AdminBean adminBean = hibernateTemplate.get(AdminBean.class, ad_id);

		adminBean.setAd_password(ad_password_new_hashed);
		adminBean.setAd_update_pwd_time(new java.util.Date());

		return adminBean;
	}

	/**
	 * 切換狀態
	 * 
	 * @param ad_id-->管理員流水號
	 * @return adminBean-->AdminBean
	 */
	@Override
	public AdminBean updateAd_status(Integer ad_id) {

		AdminBean adminBean = hibernateTemplate.get(AdminBean.class, ad_id);

		if (adminBean.getAd_status() == 1) {

			// 關閉帳號
			adminBean.setAd_status(0);
			adminBean.setAd_status_time(new java.util.Date());
		} else {

			// 開啟帳號
			adminBean.setAd_status(1);
			adminBean.setAd_status_time(new java.util.Date());
		}

		return adminBean;
	}

}
