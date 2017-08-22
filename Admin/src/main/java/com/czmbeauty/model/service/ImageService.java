/*
 * CaiZiMei
 * File: ImageService.java
 * Author: 詹晟
 * Date: 2017/8/22
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service;

import java.util.List;

import com.czmbeauty.model.entity.CategoryBean;
import com.czmbeauty.model.entity.ImageBean;

/**
 * image service interface
 * 
 * @author 詹晟
 */
public interface ImageService {

	/**
	 * @see com.czmbeauty.model.service.impl.ImageServiceImpl#selectPagination(String,
	 *      int, int)
	 */
	List<ImageBean> selectPagination(String hql, int first, int max);

	/**
	 * @see com.czmbeauty.model.service.impl.ImageServiceImpl#selectCountByIm_Ca(CategoryBean)
	 */
	int selectCountByIm_Ca(CategoryBean im_CategoryBean);

	/**
	 * @see com.czmbeauty.model.service.impl.ImageServiceImpl#selectByIm_id(Integer)
	 */
	ImageBean selectByIm_id(Integer im_id);

	/**
	 * @see com.czmbeauty.model.service.impl.ImageServiceImpl#insert(ImageBean)
	 */
	ImageBean insert(ImageBean imageBean);

	/**
	 * @see com.czmbeauty.model.service.impl.ImageServiceImpl#update(ImageBean)
	 */
	ImageBean update(ImageBean imageBean);

	/**
	 * @see com.czmbeauty.model.service.impl.ImageServiceImpl#updateIm_status(Integer)
	 */
	ImageBean updateIm_status(Integer im_id);

}
