/*
 * CaiZiMei
 * File: ImageService.java
 * Author: 詹晟
 * Date: 2017/12/15
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service;

import java.util.Map;

import com.czmbeauty.model.entity.ImageBean;

/**
 * image service interface
 * 
 * @author 詹晟
 */
public interface ImageService {

	/**
	 * @see com.czmbeauty.model.service.impl.ImageServiceImpl#selectPagination(Integer,
	 *      Integer, int)
	 */
	Map<String, Object> selectPagination(Integer im_ca_id, Integer page, int max);

	/**
	 * @see com.czmbeauty.model.service.impl.ImageServiceImpl#selectByIm_id(Integer)
	 */
	ImageBean selectByIm_id(Integer im_id) throws IllegalArgumentException;

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
