/*
 * CaiZiMei
 * File: ClinicUserDAOImpl.java
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

import com.caizimei.model.dao.ClinicUserDAO;
import com.caizimei.model.entity.ClinicUserBean;

/**
 * clinicUser DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "clinicUserDAO")
public class ClinicUserDAOImpl implements ClinicUserDAO {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 搜尋全部診所使用者
	 * 
	 * @return List<ClinicUserBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ClinicUserBean> select() {

		return (List<ClinicUserBean>) hibernateTemplate.find("from ClinicUserBean order by cu_id asc");
	}

	/**
	 * 診所使用者流水號搜尋
	 * 
	 * @param cu_id-->診所使用者流水號
	 * @return ClinicUserBean
	 */
	@Override
	public ClinicUserBean selectByCu_id(Integer cu_id) {

		return hibernateTemplate.get(ClinicUserBean.class, cu_id);
	}

	/**
	 * 診所使用者帳號搜尋
	 * 
	 * @param cu_username-->診所使用者帳號
	 * @return ClinicUserBean
	 * @return null
	 */
	@Override
	@SuppressWarnings("unchecked")
	public ClinicUserBean selectByCu_username(String cu_username) {

		List<ClinicUserBean> list = (List<ClinicUserBean>) hibernateTemplate
				.findByNamedParam("from ClinicUserBean where cu_username=:cu_username", "cu_username", cu_username);

		if (!list.isEmpty()) {

			return list.get(0);
		}
		return null;
	}

	/**
	 * 新增診所使用者
	 * 
	 * @param clinicUserBean-->ClinicUserBean
	 * @return clinicUserBean-->ClinicUserBean
	 */
	@Override
	public ClinicUserBean insert(ClinicUserBean clinicUserBean) {

		hibernateTemplate.save(clinicUserBean);

		return clinicUserBean;
	}

	/**
	 * 修改診所使用者資料
	 * 
	 * @param newClinicUserBean-->ClinicUserBean
	 * @return clinicUserBean-->ClinicUserBean
	 */
	@Override
	public ClinicUserBean update(ClinicUserBean newClinicUserBean) {

		ClinicUserBean clinicUserBean = hibernateTemplate.get(ClinicUserBean.class, newClinicUserBean.getCu_id());

		clinicUserBean.setCu_lastname(newClinicUserBean.getCu_lastname());
		clinicUserBean.setCu_firstname(newClinicUserBean.getCu_firstname());
		clinicUserBean.setCu_eng_name(newClinicUserBean.getCu_eng_name());
		clinicUserBean.setCu_email(newClinicUserBean.getCu_email());
		clinicUserBean.setCu_mobilephone(newClinicUserBean.getCu_mobilephone());
		clinicUserBean.setCu_update_info_time(new java.util.Date());

		return clinicUserBean;
	}

	/**
	 * 修改診所使用者密碼
	 * 
	 * @param cu_id-->診所使用者流水號
	 * @param cu_password_new_hashed-->新密碼(雜湊)
	 * @return clinicUserBean-->ClinicUserBean
	 */
	@Override
	public ClinicUserBean updateCu_password(Integer cu_id, String cu_password_new_hashed) {

		ClinicUserBean clinicUserBean = hibernateTemplate.get(ClinicUserBean.class, cu_id);
		clinicUserBean.setCu_password(cu_password_new_hashed);
		clinicUserBean.setCu_update_pass_time(new java.util.Date());

		return clinicUserBean;
	}

	/**
	 * 更新登入次數
	 * 
	 * @param cu_id-->診所使用者流水號
	 * @return clinicUserBean-->ClinicUserBean
	 */
	@Override
	public ClinicUserBean updateCu_signin_number(Integer cu_id) {

		ClinicUserBean clinicUserBean = hibernateTemplate.get(ClinicUserBean.class, cu_id);
		Integer cu_signin_number = hibernateTemplate.get(ClinicUserBean.class, cu_id).getCu_signin_number();
		clinicUserBean.setCu_signin_number(cu_signin_number + 1);

		return clinicUserBean;
	}

	/**
	 * 更新登入IP
	 * 
	 * @param cu_id-->診所使用者流水號
	 * @param cu_signin_ip-->登入IP
	 * @return clinicUserBean-->ClinicUserBean
	 */
	@Override
	public ClinicUserBean updateCu_signin_ip(Integer cu_id, String cu_signin_ip) {

		ClinicUserBean clinicUserBean = hibernateTemplate.get(ClinicUserBean.class, cu_id);
		clinicUserBean.setCu_signin_ip(cu_signin_ip);

		return clinicUserBean;
	}

	/**
	 * 更新登入時間
	 * 
	 * @param cu_id-->診所使用者流水號
	 * @return clinicUserBean-->ClinicUserBean
	 */
	@Override
	public ClinicUserBean updateCu_signin_time(Integer cu_id) {

		ClinicUserBean clinicUserBean = hibernateTemplate.get(ClinicUserBean.class, cu_id);
		clinicUserBean.setCu_signin_time(new java.util.Date());

		return clinicUserBean;
	}

}
