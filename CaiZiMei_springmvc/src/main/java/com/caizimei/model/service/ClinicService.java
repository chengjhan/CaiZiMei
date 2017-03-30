/*
 * CaiZiMei
 * File: ClinicService.java
 * Author: 詹晟
 * Date: 2017/3/30
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.service;

import java.util.List;

import com.caizimei.model.entity.ClinicBean;

/**
 * clinic service interface
 * 
 * @author 詹晟
 */
public interface ClinicService {

	List<ClinicBean> select();

	ClinicBean selectByC_id(String c_id);

	List<ClinicBean> selectByC_r_id(String c_r_id);

	List<ClinicBean> selectByConditions(String c_name, String c_localphone);

	ClinicBean insert(ClinicBean clinicBean);

	ClinicBean update(ClinicBean clinicBean);

	Boolean delete(Integer c_id);

	Double[] addressToLatLng(String address) throws Exception;

}
