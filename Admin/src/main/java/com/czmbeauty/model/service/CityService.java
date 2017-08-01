/*
 * CaiZiMei
 * File: CityService.java
 * Author: 詹晟
 * Date: 2017/8/1
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service;

import java.util.List;

import com.czmbeauty.model.entity.CityBean;

/**
 * city service interface
 * 
 * @author 詹晟
 */
public interface CityService {

	/**
	 * @see com.czmbeauty.model.service.impl.CityServiceImpl#selectByCi_id(Integer)
	 */
	CityBean selectByCi_id(Integer ci_id);

	/**
	 * @see com.czmbeauty.model.service.impl.CityServiceImpl#selectByCi_st_id(Integer)
	 */
	List<CityBean> selectByCi_st_id(Integer ci_st_id);

	/**
	 * @see com.czmbeauty.model.service.impl.CityServiceImpl#insert(CityBean)
	 */
	CityBean insert(CityBean cityBean);

	/**
	 * @see com.czmbeauty.model.service.impl.CityServiceImpl#update(CityBean)
	 */
	CityBean update(CityBean cityBean);

	/**
	 * @see com.czmbeauty.model.service.impl.CityServiceImpl#delete(Integer)
	 */
	Boolean delete(Integer ci_id);

}
