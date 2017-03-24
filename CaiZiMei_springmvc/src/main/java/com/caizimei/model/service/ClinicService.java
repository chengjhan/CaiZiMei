/*
 * CaiZiMei
 * File: ClinicService.java
 * Author: 詹晟
 * Date: 2017/3/24
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

	List<ClinicBean> selectByConditions(String c_name, String c_telephone);

	ClinicBean insert(ClinicBean clinicBean);

	ClinicBean update(ClinicBean clinicBean);

	Boolean delete(Integer c_id);

	Double[] addressToLatLng(String address) throws Exception;

}
