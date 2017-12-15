/*
 * CaiZiMei
 * File: HtmlDao.java
 * Author: 詹晟
 * Date: 2017/12/15
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import java.util.List;
import java.util.Map;

import com.czmbeauty.common.constants.HqlConstants;
import com.czmbeauty.model.entity.HtmlBean;

/**
 * html DAO interface
 * 
 * @author 詹晟
 */
public interface HtmlDao extends HqlConstants {

	/**
	 * @see com.czmbeauty.model.dao.impl.HtmlDaoImpl#selectPagination(Integer, int,
	 *      int)
	 */
	Map<String, Object> selectPagination(Integer ht_ca_id, int first, int max);

	/**
	 * @see com.czmbeauty.model.dao.impl.HtmlDaoImpl#selectByHt_id(Integer)
	 */
	HtmlBean selectByHt_id(Integer ht_id);

	/**
	 * @see com.czmbeauty.model.dao.impl.HtmlDaoImpl#selectByHt_status(Integer)
	 */
	List<HtmlBean> selectByHt_status(Integer ht_ca_id);

	/**
	 * @see com.czmbeauty.model.dao.impl.HtmlDaoImpl#insert(HtmlBean)
	 */
	HtmlBean insert(HtmlBean htmlBean);

	/**
	 * @see com.czmbeauty.model.dao.impl.HtmlDaoImpl#update(HtmlBean)
	 */
	HtmlBean update(HtmlBean htmlBean);

}
