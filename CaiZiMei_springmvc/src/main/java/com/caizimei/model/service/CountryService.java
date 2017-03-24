/*
 * CaiZiMei
 * File: CountryService.java
 * Author: 詹晟
 * Date: 2017/3/24
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.service;

import java.util.List;

import com.caizimei.model.entity.CountryBean;

/**
 * country service interface
 * 
 * @author 詹晟
 */
public interface CountryService {

	List<CountryBean> select();

	CountryBean selectByCo_id(Integer co_id);

	CountryBean selectByCo_name(String co_name);

	CountryBean insert(CountryBean countryBean);

	CountryBean update(CountryBean countryBean);

	Boolean delete(Integer co_id);

}
