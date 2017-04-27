/*
 * CaiZiMei
 * File: SpecialistDAOImpl.java
 * Author: 詹晟
 * Date: 2017/4/27
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.caizimei.model.dao.SpecialistDAO;
import com.caizimei.model.entity.SpecialistBean;

/**
 * specialist DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "specialistDAO")
public class SpecialistDAOImpl implements SpecialistDAO {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 搜尋全部專員
	 * 
	 * @return List<SpecialistBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<SpecialistBean> select() {

		return (List<SpecialistBean>) hibernateTemplate.find("from SpecialistBean order by s_id asc");
	}

	/**
	 * 專員流水號搜尋
	 * 
	 * @param s_id-->專員流水號
	 * @return SpecialistBean
	 */
	@Override
	public SpecialistBean selectByS_id(Integer s_id) {

		return hibernateTemplate.get(SpecialistBean.class, s_id);
	}

	/**
	 * 專員帳號搜尋
	 * 
	 * @param s_username-->專員帳號
	 * @return SpecialistBean
	 * @return null
	 */
	@Override
	@SuppressWarnings("unchecked")
	public SpecialistBean selectByS_username(String s_username) {

		List<SpecialistBean> list = (List<SpecialistBean>) hibernateTemplate
				.findByNamedParam("from SpecialistBean where s_username=:s_username", "s_username", s_username);

		if (!list.isEmpty()) {

			return list.get(0);
		}
		return null;
	}

	/**
	 * 新增專員
	 * 
	 * @param specialistBean-->SpecialistBean
	 * @return specialistBean-->SpecialistBean
	 */
	@Override
	public SpecialistBean insert(SpecialistBean specialistBean) {

		hibernateTemplate.save(specialistBean);

		return specialistBean;
	}

	/**
	 * 修改專員資料
	 * 
	 * @param newSpecialistBean-->SpecialistBean
	 * @return specialistBean-->SpecialistBean
	 */
	@Override
	public SpecialistBean update(SpecialistBean newSpecialistBean) {

		SpecialistBean specialistBean = hibernateTemplate.get(SpecialistBean.class, newSpecialistBean.getS_id());

		specialistBean.setS_lastname(newSpecialistBean.getS_lastname());
		specialistBean.setS_firstname(newSpecialistBean.getS_firstname());
		specialistBean.setS_eng_name(newSpecialistBean.getS_eng_name());
		specialistBean.setS_email(newSpecialistBean.getS_email());
		specialistBean.setS_mobilephone(newSpecialistBean.getS_mobilephone());
		specialistBean.setS_update_info_time(new java.util.Date());

		return specialistBean;
	}

	/**
	 * 修改專員密碼
	 * 
	 * @param s_id-->專員流水號
	 * @param s_password_new_hashed-->新密碼(雜湊)
	 * @return specialistBean-->SpecialistBean
	 */
	@Override
	public SpecialistBean updateS_password(Integer s_id, String s_password_new_hashed) {

		SpecialistBean specialistBean = hibernateTemplate.get(SpecialistBean.class, s_id);
		specialistBean.setS_password(s_password_new_hashed);
		specialistBean.setS_update_pass_time(new java.util.Date());

		return specialistBean;
	}

	/**
	 * 更新登入次數
	 * 
	 * @param s_id-->專員流水號
	 * @return specialistBean-->SpecialistBean
	 */
	@Override
	public SpecialistBean updateS_signin_number(Integer s_id) {

		SpecialistBean specialistBean = hibernateTemplate.get(SpecialistBean.class, s_id);
		Integer s_signin_number = hibernateTemplate.get(SpecialistBean.class, s_id).getS_signin_number();
		specialistBean.setS_signin_number(s_signin_number + 1);

		return specialistBean;
	}

	/**
	 * 更新登入IP
	 * 
	 * @param s_id-->專員流水號
	 * @param s_signin_ip-->登入IP
	 * @return specialistBean-->SpecialistBean
	 */
	@Override
	public SpecialistBean updateS_signin_ip(Integer s_id, String s_signin_ip) {

		SpecialistBean specialistBean = hibernateTemplate.get(SpecialistBean.class, s_id);
		specialistBean.setS_signin_ip(s_signin_ip);

		return specialistBean;
	}

	/**
	 * 更新登入時間
	 * 
	 * @param s_id-->專員流水號
	 * @return specialistBean-->SpecialistBean
	 */
	@Override
	public SpecialistBean updateS_signin_time(Integer s_id) {

		SpecialistBean specialistBean = hibernateTemplate.get(SpecialistBean.class, s_id);
		specialistBean.setS_signin_time(new java.util.Date());

		return specialistBean;
	}

}
