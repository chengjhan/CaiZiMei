/*
 * CaiZiMei
 * File: VideoService.java
 * Author: 詹晟
 * Date: 2017/12/15
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service;

import java.util.Map;

import com.czmbeauty.model.entity.VideoBean;

/**
 * video service interface
 * 
 * @author 詹晟
 */
public interface VideoService {

	/**
	 * @see com.czmbeauty.model.service.impl.VideoServiceImpl#selectPagination(Integer,
	 *      Integer, int)
	 */
	Map<String, Object> selectPagination(Integer vi_ca_id, Integer page, int max);

	/**
	 * @see com.czmbeauty.model.service.impl.VideoServiceImpl#selectByVi_id(Integer)
	 */
	VideoBean selectByVi_id(Integer vi_id) throws IllegalArgumentException;

	/**
	 * @see com.czmbeauty.model.service.impl.VideoServiceImpl#insert(VideoBean)
	 */
	VideoBean insert(VideoBean videoBean);

	/**
	 * @see com.czmbeauty.model.service.impl.VideoServiceImpl#update(VideoBean)
	 */
	VideoBean update(VideoBean videoBean);

	/**
	 * @see com.czmbeauty.model.service.impl.VideoServiceImpl#updateVi_status(Integer)
	 */
	VideoBean updateVi_status(Integer vi_id);

}
