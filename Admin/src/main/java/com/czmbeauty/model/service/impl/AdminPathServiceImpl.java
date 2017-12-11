/*
 * CaiZiMei
 * File: AdminPathServiceImpl.java
 * Author: 詹晟
 * Date: 2017/12/11
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czmbeauty.model.dao.AdminPathDao;
import com.czmbeauty.model.dao.CategoryPathDao;
import com.czmbeauty.model.entity.AdminPathBean;
import com.czmbeauty.model.service.AdminPathService;

/**
 * admin_path service implement
 * 
 * @author 詹晟
 */
@Service(value = "adminPathService")
public class AdminPathServiceImpl implements AdminPathService {

	/**
	 * 注入 CategoryPathDao
	 */
	@Autowired
	private CategoryPathDao categoryPathDao;

	/**
	 * 注入 AdminPathDao
	 */
	@Autowired
	private AdminPathDao adminPathDao;

	/**
	 * path 類別流水號搜尋
	 * 
	 * @param ap_cp_id
	 *            Integer --> path 類別流水號
	 * @return List<AdminPathBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<AdminPathBean> selectByAp_cp_id(Integer ap_cp_id) {

		return adminPathDao.selectByAp_cp_id(ap_cp_id);
	}

	/**
	 * extension 及 path 搜尋
	 * 
	 * @param cp_extension
	 *            String --> extension
	 * @param ap_path
	 *            String --> path
	 * @return AdminPathBean
	 * @return null
	 */
	@Override
	@Transactional(readOnly = true)
	public AdminPathBean selectByAp_path(String cp_extension, String ap_path) {

		return adminPathDao.selectByAp_path(categoryPathDao.selectByCp_extension(cp_extension), ap_path);
	}

}
