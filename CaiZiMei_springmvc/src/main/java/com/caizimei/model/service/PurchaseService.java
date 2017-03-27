/*
 * CaiZiMei
 * File: PurchaseService.java
 * Author: 詹晟
 * Date: 2017/3/28
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.service;

import com.caizimei.model.entity.PurchaseBean;

/**
 * purchase service interface
 * 
 * @author 詹晟
 */
public interface PurchaseService {

	PurchaseBean order(PurchaseBean purchaseBean);

}
