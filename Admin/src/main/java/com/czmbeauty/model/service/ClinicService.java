/*
 * CaiZiMei
 * File: ClinicService.java
 * Author: 詹晟
 * Date: 2017/7/28
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

	/**
	 * @see com.czmbeauty.model.service.impl.ClinicServiceImpl
	 */
	List<ClinicBean> selectAll();

	/**
	 * @see com.czmbeauty.model.service.impl.ClinicServiceImpl#selectByCl_id(Integer)
	 */
	ClinicBean selectByCl_id(Integer cl_id);

	/**
	 * @see com.czmbeauty.model.service.impl.ClinicServiceImpl#selectByCl_status()
	 */
	List<ClinicBean> selectByCl_status();

	/**
	 * @see com.czmbeauty.model.service.impl.ClinicServiceImpl#insert(ClinicBean)
	 */
	ClinicBean insert(ClinicBean clinicBean);

	/**
	 * @see com.czmbeauty.model.service.impl.ClinicServiceImpl#update(ClinicBean)
	 */
	ClinicBean update(ClinicBean clinicBean);

	/**
	 * @see com.czmbeauty.model.service.impl.ClinicServiceImpl#updateCl_status(Integer)
	 */
	ClinicBean updateCl_status(Integer cl_id);

}
