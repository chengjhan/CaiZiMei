/*
 * CaiZiMei/User
 * File: ClinicService.java
 * Author: 詹晟
 * Date: 2017/7/27
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
	 * @see com.czmbeauty.model.service.impl.ClinicServiceImpl#selectByCl_status()
	 */
	List<ClinicBean> selectByCl_status();

}
