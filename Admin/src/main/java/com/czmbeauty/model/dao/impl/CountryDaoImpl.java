/*
 * CaiZiMei
 * File: CountryDaoImpl.java
 * Author: 詹晟
 * Date: 2017/8/13
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao.impl;

import static com.czmbeauty.common.constants.HqlConstants.HQL_SELECT_ALL_COUNTRY;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.czmbeauty.model.dao.CountryDao;
import com.czmbeauty.model.entity.CountryBean;

/**
 * country DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "countryDao")
public class CountryDaoImpl implements CountryDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 搜尋所有國家
	 * 
	 * @return List<CountryBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<CountryBean> selectAll() {

		return (List<CountryBean>) hibernateTemplate.find(HQL_SELECT_ALL_COUNTRY);
	}

	/**
	 * 國家流水號搜尋
	 * 
	 * @param co_id
	 *            Integer --> 國家流水號
	 * @return CountryBean
	 */
	@Override
	public CountryBean selectByCo_id(Integer co_id) {

		return hibernateTemplate.get(CountryBean.class, co_id);
	}

	/**
	 * 新增國家
	 * 
	 * @param countryBean
	 *            CountryBean
	 * @return CountryBean
	 */
	@Override
	public CountryBean insert(CountryBean countryBean) {

		hibernateTemplate.save(countryBean);

		return countryBean;
	}

	/**
	 * 修改資料
	 * 
	 * @param newCountryBean
	 *            CountryBean
	 * @return CountryBean
	 */
	@Override
	public CountryBean update(CountryBean newCountryBean) {

		CountryBean countryBean = hibernateTemplate.get(CountryBean.class, newCountryBean.getCo_id());

		countryBean.setCo_iso(newCountryBean.getCo_iso());
		countryBean.setCo_name(newCountryBean.getCo_name());
		countryBean.setCo_phonecode(newCountryBean.getCo_phonecode());
		countryBean.setCo_rank(newCountryBean.getCo_rank());

		return countryBean;
	}

}
