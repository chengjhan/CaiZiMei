/*
 * CaiZiMei
 * File: RegionDAOImpl.java
 * Author: 詹晟
 * Date: 2017/3/30
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.caizimei.model.dao.RegionDAO;
import com.caizimei.model.entity.RegionBean;

/**
 * region DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "regionDAO")
public class RegionDAOImpl implements RegionDAO {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 區域流水號搜尋
	 * 
	 * @param r_id-->區域流水號
	 * @return RegionBean
	 */
	@Override
	public RegionBean selectByR_id(Integer r_id) {

		return hibernateTemplate.get(RegionBean.class, r_id);
	}

	/**
	 * 城市流水號搜尋
	 * 
	 * @param r_ci_id-->城市流水號
	 * @return List<RegionBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<RegionBean> selectByR_ci_id(Integer r_ci_id) {

		return (List<RegionBean>) hibernateTemplate.findByNamedParam("from RegionBean where r_ci_id=:r_ci_id",
				"r_ci_id", r_ci_id);
	}

	/**
	 * 區域名搜尋
	 * 
	 * @param r_name-->區域名
	 * @return List<RegionBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<RegionBean> selectByR_name(String r_name) {

		return (List<RegionBean>) hibernateTemplate.findByNamedParam("from RegionBean where r_name=:r_name", "r_name",
				r_name);
	}

	/**
	 * 郵遞區號搜尋
	 * 
	 * @param r_zipcode-->郵遞區號
	 * @return List<RegionBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<RegionBean> selectByR_zipcode(String r_zipcode) {

		return (List<RegionBean>) hibernateTemplate.findByNamedParam("from RegionBean where r_zipcode=:r_zipcode",
				"r_zipcode", r_zipcode);
	}

	/**
	 * 新增區域
	 * 
	 * @param regionBean-->RegionBean
	 * @return regionBean-->RegionBean
	 */
	@Override
	public RegionBean insert(RegionBean regionBean) {

		hibernateTemplate.save(regionBean);

		return regionBean;
	}

	/**
	 * 修改區域資料
	 * 
	 * @param regionBean-->RegionBean
	 * @return regionBean-->RegionBean
	 */
	@Override
	public RegionBean update(RegionBean regionBean) {

		hibernateTemplate.clear();
		hibernateTemplate.update(regionBean);

		return regionBean;
	}

	/**
	 * 刪除區域
	 * 
	 * @param r_id-->區域流水號
	 * @return true-->成功
	 */
	@Override
	public Boolean delete(Integer r_id) {

		hibernateTemplate.delete(hibernateTemplate.get(RegionBean.class, r_id));

		return true;
	}

}
