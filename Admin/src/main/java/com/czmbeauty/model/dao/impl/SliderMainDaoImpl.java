/*
 * CaiZiMei
 * File: SliderMainDaoImpl.java
 * Author: 詹晟
 * Date: 2017/7/25
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
	 * 搜尋所有圖片
	 * 
	 * @return List<SliderMainBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<SliderMainBean> selectAll() {

		return (List<SliderMainBean>) hibernateTemplate.find("from SliderMainBean order by sm_id asc");
	}

	/**
	 * 圖片流水號搜尋
	 * 
	 * @param sm_id-->圖片流水號
	 * @return List<SliderMainBean>
	 */
	@Override
	public SliderMainBean selectBySm_id(Integer sm_id) {

		return hibernateTemplate.get(SliderMainBean.class, sm_id);
	}

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

	/**
	 * 新增圖片
	 * 
	 * @param sliderMainBean-->SliderMainBean
	 * @return sliderMainBean-->SliderMainBean
	 */
	@Override
	public SliderMainBean insert(SliderMainBean sliderMainBean) {

		hibernateTemplate.save(sliderMainBean);

		return sliderMainBean;
	}

	/**
	 * 修改資料
	 * 
	 * @param sliderMainBean-->SliderMainBean
	 * @return sliderMainBean-->SliderMainBean
	 */
	@Override
	public SliderMainBean update(SliderMainBean sliderMainBean) {

		hibernateTemplate.clear();
		hibernateTemplate.update(sliderMainBean);

		return sliderMainBean;
	}

}
