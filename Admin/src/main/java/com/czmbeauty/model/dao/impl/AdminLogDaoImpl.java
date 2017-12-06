/*
 * CaiZiMei
 * File: AdminLogDaoImpl.java
 * Author: 詹晟
 * Date: 2017/12/6
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.czmbeauty.model.dao.AdminLogDao;
import com.czmbeauty.model.entity.AdminBean;
import com.czmbeauty.model.entity.AdminLogBean;
import com.czmbeauty.model.entity.AdminPathBean;

/**
 * admin_log DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "adminLogDao")
public class AdminLogDaoImpl implements AdminLogDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 條件搜尋
	 * 
	 * @param startDate
	 *            Date --> 開始日期
	 * @param endDate
	 *            Date --> 結束日期
	 * @param adminBean
	 *            AdminBean
	 * @param adminPathBean
	 *            AdminPathBean
	 * @return List<AdminLogBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<AdminLogBean> selectByConditions(Date startDate, Date endDate, AdminBean adminBean,
			AdminPathBean adminPathBean) {

		DetachedCriteria criteria = DetachedCriteria.forClass(AdminLogBean.class);

		if (startDate != null) {
			criteria.add(Restrictions.ge("al_insert_time", startDate));
		}
		if (endDate != null) {
			criteria.add(Restrictions.le("al_insert_time", endDate));
		}
		if (adminBean != null) {
			criteria.add(Restrictions.eq("al_AdminBean", adminBean));
		}
		if (adminPathBean != null) {
			criteria.add(Restrictions.eq("al_AdminPathBean", adminPathBean));
		}
		criteria.addOrder(Order.desc("al_insert_time"));

		List<AdminLogBean> list = (List<AdminLogBean>) hibernateTemplate.findByCriteria(criteria);

		return list;
	}

	/**
	 * 新增管理員日誌
	 * 
	 * @param adminLogBean
	 *            AdminLogBean
	 * @return AdminLogBean
	 */
	@Override
	public AdminLogBean insert(AdminLogBean adminLogBean) {

		hibernateTemplate.save(adminLogBean);

		return adminLogBean;
	}

}
