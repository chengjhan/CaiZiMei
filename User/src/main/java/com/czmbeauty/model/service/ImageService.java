/*
 * CaiZiMei/User
 * File: ImageService.java
 * Author: 詹晟
 * Date: 2017/8/10
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service;

import java.util.List;

import com.czmbeauty.model.entity.ImageBean;

/**
 * image service interface
 * 
 * @author 詹晟
 */
public interface ImageService {

	/**
	 * @see com.czmbeauty.model.service.impl.ImageServiceImpl#selectOpenImage(String)
	 */
	List<ImageBean> selectOpenImage(String hql);

}
