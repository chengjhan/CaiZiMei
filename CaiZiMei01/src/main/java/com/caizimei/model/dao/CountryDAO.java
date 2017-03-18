/*
 * CaiZiMei
 * File: CountryDAO.java
 * Author: 詹晟
 * Date: 2017/3/19
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.dao;

import java.util.List;

import com.caizimei.model.entity.CountryBean;

/**
 * country DAO interface
 * 
 * @author 詹晟
 */
public interface CountryDAO {

	List<CountryBean> select();

	CountryBean selectByCo_id(Integer co_id);

	List<CountryBean> selectByCo_name(String co_name);

	CountryBean insert(CountryBean countryBean);

	CountryBean update(CountryBean countryBean);

	Boolean delete(Integer co_id);

}
