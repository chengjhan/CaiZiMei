/*
 * CaiZiMei
 * File: CountryDAOImpl.java
 * Author: 詹晟
 * Date: 2017/3/27
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.caizimei.model.dao.CountryDAO;
import com.caizimei.model.entity.CountryBean;

/**
 * country DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "countryDAO")
public class CountryDAOImpl implements CountryDAO {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 搜尋全部國家
	 * 
	 * @return List<CountryBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<CountryBean> select() {

		return (List<CountryBean>) hibernateTemplate.find("from CountryBean");
	}

	/**
	 * 國家流水號搜尋
	 * 
	 * @param co_id-->國家流水號
	 * @return CountryBean
	 */
	@Override
	public CountryBean selectByCo_id(Integer co_id) {

		return hibernateTemplate.get(CountryBean.class, co_id);
	}

	/**
	 * 國家名搜尋
	 * 
	 * @param co_name-->國家名
	 * @return List<CountryBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<CountryBean> selectByCo_name(String co_name) {

		return (List<CountryBean>) hibernateTemplate.findByNamedParam("from CountryBean where co_name=:co_name",
				"co_name", co_name);
	}

	/**
	 * 新增國家
	 * 
	 * @param countryBean-->CountryBean
	 * @return countryBean-->CountryBean
	 */
	@Override
	public CountryBean insert(CountryBean countryBean) {

		hibernateTemplate.save(countryBean);

		return countryBean;
	}

	/**
	 * 修改國家資料
	 * 
	 * @param countryBean-->CountryBean
	 * @return countryBean-->CountryBean
	 */
	@Override
	public CountryBean update(CountryBean countryBean) {

		hibernateTemplate.clear();
		hibernateTemplate.update(countryBean);

		return countryBean;
	}

	/**
	 * 刪除國家
	 * 
	 * @param co_id-->國家流水號
	 * @return true-->成功
	 */
	@Override
	public Boolean delete(Integer co_id) {

		hibernateTemplate.delete(hibernateTemplate.get(CountryBean.class, co_id));

		return true;
	}

}
