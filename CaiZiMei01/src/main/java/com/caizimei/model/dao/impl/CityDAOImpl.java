package com.caizimei.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.caizimei.model.dao.CityDAO;
import com.caizimei.model.entity.CityBean;

@Repository(value = "cityDAO")
public class CityDAOImpl implements CityDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	@SuppressWarnings("unchecked")
	public List<CityBean> select() {
		return (List<CityBean>) hibernateTemplate.find("from CityBean");
	}

	@Override
	public CityBean selectByCi_id(Integer ci_id) {
		return hibernateTemplate.get(CityBean.class, ci_id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<CityBean> selectByCi_name(String ci_name) {
		return (List<CityBean>) hibernateTemplate.find("from CityBean where ci_name=?", ci_name);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<CityBean> selectByCi_co_id(Integer ci_co_id) {
		return (List<CityBean>) hibernateTemplate.find("from CityBean where ci_co_id=?", ci_co_id);
	}

	@Override
	public CityBean insert(CityBean cityBean) {
		hibernateTemplate.save(cityBean);
		return cityBean;
	}

	@Override
	public CityBean update(CityBean cityBean) {
		hibernateTemplate.clear();
		hibernateTemplate.update(cityBean);
		return cityBean;
	}

	@Override
	public Boolean delete(Integer ci_id) {
		hibernateTemplate.delete(hibernateTemplate.get(CityBean.class, ci_id));
		return true;
	}

}
