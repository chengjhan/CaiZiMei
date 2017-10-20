/*
 * CaiZiMei/User
 * File: ImageDaoImpl.java
 * Author: 詹晟
 * Date: 2017/10/20
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.czmbeauty.model.dao.ImageDao;
import com.czmbeauty.model.entity.CategoryBean;
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
	 * 類別搜尋開啟的圖片
	 * 
	 * @param im_CategoryBean
	 *            CategoryBean --> 類別
	 * @return List<ImageBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ImageBean> selectOpenImage(CategoryBean im_CategoryBean) {

		DetachedCriteria criteria = DetachedCriteria.forClass(ImageBean.class);

		criteria.add(Restrictions.eq("im_CategoryBean", im_CategoryBean));
		criteria.add(Restrictions.eq("im_status", 1));
		criteria.addOrder(Order.asc("im_rank"));

		return (List<ImageBean>) hibernateTemplate.findByCriteria(criteria);
	}

}
