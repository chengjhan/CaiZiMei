/*
 * CaiZiMei
 * File: AdministratorDAOImpl.java
 * Author: 詹晟
 * Date: 2017/4/10
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.caizimei.model.dao.AdminUserDAO;
import com.caizimei.model.entity.AdministratorBean;

/**
 * administrator DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "administratorDAO")
public class AdministratorDAOImpl implements AdminUserDAO {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 搜尋全部管理員
	 * 
	 * @return List<AdministratorBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<AdministratorBean> select() {

		return (List<AdministratorBean>) hibernateTemplate.find("from AdministratorBean order by a_id asc");
	}

	/**
	 * 管理員流水號搜尋
	 * 
	 * @param a_id-->管理員流水號
	 * @return AdministratorBean
	 */
	@Override
	public AdministratorBean selectByA_id(Integer a_id) {

		return hibernateTemplate.get(AdministratorBean.class, a_id);
	}

	/**
	 * 管理員帳號搜尋
	 * 
	 * @param a_username-->管理員帳號
	 * @return AdministratorBean
	 * @return null
	 */
	@Override
	@SuppressWarnings("unchecked")
	public AdministratorBean selectByA_username(String a_username) {

		List<AdministratorBean> list = (List<AdministratorBean>) hibernateTemplate
				.findByNamedParam("from AdministratorBean where a_username=:a_username", "a_username", a_username);

		if (!list.isEmpty()) {

			return list.get(0);
		}
		return null;
	}

	/**
	 * 新增管理員
	 * 
	 * @param administratorBean-->AdministratorBean
	 * @return administratorBean-->AdministratorBean
	 */
	@Override
	public AdministratorBean insert(AdministratorBean administratorBean) {

		hibernateTemplate.save(administratorBean);

		return administratorBean;
	}

	/**
	 * 修改管理員資料
	 * 
	 * @param newAdministratorBean-->AdministratorBean
	 * @return administratorBean-->AdministratorBean
	 */
	@Override
	public AdministratorBean update(AdministratorBean newAdministratorBean) {

		AdministratorBean administratorBean = hibernateTemplate.get(AdministratorBean.class,
				newAdministratorBean.getA_id());

		administratorBean.setA_lastname(newAdministratorBean.getA_lastname());
		administratorBean.setA_firstname(newAdministratorBean.getA_firstname());
		administratorBean.setA_eng_name(newAdministratorBean.getA_eng_name());
		administratorBean.setA_email(newAdministratorBean.getA_email());
		administratorBean.setA_mobilephone(newAdministratorBean.getA_mobilephone());
		administratorBean.setA_update_info_time(new java.util.Date());

		return administratorBean;
	}

	/**
	 * 修改管理員密碼
	 * 
	 * @param a_id-->管理員流水號
	 * @param a_password_new_hashed-->新密碼(雜湊)
	 * @return administratorBean-->AdministratorBean
	 */
	@Override
	public AdministratorBean updateA_password(Integer a_id, String a_password_new_hashed) {

		AdministratorBean administratorBean = hibernateTemplate.get(AdministratorBean.class, a_id);
		administratorBean.setA_password(a_password_new_hashed);
		administratorBean.setA_update_pass_time(new java.util.Date());

		return administratorBean;
	}

	/**
	 * 更新登入次數
	 * 
	 * @param a_id-->管理員流水號
	 * @return administratorBean-->AdministratorBean
	 */
	@Override
	public AdministratorBean updateA_signin_number(Integer a_id) {

		AdministratorBean administratorBean = hibernateTemplate.get(AdministratorBean.class, a_id);
		Integer a_signin_number = hibernateTemplate.get(AdministratorBean.class, a_id).getA_signin_number();
		administratorBean.setA_signin_number(a_signin_number + 1);

		return administratorBean;
	}

	/**
	 * 更新登入IP
	 * 
	 * @param a_id-->管理員流水號
	 * @param a_signin_ip-->登入IP
	 * @return administratorBean-->AdministratorBean
	 */
	@Override
	public AdministratorBean updateA_signin_ip(Integer a_id, String a_signin_ip) {

		AdministratorBean administratorBean = hibernateTemplate.get(AdministratorBean.class, a_id);
		administratorBean.setA_signin_ip(a_signin_ip);

		return administratorBean;
	}

	/**
	 * 更新登入時間
	 * 
	 * @param a_id-->管理員流水號
	 * @return administratorBean-->AdministratorBean
	 */
	@Override
	public AdministratorBean updateA_signin_time(Integer a_id) {

		AdministratorBean administratorBean = hibernateTemplate.get(AdministratorBean.class, a_id);
		administratorBean.setA_signin_time(new java.util.Date());

		return administratorBean;
	}

}
