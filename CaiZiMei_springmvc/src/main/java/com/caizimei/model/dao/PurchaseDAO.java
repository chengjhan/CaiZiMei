/*
 * CaiZiMei
 * File: PurchaseDAO.java
 * Author: 詹晟
 * Date: 2017/4/21
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.dao;

import java.util.List;

import com.caizimei.model.entity.PurchaseBean;

/**
 * purchase DAO interface
 *
 * @author 詹晟
 */
public interface PurchaseDAO {

	List<PurchaseBean> selectByP_m_id(Integer p_m_id);

	PurchaseBean insert(PurchaseBean purchaseBean);

}
