/*
 * CaiZiMei
 * File: CompanyDAOImpl.java
 * Author: 詹晟
 * Date: 2017/4/9
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.caizimei.model.dao.CompanyDAO;
import com.caizimei.model.entity.CompanyBean;

/**
 * company DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "companyDAO")
public class CompanyDAOImpl implements CompanyDAO {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 搜尋全部代理商
	 * 
	 * @return List<CompanyBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<CompanyBean> select() {

		return (List<CompanyBean>) hibernateTemplate.find("from CompanyBean");
	}

	/**
	 * 新增代理商
	 * 
	 * @param companyBean-->CompanyBean
	 * @return companyBean-->CompanyBean
	 */
	@Override
	public CompanyBean insert(CompanyBean companyBean) {

		hibernateTemplate.save(companyBean);

		return companyBean;
	}

}
