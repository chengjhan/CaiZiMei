/*
 * CaiZiMei
 * File: AdministratorLogDAOImpl.java
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

import com.caizimei.model.dao.AdministratorLogDAO;
import com.caizimei.model.entity.AdministratorLogBean;

/**
 * administrator_log DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "administratorLogDAO")
public class AdministratorLogDAOImpl implements AdministratorLogDAO {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 搜尋全部管理員日誌
	 * 
	 * @return List<AdministratorLogBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<AdministratorLogBean> select() {

		return (List<AdministratorLogBean>) hibernateTemplate
				.find("from AdministratorLogBean order by al_insert_time asc");
	}

	/**
	 * 管理員流水號搜尋
	 * 
	 * @return List<AdministratorLogBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<AdministratorLogBean> selectByAl_a_id(Integer al_a_id) {

		return (List<AdministratorLogBean>) hibernateTemplate
				.findByNamedParam("from AdministratorLogBean where al_a_id=:al_a_id", "al_a_id", al_a_id);
	}

	/**
	 * 新增管理員日誌
	 * 
	 * @return administratorLogBean
	 */
	@Override
	public AdministratorLogBean insert(AdministratorLogBean administratorLogBean) {

		hibernateTemplate.save(administratorLogBean);

		return administratorLogBean;
	}

}
