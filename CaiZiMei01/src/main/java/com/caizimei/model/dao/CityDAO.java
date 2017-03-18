/*
 * CaiZiMei
 * File: CityDAO.java
 * Author: 詹晟
 * Date: 2017/3/19
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.dao;

import java.util.List;

import com.caizimei.model.entity.CityBean;

/**
 * city DAO interface
 * 
 * @author 詹晟
 */
public interface CityDAO {

	List<CityBean> select();

	CityBean selectByCi_id(Integer ci_id);

	List<CityBean> selectByCi_name(String ci_name);

	List<CityBean> selectByCi_co_id(Integer ci_co_id);

	CityBean insert(CityBean cityBean);

	CityBean update(CityBean cityBean);

	Boolean delete(Integer ci_id);

}
