/*
 * CaiZiMei/User
 * File: VideoDao.java
 * Author: 詹晟
 * Date: 2017/9/8
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import java.util.List;

import com.czmbeauty.model.entity.CategoryBean;
import com.czmbeauty.model.entity.VideoBean;

/**
 * video DAO interface
 * 
 * @author 詹晟
 */
public interface VideoDao {

	/**
	 * @see com.czmbeauty.model.dao.impl.VideoDaoImpl#selectOpenVideo(CategoryBean)
	 */
	List<VideoBean> selectOpenVideo(CategoryBean vi_CategoryBean);

}
