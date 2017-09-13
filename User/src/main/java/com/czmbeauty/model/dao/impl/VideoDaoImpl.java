/*
 * CaiZiMei/User
 * File: VideoDaoImpl.java
 * Author: 詹晟
 * Date: 2017/9/13
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.czmbeauty.model.dao.VideoDao;
import com.czmbeauty.model.entity.CategoryBean;
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
	 * 類別搜尋開啟的影片
	 * 
	 * @param vi_CategoryBean
	 *            CategoryBean --> 類別
	 * @return List<VideoBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<VideoBean> selectOpenVideo(CategoryBean vi_CategoryBean) {

		DetachedCriteria criteria = DetachedCriteria.forClass(VideoBean.class);

		criteria.add(Restrictions.eq("vi_CategoryBean", vi_CategoryBean));
		criteria.add(Restrictions.eq("vi_status", 1));

		return (List<VideoBean>) hibernateTemplate.findByCriteria(criteria);
	}

}
