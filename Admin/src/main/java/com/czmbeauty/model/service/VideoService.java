/*
 * CaiZiMei
 * File: YoutubeService.java
 * Author: 詹晟
 * Date: 2017/8/24
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service;

import java.util.List;

import com.czmbeauty.model.entity.CategoryBean;
import com.czmbeauty.model.entity.YoutubeBean;

/**
 * youtube service interface
 * 
 * @author 詹晟
 */
public interface YoutubeService {

	/**
	 * @see com.czmbeauty.model.service.impl.YoutubeServiceImpl#selectPagination(String,
	 *      int, int)
	 */
	List<YoutubeBean> selectPagination(String hql, int first, int max);

	/**
	 * @see com.czmbeauty.model.service.impl.YoutubeServiceImpl#selectCountByYo_Ca(CategoryBean)
	 */
	int selectCountByYo_Ca(CategoryBean yo_CategoryBean);

	/**
	 * @see com.czmbeauty.model.service.impl.YoutubeServiceImpl#selectByYo_id(Integer)
	 */
	YoutubeBean selectByYo_id(Integer yo_id);

	/**
	 * @see com.czmbeauty.model.service.impl.YoutubeServiceImpl#insert(YoutubeBean)
	 */
	YoutubeBean insert(YoutubeBean youtubeBean);

	/**
	 * @see com.czmbeauty.model.service.impl.YoutubeServiceImpl#update(YoutubeBean)
	 */
	YoutubeBean update(YoutubeBean youtubeBean);

}
