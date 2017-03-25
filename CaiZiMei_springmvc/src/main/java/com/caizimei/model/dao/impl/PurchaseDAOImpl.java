/*
 * CaiZiMei
 * File: PurchaseDAOImpl.java
 * Author: 詹晟
 * Date: 2017/3/26
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.caizimei.model.dao.PurchaseDAO;
import com.caizimei.model.entity.PurchaseBean;

/**
 * purchase DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "purchaseDAO")
public class PurchaseDAOImpl implements PurchaseDAO {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 新增訂單
	 * 
	 * @param purchaseBean-->PurchaseBean
	 * @return purchaseBean-->PurchaseBean
	 */
	@Override
	public PurchaseBean insert(PurchaseBean purchaseBean) {

		hibernateTemplate.save(purchaseBean);

		return purchaseBean;
	}

}
