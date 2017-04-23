/*
 * CaiZiMei
 * File: PurchaseService.java
 * Author: 詹晟
 * Date: 2017/4/23
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.service;

import java.util.List;

import com.caizimei.model.entity.PurchaseBean;

/**
 * purchase service interface
 * 
 * @author 詹晟
 */
public interface PurchaseService {
	
	List<PurchaseBean> selectByP_m_id(Integer p_m_id);

	PurchaseBean insert(PurchaseBean purchaseBean);

}
