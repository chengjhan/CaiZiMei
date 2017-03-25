/*
 * CaiZiMei
 * File: PurchaseServiceImpl.java
 * Author: 詹晟
 * Date: 2017/3/26
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
	 * 新增訂單
	 * 
	 * @param purchaseBean-->PurchaseBean
	 * @return result-->PurchaseBean
	 */
	@Override
	public PurchaseBean insert(PurchaseBean purchaseBean) {

		PurchaseBean result = null;

		if (purchaseBean != null) {

			purchaseDAO.insert(purchaseBean);
		}
		return result;
	}

}
