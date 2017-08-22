/*
 * CaiZiMei
 * File: ImageDao.java
 * Author: 詹晟
 * Date: 2017/8/22
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import java.util.List;

import com.czmbeauty.model.entity.CategoryBean;
import com.czmbeauty.model.entity.ImageBean;

/**
 * image DAO interface
 * 
 * @author 詹晟
 */
public interface ImageDao {

	/**
	 * @see com.czmbeauty.model.dao.impl.ImageDaoImpl#selectPagination(String,
	 *      int, int)
	 */
	List<ImageBean> selectPagination(String hql, int first, int max);

	/**
	 * @see com.czmbeauty.model.dao.impl.ImageDaoImpl#selectCountByIm_Ca(CategoryBean)
	 */
	int selectCountByIm_Ca(CategoryBean im_CategoryBean);

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
