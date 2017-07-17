/*
 * CaiZiMei
 * File: CityDao.java
 * Author: 詹晟
 * Date: 2017/7/17
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import java.util.List;

import com.czmbeauty.model.entity.CityBean;

/**
 * city DAO interface
 * 
 * @author 詹晟
 */
public interface CityDao {

	List<CityBean> selectAll();

	CityBean selectByCi_id(Integer ci_id);

	List<CityBean> selectByCi_st_id(Integer ci_st_id);

	CityBean insert(CityBean cityBean);

	CityBean update(CityBean cityBean);

	Boolean delete(Integer ci_id);

}
