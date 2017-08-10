/*
 * CaiZiMei/User
 * File: ImageDaoImpl.java
 * Author: 詹晟
 * Date: 2017/8/10
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.czmbeauty.model.dao.ImageDao;
import com.czmbeauty.model.entity.ImageBean;

/**
 * image DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "imageDao")
public class ImageDaoImpl implements ImageDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 搜尋開啟的圖片
	 * 
	 * @return List<ImageBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ImageBean> selectOpenImage(String hql) {

		return (List<ImageBean>) hibernateTemplate.find(hql);
	}

}
