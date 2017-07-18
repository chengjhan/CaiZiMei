/*
 * CaiZiMei
 * File: ClinicService.java
 * Author: 詹晟
 * Date: 2017/7/18
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service;

import java.util.List;

import com.czmbeauty.model.entity.ClinicBean;

/**
 * clinic service interface
 * 
 * @author 詹晟
 */
public interface ClinicService {

	List<ClinicBean> selectAll();

	ClinicBean selectByCl_id(Integer cl_id);

	ClinicBean insert(ClinicBean clinicBean);

	ClinicBean update(ClinicBean clinicBean);

	ClinicBean updateCl_status(Integer cl_id);

	Double[] addressToLatLng(String address) throws Exception;

}
