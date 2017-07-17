/*
 * CaiZiMei
 * File: CountryService.java
 * Author: 詹晟
 * Date: 2017/7/12
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service;

import java.util.List;

import com.czmbeauty.model.entity.CountryBean;

/**
 * country service interface
 * 
 * @author 詹晟
 */
public interface CountryService {

	List<CountryBean> selectAll();

	CountryBean selectByCo_id(Integer co_id);

	CountryBean insert(CountryBean countryBean);

	CountryBean update(CountryBean countryBean);

	Boolean delete(Integer co_id);

}
