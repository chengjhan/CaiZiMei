/*
 * CaiZiMei/User
 * File: HtmlServiceImpl.java
 * Author: 詹晟
 * Date: 2017/10/24
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czmbeauty.model.dao.CategoryDao;
import com.czmbeauty.model.dao.HtmlDao;
import com.czmbeauty.model.entity.HtmlBean;
import com.czmbeauty.model.service.HtmlService;

/**
 * html service implement
 * 
 * @author 詹晟
 */
@Service(value = "htmlService")
public class HtmlServiceImpl implements HtmlService {

	/**
	 * 注入 CategoryDao
	 */
	@Autowired
	private CategoryDao categoryDao;

	/**
	 * 注入 HtmlDao
	 */
	@Autowired
	private HtmlDao htmlDao;

	/**
	 * 類別資料夾名稱搜尋開啟的 html
	 * 
	 * @param ca_directory
	 *            String --> 類別資料夾名稱
	 * @return List<HtmlBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<HtmlBean> selectOpenHtml(String ca_directory) {

		return htmlDao.selectOpenHtml(categoryDao.selectByCa_directory(ca_directory));
	}

}
