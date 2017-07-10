/*
 * CaiZiMei
 * File: AdminDaoImpl.java
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

import com.caizimei.model.dao.AdminDao;
import com.caizimei.model.entity.AdminBean;

/**
 * admin dao implement
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
	 * 搜尋全部管理員
	 * 
	 * @return List<AdminBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<AdminBean> selectAll() {

		return (List<AdminBean>) hibernateTemplate.find("from AdminBean order by a_id asc");
	}

	/**
	 * 管理員流水號搜尋
	 * 
	 * @param a_id-->管理員流水號
	 * @return AdminBean
	 */
	@Override
	public AdminBean selectByA_id(Integer a_id) {

		return hibernateTemplate.get(AdminBean.class, a_id);
	}

	/**
	 * 管理員帳號搜尋
	 * 
	 * @param a_username-->管理員帳號
	 * @return AdminBean
	 * @return null
	 */
	@Override
	@SuppressWarnings("unchecked")
	public AdminBean selectByA_username(String a_username) {

		List<AdminBean> list = (List<AdminBean>) hibernateTemplate
				.findByNamedParam("from AdminBean where a_username=:a_username", "a_username", a_username);

		if (!list.isEmpty()) {

			return list.get(0);
		}
		return null;
	}

	/**
	 * 管理員信箱搜尋
	 * 
	 * @param a_email-->管理員信箱
	 * @return AdminBean
	 * @return null
	 */
	@Override
	@SuppressWarnings("unchecked")
	public AdminBean selectByA_email(String a_email) {

		List<AdminBean> list = (List<AdminBean>) hibernateTemplate
				.findByNamedParam("from AdminBean where a_email=:a_email", "a_email", a_email);

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
	 * @param newAdminBean-->AdminBean
	 * @return adminBean-->AdminBean
	 */
	@Override
	public AdminBean update(AdminBean newAdminBean) {

		AdminBean adminBean = hibernateTemplate.get(AdminBean.class, newAdminBean.getA_id());

		adminBean.setA_lastname(newAdminBean.getA_lastname());
		adminBean.setA_firstname(newAdminBean.getA_firstname());
		adminBean.setA_email(newAdminBean.getA_email());
		adminBean.setA_update_info_time(new java.util.Date());

		return adminBean;
	}

	/**
	 * 修改密碼
	 * 
	 * @param a_id-->管理員流水號
	 * @param a_password_new_hashed-->新密碼(雜湊)
	 * @return adminBean-->AdminBean
	 */
	@Override
	public AdminBean updateA_password(Integer a_id, String a_password_new_hashed) {

		AdminBean adminBean = hibernateTemplate.get(AdminBean.class, a_id);
		adminBean.setA_password(a_password_new_hashed);
		adminBean.setA_update_pwd_time(new java.util.Date());

		return adminBean;
	}

	/**
	 * 更新登入次數
	 * 
	 * @param a_id-->管理員流水號
	 * @return adminBean-->AdminBean
	 */
	@Override
	public AdminBean updateA_signin_number(Integer a_id) {

		AdminBean adminBean = hibernateTemplate.get(AdminBean.class, a_id);
		Integer a_signin_number = hibernateTemplate.get(AdminBean.class, a_id).getA_signin_number();
		adminBean.setA_signin_number(a_signin_number + 1);

		return adminBean;
	}

	/**
	 * 更新登入IP
	 * 
	 * @param a_id-->管理員流水號
	 * @param a_signin_ip-->登入IP
	 * @return adminBean-->AdminBean
	 */
	@Override
	public AdminBean updateA_signin_ip(Integer a_id, String a_signin_ip) {

		AdminBean adminBean = hibernateTemplate.get(AdminBean.class, a_id);
		adminBean.setA_signin_ip(a_signin_ip);

		return adminBean;
	}

	/**
	 * 更新登入時間
	 * 
	 * @param a_id-->管理員流水號
	 * @return adminBean-->AdminBean
	 */
	@Override
	public AdminBean updateA_signin_time(Integer a_id) {

		AdminBean adminBean = hibernateTemplate.get(AdminBean.class, a_id);
		adminBean.setA_signin_time(new java.util.Date());

		return adminBean;
	}

	/**
	 * 切換狀態
	 * 
	 * @param a_id-->管理員流水號
	 * @return adminBean-->AdminBean
	 */
	@Override
	public AdminBean updateA_status(Integer a_id) {

		AdminBean adminBean = hibernateTemplate.get(AdminBean.class, a_id);

		if (adminBean.getA_status() == 1) {

			// 關閉帳號
			adminBean.setA_status(0);
			adminBean.setA_status_time(new java.util.Date());
		} else {

			// 開啟帳號
			adminBean.setA_status(1);
			adminBean.setA_status_time(new java.util.Date());
		}

		return adminBean;
	}

}
