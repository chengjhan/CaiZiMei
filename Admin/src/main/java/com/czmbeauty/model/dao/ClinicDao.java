/*
 * CaiZiMei
 * File: ClinicDao.java
 * Author: 詹晟
 * Date: 2017/7/21
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import java.util.List;

import com.czmbeauty.model.entity.ClinicBean;

/**
 * clinic DAO interface
 * 
 * @author 詹晟
 */
public interface ClinicDao {

	List<ClinicBean> selectAll();

	ClinicBean selectByCl_id(Integer cl_id);

	List<ClinicBean> selectByCl_status();

	ClinicBean insert(ClinicBean clinicBean);

	ClinicBean update(ClinicBean clinicBean);

}
