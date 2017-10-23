/*
 * CaiZiMei/User
 * File: HtmlDao.java
 * Author: 詹晟
 * Date: 2017/10/24
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import java.util.List;

import com.czmbeauty.model.entity.CategoryBean;
import com.czmbeauty.model.entity.HtmlBean;

/**
 * html DAO interface
 * 
 * @author 詹晟
 */
public interface HtmlDao {

	/**
	 * @see com.czmbeauty.model.dao.impl.HtmlDaoImpl#selectOpenHtml(CategoryBean)
	 */
	List<HtmlBean> selectOpenHtml(CategoryBean ht_CategoryBean);

}
