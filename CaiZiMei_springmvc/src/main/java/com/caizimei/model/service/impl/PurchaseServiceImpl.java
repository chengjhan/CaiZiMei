/*
 * CaiZiMei
 * File: PurchaseServiceImpl.java
 * Author: 詹晟
 * Date: 2017/3/28
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caizimei.model.dao.PurchaseDAO;
import com.caizimei.model.entity.PurchaseBean;
import com.caizimei.model.service.PurchaseService;

/**
 * purchase service implement
 * 
 * @author 詹晟
 */
@Service(value = "purchaseService")
@Transactional
public class PurchaseServiceImpl implements PurchaseService {

	/**
	 * 注入 PurchaseDAO
	 */
	@Autowired
	private PurchaseDAO purchaseDAO;

	/**
	 * 訂購
	 * 
	 * @param purchaseBean-->PurchaseBean
	 * @return result-->PurchaseBean
	 */
	@Override
	@Transactional
	public PurchaseBean order(PurchaseBean purchaseBean) {

		PurchaseBean result = null;

		if (purchaseBean != null) {

			result = purchaseDAO.insert(purchaseBean);
		}
		return result;
	}

}
