/*
 * CaiZiMei
 * File: CompanyService.java
 * Author: 詹晟
 * Date: 2017/4/13
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.service;

import java.util.List;

import com.caizimei.model.entity.CompanyBean;

/**
 * company service interface
 * 
 * @author 詹晟
 */
public interface CompanyService {

	List<CompanyBean> select();

	CompanyBean selectByCom_id(Integer com_id);

	List<CompanyBean> selectByCom_status();

	CompanyBean insert(CompanyBean companyBean);

	CompanyBean updateCom_status(Integer com_id);

}
