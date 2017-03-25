/*
 * CaiZiMei
 * File: PurchaseDAO.java
 * Author: 詹晟
 * Date: 2017/3/26
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.dao;

import com.caizimei.model.entity.PurchaseBean;

/**
 * purchase DAO interface
 *
 * @author 詹晟
 */
public interface PurchaseDAO {
	
	PurchaseBean insert(PurchaseBean purchaseBean);
	
}
