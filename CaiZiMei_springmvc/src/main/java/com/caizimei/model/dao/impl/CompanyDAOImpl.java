/*
 * CaiZiMei
 * File: CompanyDAOImpl.java
 * Author: 詹晟
 * Date: 2017/4/18
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
	 * 搜尋全部公司
	 * 
	 * @return List<CompanyBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<CompanyBean> select() {

		return (List<CompanyBean>) hibernateTemplate.find("from CompanyBean order by com_id asc");
	}

	/**
	 * 公司流水號搜尋
	 * 
	 * @param com_id-->公司流水號
	 * @return CompanyBean
	 */
	@Override
	public CompanyBean selectByCom_id(Integer com_id) {

		return hibernateTemplate.get(CompanyBean.class, com_id);
	}

	/**
	 * 搜尋可顯示的公司
	 * 
	 * List<CompanyBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<CompanyBean> selectByCom_status() {

		return (List<CompanyBean>) hibernateTemplate.find("from CompanyBean where com_status=1 order by com_id asc");
	}

	/**
	 * 新增公司
	 * 
	 * @param companyBean-->CompanyBean
	 * @return companyBean-->CompanyBean
	 */
	@Override
	public CompanyBean insert(CompanyBean companyBean) {

		hibernateTemplate.save(companyBean);

		return companyBean;
	}

	/**
	 * 修改公司資料
	 * 
	 * @param newCompanyBean-->CompanyBean
	 * @return companyBean-->CompanyBean
	 */
	@Override
	public CompanyBean update(CompanyBean newCompanyBean) {

		CompanyBean companyBean = hibernateTemplate.get(CompanyBean.class, newCompanyBean.getCom_id());

		companyBean.setCom_name(newCompanyBean.getCom_name());
		companyBean.setCom_localphone(newCompanyBean.getCom_localphone());
		companyBean.setCom_update_time(new java.util.Date());

		return companyBean;
	}

	/**
	 * 切換公司顯示狀態
	 * 
	 * @param com_id-->公司流水號
	 * @return companyBean-->CompanyBean
	 */
	@Override
	public CompanyBean updateCom_status(Integer com_id) {

		CompanyBean companyBean = hibernateTemplate.get(CompanyBean.class, com_id);

		if (companyBean.getCom_status() == 1) {

			// 不顯示
			companyBean.setCom_status(0);
			companyBean.setCom_status_time(new java.util.Date());

		} else {

			// 顯示
			companyBean.setCom_status(1);
			companyBean.setCom_status_time(new java.util.Date());

		}

		return companyBean;
	}

}
