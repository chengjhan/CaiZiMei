package com.caizimei.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.caizimei.model.CountryBean;
import com.caizimei.model.dao.CountryDAO;

@Repository(value = "countryDAO")
public class CountryDAOImpl implements CountryDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	@SuppressWarnings("unchecked")
	public List<CountryBean> select() {
		return (List<CountryBean>) hibernateTemplate.find("from CountryBean");
	}

	@Override
	public CountryBean selectByCo_id(Integer co_id) {
		return hibernateTemplate.get(CountryBean.class, co_id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<CountryBean> selectByCo_name(String co_name) {
		return (List<CountryBean>) hibernateTemplate.find("from CountryBean where co_name=?", co_name);
	}

	@Override
	public CountryBean insert(CountryBean countryBean) {
		hibernateTemplate.save(countryBean);
		return countryBean;
	}

	@Override
	public CountryBean update(CountryBean countryBean) {
		hibernateTemplate.clear();
		hibernateTemplate.update(countryBean);
		return countryBean;
	}

	@Override
	public Boolean delete(Integer co_id) {
		hibernateTemplate.delete(hibernateTemplate.get(CountryBean.class, co_id));
		return true;
	}

}
