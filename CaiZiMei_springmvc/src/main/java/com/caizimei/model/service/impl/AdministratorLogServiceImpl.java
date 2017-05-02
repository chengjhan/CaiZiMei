/*
 * CaiZiMei
 * File: AdministratorLogServiceImpl.java
 * Author: 詹晟
 * Date: 2017/4/10
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caizimei.model.dao.AdminUserLogDAO;
import com.caizimei.model.entity.AdministratorLogBean;
import com.caizimei.model.service.AdminUserLogService;

/**
 * administrator_log service implement
 * 
 * @author 詹晟
 */
@Service(value = "administratorLogService")
public class AdministratorLogServiceImpl implements AdminUserLogService {

	/**
	 * 注入 AdministratorLogDAO
	 */
	@Autowired
	private AdminUserLogDAO administratorLogDAO;

	/**
	 * 搜尋全部管理員日誌
	 * 
	 * @return List<AdministratorLogBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<AdministratorLogBean> select() {

		return administratorLogDAO.select();
	}

	/**
	 * 管理員流水號搜尋
	 * 
	 * @return List<AdministratorLogBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<AdministratorLogBean> selectByAl_a_id(Integer al_a_id) {

		return administratorLogDAO.selectByAl_a_id(al_a_id);
	}

	/**
	 * 新增管理員日誌
	 * 
	 * @return administratorLogBean
	 */
	@Override
	@Transactional
	public AdministratorLogBean insert(AdministratorLogBean administratorLogBean) {

		AdministratorLogBean result = null;

		if (administratorLogBean != null) {

			result = administratorLogDAO.insert(administratorLogBean);
		}
		return result;
	}

}
