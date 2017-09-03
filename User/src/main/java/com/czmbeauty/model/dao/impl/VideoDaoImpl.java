/*
 * CaiZiMei/User
 * File: VideoDaoImpl.java
 * Author: 詹晟
 * Date: 2017/9/3
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.czmbeauty.model.dao.VideoDao;
import com.czmbeauty.model.entity.VideoBean;

/**
 * video DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "videoDao")
public class VideoDaoImpl implements VideoDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 搜尋開啟的影片
	 * 
	 * @return List<VideoBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<VideoBean> selectOpenVideo(String hql) {

		return (List<VideoBean>) hibernateTemplate.find(hql);
	}

}
