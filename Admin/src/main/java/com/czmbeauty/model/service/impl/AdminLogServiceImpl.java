/*
 * CaiZiMei
 * File: AdminLogServiceImpl.java
 * Author: 詹晟
 * Date: 2017/12/13
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czmbeauty.model.dao.AdminDao;
import com.czmbeauty.model.dao.AdminLogDao;
import com.czmbeauty.model.dao.AdminPathDao;
import com.czmbeauty.model.entity.AdminBean;
import com.czmbeauty.model.entity.AdminLogBean;
import com.czmbeauty.model.entity.AdminPathBean;
import com.czmbeauty.model.service.AdminLogService;

/**
 * admin_log service implement
 * 
 * @author 詹晟
 */
@Service(value = "adminLogService")
public class AdminLogServiceImpl implements AdminLogService {

	/**
	 * 注入 AdminPathDao
	 */
	@Autowired
	private AdminPathDao adminPathDao;

	/**
	 * 注入 AdminDao
	 */
	@Autowired
	private AdminDao adminDao;

	/**
	 * 注入 AdminLogDao
	 */
	@Autowired
	private AdminLogDao adminLogDao;

	/**
	 * 條件搜尋 (分頁)
	 * 
	 * @param startDate
	 *            Date --> 開始日期
	 * @param endDate
	 *            Date --> 結束日期
	 * @param adminLogBean
	 *            AdminLogBean
	 * @param page
	 *            Integer --> 當前頁碼
	 * @param max
	 *            int --> 每頁最大筆數
	 * @return Map<String, Object>
	 */
	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> selectByConditions(Date startDate, Date endDate, AdminLogBean adminLogBean, Integer page,
			int max) {

		AdminBean al_AdminBean = adminLogBean.getAl_AdminBean();

		if (al_AdminBean != null) {

			al_AdminBean = adminDao.selectByAd_id(al_AdminBean.getAd_id());
		}

		AdminPathBean al_AdminPathBean = adminLogBean.getAl_AdminPathBean();

		if (al_AdminPathBean != null) {

			al_AdminPathBean = adminPathDao.selectByAp_id(al_AdminPathBean.getAp_id());
		}

		// 取得當頁起始筆數
		int first = (page - 1) * max;

		return adminLogDao.selectByConditions(startDate, endDate, al_AdminBean, al_AdminPathBean, first, max);
	}

	/**
	 * 新增管理員日誌
	 * 
	 * @param adminLogBean
	 *            AdminLogBean
	 * @return AdminLogBean
	 */
	@Override
	@Transactional
	public AdminLogBean insert(AdminLogBean adminLogBean) {

		return adminLogDao.insert(adminLogBean);
	}

}
