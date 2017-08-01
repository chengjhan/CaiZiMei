/*
 * CaiZiMei
 * File: CityDaoImpl.java
 * Author: 詹晟
 * Date: 2017/8/1
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.czmbeauty.model.dao.CityDao;
import com.czmbeauty.model.entity.CityBean;

/**
 * city DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "cityDao")
public class CityDaoImpl implements CityDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 城市流水號搜尋
	 * 
	 * @param ci_id
	 *            Integer --> 城市流水號
	 * @return CityBean
	 */
	@Override
	public CityBean selectByCi_id(Integer ci_id) {

		return hibernateTemplate.get(CityBean.class, ci_id);
	}

	/**
	 * 區域流水號搜尋
	 * 
	 * @param ci_st_id
	 *            Integer --> 區域流水號
	 * @return List<CityBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<CityBean> selectByCi_st_id(Integer ci_st_id) {

		return (List<CityBean>) hibernateTemplate.findByNamedParam("from CityBean where ci_st_id=:ci_st_id", "ci_st_id",
				ci_st_id);
	}

	/**
	 * 新增城市
	 * 
	 * @param cityBean
	 *            CityBean
	 * @return CityBean
	 */
	@Override
	public CityBean insert(CityBean cityBean) {

		hibernateTemplate.save(cityBean);

		return cityBean;
	}

	/**
	 * 修改資料
	 * 
	 * @param cityBean
	 *            CityBean
	 * @return CityBean
	 */
	@Override
	public CityBean update(CityBean cityBean) {

		hibernateTemplate.clear();
		hibernateTemplate.update(cityBean);

		return cityBean;
	}

	/**
	 * 刪除城市
	 * 
	 * @param ci_id
	 *            Integer --> 城市流水號
	 * @return true
	 */
	@Override
	public Boolean delete(Integer ci_id) {

		hibernateTemplate.delete(hibernateTemplate.get(CityBean.class, ci_id));

		return true;
	}

}
