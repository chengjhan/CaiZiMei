/*
 * CaiZiMei/User
 * File: HtmlDaoImpl.java
 * Author: 詹晟
 * Date: 2017/10/24
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.czmbeauty.model.dao.HtmlDao;
import com.czmbeauty.model.entity.CategoryBean;
import com.czmbeauty.model.entity.HtmlBean;

/**
 * html DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "htmlDao")
public class HtmlDaoImpl implements HtmlDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 類別搜尋開啟的 html
	 * 
	 * @param ht_CategoryBean
	 *            CategoryBean --> 類別
	 * @return List<HtmlBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<HtmlBean> selectOpenHtml(CategoryBean ht_CategoryBean) {

		DetachedCriteria criteria = DetachedCriteria.forClass(HtmlBean.class);

		criteria.add(Restrictions.eq("ht_CategoryBean", ht_CategoryBean));
		criteria.add(Restrictions.eq("ht_status", 1));
		criteria.addOrder(Order.asc("ht_rank"));

		return (List<HtmlBean>) hibernateTemplate.findByCriteria(criteria);
	}

}
