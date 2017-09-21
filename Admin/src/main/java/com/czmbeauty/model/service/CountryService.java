/*
 * CaiZiMei
 * File: CountryService.java
 * Author: 詹晟
 * Date: 2017/9/21
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service;

import java.util.List;

import com.czmbeauty.common.exception.PageNotFoundException;
import com.czmbeauty.model.entity.CountryBean;

/**
 * country service interface
 * 
 * @author 詹晟
 */
public interface CountryService {

	/**
	 * @see com.czmbeauty.model.service.impl.CountryServiceImpl#selectAll()
	 */
	List<CountryBean> selectAll();

	/**
	 * @see com.czmbeauty.model.service.impl.CountryServiceImpl#selectByCo_id(Integer)
	 */
	CountryBean selectByCo_id(Integer co_id) throws PageNotFoundException;

	/**
	 * @see com.czmbeauty.model.service.impl.CountryServiceImpl#insert(CountryBean)
	 */
	CountryBean insert(CountryBean countryBean);

	/**
	 * @see com.czmbeauty.model.service.impl.CountryServiceImpl#update(CountryBean)
	 */
	CountryBean update(CountryBean countryBean);

	/**
	 * @see com.czmbeauty.model.service.impl.CountryServiceImpl
	 */
	CountryBean updateCo_status(Integer co_id);

}
