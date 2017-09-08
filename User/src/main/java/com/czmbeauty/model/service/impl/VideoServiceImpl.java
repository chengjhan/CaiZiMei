/*
 * CaiZiMei/User
 * File: VideoServiceImpl.java
 * Author: 詹晟
 * Date: 2017/9/8
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czmbeauty.model.dao.CategoryDao;
import com.czmbeauty.model.dao.VideoDao;
import com.czmbeauty.model.entity.VideoBean;
import com.czmbeauty.model.service.VideoService;

/**
 * video service implement
 * 
 * @author 詹晟
 */
@Service(value = "videoService")
public class VideoServiceImpl implements VideoService {

	/**
	 * 注入 CategoryDao
	 */
	@Autowired
	private CategoryDao categoryDao;

	/**
	 * 注入 VideoDao
	 */
	@Autowired
	private VideoDao videoDao;

	/**
	 * 類別資料夾名稱搜尋開啟的影片
	 * 
	 * @param ca_directory
	 *            String --> 類別資料夾名稱
	 * @return List<VideoBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<VideoBean> selectOpenVideo(String ca_directory) {

		return videoDao.selectOpenVideo(categoryDao.selectByCa_directory(ca_directory));
	}

}
