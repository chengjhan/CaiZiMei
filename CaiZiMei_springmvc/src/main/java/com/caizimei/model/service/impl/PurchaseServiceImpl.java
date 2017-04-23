/*
 * CaiZiMei
 * File: PurchaseServiceImpl.java
 * Author: 詹晟
 * Date: 2017/4/23
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.service.impl;

import java.util.List;

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
public class PurchaseServiceImpl implements PurchaseService {

	/**
	 * 注入 PurchaseDAO
	 */
	@Autowired
	private PurchaseDAO purchaseDAO;

	/**
	 * 會員流水號搜尋
	 * 
	 * @param p_m_id-->會員流水號
	 * @return List<PurchaseBean>
	 */
	@Override
	public List<PurchaseBean> selectByP_m_id(Integer p_m_id) {

		return purchaseDAO.selectByP_m_id(p_m_id);
	}

	/**
	 * 訂購
	 * 
	 * @param purchaseBean-->PurchaseBean
	 * @return result-->PurchaseBean
	 */
	@Override
	@Transactional
	public PurchaseBean insert(PurchaseBean purchaseBean) {

		PurchaseBean result = null;

		if (purchaseBean != null) {

			result = purchaseDAO.insert(purchaseBean);
		}
		return result;
	}

}
