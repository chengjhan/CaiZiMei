/*
 * CaiZiMei/User
 * File: ImageServiceImpl.java
 * Author: 詹晟
 * Date: 2017/8/10
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czmbeauty.model.dao.ImageDao;
import com.czmbeauty.model.entity.ImageBean;
import com.czmbeauty.model.service.ImageService;

/**
 * image service implement
 * 
 * @author 詹晟
 */
@Service(value = "imageService")
public class ImageServiceImpl implements ImageService {

	/**
	 * 注入 ImageDao
	 */
	@Autowired
	private ImageDao imageDao;

	/**
	 * 搜尋開啟的圖片
	 * 
	 * @return List<ImageBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ImageBean> selectOpenImage(String hql) {

		return imageDao.selectOpenImage(hql);
	}

}
