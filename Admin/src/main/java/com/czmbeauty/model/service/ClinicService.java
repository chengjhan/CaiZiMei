/*
 * CaiZiMei
 * File: ClinicService.java
 * Author: 詹晟
 * Date: 2017/7/24
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

	List<ClinicBean> selectByCl_status();

	ClinicBean insert(ClinicBean clinicBean);

	ClinicBean update(ClinicBean clinicBean);

	ClinicBean updateCl_status(ClinicBean clinicBean_cl_id);

}
