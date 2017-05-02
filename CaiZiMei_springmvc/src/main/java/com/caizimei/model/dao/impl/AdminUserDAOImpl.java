/*
 * CaiZiMei
 * File: AdminUserDAOImpl.java
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

import com.caizimei.model.dao.AdminUserDAO;
import com.caizimei.model.entity.AdminUserBean;

/**
 * admin_user DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "adminUserDAO")
public class AdminUserDAOImpl implements AdminUserDAO {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 搜尋全部管理員
	 * 
	 * @return List<AdminUserBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<AdminUserBean> select() {

		return (List<AdminUserBean>) hibernateTemplate.find("from AdminUserBean order by adu_id asc");
	}

	/**
	 * 管理員流水號搜尋
	 * 
	 * @param adu_id-->管理員流水號
	 * @return AdminUserBean
	 */
	@Override
	public AdminUserBean selectByAdu_id(Integer adu_id) {

		return hibernateTemplate.get(AdminUserBean.class, adu_id);
	}

	/**
	 * 管理員帳號搜尋
	 * 
	 * @param adu_username-->管理員帳號
	 * @return AdminUserBean
	 * @return null
	 */
	@Override
	@SuppressWarnings("unchecked")
	public AdminUserBean selectByAdu_username(String adu_username) {

		List<AdminUserBean> list = (List<AdminUserBean>) hibernateTemplate
				.findByNamedParam("from AdminUserBean where adu_username=:adu_username", "adu_username", adu_username);

		if (!list.isEmpty()) {

			return list.get(0);
		}
		return null;
	}

	/**
	 * 新增管理員
	 * 
	 * @param adminUserBean-->AdminUserBean
	 * @return adminUserBean-->AdminUserBean
	 */
	@Override
	public AdminUserBean insert(AdminUserBean adminUserBean) {

		hibernateTemplate.save(adminUserBean);

		return adminUserBean;
	}

	/**
	 * 修改管理員資料
	 * 
	 * @param newAdminUserBean-->AdminUserBean
	 * @return adminUserBean-->AdminUserBean
	 */
	@Override
	public AdminUserBean update(AdminUserBean newAdminUserBean) {

		AdminUserBean adminUserBean = hibernateTemplate.get(AdminUserBean.class, newAdminUserBean.getAdu_id());

		adminUserBean.setAdu_lastname(newAdminUserBean.getAdu_lastname());
		adminUserBean.setAdu_firstname(newAdminUserBean.getAdu_firstname());
		adminUserBean.setAdu_eng_name(newAdminUserBean.getAdu_eng_name());
		adminUserBean.setAdu_email(newAdminUserBean.getAdu_email());
		adminUserBean.setAdu_mobilephone(newAdminUserBean.getAdu_mobilephone());
		adminUserBean.setAdu_update_info_time(new java.util.Date());

		return adminUserBean;
	}

	/**
	 * 修改管理員密碼
	 * 
	 * @param adu_id-->管理員流水號
	 * @param adu_password_new_hashed-->新密碼(雜湊)
	 * @return adminUserBean-->AdminUserBean
	 */
	@Override
	public AdminUserBean updateAdu_password(Integer adu_id, String adu_password_new_hashed) {

		AdminUserBean adminUserBean = hibernateTemplate.get(AdminUserBean.class, adu_id);
		adminUserBean.setAdu_password(adu_password_new_hashed);
		adminUserBean.setAdu_update_pass_time(new java.util.Date());

		return adminUserBean;
	}

	/**
	 * 更新登入次數
	 * 
	 * @param adu_id-->管理員流水號
	 * @return adminUserBean-->AdminUserBean
	 */
	@Override
	public AdminUserBean updateAdu_signin_number(Integer adu_id) {

		AdminUserBean adminUserBean = hibernateTemplate.get(AdminUserBean.class, adu_id);
		Integer adu_signin_number = hibernateTemplate.get(AdminUserBean.class, adu_id).getAdu_signin_number();
		adminUserBean.setAdu_signin_number(adu_signin_number + 1);

		return adminUserBean;
	}

	/**
	 * 更新登入IP
	 * 
	 * @param adu_id-->管理員流水號
	 * @param adu_signin_ip-->登入IP
	 * @return adminUserBean-->AdminUserBean
	 */
	@Override
	public AdminUserBean updateAdu_signin_ip(Integer adu_id, String adu_signin_ip) {

		AdminUserBean adminUserBean = hibernateTemplate.get(AdminUserBean.class, adu_id);
		adminUserBean.setAdu_signin_ip(adu_signin_ip);

		return adminUserBean;
	}

	/**
	 * 更新登入時間
	 * 
	 * @param adu_id-->管理員流水號
	 * @return adminUserBean-->AdminUserBean
	 */
	@Override
	public AdminUserBean updateAdu_signin_time(Integer adu_id) {

		AdminUserBean adminUserBean = hibernateTemplate.get(AdminUserBean.class, adu_id);
		adminUserBean.setAdu_signin_time(new java.util.Date());

		return adminUserBean;
	}

}
