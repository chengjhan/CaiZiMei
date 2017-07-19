/*
 * CaiZiMei/User
 * File: ClinicServiceImpl.java
 * Author: 詹晟
 * Date: 2017/7/19
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
	 * 狀態搜尋
	 * 
	 * @return List<ClinicBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ClinicBean> selectByCl_status() {

		return clinicDao.selectByCl_status();
	}

}
