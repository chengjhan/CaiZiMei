/*
 * CaiZiMei
 * File: EmployeeDAOImpl.java
 * Author: 詹晟
 * Date: 2017/4/12
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.caizimei.model.dao.AgentUserDAO;
import com.caizimei.model.entity.EmployeeBean;

/**
 * employee DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "employeeDAO")
public class EmployeeDAOImpl implements AgentUserDAO {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 搜尋全部員工
	 * 
	 * @return List<EmployeeBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<EmployeeBean> select() {

		return (List<EmployeeBean>) hibernateTemplate.find("from EmployeeBean order by e_id asc");
	}

	/**
	 * 員工流水號搜尋
	 * 
	 * @param e_id-->員工流水號
	 * @return EmployeeBean
	 */
	@Override
	public EmployeeBean selectByE_id(Integer e_id) {

		return hibernateTemplate.get(EmployeeBean.class, e_id);
	}

	/**
	 * 員工帳號搜尋
	 * 
	 * @param e_username-->員工帳號
	 * @return EmployeeBean
	 * @return null
	 */
	@Override
	@SuppressWarnings("unchecked")
	public EmployeeBean selectByE_username(String e_username) {

		List<EmployeeBean> list = (List<EmployeeBean>) hibernateTemplate
				.findByNamedParam("from EmployeeBean where e_username=:e_username", "e_username", e_username);

		if (!list.isEmpty()) {

			return list.get(0);
		}
		return null;
	}

	/**
	 * 新增員工
	 * 
	 * @param employeeBean-->EmployeeBean
	 * @return employeeBean-->EmployeeBean
	 */
	@Override
	public EmployeeBean insert(EmployeeBean employeeBean) {

		hibernateTemplate.save(employeeBean);

		return employeeBean;
	}

	/**
	 * 修改員工資料
	 * 
	 * @param newEmployeeBean-->EmployeeBean
	 * @return employeeBean-->EmployeeBean
	 */
	@Override
	public EmployeeBean update(EmployeeBean newEmployeeBean) {

		EmployeeBean employeeBean = hibernateTemplate.get(EmployeeBean.class,
				newEmployeeBean.getE_id());

		employeeBean.setE_lastname(newEmployeeBean.getE_lastname());
		employeeBean.setE_firstname(newEmployeeBean.getE_firstname());
		employeeBean.setE_eng_name(newEmployeeBean.getE_eng_name());
		employeeBean.setE_email(newEmployeeBean.getE_email());
		employeeBean.setE_mobilephone(newEmployeeBean.getE_mobilephone());
		employeeBean.setE_update_info_time(new java.util.Date());

		return employeeBean;
	}

	/**
	 * 修改員工密碼
	 * 
	 * @param e_id-->員工流水號
	 * @param e_password_new_hashed-->新密碼(雜湊)
	 * @return employeeBean-->EmployeeBean
	 */
	@Override
	public EmployeeBean updateE_password(Integer e_id, String e_password_new_hashed) {

		EmployeeBean employeeBean = hibernateTemplate.get(EmployeeBean.class, e_id);
		employeeBean.setE_password(e_password_new_hashed);
		employeeBean.setE_update_pass_time(new java.util.Date());

		return employeeBean;
	}

	/**
	 * 更新登入次數
	 * 
	 * @param e_id-->員工流水號
	 * @return employeeBean-->EmployeeBean
	 */
	@Override
	public EmployeeBean updateE_signin_number(Integer e_id) {

		EmployeeBean employeeBean = hibernateTemplate.get(EmployeeBean.class, e_id);
		Integer e_signin_number = hibernateTemplate.get(EmployeeBean.class, e_id).getE_signin_number();
		employeeBean.setE_signin_number(e_signin_number + 1);

		return employeeBean;
	}

	/**
	 * 更新登入IP
	 * 
	 * @param e_id-->員工流水號
	 * @param e_signin_ip-->登入IP
	 * @return employeeBean-->EmployeeBean
	 */
	@Override
	public EmployeeBean updateE_signin_ip(Integer e_id, String e_signin_ip) {

		EmployeeBean employeeBean = hibernateTemplate.get(EmployeeBean.class, e_id);
		employeeBean.setE_signin_ip(e_signin_ip);

		return employeeBean;
	}

	/**
	 * 更新登入時間
	 * 
	 * @param e_id-->員工流水號
	 * @return employeeBean-->EmployeeBean
	 */
	@Override
	public EmployeeBean updateE_signin_time(Integer e_id) {

		EmployeeBean employeeBean = hibernateTemplate.get(EmployeeBean.class, e_id);
		employeeBean.setE_signin_time(new java.util.Date());

		return employeeBean;
	}
	
}
