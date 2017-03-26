/*
 * CaiZiMei
 * File: CityService.java
 * Author: 詹晟
 * Date: 2017/3/26
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.service;

import java.util.List;

import com.caizimei.model.entity.CityBean;

/**
 * city service interface
 * 
 * @author 詹晟
 */
public interface CityService {

	List<CityBean> select();

	CityBean selectByCi_id(Integer ci_id);

	CityBean selectByCi_name(String ci_name);

	List<CityBean> selectByCo_name(String co_name);

	CityBean insert(CityBean cityBean);

	CityBean update(CityBean cityBean);

	Boolean delete(Integer ci_id);

}
