/*
 * CaiZiMei
 * File: CompanyDAO.java
 * Author: 詹晟
 * Date: 2017/4/9
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.dao;

import java.util.List;

import com.caizimei.model.entity.CompanyBean;

/**
 * company DAO interface
 * 
 * @author 詹晟
 */
public interface CompanyDAO {

	List<CompanyBean> select();

	CompanyBean selectByCom_id(Integer com_id);

	CompanyBean insert(CompanyBean companyBean);

}
