/*
 * CaiZiMei
 * File: RegionService.java
 * Author: 詹晟
 * Date: 2017/4/15
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.service;

import java.util.List;

import com.caizimei.model.entity.RegionBean;

/**
 * region service interface
 * 
 * @author 詹晟
 */
public interface RegionService {

	RegionBean selectByR_id(Integer r_id);

	List<RegionBean> selectByR_ci_id(Integer r_ci_id);

	List<RegionBean> selectByR_name(String r_name);

	RegionBean selectByR_zipcode(String r_zipcode);

	List<RegionBean> selectByRegionConditions(Integer r_ci_id, String r_name, String r_zipcode);

	RegionBean insert(RegionBean regionBean);

	RegionBean update(RegionBean regionBean);

	Boolean delete(Integer r_id);

}
