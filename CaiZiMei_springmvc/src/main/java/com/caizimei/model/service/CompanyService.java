/*
 * CaiZiMei
 * File: CompanyService.java
 * Author: 詹晟
 * Date: 2017/4/9
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

	CompanyBean insert(CompanyBean companyBean);

}
