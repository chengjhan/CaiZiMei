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

import com.caizimei.model.dao.ClinicDAO;
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
	 * 注入 clinicDAO
	 */
	@Autowired
	private ClinicDAO clinicDAO;

	/**
	 * 訂購
	 * 
	 * @param p_m_id-->會員流水號
	 * @param c_name-->診所名
	 * @return PurchaseBean
	 */
	@Override
	@Transactional
	public PurchaseBean order(Integer p_m_id, String c_name) {

		PurchaseBean purchaseBean = new PurchaseBean();
		purchaseBean.setP_m_id(p_m_id);
		purchaseBean.setP_c_id(clinicDAO.selectByC_name(c_name).get(0).getC_id());

		return purchaseDAO.insert(purchaseBean);
	}

}
