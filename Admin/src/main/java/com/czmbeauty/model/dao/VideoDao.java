/*
 * CaiZiMei
 * File: YoutubeDao.java
 * Author: 詹晟
 * Date: 2017/8/23
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import java.util.List;

import com.czmbeauty.model.entity.CategoryBean;
import com.czmbeauty.model.entity.YoutubeBean;

/**
 * youtube DAO interface
 * 
 * @author 詹晟
 */
public interface YoutubeDao {

	/**
	 * @see com.czmbeauty.model.dao.impl.YoutubeDaoImpl#selectPagination(String,
	 *      int, int)
	 */
	List<YoutubeBean> selectPagination(String hql, int first, int max);

	/**
	 * @see com.czmbeauty.model.dao.impl.YoutubeDaoImpl#selectCountByYo_Ca(CategoryBean)
	 */
	int selectCountByYo_Ca(CategoryBean yo_CategoryBean);

	/**
	 * @see com.czmbeauty.model.dao.impl.YoutubeDaoImpl#selectByYo_id(Integer)
	 */
	YoutubeBean selectByYo_id(Integer yo_id);

	/**
	 * @see com.czmbeauty.model.dao.impl.YoutubeDaoImpl#insert(YoutubeBean)
	 */
	YoutubeBean insert(YoutubeBean youtubeBean);

	/**
	 * @see com.czmbeauty.model.dao.impl.YoutubeDaoImpl#update(YoutubeBean)
	 */
	YoutubeBean update(YoutubeBean youtubeBean);

}
