/*
 * CaiZiMei
 * File: VideoService.java
 * Author: 詹晟
 * Date: 2017/9/25
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service;

import java.util.List;

import com.czmbeauty.model.entity.CategoryBean;
import com.czmbeauty.model.entity.VideoBean;

/**
 * video service interface
 * 
 * @author 詹晟
 */
public interface VideoService {

	/**
	 * @see com.czmbeauty.model.service.impl.VideoServiceImpl#selectPagination(String,
	 *      int, int)
	 */
	List<VideoBean> selectPagination(String hql, int first, int max);

	/**
	 * @see com.czmbeauty.model.service.impl.VideoServiceImpl#selectCountByVi_Ca(CategoryBean)
	 */
	int selectCountByVi_Ca(CategoryBean vi_CategoryBean);

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
