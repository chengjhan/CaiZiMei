/*
 * CaiZiMei
 * File: RegionDAO.java
 * Author: 詹晟
 * Date: 2017/4/15
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.dao;

import java.util.List;

import com.caizimei.model.entity.CityBean;
import com.caizimei.model.entity.RegionBean;

/**
 * region DAO interface
 *
 * @author 詹晟
 */
public interface RegionDAO {

	RegionBean selectByR_id(Integer r_id);

	List<RegionBean> selectByR_ci_id(Integer r_ci_id);

	List<RegionBean> selectByR_name(String r_name);

	List<RegionBean> selectByR_zipcode(String r_zipcode);

	List<RegionBean> selectByRegionConditions(CityBean cityBean, String r_name, String r_zipcode);

	RegionBean insert(RegionBean regionBean);

	RegionBean update(RegionBean regionBean);

	Boolean delete(Integer r_id);

}
