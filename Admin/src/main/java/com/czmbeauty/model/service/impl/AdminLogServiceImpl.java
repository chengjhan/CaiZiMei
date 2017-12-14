/*
 * CaiZiMei
 * File: AdminLogServiceImpl.java
 * Author: 詹晟
 * Date: 2017/12/14
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czmbeauty.model.dao.AdminLogDao;
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
	 * 注入 AdminLogDao
	 */
	@Autowired
	private AdminLogDao adminLogDao;

	/**
	 * 條件搜尋 (分頁)
	 * 
	 * @param beginDate
	 *            Date --> 開始日期
	 * @param endDate
	 *            Date --> 結束日期
	 * @param al_AdminBean
	 *            AdminBean
	 * @param al_AdminPathBean
	 *            AdminPathBean
	 * @param page
	 *            Integer --> 當前頁碼
	 * @param max
	 *            int --> 每頁最大筆數
	 * @return Map<String, Object>
	 */
	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> selectByConditions(Date beginDate, Date endDate, AdminBean al_AdminBean,
			AdminPathBean al_AdminPathBean, Integer page, int max) {

		// 取得當頁起始筆數
		int first = (page - 1) * max;

		return adminLogDao.selectByConditions(beginDate, endDate, al_AdminBean, al_AdminPathBean, first, max);
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
