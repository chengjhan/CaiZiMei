/*
 * CaiZiMei
 * File: StateDaoImpl.java
 * Author: 詹晟
 * Date: 2017/8/12
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.czmbeauty.model.dao.StateDao;
import com.czmbeauty.model.entity.StateBean;

/**
 * state DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "stateDao")
public class StateDaoImpl implements StateDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 區域流水號搜尋
	 * 
	 * @param st_id
	 *            Integer --> 區域流水號
	 * @return StateBean
	 */
	@Override
	public StateBean selectBySt_id(Integer st_id) {

		return hibernateTemplate.get(StateBean.class, st_id);
	}

	/**
	 * 國家流水號搜尋
	 * 
	 * @param st_co_id
	 *            Integer --> 國家流水號
	 * @return List<CityBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<StateBean> selectBySt_co_id(Integer st_co_id) {

		return (List<StateBean>) hibernateTemplate.findByNamedParam("from StateBean where st_co_id=:st_co_id",
				"st_co_id", st_co_id);
	}

	/**
	 * 新增區域
	 * 
	 * @param stateBean
	 *            StateBean
	 * @return StateBean
	 */
	@Override
	public StateBean insert(StateBean stateBean) {

		hibernateTemplate.save(stateBean);

		return stateBean;
	}

	/**
	 * 修改資料
	 * 
	 * @param newStateBean
	 *            StateBean
	 * @return StateBean
	 */
	@Override
	public StateBean update(StateBean newStateBean) {

		StateBean stateBean = hibernateTemplate.get(StateBean.class, newStateBean.getSt_id());

		stateBean.setSt_CountryBean(newStateBean.getSt_CountryBean());
		stateBean.setSt_name(newStateBean.getSt_name());
		stateBean.setSt_rank(newStateBean.getSt_rank());

		return stateBean;
	}

}
