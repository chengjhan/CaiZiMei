/*
 * CaiZiMei
 * File: CityDAOImpl.java
 * Author: 詹晟
 * Date: 2017/4/9
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.caizimei.model.dao.CityDAO;
import com.caizimei.model.entity.CityBean;

/**
 * city DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "cityDAO")
public class CityDAOImpl implements CityDAO {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 搜尋全部城市
	 * 
	 * @return List<CityBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<CityBean> select() {

		return (List<CityBean>) hibernateTemplate.find("from CityBean order by ci_rank asc");
	}

	/**
	 * 城市流水號搜尋
	 * 
	 * @param ci_id-->城市流水號
	 * @return CityBean
	 */
	@Override
	public CityBean selectByCi_id(Integer ci_id) {

		return hibernateTemplate.get(CityBean.class, ci_id);
	}

	/**
	 * 國家流水號搜尋
	 * 
	 * @param ci_co_id-->國家流水號
	 * @return List<CityBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<CityBean> selectByCi_co_id(Integer ci_co_id) {

		return (List<CityBean>) hibernateTemplate.findByNamedParam("from CityBean where ci_co_id=:ci_co_id", "ci_co_id",
				ci_co_id);
	}

	/**
	 * 城市名搜尋
	 * 
	 * @param ci_name-->城市名
	 * @return List<CityBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<CityBean> selectByCi_name(String ci_name) {

		return (List<CityBean>) hibernateTemplate.findByNamedParam("from CityBean where ci_name=:ci_name", "ci_name",
				ci_name);
	}

	/**
	 * 新增城市
	 * 
	 * @param cityBean-->CityBean
	 * @return cityBean-->CityBean
	 */
	@Override
	public CityBean insert(CityBean cityBean) {

		hibernateTemplate.save(cityBean);

		return cityBean;
	}

	/**
	 * 修改城市資料
	 * 
	 * @param cityBean-->CityBean
	 * @return cityBean-->CityBean
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
	 * @param ci_id-->城市流水號
	 * @return true-->成功
	 */
	@Override
	public Boolean delete(Integer ci_id) {

		hibernateTemplate.delete(hibernateTemplate.get(CityBean.class, ci_id));

		return true;
	}

}
