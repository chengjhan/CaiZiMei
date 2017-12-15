/*
 * CaiZiMei
 * File: ImageDao.java
 * Author: 詹晟
 * Date: 2017/12/15
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import java.util.Map;

import com.czmbeauty.common.constants.HqlConstants;
import com.czmbeauty.model.entity.ImageBean;

/**
 * image DAO interface
 * 
 * @author 詹晟
 */
public interface ImageDao extends HqlConstants {

	/**
	 * @see com.czmbeauty.model.dao.impl.ImageDaoImpl#selectPagination(Integer, int,
	 *      int)
	 */
	Map<String, Object> selectPagination(Integer im_ca_id, int first, int max);

	/**
	 * @see com.czmbeauty.model.dao.impl.ImageDaoImpl#selectByIm_id(Integer)
	 */
	ImageBean selectByIm_id(Integer im_id);

	/**
	 * @see com.czmbeauty.model.dao.impl.ImageDaoImpl#insert(ImageBean)
	 */
	ImageBean insert(ImageBean imageBean);

	/**
	 * @see com.czmbeauty.model.dao.impl.ImageDaoImpl#update(ImageBean)
	 */
	ImageBean update(ImageBean imageBean);

}
