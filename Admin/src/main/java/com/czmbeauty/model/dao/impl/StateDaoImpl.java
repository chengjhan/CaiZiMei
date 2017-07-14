/*
 * CaiZiMei
 * File: StateDaoImpl.java
 * Author: 詹晟
 * Date: 2017/7/14
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
 * state dao implement
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
	 * 搜尋全部區域
	 * 
	 * @return List<StateBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<StateBean> selectAll() {

		return (List<StateBean>) hibernateTemplate.find("from StateBean order by s_rank asc");
	}

	/**
	 * 區域流水號搜尋
	 * 
	 * @param s_id-->區域流水號
	 * @return StateBean
	 */
	@Override
	public StateBean selectByS_id(Integer s_id) {

		return hibernateTemplate.get(StateBean.class, s_id);
	}

	/**
	 * 國家流水號搜尋
	 * 
	 * @param s_co_id-->國家流水號
	 * @return List<CityBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<StateBean> selectByS_co_id(Integer s_co_id) {

		return (List<StateBean>) hibernateTemplate.findByNamedParam("from StateBean where s_co_id=:s_co_id", "s_co_id",
				s_co_id);
	}

	/**
	 * 區域名搜尋
	 * 
	 * @param s_name-->區域名
	 * @return List<StateBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<StateBean> selectByS_name(String s_name) {

		return (List<StateBean>) hibernateTemplate.findByNamedParam("from StateBean where s_name=:s_name", "s_name",
				s_name);
	}

	/**
	 * 新增區域
	 * 
	 * @param stateBean-->StateBean
	 * @return stateBean-->StateBean
	 */
	@Override
	public StateBean insert(StateBean stateBean) {

		hibernateTemplate.save(stateBean);

		return stateBean;
	}

	/**
	 * 修改資料
	 * 
	 * @param stateBean-->StateBean
	 * @return stateBean-->StateBean
	 */
	@Override
	public StateBean update(StateBean stateBean) {

		hibernateTemplate.clear();
		hibernateTemplate.update(stateBean);

		return stateBean;
	}

	/**
	 * 刪除區域
	 * 
	 * @param s_id-->區域流水號
	 * @return true-->成功
	 */
	@Override
	public Boolean delete(Integer s_id) {

		hibernateTemplate.delete(hibernateTemplate.get(StateBean.class, s_id));

		return true;
	}

}
