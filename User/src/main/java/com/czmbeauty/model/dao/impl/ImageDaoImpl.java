/*
 * CaiZiMei/User
 * File: ImageDaoImpl.java
 * Author: 詹晟
 * Date: 2017/9/8
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao.impl;

import static com.czmbeauty.common.constants.HqlConstants.HQL_SELECT_OPEN_IMAGE_BY_CATEGORY;

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
	 * 類別流水號搜尋開啟的圖片
	 * 
	 * @param im_ca_id
	 *            String --> 類別流水號
	 * @return List<ImageBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ImageBean> selectOpenImage(String im_ca_id) {

		return (List<ImageBean>) hibernateTemplate.findByNamedParam(HQL_SELECT_OPEN_IMAGE_BY_CATEGORY, "im_ca_id",
				im_ca_id);
	}

}
