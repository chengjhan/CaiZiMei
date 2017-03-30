/*
 * CaiZiMei
 * File: ClinicDAO.java
 * Author: 詹晟
 * Date: 2017/3/30
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.dao;

import java.util.List;

import com.caizimei.model.entity.ClinicBean;

/**
 * clinic DAO interface
 * 
 * @author 詹晟
 */
public interface ClinicDAO {

	List<ClinicBean> select();

	ClinicBean selectByC_id(Integer c_id);

	List<ClinicBean> selectByC_name(String c_name);

	List<ClinicBean> selectByC_r_id(Integer c_r_id);

	List<ClinicBean> selectByConditions(String c_name, String c_localphone);

	ClinicBean insert(ClinicBean clinicBean);

	ClinicBean update(ClinicBean clinicBean);

	Boolean delete(Integer c_id);

}
