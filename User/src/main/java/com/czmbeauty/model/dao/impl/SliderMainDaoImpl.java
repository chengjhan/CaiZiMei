/*
 * CaiZiMei/User
 * File: SliderMainDaoImpl.java
 * Author: 詹晟
 * Date: 2017/7/27
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.czmbeauty.model.dao.SliderMainDao;
import com.czmbeauty.model.entity.SliderMainBean;

/**
 * slider_main DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "sliderMainDao")
public class SliderMainDaoImpl implements SliderMainDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 狀態搜尋
	 * 
	 * @return List<SliderMainBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<SliderMainBean> selectBySm_status() {

		return (List<SliderMainBean>) hibernateTemplate
				.find("from SliderMainBean where sm_status=1 order by sm_rank asc");
	}

}
