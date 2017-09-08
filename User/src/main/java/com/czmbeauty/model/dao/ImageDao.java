/*
 * CaiZiMei/User
 * File: ImageDao.java
 * Author: 詹晟
 * Date: 2017/9/8
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import java.util.List;

import com.czmbeauty.model.entity.ImageBean;

/**
 * image DAO interface
 * 
 * @author 詹晟
 */
public interface ImageDao {

	/**
	 * @see com.czmbeauty.model.dao.impl.ImageDaoImpl#selectOpenImage(String)
	 */
	List<ImageBean> selectOpenImage(String im_ca_id);

}
