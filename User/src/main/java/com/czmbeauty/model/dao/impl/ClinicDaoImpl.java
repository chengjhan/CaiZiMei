/*
 * CaiZiMei/User
 * File: ClinicDaoImpl.java
 * Author: 詹晟
 * Date: 2017/7/19
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.czmbeauty.model.dao.ClinicDao;
import com.czmbeauty.model.entity.ClinicBean;

/**
 * clinic DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "clinicDao")
public class ClinicDaoImpl implements ClinicDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 狀態搜尋
	 * 
	 * @return List<ClinicBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ClinicBean> selectByCl_status() {

		return (List<ClinicBean>) hibernateTemplate.find("from ClinicBean where cl_status=1 order by cl_id asc");
	}

}
