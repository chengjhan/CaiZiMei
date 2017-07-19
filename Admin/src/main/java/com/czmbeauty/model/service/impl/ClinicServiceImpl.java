/*
 * CaiZiMei
 * File: ClinicServiceImpl.java
 * Author: 詹晟
 * Date: 2017/7/20
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czmbeauty.model.dao.ClinicDao;
import com.czmbeauty.model.entity.ClinicBean;
import com.czmbeauty.model.service.ClinicService;

/**
 * clinic service implement
 * 
 * @author 詹晟
 */
@Service(value = "clinicService")
public class ClinicServiceImpl implements ClinicService {

	/**
	 * 注入 ClinicDao
	 */
	@Autowired
	private ClinicDao clinicDao;

	/**
	 * 搜尋所有診所
	 * 
	 * @return List<ClinicBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ClinicBean> selectAll() {

		return clinicDao.selectAll();
	}

	/**
	 * 診所流水號搜尋
	 * 
	 * @param cl_id-->診所流水號
	 * @return ClinicBean
	 */
	@Override
	@Transactional(readOnly = true)
	public ClinicBean selectByCl_id(Integer cl_id) {

		return clinicDao.selectByCl_id(cl_id);
	}

	/**
	 * 狀態搜尋
	 * 
	 * @return List<ClinicBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ClinicBean> selectByCl_status() {

		return clinicDao.selectByCl_status();
	}

	/**
	 * 新增診所
	 * 
	 * @param clinicBean-->ClinicBean
	 * @return result-->ClinicBean
	 */
	@Override
	@Transactional
	public ClinicBean insert(ClinicBean clinicBean) {

		ClinicBean result = null;

		if (clinicBean != null) {

			result = clinicDao.insert(clinicBean);
		}
		return result;
	}

	/**
	 * 修改資料
	 * 
	 * @param clinicBean-->ClinicBean
	 * @return ClinicBean
	 */
	@Override
	@Transactional
	public ClinicBean update(ClinicBean clinicBean) {

		return clinicDao.update(clinicBean);
	}

	/**
	 * 切換狀態
	 * 
	 * @param cl_id-->診所流水號
	 * @return ClinicBean
	 */
	@Override
	@Transactional
	public ClinicBean updateCl_status(Integer cl_id) {

		return clinicDao.updateCl_status(cl_id);
	}

}
