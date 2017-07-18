/*
 * CaiZiMei
 * File: ClinicDaoImpl.java
 * Author: 詹晟
 * Date: 2017/7/18
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
	 * 搜尋所有診所
	 * 
	 * @return List<ClinicBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ClinicBean> selectAll() {

		return (List<ClinicBean>) hibernateTemplate.find("from ClinicBean order by cl_id asc");
	}

	/**
	 * 診所流水號搜尋
	 * 
	 * @param cl_id-->診所流水號
	 * @return ClinicBean
	 */
	@Override
	public ClinicBean selectByCl_id(Integer cl_id) {

		return hibernateTemplate.get(ClinicBean.class, cl_id);
	}

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

	/**
	 * 新增診所
	 * 
	 * @param clinicBean-->ClinicBean
	 * @return clinicBean-->ClinicBean
	 */
	@Override
	public ClinicBean insert(ClinicBean clinicBean) {

		hibernateTemplate.save(clinicBean);

		return clinicBean;
	}

	/**
	 * 修改資料
	 * 
	 * @param newClinicBean-->ClinicBean
	 * @return clinicBean-->ClinicBean
	 */
	@Override
	public ClinicBean update(ClinicBean newClinicBean) {

		ClinicBean clinicBean = hibernateTemplate.get(ClinicBean.class, newClinicBean.getCl_id());

		clinicBean.setCl_name(newClinicBean.getCl_name());
		clinicBean.setCl_eng_name(newClinicBean.getCl_eng_name());
		clinicBean.setCl_localphone(newClinicBean.getCl_localphone());
		clinicBean.setCl_CountryBean(newClinicBean.getCl_CountryBean());
		clinicBean.setCl_StateBean(newClinicBean.getCl_StateBean());
		clinicBean.setCl_CityBean(newClinicBean.getCl_CityBean());
		clinicBean.setCl_address(newClinicBean.getCl_address());
		clinicBean.setCl_latitude(newClinicBean.getCl_latitude());
		clinicBean.setCl_longitude(newClinicBean.getCl_longitude());
		clinicBean.setCl_url(newClinicBean.getCl_url());
		clinicBean.setCl_update_time(new java.util.Date());

		return clinicBean;
	}

	/**
	 * 切換狀態
	 * 
	 * @param cl_id-->診所流水號
	 * @return clinicBean-->ClinicBean
	 */
	@Override
	public ClinicBean updateCl_status(Integer cl_id) {

		ClinicBean clinicBean = hibernateTemplate.get(ClinicBean.class, cl_id);

		if (clinicBean.getCl_status() == 1) {

			// 不顯示
			clinicBean.setCl_status(0);
			clinicBean.setCl_status_time(new java.util.Date());
		} else {

			// 顯示
			clinicBean.setCl_status(1);
			clinicBean.setCl_status_time(new java.util.Date());
		}

		return clinicBean;
	}

}
