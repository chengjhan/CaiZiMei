/*
 * CaiZiMei
 * File: ImageService.java
 * Author: 詹晟
 * Date: 2017/8/10
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
	 * @see com.czmbeauty.model.service.impl.ImageServiceImpl#selectAllImagePagination(String,
	 *      int, int)
	 */
	List<ImageBean> selectAllImagePagination(String hql, int first, int max);

	/**
	 * @see com.czmbeauty.model.service.impl.ImageServiceImpl#selectAllImageCount(CategoryBean)
	 */
	int selectAllImageCount(CategoryBean im_CategoryBean);

	/**
	 * @see com.czmbeauty.model.service.impl.ImageServiceImpl#selectByIm_id(Integer)
	 */
	ImageBean selectByIm_id(Integer im_id);

	/**
	 * @see com.czmbeauty.model.service.impl.ImageServiceImpl#selectByIm_status()
	 */
	List<ImageBean> selectByIm_status();

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
